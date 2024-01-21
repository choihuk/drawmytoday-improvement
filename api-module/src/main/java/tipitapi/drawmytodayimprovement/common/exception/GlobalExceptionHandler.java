package tipitapi.drawmytodayimprovement.common.exception;

import static tipitapi.drawmytodayimprovement.common.dto.ErrorResponse.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;
import tipitapi.drawmytodayimprovement.common.dto.ErrorResponse;
import tipitapi.drawmytodayimprovement.exception.BusinessException;
import tipitapi.drawmytodayimprovement.exception.CommonErrorCode;
import tipitapi.drawmytodayimprovement.exception.ErrorCode;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<Object> handleBusinessException(BusinessException e) {
		if (e.getErrorCode().getStatus() == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
			log.error("handleBusinessException", e);
		} else {
			log.warn("handleBusinessException", e);
		}
		return handleExceptionInternal(e.getErrorCode());
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Object> handleIllegalArgument(IllegalArgumentException e) {
		log.warn("handleIllegalArgument", e);
		return handleExceptionInternal(CommonErrorCode.INVALID_INPUT_VALUE, e.getMessage());
	}

	// @Valid 예외처리
	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(
		MethodArgumentNotValidException e,
		HttpHeaders headers,
		HttpStatus status,
		WebRequest request) {
		log.warn("handleIllegalArgument", e);
		return handleExceptionInternal(e, CommonErrorCode.INVALID_INPUT_VALUE);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(
		HttpMessageNotReadableException e,
		HttpHeaders headers,
		HttpStatus status,
		WebRequest request) {
		log.warn("handleHttpMessageNotReadable", e);
		return handleExceptionInternal(CommonErrorCode.INVALID_INPUT_VALUE);
	}

	// 이 외의 500 에러 처리
	@ExceptionHandler({Exception.class})
	public ResponseEntity<Object> handleAllException(Exception ex) {
		log.error("handleAllException", ex);
		CommonErrorCode commonErrorCode = CommonErrorCode.INTERNAL_SERVER_ERROR;
		return handleExceptionInternal(commonErrorCode);
	}

	// @ExceptionHandler(SdkClientException.class)
	// public ResponseEntity<Object> handleR2SdkClientException(SdkClientException e) {
	//     log.error("R2SdkClientException", e);
	//     return handleExceptionInternal(ErrorCode.R2_SDK_ERROR);
	// }
	//
	// @ExceptionHandler(S3Exception.class)
	// public ResponseEntity<Object> handleR2Exception(S3Exception e) {
	//     log.error("R2Exception", e);
	//     return handleExceptionInternal(ErrorCode.R2_SERVICE_ERROR);
	// }
	//
	// @ExceptionHandler(R2FailedException.class)
	// public ResponseEntity<Object> handleR2FailedException(R2FailedException e) {
	//     log.error("R2FailedException", e);
	//     return handleExceptionInternal(ErrorCode.R2_FAILED);
	// }
	//
	// @ExceptionHandler(ImageGeneratorException.class)
	// public ResponseEntity<Object> handleImageGeneratorException(ImageGeneratorException e) {
	//     log.error("ImageGeneratorException", e);
	//     return handleExceptionInternal(e.getErrorCode());
	// }
	//
	// @ExceptionHandler(ImageInputStreamFailException.class)
	// public ResponseEntity<Object> handleImageInputStreamFailException(
	//     ImageInputStreamFailException e) {
	//     log.error("ImageInputStreamFailException", e);
	//     return handleExceptionInternal(ErrorCode.IMAGE_INPUT_STREAM_FAIL);
	// }

	@ExceptionHandler(RestClientException.class)
	public ResponseEntity<Object> handleRestClientException(RestClientException e) {
		log.error("RestClientException", e);
		return handleExceptionInternal(CommonErrorCode.REST_CLIENT_FAILED);
	}

	private ResponseEntity<Object> handleExceptionInternal(ErrorCode errorCode) {
		return ResponseEntity.status(errorCode.getStatus())
			.body(makeErrorResponse(errorCode));
	}

	private ResponseEntity<Object> handleExceptionInternal(ErrorCode errorCode, String message) {
		return ResponseEntity.status(errorCode.getStatus())
			.body(makeErrorResponse(errorCode, message));
	}

	private ResponseEntity<Object> handleExceptionInternal(BindException e, ErrorCode errorCode) {
		return ResponseEntity.status(errorCode.getStatus())
			.body(makeErrorResponse(e, errorCode));
	}

	private ErrorResponse makeErrorResponse(ErrorCode errorCode) {
		return builder()
			.code(errorCode.getCode())
			.message(errorCode.getMessage())
			.build();
	}

	private ErrorResponse makeErrorResponse(ErrorCode errorCode, String message) {
		return builder()
			.code(errorCode.getCode())
			.message(message)
			.build();
	}

	private ErrorResponse makeErrorResponse(BindException e, ErrorCode errorCode) {
		List<ValidationError> validationErrorList = e.getBindingResult()
			.getFieldErrors()
			.stream()
			.map(ValidationError::of)
			.collect(Collectors.toList());

		return builder()
			.code(errorCode.getCode())
			.message(errorCode.getMessage())
			.errors(validationErrorList)
			.build();
	}

}
