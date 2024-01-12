package tipitapi.drawmytodayimprovement.diary.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import tipitapi.drawmytodayimprovement.domain.Diary;

@Getter
@AllArgsConstructor
public class GetDiaryResponse {

    private final Long id;

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
