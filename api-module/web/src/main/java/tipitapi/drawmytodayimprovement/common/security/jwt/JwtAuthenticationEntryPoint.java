package tipitapi.drawmytodayimprovement.common.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tipitapi.drawmytodayimprovement.common.dto.ErrorResponse;
import tipitapi.drawmytodayimprovement.common.security.jwt.exception.TokenException;
import tipitapi.drawmytodayimprovement.exception.ErrorCode;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationEntryPoint extends OncePerRequestFilter {

	private final ObjectMapper objectMapper;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws ServletException, IOException {
		try {
			filterChain.doFilter(request, response);
		} catch (TokenException e) {
			log.warn("security exception = {}", e.getErrorCode(), e);
			ErrorCode errorCode = e.getErrorCode();

			ErrorResponse errorResponse = makeErrorResponse(errorCode);
			response.setStatus(errorCode.getStatus());
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
		}
	}

	private ErrorResponse makeErrorResponse(ErrorCode errorCode) {
		return ErrorResponse.builder()
			.code(errorCode.getCode())
			.message(errorCode.getMessage())
			.build();
	}
}
