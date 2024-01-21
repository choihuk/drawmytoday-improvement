package tipitapi.drawmytodayimprovement.common.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import io.jsonwebtoken.Claims;
import tipitapi.drawmytodayimprovement.common.security.jwt.JwtProperties;
import tipitapi.drawmytodayimprovement.enumeration.UserRole;
import tipitapi.drawmytodayimprovement.vo.JwtTokenInfo;

@Component
public class AuthUserArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.getParameterType().equals(JwtTokenInfo.class) &&
			parameter.hasParameterAnnotation(AuthUser.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
		NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Claims claims = (Claims)authentication.getPrincipal();
		Long userId = Long.parseLong((String)claims.get(JwtProperties.USER_ID));
		UserRole userRole = UserRole.valueOf((String)claims.get(JwtProperties.ROLE));

		return JwtTokenInfo.of(userId, userRole);
	}
}
