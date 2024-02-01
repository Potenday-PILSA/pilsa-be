package potenday.pilsa.pilsa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import potenday.pilsa.global.exception.BadRequestException;
import potenday.pilsa.global.exception.ExceptionCode;
import potenday.pilsa.member.domain.Member;
import potenday.pilsa.member.domain.Status;
import potenday.pilsa.member.domain.repository.MemberRepository;
import potenday.pilsa.pilsa.domain.Pilsa;
import potenday.pilsa.pilsa.domain.repository.PilsaRepository;
import potenday.pilsa.pilsa.dto.request.RequestPilsaInfoDto;

@Service
@RequiredArgsConstructor
public class PilsaService {

    private final PilsaRepository pilsaRepository;
    private final MemberRepository memberRepository;

    public Pilsa createPilsa(Long memberId, RequestPilsaInfoDto request) {

        Member member = getMember(memberId);

        Pilsa pilsa = pilsaRepository.save(
                Pilsa.builder()
                        .title(request.getTitle())
                        .author(request.getAuthor())
                        .publisher(request.getPublisher())
                        .privateType(request.getPrivateType())
                        .followPilsaId(request.getFollowPilsaId())
                        .build()

        );

        return pilsa;
    }

    private Member getMember(Long memberId) {
        return memberRepository.findByIdAndStatus(memberId, Status.ACTIVE).orElseThrow(
                () -> new BadRequestException(ExceptionCode.NOT_FOUND_MEMBER)
        );
    }
}
