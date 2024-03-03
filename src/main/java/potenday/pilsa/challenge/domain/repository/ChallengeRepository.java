package potenday.pilsa.challenge.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import potenday.pilsa.challenge.domain.Challenge;
import potenday.pilsa.challenge.domain.Status;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
    Optional<Challenge> findByMember_IdAndDeleteDateIsNullAndId(Long memberId, Long id);
    Page<Challenge> findByMember_IdAndDeleteDateIsNullOrderByRegistDateDesc(Long memberId, Pageable pageable);
    List<Challenge> findByDeleteDateIsNullAndStatusAndStartDateIsBefore(Status status, LocalDateTime startDate);
    List<Challenge> findByMember_IdAndDeleteDateIsNullAndStatusAndEndDateIsBetween(Long memberId, Status status, LocalDateTime endDate, LocalDateTime endDate2);
}
