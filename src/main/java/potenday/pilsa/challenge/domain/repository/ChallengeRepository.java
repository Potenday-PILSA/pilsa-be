package potenday.pilsa.challenge.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import potenday.pilsa.challenge.domain.Challenge;

import java.util.Optional;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
    Optional<Challenge> findByMember_IdAndDeleteDateIsNullAndId(Long member_id, Long id);
}
