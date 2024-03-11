package tipitapi.drawmytodayimprovement.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tipitapi.drawmytodayimprovement.domain.Image;
import tipitapi.drawmytodayimprovement.domain.ImageUrlConvertable;
import tipitapi.drawmytodayimprovement.dto.ImageForMonitoring;
import tipitapi.drawmytodayimprovement.dto.MonthlyDiary;
import tipitapi.drawmytodayimprovement.dto.PageResponse;
import tipitapi.drawmytodayimprovement.dto.PageableRequest;
import tipitapi.drawmytodayimprovement.repository.ImageRepository;
import tipitapi.drawmytodayimprovement.storage.PreSignedUrlService;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;
    private final PreSignedUrlService preSignedUrlService;

    public List<Image> getLatestSortedImages(Long diaryId) {
        return imageRepository.findAllLatestSorted(diaryId)
                .stream()
                .map(image -> {
                    convertImageUrl(image, image.getImageUrl());
                    return image;
                })
                .toList();
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
                    convertImageUrl(monthlyDiary, latestImage.get().getImageUrl());
                } else {
                    monthlyDiaries.remove(i--);
                }
            } else {
                convertImageUrl(monthlyDiary, monthlyDiary.getImageUrl());
            }
        }
        return monthlyDiaries;
    }

    public Image save(Image image) {
        return imageRepository.save(image);
    }

    public void unSelectAllImage(Long diaryId) {
        List<Image> images = imageRepository.findAll(diaryId);
        images.forEach(Image::makeSubImage);
        imageRepository.saveAll(images);
    }

    public PageResponse<ImageForMonitoring> getImagesForMonitoring(PageableRequest pageableRequest,
                                                                   Long emotionId, boolean withTest) {
        PageResponse<ImageForMonitoring> pageResponses = imageRepository.getImagesForMonitoring(
                pageableRequest, emotionId, withTest);
        pageResponses.forEach(image -> convertImageUrl(image, image.getImageUrl()));
        return pageResponses;
    }

    private void convertImageUrl(ImageUrlConvertable imageUrlConvertable, String imageUrl) {
        imageUrlConvertable.convertImageUrl(preSignedUrlService.getCustomDomainUrl(imageUrl));
    }

}
