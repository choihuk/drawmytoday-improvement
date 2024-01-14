package tipitapi.drawmytodayimprovement.auth.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tipitapi.drawmytodayimprovement.auth.mapper.AuthMapper;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthEntityRepository {

	private final AuthJpaRepository authJpaRepository;
	private final AuthMapper authMapper;

}
