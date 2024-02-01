package potenday.pilsa.pilsaCategory.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import potenday.pilsa.pilsaCategory.domain.PilsaCategory;
import potenday.pilsa.pilsaCategory.domain.YN;

import java.util.List;

public interface PilsaCategoryRepository extends JpaRepository<PilsaCategory, Long> {

    List<PilsaCategory> findByUseYn(YN useYn);

    List<PilsaCategory> findByCategoryCdIn(List<Long> categoryCd);
}
