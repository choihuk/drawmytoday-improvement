package tipitapi.drawmytodayimprovement.repository;

import org.springframework.stereotype.Repository;

import tipitapi.drawmytodayimprovement.component.Auth;

@Repository
public interface AuthRepository {
	Auth save(Auth auth);
}
