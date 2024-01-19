package tipitapi.drawmytodayimprovement.component;

import java.util.List;

import tipitapi.drawmytodayimprovement.exception.ImageNotFoundException;

public record AllDiaryElement(Diary diary,
							  Image selectedImage,
							  List<Image> imageList,
							  Emotion emotion,
							  Prompt prompt) {

	public static AllDiaryElement of(Diary diary, Emotion emotion, Prompt prompt, List<Image> images) {
		Image selectedImage = images.stream()
			.filter(Image::isSelected)
			.findFirst()
			.orElseThrow(ImageNotFoundException::new);
		return new AllDiaryElement(diary, selectedImage, images, emotion, prompt);
	}
}
