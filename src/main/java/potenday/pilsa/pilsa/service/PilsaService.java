package potenday.pilsa.pilsa.service;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import potenday.pilsa.global.dto.request.RequestPageDto;
import potenday.pilsa.global.exception.BadRequestException;
import potenday.pilsa.global.exception.ExceptionCode;
import potenday.pilsa.member.domain.Member;
import potenday.pilsa.member.domain.Status;
import potenday.pilsa.member.domain.repository.MemberRepository;
import potenday.pilsa.pilsa.domain.Pilsa;
import potenday.pilsa.pilsa.domain.YN;
import potenday.pilsa.pilsa.domain.repository.PilsaRepository;
import potenday.pilsa.pilsa.dto.request.RequestPilsaInfoDto;
import potenday.pilsa.pilsa.dto.response.ResponsePilsaDetailDto;
import potenday.pilsa.pilsa.dto.response.ResponsePilsaMainListDto;
import potenday.pilsa.pilsaCategory.domain.PilsaCategory;
import potenday.pilsa.pilsaCategory.domain.repository.PilsaCategoryRepository;
import potenday.pilsa.pilsaImage.domain.PilsaImage;
import potenday.pilsa.pilsaImage.dto.request.ImageRequest;
import potenday.pilsa.relationPilsaCategory.domain.RelationPilsaCategory;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@ToString
public class PilsaService {

    private final PilsaRepository pilsaRepository;
    private final MemberRepository memberRepository;
    private final PilsaCategoryRepository pilsaCategoryRepository;

    @Transactional(readOnly = true)
    public ResponsePilsaMainListDto getAllPilsalList(RequestPageDto request) {
        Page<Pilsa> pilsas = pilsaRepository.findByPrivateTypeOrderByRegistDateDesc(YN.N, request.toPageable());

        return ResponsePilsaMainListDto.from(pilsas);
    }

    @Transactional(readOnly = true)
    public ResponsePilsaMainListDto getPilsalListOfMember(Long memberId, RequestPageDto request) {
        Member member = getMember(memberId);

        Page<Pilsa> pilsas = pilsaRepository.findByMember_IdOrderByRegistDateDesc(member.getId(), request.toPageable());

        return ResponsePilsaMainListDto.from(pilsas);
    }


    public ResponsePilsaDetailDto createPilsa(Long memberId, RequestPilsaInfoDto request) {
        validationCategoryCount(request.getCategoryCd());

        Member member = getMember(memberId);
        List<PilsaCategory> categoryList = pilsaCategoryRepository.findByCategoryCdIn(request.getCategoryCd());

        Pilsa pilsa = new Pilsa(
                request.getTitle(),
                member,
                request.getAuthor(),
                request.getPublisher(),
                request.getTextContents(),
                request.getBackgroundImageUrl(),
                request.getBackgroundColor(),
                getPilsaImages(request.getImages()));

        List<RelationPilsaCategory> relationPilsaCategories = categoryList.stream()
                .map(category -> new RelationPilsaCategory(pilsa, category)) // 생성자를 통해 RelationPilsaCategory 인스턴스 생성
                .collect(Collectors.toList());

        // Pilsa에 RelationPilsaCategory 설정
        pilsa.setRelationPilsaCategories(relationPilsaCategories);
        pilsaRepository.save(pilsa);

        return ResponsePilsaDetailDto.from(pilsa);
    }

    private void validationCategoryCount(List<Long> categoryCd) {
        if (!categoryCd.isEmpty() && categoryCd.size() > 3) {
            throw new BadRequestException(ExceptionCode.MAX_CATEGORY_SIZE);
        }
    }

    private List<PilsaImage> getPilsaImages(List<ImageRequest> imageRequests) {
        return imageRequests.stream()
                .map(image -> new PilsaImage(image.getImageUrl(), image.getThumbnail(), image.getImageSeq()))
                .collect(Collectors.toList());
    }

    private Member getMember(Long memberId) {
        return memberRepository.findByIdAndStatus(memberId, Status.ACTIVE).orElseThrow(
                () -> new BadRequestException(ExceptionCode.NOT_FOUND_MEMBER)
        );
    }
}
