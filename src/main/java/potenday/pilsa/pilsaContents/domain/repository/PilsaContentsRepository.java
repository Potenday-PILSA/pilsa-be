package potenday.pilsa.pilsaContents.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import potenday.pilsa.pilsaContents.domain.PilsaContents;

public interface PilsaContentsRepository extends JpaRepository<PilsaContents, Long> {
}
