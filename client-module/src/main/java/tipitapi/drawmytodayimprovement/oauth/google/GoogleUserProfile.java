package tipitapi.drawmytodayimprovement.oauth.google;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GoogleUserProfile(String email) {

}
