package tipitapi.drawmytodayimprovement.config;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.impl.DefaultClaims;
import tipitapi.drawmytodayimprovement.common.security.jwt.JwtAuthenticationToken;
import tipitapi.drawmytodayimprovement.common.security.jwt.JwtProperties;

public class WithCustomUserSecurityContextFactory implements
	WithSecurityContextFactory<WithCustomUser> {

	@Override
	public SecurityContext createSecurityContext(WithCustomUser customUser) {
		Claims claims = new DefaultClaims();
		claims.put(JwtProperties.USER_ID, customUser.userId());
		claims.put(JwtProperties.ROLE, customUser.role());

		JwtAuthenticationToken authentication = new JwtAuthenticationToken(claims, "", null);
		SecurityContext context = SecurityContextHolder.createEmptyContext();
		context.setAuthentication(authentication);
		return context;
	}

}
