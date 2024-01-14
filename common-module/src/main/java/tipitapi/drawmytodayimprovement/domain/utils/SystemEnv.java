package tipitapi.drawmytodayimprovement.domain.utils;

import java.util.Optional;

import tipitapi.drawmytodayimprovement.domain.exception.EnvironmentVariableNotFoundException;

public class SystemEnv {

	public static String get(String key) {
		return Optional.ofNullable(System.getenv(key))
			.orElseThrow(EnvironmentVariableNotFoundException::new);
	}
}
