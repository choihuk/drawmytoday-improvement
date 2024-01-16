package tipitapi.drawmytodayimprovement.image.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tipitapi.drawmytodayimprovement.component.Image;
import tipitapi.drawmytodayimprovement.image.mapper.ImageMapper;
import tipitapi.drawmytodayimprovement.repository.ImageRepository;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
class ImageEntityRepository implements ImageRepository {

	private final ImageJpaRepository imageJpaRepository;
	private final ImageMapper imageMapper;

	@Override
	@Transactional
	public Image save(Image image) {
		return imageMapper.toDomain(imageJpaRepository.save(imageMapper.toEntity(image)));
	}
}
