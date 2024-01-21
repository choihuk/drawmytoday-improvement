package tipitapi.drawmytodayimprovement.component;

import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.util.StringUtils;

import tipitapi.drawmytodayimprovement.exception.BusinessException;
import tipitapi.drawmytodayimprovement.exception.CommonErrorCode;
import tipitapi.drawmytodayimprovement.utils.SystemEnv;

public class DiaryEncryptor {

	private final String ALGORITHM = "AES";
	private final SecretKeySpec SECRET_KEY;

	public DiaryEncryptor() {
		String key = SystemEnv.get("ENCRYPTOR_SECRET_KEY");
		this.SECRET_KEY = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), ALGORITHM);
	}

	public String encrypt(String plainText) {
		if (!StringUtils.hasText(plainText)) {
			return null;
		}
		byte[] encryptedBytes = null;
		try {
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, SECRET_KEY);
			encryptedBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
		} catch (GeneralSecurityException e) {
			throw new BusinessException(CommonErrorCode.ENCRYPTION_ERROR, e);
		}
		return Base64.getEncoder().encodeToString(encryptedBytes);
	}

	public String decrypt(String encryptedText) {
		if (!StringUtils.hasText(encryptedText)) {
			return null;
		}
		byte[] decryptedBytes = null;
		try {
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, SECRET_KEY);
			byte[] decodedBytes = Base64.getDecoder().decode(encryptedText);
			decryptedBytes = cipher.doFinal(decodedBytes);
		} catch (GeneralSecurityException e) {
			throw new BusinessException(CommonErrorCode.DECRYPTION_ERROR, e);
		}
		return new String(decryptedBytes, StandardCharsets.UTF_8);
	}

}
