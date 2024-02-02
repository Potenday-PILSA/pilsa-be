package potenday.pilsa.pilsaImage.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import potenday.pilsa.pilsaImage.domain.PilsaImage;

import java.util.List;

public interface PilsaImageRepository extends JpaRepository<PilsaImage, Long> {

    List<PilsaImage> findByPilsa_Member_Id(Long memberId);
    void deleteByPilsa_PilsaId(Long pilsa_pilsaId);
}
