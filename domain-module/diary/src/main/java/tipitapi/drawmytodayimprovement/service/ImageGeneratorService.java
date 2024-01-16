package tipitapi.drawmytodayimprovement.service;

import java.util.List;

public interface ImageGeneratorService {

	List<byte[]> generateImage(String prompt);
}
