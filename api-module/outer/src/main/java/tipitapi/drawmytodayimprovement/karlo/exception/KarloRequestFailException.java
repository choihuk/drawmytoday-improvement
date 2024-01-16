package tipitapi.drawmytodayimprovement.karlo.exception;

import tipitapi.drawmytodayimprovement.exception.ErrorCode;
import tipitapi.drawmytodayimprovement.exception.ImageGeneratorException;

public class KarloRequestFailException extends ImageGeneratorException {

    public KarloRequestFailException() {
        super(ErrorCode.KARLO_REQUEST_FAIL);
    }

    public KarloRequestFailException(Throwable throwable) {
        super(ErrorCode.KARLO_REQUEST_FAIL, throwable);
    }
}
