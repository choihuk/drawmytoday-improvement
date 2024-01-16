package tipitapi.drawmytodayimprovement.domain.diary.response;

import tipitapi.drawmytodayimprovement.component.Diary;

public record GetDiaryResponse(Long id) {

    // private final String imageUrl;
    //
    // private final List<GetImageResponse> imageList;
    //
    // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    // @JsonSerialize(using = LocalDateTimeSerializer.class)
    // @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    // private final LocalDateTime date;
    //
    // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    // @JsonSerialize(using = LocalDateTimeSerializer.class)
    // @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    // private final LocalDateTime createdAt;
    //
    // private final String emotion;
    //
    // private String notes;
    //
    // private String prompt;

    // public static GetDiaryResponse of(Diary diary, String selectedImageUrl,
    //     List<GetImageResponse> imageList, String emotionText, String promptText) {
    //     return new GetDiaryResponse(diary.getDiaryId(), selectedImageUrl, imageList,
    //         diary.getDiaryDate(), diary.getCreatedAt(), emotionText, diary.getNotes(), promptText);
    // }

    public static GetDiaryResponse of(Diary diary) {
        return new GetDiaryResponse(diary.getDiaryId());
    }

}
