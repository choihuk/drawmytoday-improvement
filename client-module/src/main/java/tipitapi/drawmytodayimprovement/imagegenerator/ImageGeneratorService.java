package tipitapi.drawmytodayimprovement.imagegenerator;

import java.util.List;

public interface ImageGeneratorService {

	List<byte[]> generateImage(String prompt);
}
