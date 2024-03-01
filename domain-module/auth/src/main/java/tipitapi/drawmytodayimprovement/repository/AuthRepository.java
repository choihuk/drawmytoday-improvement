package tipitapi.drawmytodayimprovement.repository;

import org.springframework.stereotype.Repository;
import tipitapi.drawmytodayimprovement.domain.Auth;

@Repository
public interface AuthRepository {
    Auth save(Auth auth);
}
