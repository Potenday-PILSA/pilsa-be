package potenday.pilsa.pilsa.service;

import lombok.RequiredArgsConstructor;
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
import potenday.pilsa.pilsa.dto.response.ResponsePilsaMainListDto;
import potenday.pilsa.pilsaCategory.domain.PilsaCategory;
import potenday.pilsa.pilsaCategory.domain.repository.PilsaCategoryRepository;
import potenday.pilsa.relationPilsaCategory.domain.RelationPilsaCategory;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PilsaService {

    private final PilsaRepository pilsaRepository;
    private final MemberRepository memberRepository;
    private final PilsaCategoryRepository pilsaCategoryRepository;

    @Transactional(readOnly = true)
    public ResponsePilsaMainListDto getAllPilsalList(RequestPageDto request) {

        Page<Pilsa> pilsas = pilsaRepository.findByPrivateTypeOrderByRegistDateDesc(YN.N, request.pageable());
        List<Pilsa> pilsaList = pilsaRepository.findAll();

        return ResponsePilsaMainListDto.from(pilsas, pilsaList);
    }

    @Transactional(readOnly = true)
    public ResponsePilsaMainListDto getPilsalListOfMember(Long memberId, RequestPageDto request) {

        Member member = getMember(memberId);

        Page<Pilsa> pilsas = pilsaRepository.findByMember_IdOrderByRegistDateDesc(member.getId(), request.pageable());
        List<Pilsa> pilsaList = pilsaRepository.findAll();

        return ResponsePilsaMainListDto.from(pilsas, pilsaList);
    }


    public Pilsa createPilsa(Long memberId, RequestPilsaInfoDto request) {

        Member member = getMember(memberId);
        List<PilsaCategory> categoryList = pilsaCategoryRepository.findByCategoryCdIn(request.getCategoryCd());

        Pilsa pilsa = Pilsa.builder()
                        .title(request.getTitle())
                        .member(member)
                        .author(request.getAuthor())
                        .publisher(request.getPublisher())
                        .privateType(request.getPrivateType())
                        .followPilsaId(request.getFollowPilsaId())
                        .textContents(request.getTextContents())
                        .backgroundColor(request.getBackgroundColor())
                        .backgroundImageUrl(request.getBackgroundImageUrl())
                        .images(null)
                        .build();

        List<RelationPilsaCategory> relationPilsaCategories = categoryList.stream()
                .map(category -> new RelationPilsaCategory(pilsa, category)) // 생성자를 통해 RelationPilsaCategory 인스턴스 생성
                .collect(Collectors.toList());

        // Pilsa에 RelationPilsaCategory 설정
        pilsa.setRelationPilsaCategories(relationPilsaCategories);

        return pilsaRepository.save(pilsa);
    }


    private Member getMember(Long memberId) {
        return memberRepository.findByIdAndStatus(memberId, Status.ACTIVE).orElseThrow(
                () -> new BadRequestException(ExceptionCode.NOT_FOUND_MEMBER)
        );
    }
}
