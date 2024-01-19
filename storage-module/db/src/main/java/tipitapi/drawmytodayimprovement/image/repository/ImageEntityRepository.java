package tipitapi.drawmytodayimprovement.image.repository;

import java.util.List;

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

	@Override
	public List<Image> findAll(Long diaryId) {
		return imageJpaRepository.findAllByDiaryId(diaryId)
			.stream()
			.map(imageMapper::toDomain)
			.toList();
	}

	@Override
	public List<Image> findAllLatestSorted(Long diaryId) {
		return imageJpaRepository.findAllByDiaryIdOrderByCreatedAtDesc(diaryId)
			.stream()
			.map(imageMapper::toDomain)
			.toList();
	}
}
