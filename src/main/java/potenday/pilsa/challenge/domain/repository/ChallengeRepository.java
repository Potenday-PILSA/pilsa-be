package potenday.pilsa.challenge.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import potenday.pilsa.challenge.domain.Challenge;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
}
