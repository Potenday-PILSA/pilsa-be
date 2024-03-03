package potenday.pilsa.challenge.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChallengeScheduler {

    private final ChallengeService challengeService;

    @Scheduled(cron = "2 0 0 * * *") // 매일 밤 12시 2분에 실행
    public void changeChallengeStatue() {
        challengeService.changeStatueIng();
    }

}
