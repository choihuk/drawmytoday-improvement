package tipitapi.drawmytodayimprovement.imagegenerator.karlo;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;

record KarloUrlResponse(String id, @JsonProperty("model_version") String modelVersion,
						List<KarloImageUrlResponse> images) {

	public String getUrl(int i) {
		return images.get(0).imageUrl();
	}

	public List<String> getUrls() {
		return images.stream()
			.map(KarloImageUrlResponse::imageUrl)
			.collect(Collectors.toList());
	}
}
