package tipitapi.drawmytodayimprovement.exception;

public enum CommonErrorCode implements ErrorCode {

    // Common
    INVALID_INPUT_VALUE(400, "C001", "잘못된 입력값입니다."),
    METHOD_NOT_ALLOWED(405, "C002", "허용하지 않는 HTTP 메서드입니다."),
    ENTITY_NOT_FOUND(400, "C003", "엔티티를 찾을 수 없습니다."),
    INTERNAL_SERVER_ERROR(500, "C004", "서버 오류"),
    INVALID_TYPE_VALUE(400, "C005", "잘못된 타입의 값입니다."),
    HANDLE_ACCESS_DENIED(403, "C006", "접근이 거부됐습니다."),
    ENCRYPTION_ERROR(500, "C007", "암호화에 실패했습니다."),
    DECRYPTION_ERROR(500, "C008", "복호화에 실패했습니다."),
    PARSING_ERROR(500, "C009", "파싱에 실패했습니다."),

    // R2
    R2_SERVICE_ERROR(500, "R001", "R2Exception 에러가 발생하였습니다."),
    R2_SDK_ERROR(500, "R002", "SdkClientException 에러가 발생하였습니다."),
    R2_FAILED(500, "R003", "R2 처리에 실패하였습니다."),

    // DALL-E
    DALLE_REQUEST_FAIL(500, "DE001", "DALL-E 요청에 실패하였습니다."),
    DALLE_CONTENT_POLICY_VIOLATION(500, "DE002", "DALL-E의 컨텐츠 정책에 위배되었습니다."),

    // Karlo
    KARLO_REQUEST_FAIL(500, "K001", "Karlo 요청에 실패하였습니다."),

    // Image InputStream
    IMAGE_INPUT_STREAM_FAIL(500, "IIS001", "이미지 스트림을 가져오는데 실패하였습니다."),

    // OAuth
    OAUTH_NOT_FOUND(404, "O001", "유저의 refresh token을 찾을 수 없습니다."),
    OAUTH_SERVER_FAILED(500, "O002", "OAuth 서버와의 통신 중 에러가 발생하였습니다."),
    GENERATE_KEY_FAILED(500, "O003", "키 생성에 실패하였습니다."),

    // REST
    REST_CLIENT_FAILED(500, "R001", "외부로의 REST 통신에 실패하였습니다."),

    // GPT
    GPT_REQUEST_FAIL(500, "G001", "GPT 요청에 실패하였습니다.");

    private final int status;
    private final String code;
    private final String message;

    CommonErrorCode(final int status, final String code, final String message) {
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