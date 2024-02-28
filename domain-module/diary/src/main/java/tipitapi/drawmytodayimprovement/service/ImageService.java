package tipitapi.drawmytodayimprovement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tipitapi.drawmytodayimprovement.component.Image;
import tipitapi.drawmytodayimprovement.component.MonthlyDiary;
import tipitapi.drawmytodayimprovement.exception.ImageNotFoundException;
import tipitapi.drawmytodayimprovement.repository.ImageRepository;
import tipitapi.drawmytodayimprovement.storage.PreSignedUrlService;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageService {

	private final ImageRepository imageRepository;
	private final PreSignedUrlService preSignedUrlService;

	public Image getSelectedImage(Long diaryId) {
		return imageRepository.findAll(diaryId).stream()
			.filter(Image::isSelected)
			.findFirst()
			.map(image -> {
				image.convertImageUrl(preSignedUrlService.getCustomDomainUrl(image.getImageUrl()));
				return image;
			})
			.orElseThrow(ImageNotFoundException::new);
	}

	public List<Image> getLatestSortedImages(Long diaryId) {
		return imageRepository.findAllLatestSorted(diaryId)
			.stream()
			.map(image -> {
				image.convertImageUrl(preSignedUrlService.getCustomDomainUrl(image.getImageUrl()));
				return image;
			}).toList();
	}

	public Optional<Image> getOneLatestImage(Long diaryId) {
		return imageRepository.findRecentByDiary(diaryId);
	}

	public List<MonthlyDiary> fillImages(List<MonthlyDiary> monthlyDiaries) {
		for (int i = 0; i < monthlyDiaries.size(); i++) {
			MonthlyDiary monthlyDiary = monthlyDiaries.get(i);
			if (monthlyDiary.getImageUrl() == null) {
				log.warn("DiaryId가 {}인 일기에 해당하는 대표 이미지가 없습니다.", monthlyDiary.getDiaryId());
				Optional<Image> latestImage = getOneLatestImage(monthlyDiary.getDiaryId());
				if (latestImage.isPresent()) {
					latestImage.get().makeMainImage();
					imageRepository.save(latestImage.get());
					monthlyDiary.convertImageUrl(
						preSignedUrlService.getCustomDomainUrl(latestImage.get().getImageUrl())
					);
				} else {
					monthlyDiaries.remove(i--);
				}
			} else {
				monthlyDiary.convertImageUrl(
					preSignedUrlService.getCustomDomainUrl(monthlyDiary.getImageUrl())
				);
			}
		}
		return monthlyDiaries;
	}
}
