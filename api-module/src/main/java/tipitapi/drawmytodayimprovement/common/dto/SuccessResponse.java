package tipitapi.drawmytodayimprovement.common.dto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Schema(description = "성공 Response")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SuccessResponse<T> {

	@Schema(description = "성공 여부. 항상 true 이다.", defaultValue = "true")
	private final boolean status = true;

	@JsonInclude(Include.NON_NULL)
	private T data;

	public static <T> SuccessResponse<T> of(T data) {
		SuccessResponse<T> SuccessResponse = new SuccessResponse<>();

		SuccessResponse.data = data;

		return SuccessResponse;
	}

	public ResponseEntity<SuccessResponse<T>> asHttp(HttpStatus httpStatus) {
		return ResponseEntity.status(httpStatus).body(this);
	}
}
