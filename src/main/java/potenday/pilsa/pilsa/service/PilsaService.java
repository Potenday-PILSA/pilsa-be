package potenday.pilsa.pilsa.service;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import potenday.pilsa.global.dto.request.RequestPageDto;
import potenday.pilsa.global.exception.BadRequestException;
import potenday.pilsa.global.exception.ExceptionCode;
import potenday.pilsa.global.util.LocalDateUtil;
import potenday.pilsa.like.domain.repository.LikeRepository;
import potenday.pilsa.member.domain.Member;
import potenday.pilsa.member.domain.Status;
import potenday.pilsa.member.domain.repository.MemberRepository;
import potenday.pilsa.pilsa.domain.Pilsa;
import potenday.pilsa.pilsa.domain.YN;
import potenday.pilsa.pilsa.domain.repository.PilsaRepository;
import potenday.pilsa.pilsa.dto.request.RequestCalenderPilsa;
import potenday.pilsa.pilsa.dto.request.RequestGetPilsa;
import potenday.pilsa.pilsa.dto.request.RequestPilsaInfoDto;
import potenday.pilsa.pilsa.dto.request.RequestPilsaList;
import potenday.pilsa.pilsa.dto.response.ResponseCalenderPilsa;
import potenday.pilsa.pilsa.dto.response.ResponsePilsaDetailDto;
import potenday.pilsa.pilsa.dto.response.ResponsePilsaIncludeDetailDto;
import potenday.pilsa.pilsa.dto.response.ResponsePilsaListDto;
import potenday.pilsa.pilsaCategory.domain.PilsaCategory;
import potenday.pilsa.pilsaCategory.domain.repository.PilsaCategoryRepository;
import potenday.pilsa.pilsaImage.domain.PilsaImage;
import potenday.pilsa.pilsaImage.domain.repository.PilsaImageRepository;
import potenday.pilsa.pilsaImage.dto.request.ImageRequest;
import potenday.pilsa.relationPilsaCategory.domain.RelationPilsaCategory;
import potenday.pilsa.relationPilsaCategory.domain.repository.RelationPilsaCategoryRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@ToString
@Transactional
@jakarta.transaction.Transactional
public class PilsaService {

    private final PilsaRepository pilsaRepository;
    private final MemberRepository memberRepository;
    private final PilsaCategoryRepository pilsaCategoryRepository;
    private final PilsaImageRepository pilsaImageRepository;
    private final RelationPilsaCategoryRepository relationPilsaCategoryRepository;
    private final LikeRepository likeRepository;

    @Transactional(readOnly = true)
    public ResponsePilsaListDto getAllPilsalList(RequestPilsaList request, Long memberId) {
        Page<Pilsa> pilsas = (request.getPilsaListType() == null) ?
                pilsaRepository.findByPrivateTypeAndDeleteDateIsNullOrderByRegistDateDesc(YN.N, request.toPageable()) :
                switch (request.getPilsaListType()) {
                    case FASTEST -> pilsaRepository.findByPrivateTypeAndDeleteDateIsNullOrderByRegistDateDesc(YN.N, request.toPageable());
                    case POPULAR -> pilsaRepository.findPilsaListSortedByLikes(request.toPageable());
                };

        return ResponsePilsaListDto.from(getPilsaDetailResponseDto(pilsas.getContent(), memberId), pilsas.getTotalElements());
    }

    @Transactional(readOnly = true)
    public ResponsePilsaListDto getPilsalListOfMember(Long memberId, RequestPageDto request) {
        Member member = getMember(memberId);

        Page<Pilsa> pilsas = pilsaRepository.findByMember_IdAndDeleteDateIsNullOrderByRegistDateDesc(member.getId(), request.toPageable());

        return ResponsePilsaListDto.from(getPilsaDetailResponseDto(pilsas.getContent(), memberId), pilsas.getTotalElements());
    }

    public ResponsePilsaIncludeDetailDto getPilsaDetail(Long pilsaId, Long memberId, RequestGetPilsa requestGetPilsa) {
        Optional<Pilsa> nextPilsa;
        Optional<Pilsa> previousPilsa;

        if (requestGetPilsa.getGetMyPilsa()) {
            if (memberId == null) {
                throw new BadRequestException(ExceptionCode.NOT_FOUND_MEMBER);
            }

            nextPilsa = pilsaRepository.getNextAndPreviousPilsa(pilsaId, memberId, true);
            previousPilsa = pilsaRepository.getNextAndPreviousPilsa(pilsaId, memberId, false);
        } else {
            nextPilsa = pilsaRepository.getNextAndPreviousPilsa(pilsaId, null, true);
            previousPilsa = pilsaRepository.getNextAndPreviousPilsa(pilsaId, null, false);
        }

        return ResponsePilsaIncludeDetailDto.from(getPilsa(pilsaId), nextPilsa, previousPilsa, isLikeAblePilsa(memberId, pilsaId));
    }

    @Transactional
    public void deletePilsa(Long pilsaId, Long memberId) {
        Pilsa pilsa = pilsaRepository.findByMember_IdAndDeleteDateIsNullAndPilsaId(memberId, pilsaId).orElseThrow(
                () -> new BadRequestException(ExceptionCode.NOT_FOUND_PILSA));

        pilsa.deletePilsa();
    }

    private Pilsa getPilsa(Long pilsaId) {
        return pilsaRepository.findByPilsaIdAndDeleteDateIsNull(pilsaId).orElseThrow(
                () -> new BadRequestException(ExceptionCode.NOT_FOUND_PILSA)
        );
    }

    @Transactional
    public Long createPilsa(Long memberId, RequestPilsaInfoDto request) {
        Member member = getMember(memberId);

        Pilsa pilsa = new Pilsa(
                request.getTitle(),
                member,
                request.getAuthor(),
                request.getPublisher(),
                request.getTextContents(),
                request.getBackgroundImageUrl(),
                request.getBackgroundColor(),
                request);

        return pilsaSave(request, pilsa).getPilsaId();
    }

    public Pilsa pilsaSave(RequestPilsaInfoDto request, Pilsa pilsa) {
        validationCategoryCount(request.getCategoryCd());
        validationImageCount(request.getImages());

        List<PilsaCategory> categoryList = pilsaCategoryRepository.findByCategoryCdInAndUseYn(request.getCategoryCd(), potenday.pilsa.pilsaCategory.domain.YN.Y);
        List<RelationPilsaCategory> relationPilsaCategories = categoryList.stream()
                .map(category -> new RelationPilsaCategory(pilsa, category)) // 생성자를 통해 RelationPilsaCategory 인스턴스 생성
                .collect(Collectors.toList());

        // Pilsa에 RelationPilsaCategory 설정
        pilsa.setRelationPilsaCategories(relationPilsaCategories);
        pilsa.setPilsaImages(getPilsaImages(request.getImages(), pilsa));

        return pilsaRepository.save(pilsa);
    }

    @Transactional
    public ResponsePilsaDetailDto updatePilsa(Long memberId, RequestPilsaInfoDto requestPilsaInfoDto, Long pilsaId) {
        Pilsa pilsa = pilsaRepository.findByMember_IdAndDeleteDateIsNullAndPilsaId(memberId, pilsaId).orElseThrow(
                () -> new BadRequestException(ExceptionCode.NOT_FOUND_PILSA));

        pilsa.updatePilsa(requestPilsaInfoDto);

        pilsaImageRepository.deleteByPilsa_PilsaId(pilsaId);
        relationPilsaCategoryRepository.deleteByPilsa_PilsaId(pilsaId);

        return ResponsePilsaDetailDto.from(pilsaSave(requestPilsaInfoDto, pilsa), isLikeAblePilsa(memberId, pilsaId));
    }

    public List<ResponseCalenderPilsa> getCalenderPilsaList(RequestCalenderPilsa request, Long memberId) {
        List<Pilsa> pilsas
                = pilsaRepository.findByMember_IdAndRegistDateBetweenAndDeleteDateIsNullOrderByRegistDateAsc(memberId, LocalDateUtil.startLocalDateToTime(request.getStartDt()), LocalDateUtil.endLocalDateToTime(request.getEndDt()));

        return ResponseCalenderPilsa.from(pilsas);
    }

    private List<ResponsePilsaDetailDto> getPilsaDetailResponseDto(List<Pilsa> pilsas, Long memberId) {
        return pilsas.stream().map(
                pilsa -> ResponsePilsaDetailDto.from(
                        pilsa, isLikeAblePilsa(memberId, pilsa.getPilsaId())))
                .collect(Collectors.toList());
    }

    private void validationCategoryCount(List<Long> categoryCd) {
        if (!categoryCd.isEmpty() && categoryCd.size() > 3) {
            throw new BadRequestException(ExceptionCode.MAX_CATEGORY_SIZE);
        }
    }

    private void validationImageCount(List<ImageRequest> images) {
        if (images != null && images.size() >= 2) {
            throw new BadRequestException(ExceptionCode.IMAGE_COUNT_MUST_ONE);

        }
    }

    private List<PilsaImage> getPilsaImages(List<ImageRequest> imageRequests, Pilsa pilsa) {
        return imageRequests.stream()
                .map(image -> new PilsaImage(image.getImageUrl(), image.getThumbnail(), image.getImageSeq(), pilsa))
                .collect(Collectors.toList());
    }

    private Member getMember(Long memberId) {
        return memberRepository.findByIdAndStatus(memberId, Status.ACTIVE).orElseThrow(
                () -> new BadRequestException(ExceptionCode.NOT_FOUND_MEMBER)
        );
    }

    private Boolean isLikeAblePilsa(Long memberId, Long pilsaId) {
        return likeRepository.existsByMember_IdAndPilsa_PilsaId(memberId, pilsaId);
    }
}
