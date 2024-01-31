package potenday.pilsa.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import potenday.pilsa.global.exception.BadRequestException;
import potenday.pilsa.global.exception.ExceptionCode;
import potenday.pilsa.member.domain.Member;
import potenday.pilsa.member.domain.Status;
import potenday.pilsa.member.domain.repository.MemberRepository;
import potenday.pilsa.member.dto.request.MemberUpdateRequest;
import potenday.pilsa.member.dto.response.MemberInfoResponse;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberInfoResponse getMemberInfo(Long memberId) {
        Member member = getMember(memberId);

        return MemberInfoResponse.from(member);
    }

    @Transactional
    public MemberInfoResponse updateMember(Long memberId, MemberUpdateRequest request) {
        Member member = getMember(memberId);
        member.updateDescription(request.getDescription());

        return MemberInfoResponse.from(member);
    }

    private Member getMember(Long memberId) {
        return memberRepository.findByIdAndStatus(memberId, Status.ACTIVE).orElseThrow(
                () -> new BadRequestException(ExceptionCode.NOT_FOUND_MEMBER)
        );
    }
}
