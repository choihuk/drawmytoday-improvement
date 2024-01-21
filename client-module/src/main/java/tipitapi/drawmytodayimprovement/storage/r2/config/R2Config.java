package tipitapi.drawmytodayimprovement.storage.r2.config;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

@Configuration
public class R2Config {

	private final String accessKey;
	private final String secretKey;
	private final String accountId;

	public R2Config() {
		this.accessKey = System.getenv("R2_ACCESS_KEY_ID");
		this.secretKey = System.getenv("R2_SECRET_ACCESS_KEY");
		this.accountId = System.getenv("R2_ACCOUNT_ID");
	}

	@Bean
	public S3Client r2Client() throws URISyntaxException {
		AwsBasicCredentials credentials = AwsBasicCredentials.create(accessKey, secretKey);
		return S3Client.builder()
			.credentialsProvider(() -> credentials)
			.region(Region.of("auto"))
			.endpointOverride(new URI("https://" + accountId + ".r2.cloudflarestorage.com"))
			.build();
	}

	@Bean
	public S3Presigner r2Presigner() throws URISyntaxException {
		AwsBasicCredentials credentials = AwsBasicCredentials.create(accessKey, secretKey);
		return S3Presigner.builder()
			.credentialsProvider(() -> credentials)
			.region(Region.of("auto"))
			.endpointOverride(new URI("https://" + accountId + ".r2.cloudflarestorage.com"))
			.build();
	}
}
