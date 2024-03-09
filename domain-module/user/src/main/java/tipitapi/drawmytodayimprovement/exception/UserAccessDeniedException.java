package tipitapi.drawmytodayimprovement.exception;

public class UserAccessDeniedException extends BusinessException {

    public UserAccessDeniedException() {
        super(UserErrorCode.USER_ACCESS_DENIED);
    }
}
