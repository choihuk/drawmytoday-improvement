package tipitapi.drawmytodayimprovement.karlo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
record KarloImageUrlResponse(String id, Long seed, @JsonProperty("image") String imageUrl) {

}
