package tipitapi.drawmytodayimprovement.table.auth.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tipitapi.drawmytodayimprovement.domain.Auth;
import tipitapi.drawmytodayimprovement.repository.AuthRepository;
import tipitapi.drawmytodayimprovement.table.auth.mapper.AuthMapper;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
class AuthEntityRepository implements AuthRepository {

    private final AuthJpaRepository authJpaRepository;
    private final AuthMapper authMapper;

    @Override
    @Transactional
    public Auth save(Auth auth) {
        return authMapper.toDomain(authJpaRepository.save(authMapper.toEntity(auth)));
    }
}
