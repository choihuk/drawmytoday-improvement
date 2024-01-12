package tipitapi.drawmytodayimprovement.common.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SuccessResponse<T> {

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
