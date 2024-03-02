package potenday.pilsa.challenge.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import potenday.pilsa.challenge.domain.Challenge;
import potenday.pilsa.challenge.domain.repository.ChallengeRepository;
import potenday.pilsa.challenge.dto.request.RequestCreateChallenge;
import potenday.pilsa.challenge.dto.response.ResponseChallengeInfo;
import potenday.pilsa.challenge.dto.response.ResponseChallengeList;
import potenday.pilsa.global.dto.request.RequestPageDto;
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

    @Transactional(readOnly = true)
    public ResponseChallengeInfo getChallengeInfo(Long memberId, Long challengeId) {
        Challenge challenge = challengeRepository.findByMember_IdAndDeleteDateIsNullAndId(memberId, challengeId).orElseThrow(() -> new BadRequestException(ExceptionCode.NOT_FOUND_CHALLENGE));

        return ResponseChallengeInfo.from(challenge);
    }

    @Transactional(readOnly = true)
    public ResponseChallengeList getChallengeList(Long memberId, RequestPageDto requestPageDto) {
        Page<Challenge> challenges = challengeRepository.findByMember_IdAndDeleteDateIsNullOrderByRegistDateDesc(memberId, requestPageDto.toPageable());

        return ResponseChallengeList.from(challenges);
    }

    @Transactional
    public void deleteChallenge(Long memberId, Long challengeId) {
        Challenge challenge = challengeRepository.findByMember_IdAndDeleteDateIsNullAndId(memberId, challengeId).orElseThrow(() -> new BadRequestException(ExceptionCode.NOT_FOUND_CHALLENGE));

        challenge.deleteChallenge();
    }

    private Member getMember(Long memberId) {
        return memberRepository.findByIdAndStatus(memberId, Status.ACTIVE).orElseThrow(() -> new BadRequestException(ExceptionCode.NOT_FOUND_MEMBER));
    }

}
