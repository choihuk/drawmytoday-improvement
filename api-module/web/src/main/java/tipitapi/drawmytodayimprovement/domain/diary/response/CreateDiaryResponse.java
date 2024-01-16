package tipitapi.drawmytodayimprovement.domain.diary.response;

public record CreateDiaryResponse(Long id) {

	public static CreateDiaryResponse of(Long id) {
		return new CreateDiaryResponse(id);
	}
}
