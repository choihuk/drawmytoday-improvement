package tipitapi.drawmytodayimprovement.common.utils;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import tipitapi.drawmytodayimprovement.common.security.jwt.JwtProperties;
import tipitapi.drawmytodayimprovement.common.security.jwt.JwtType;
import tipitapi.drawmytodayimprovement.common.security.jwt.exception.InvalidTokenException;
import tipitapi.drawmytodayimprovement.common.security.jwt.exception.SecurityErrorCode;
import tipitapi.drawmytodayimprovement.common.security.jwt.exception.TokenNotFoundException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HeaderUtils {

	/**
	 * @throws TokenNotFoundException - 헤더에 토큰이 없는 경우
	 * @throws InvalidTokenException  - 헤더에 토큰이 있지만 유효하지 않은 경우
	 */
	public static String getJwtToken(HttpServletRequest request, JwtType jwtType) {
		String authorization = request.getHeader(JwtProperties.JWT_TOKEN_HEADER);

		if (Objects.isNull(authorization)) {
			if (jwtType == JwtType.ACCESS) {
				throw new TokenNotFoundException(SecurityErrorCode.JWT_TOKEN_NOT_FOUND);
			} else if (jwtType == JwtType.REFRESH) {
				throw new TokenNotFoundException(SecurityErrorCode.JWT_REFRESH_TOKEN_NOT_FOUND);
			} else if (jwtType == JwtType.BOTH) {
				throw new TokenNotFoundException(SecurityErrorCode.JWT_TOKEN_NOT_FOUND);
			}
		}
		String[] tokens = StringUtils.delimitedListToStringArray(authorization, " ");

		if (tokens.length != 2 || !"Bearer".equals(tokens[0])) {
			throw new InvalidTokenException();
		}

		return tokens[1];
	}

	public static String getAuthCode(HttpServletRequest request) {
		String authorization = request.getHeader("Authorization");
		if (!StringUtils.hasText(authorization)) {
			throw new TokenNotFoundException(SecurityErrorCode.AUTH_CODE_NOT_FOUND);
		}

		String[] tokens = StringUtils.delimitedListToStringArray(authorization, " ");
		if (tokens.length != 2 || !"Bearer".equals(tokens[0])) {
			throw new InvalidTokenException();
		}
		return tokens[1];
	}
}
