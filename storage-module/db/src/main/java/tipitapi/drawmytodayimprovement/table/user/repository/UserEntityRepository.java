package tipitapi.drawmytodayimprovement.table.user.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tipitapi.drawmytodayimprovement.domain.User;
import tipitapi.drawmytodayimprovement.repository.UserRepository;
import tipitapi.drawmytodayimprovement.table.user.mapper.UserMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
class UserEntityRepository implements UserRepository {

    private final UserJpaRepository userJpaRepository;
    private final UserMapper userMapper;

    @Override
    public List<User> findAllByEmail(String email) {
        return userJpaRepository.findAllByEmail(email).stream()
                .map(userMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public User save(User user) {
        return userMapper.toDomain(userJpaRepository.save(userMapper.toEntity(user)));
    }

    @Override
    public Optional<User> findById(Long userId) {
        return userJpaRepository.findById(userId)
                .map(userMapper::toDomain);
    }
}
