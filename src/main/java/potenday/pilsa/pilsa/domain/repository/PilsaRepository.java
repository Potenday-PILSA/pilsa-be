package potenday.pilsa.pilsa.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import potenday.pilsa.pilsa.domain.Pilsa;
import potenday.pilsa.pilsa.domain.YN;

import java.util.List;
import java.util.Optional;

public interface PilsaRepository extends JpaRepository<Pilsa, Long> {

    Optional<Pilsa> findByPilsaId(Long pilsaId);
    List<Pilsa> findByMember_Id(Long id);

    Page<Pilsa> findByPrivateTypeOrderByRegistDateDesc(YN privateType, Pageable pageable);

    Page<Pilsa> findByOrderByRegistDateDesc(Pageable pageable);

    Page<Pilsa> findByMember_IdOrderByRegistDateDesc(Long memberId, Pageable pageable);
}
