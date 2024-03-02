package potenday.pilsa.challenge.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import potenday.pilsa.challenge.domain.Challenge;

import java.util.Optional;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
    Optional<Challenge> findByMember_IdAndDeleteDateIsNullAndId(Long memberId, Long id);
    Page<Challenge> findByMember_IdAndDeleteDateIsNullOrderByRegistDateDesc(Long memberId, Pageable pageable);
}
