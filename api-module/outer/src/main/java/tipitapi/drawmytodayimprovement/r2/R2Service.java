package tipitapi.drawmytodayimprovement.r2;

import static software.amazon.awssdk.services.s3.model.ObjectCannedACL.*;

import org.springframework.stereotype.Service;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import tipitapi.drawmytodayimprovement.service.ImageUploadService;
import tipitapi.drawmytodayimprovement.utils.SystemEnv;

@Service
public class R2Service implements ImageUploadService {

    private final S3Client s3Client;
    private String bucketName;

    public R2Service(S3Client s3Client) {
        this.s3Client = s3Client;
        this.bucketName = SystemEnv.get("R2_BUCKET_NAME");
    }

    @Override
    public void uploadImage(byte[] imageBytes, String filePath) {
        try {
            PutObjectRequest putObjectRequest = buildPutObjectRequest(filePath);
            s3Client.putObject(putObjectRequest, RequestBody.fromBytes(imageBytes));
        } catch (Exception e) {
            throw new R2FailedException(e);
        }
    }

    private PutObjectRequest buildPutObjectRequest(String filePath) {
        return PutObjectRequest.builder()
            .bucket(bucketName)
            .key(filePath)
            .contentType("image/webp")
            .acl(PRIVATE).build();
    }
}