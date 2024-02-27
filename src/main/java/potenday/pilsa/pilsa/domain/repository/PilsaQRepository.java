package potenday.pilsa.pilsa.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import potenday.pilsa.pilsa.domain.Pilsa;

import java.util.Optional;

public interface PilsaQRepository {
    Optional<Pilsa> getNextAndPreviousPilsa(Long pilsaId, Long memberId, Boolean isNext);

    Page<Pilsa> findPilsaListSortedByLikes(Pageable pageable);
}
