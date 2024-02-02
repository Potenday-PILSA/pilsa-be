package potenday.pilsa.pilsaBasicContents.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import potenday.pilsa.pilsaBasicContents.domain.PilsaBasicContents;

import java.util.List;

public interface PilsaBasicContentsRepository extends JpaRepository<PilsaBasicContents, Long> {

    Page<PilsaBasicContents> findAll(Pageable pageable);

    @Query("SELECT pbc FROM PilsaBasicContents pbc JOIN pbc.categoryList cat WHERE cat.categoryCd = :categoryCd")
    List<PilsaBasicContents> findByCategoryCd(@Param("categoryCd") Long categoryCd);

}
