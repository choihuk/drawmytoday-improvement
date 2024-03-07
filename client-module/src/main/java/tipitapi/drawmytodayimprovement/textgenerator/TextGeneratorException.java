package tipitapi.drawmytodayimprovement.textgenerator;

import tipitapi.drawmytodayimprovement.exception.BusinessException;
import tipitapi.drawmytodayimprovement.exception.ErrorCode;

public abstract class TextGeneratorException extends BusinessException {

    public TextGeneratorException(ErrorCode errorCode) {
        super(errorCode);
    }

    public TextGeneratorException(ErrorCode errorCode, Throwable throwable) {
        super(errorCode, throwable);
    }
}
