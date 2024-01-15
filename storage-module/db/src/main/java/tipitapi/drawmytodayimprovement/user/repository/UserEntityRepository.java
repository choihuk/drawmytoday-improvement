package tipitapi.drawmytodayimprovement.user.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tipitapi.drawmytodayimprovement.domain.User;
import tipitapi.drawmytodayimprovement.repository.UserRepository;
import tipitapi.drawmytodayimprovement.user.mapper.UserMapper;

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
}
