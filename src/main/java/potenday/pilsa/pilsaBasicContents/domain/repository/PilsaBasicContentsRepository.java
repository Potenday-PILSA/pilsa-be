package potenday.pilsa.pilsaBasicContents.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import potenday.pilsa.pilsaBasicContents.domain.PilsaBasicContents;

public interface PilsaBasicContentsRepository extends JpaRepository<PilsaBasicContents, Long> {

    Page<PilsaBasicContents> findAll(Pageable pageable);
}
