package tipitapi.drawmytodayimprovement.service;

import static tipitapi.drawmytodayimprovement.exception.ErrorCode.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import tipitapi.drawmytodayimprovement.exception.BusinessException;
import tipitapi.drawmytodayimprovement.properties.GoogleProperties;
import tipitapi.drawmytodayimprovement.service.dto.GoogleAccessToken;
import tipitapi.drawmytodayimprovement.service.dto.GoogleUserProfile;

@Service
@RequiredArgsConstructor
public class GoogleOAuthService {

	private final GoogleProperties googleProperties;
	private final ObjectMapper objectMapper;

	public GoogleAccessToken getAccessToken(String authCode) {
		try {
			HttpClient httpClient = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(googleProperties.getTokenUrl()))
				.header("Content-Type", "application/x-www-form-urlencoded")
				.POST(HttpRequest.BodyPublishers.ofString(buildAccessTokenRequestBody(authCode)))
				.build();

			HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

			if (response.statusCode() != HttpURLConnection.HTTP_OK) {
				throw new IOException("Failed to get google access token. "
					+ "Response: " + response.statusCode() + ", " + response.body());
			}

			return objectMapper.readValue(response.body(), GoogleAccessToken.class);
		} catch (JsonProcessingException e) {
			throw new BusinessException(PARSING_ERROR, e);
		} catch (IOException | InterruptedException e) {
			throw new BusinessException(OAUTH_SERVER_FAILED, e);
		}
	}

	public GoogleUserProfile getUserProfile(GoogleAccessToken accessToken) {
		try {
			String userInfoUrl = googleProperties.getUserInfoUrl();

			HttpClient httpClient = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(userInfoUrl))
				.header("Authorization", "Bearer " + accessToken.accessToken())
				.GET()
				.build();

			HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

			if (response.statusCode() != HttpURLConnection.HTTP_OK) {
				throw new IOException("Failed to get google access token. "
					+ "Response code: " + response.statusCode());
			}

			return objectMapper.readValue(response.body(), GoogleUserProfile.class);
		} catch (JsonProcessingException e) {
			throw new BusinessException(PARSING_ERROR, e);
		} catch (IOException | InterruptedException e) {
			throw new BusinessException(OAUTH_SERVER_FAILED, e);
		}
	}

	private String buildAccessTokenRequestBody(String authCode) {
		StringBuilder requestBody = new StringBuilder();
		requestBody.append("code=")
			.append(URLEncoder.encode(authCode, StandardCharsets.UTF_8));
		requestBody.append("&client_id=")
			.append(URLEncoder.encode(googleProperties.getClientId(), StandardCharsets.UTF_8));
		requestBody.append("&client_secret=")
			.append(URLEncoder.encode(googleProperties.getClientSecret(), StandardCharsets.UTF_8));
		requestBody.append("&redirect_uri=")
			.append(URLEncoder.encode(googleProperties.getRedirectUri(), StandardCharsets.UTF_8));
		requestBody.append("&grant_type=authorization_code");
		return requestBody.toString();
	}
}
