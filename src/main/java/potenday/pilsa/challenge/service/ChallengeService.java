package potenday.pilsa.challenge.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import potenday.pilsa.challenge.domain.Challenge;
import potenday.pilsa.challenge.domain.repository.ChallengeRepository;
import potenday.pilsa.challenge.dto.request.RequestCreateChallenge;
import potenday.pilsa.challenge.dto.response.ResponseChallengeInfo;
import potenday.pilsa.global.exception.BadRequestException;
import potenday.pilsa.global.exception.ExceptionCode;
import potenday.pilsa.global.util.LocalDateUtil;
import potenday.pilsa.member.domain.Member;
import potenday.pilsa.member.domain.Status;
import potenday.pilsa.member.domain.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class ChallengeService {

    private final ChallengeRepository challengeRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public ResponseChallengeInfo createChallenge(Long memberId, RequestCreateChallenge request) {
        Challenge challenge = new Challenge(
                getMember(memberId),
                LocalDateUtil.startLocalDateToTime(request.getStartDate()),
                LocalDateUtil.endLocalDateToTime(request.getEndDate()),
                request.getTitle(),
                request.getDescription());

        challengeRepository.save(challenge);

        return ResponseChallengeInfo.from(challenge);
    }

    private Member getMember(Long memberId) {
        return memberRepository.findByIdAndStatus(memberId, Status.ACTIVE).orElseThrow(() -> new BadRequestException(ExceptionCode.NOT_FOUND_MEMBER));
    }

}
