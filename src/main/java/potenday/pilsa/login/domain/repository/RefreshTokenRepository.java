package potenday.pilsa.login.domain.repository;

import org.springframework.data.repository.CrudRepository;
import potenday.pilsa.login.domain.RefreshToken;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
}
