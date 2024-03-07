package tipitapi.drawmytodayimprovement.textgenerator.gpt.exception;

import tipitapi.drawmytodayimprovement.exception.CommonErrorCode;
import tipitapi.drawmytodayimprovement.textgenerator.TextGeneratorException;

public class GptRequestFailException extends TextGeneratorException {

    public GptRequestFailException() {
        super(CommonErrorCode.GPT_REQUEST_FAIL);
    }

    public GptRequestFailException(Throwable throwable) {
        super(CommonErrorCode.GPT_REQUEST_FAIL, throwable);
    }
}
