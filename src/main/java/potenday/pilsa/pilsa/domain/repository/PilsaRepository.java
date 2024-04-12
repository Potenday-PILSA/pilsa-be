package potenday.pilsa.pilsa.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import potenday.pilsa.pilsa.domain.Pilsa;
import potenday.pilsa.pilsa.domain.YN;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PilsaRepository extends JpaRepository<Pilsa, Long>, PilsaQRepository {

    List<Pilsa> findByMember_Id(Long id);
    Optional<Pilsa> findByPilsaId(Long pilsaId);
    List<Pilsa> findByMember_IdAndDeleteDateIsNull(Long id);
    Optional<Pilsa> findByPilsaIdAndDeleteDateIsNull(Long pilsaId);

    Page<Pilsa> findByPrivateTypeAndDeleteDateIsNullOrderByRegistDateDesc(YN privateType, Pageable pageable);

    Page<Pilsa> findByOrderByRegistDateDesc(Pageable pageable);

    Optional<Pilsa> findByMember_IdAndDeleteDateIsNullAndPilsaId(Long member_id, Long pilsaId);

    Page<Pilsa> findByMember_IdAndDeleteDateIsNullOrderByRegistDateDesc(Long memberId, Pageable pageable);

    List<Pilsa> findByMember_IdAndRegistDateBetweenAndDeleteDateIsNullOrderByRegistDateAsc(Long member_id, LocalDateTime startDate, LocalDateTime endDate);

    List<Pilsa> findByMember_IdAndRegistDateBetweenAndDeleteDateIsNull(Long member_id, LocalDateTime registDate, LocalDateTime registDate2);
    List<Pilsa> findByMember_IdAndChallenge_IdAndDeleteDateIsNull(Long member_id, Long challenge_id);

}
