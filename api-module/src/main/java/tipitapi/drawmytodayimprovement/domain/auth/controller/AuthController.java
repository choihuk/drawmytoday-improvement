package tipitapi.drawmytodayimprovement.domain.auth.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import tipitapi.drawmytodayimprovement.AuthUseCase;
import tipitapi.drawmytodayimprovement.common.dto.SuccessResponse;
import tipitapi.drawmytodayimprovement.common.security.jwt.JwtTokenProvider;
import tipitapi.drawmytodayimprovement.common.utils.HeaderUtils;
import tipitapi.drawmytodayimprovement.domain.auth.response.JwtTokenResponse;
import tipitapi.drawmytodayimprovement.vo.JwtTokenInfo;

@RestController
@RequiredArgsConstructor
@RequestMapping("/oauth2")
public class AuthController implements AuthApi {

	private final AuthUseCase authUseCase;
	private final JwtTokenProvider jwtTokenProvider;

	@Override
	@PostMapping(value = "/google/login")
	public ResponseEntity<SuccessResponse<JwtTokenResponse>> googleLogin(HttpServletRequest request) {
		JwtTokenInfo jwtTokenInfo = authUseCase.googleLogin(HeaderUtils.getAuthCode(request));

		String accessToken = jwtTokenProvider.createAccessToken(jwtTokenInfo.userId(), jwtTokenInfo.userRole());
		String refreshToken = jwtTokenProvider.createRefreshToken(jwtTokenInfo.userId(), jwtTokenInfo.userRole());
		return SuccessResponse.of(JwtTokenResponse.of(accessToken, refreshToken)).asHttp(HttpStatus.OK);
	}
}
