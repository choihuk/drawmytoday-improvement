package tipitapi.drawmytodayimprovement.storage;

public interface ImageUploadService {
	void uploadImage(byte[] imageBytes, String filePath);
}
