package tipitapi.drawmytodayimprovement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tipitapi.drawmytodayimprovement.component.Image;
import tipitapi.drawmytodayimprovement.exception.ImageNotFoundException;
import tipitapi.drawmytodayimprovement.repository.ImageRepository;

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
}
