package potenday.pilsa.pilsa.domain.repository;

import potenday.pilsa.pilsa.domain.Pilsa;

import java.util.Optional;

public interface PilsaQRepository {
    Optional<Pilsa> getNextAndPreviousPilsa(Long pilsaId, Long memberId, Boolean isNext);
}
