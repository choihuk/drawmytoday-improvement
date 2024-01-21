package tipitapi.drawmytodayimprovement.exception;

public enum DiaryErrorCode implements ErrorCode {

	// Diary
	DIARY_NOT_FOUND(404, "D001", "일기를 찾을 수 없습니다."),
	DIARY_NOT_OWNER(403, "D002", "자신의 일기에만 접근할 수 있습니다."),
	INVALID_CREATE_DIARY_DATE(400, "D003", "일기를 그릴 수 없는 날짜입니다."),
	DIARY_DATE_ALREADY_EXISTS(409, "D004", "이미 일기를 그린 날짜입니다."),

	// Image
	IMAGE_NOT_FOUND(404, "I001", "이미지를 찾을 수 없습니다."),
	SELECTED_IMAGE_DELETION_DENIED(403, "I002", "대표 이미지는 삭제할 수 없습니다."),
	DIARY_NEEDS_IMAGE(403, "I003", "일기는 최소 한 장의 이미지가 필요합니다."),
	IMAGE_NOT_OWNER(403, "I004", "자신의 이미지에만 접근할 수 있습니다."),

	// Emotion
	EMOTION_NOT_FOUND(404, "E001", "감정을 찾을 수 없습니다."),

	// Prompt
	PROMPT_NOT_EXIST(500, "P001", "프롬프트가 존재하지 않습니다.");

	private final int status;
	private final String code;
	private final String message;

	DiaryErrorCode(final int status, final String code, final String message) {
		this.status = status;
		this.code = code;
		this.message = message;
	}

	@Override
	public int getStatus() {
		return status;
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
