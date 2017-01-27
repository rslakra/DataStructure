import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * This class implements the PBKDF2 in pure java.
 * 
 * https://www.ietf.org/rfc/rfc2898.txt
 * 
 * @author Rohtash Singh Lakra (rohtash.singh@gmail.com)
 * @date 09/21/2016 04:00:03 PM
 *
 */
public final class PBKDF2Java {
	
	/* The secret keys algorithm */
	private static final String PBKDF2_WITH_HMAC_SHA512 = "PBKDF2WithHmacSHA512";
	/* Secure Random Algorithm */
	private static final String SHA1PRNG = "SHA1PRNG";
	/* Iterations */
	private static final int ITERATIONS = 1000;
	/* Key Length */
	private static final int KEY_LENGTH = 16;
	
	/**
	 * 
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	private static byte[] getSalt(String algorithm) throws NoSuchAlgorithmException {
		SecureRandom secureRandom = SecureRandom.getInstance(algorithm);
		byte[] salt = new byte[16];
		secureRandom.nextBytes(salt);
		return salt;
	}
	
	/**
	 * 
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	private static byte[] getSalt() throws NoSuchAlgorithmException {
		return getSalt(SHA1PRNG);
	}
	
	/**
	 * Returns the hex-string for the specified <code>dataBytes</code> bytes.
	 * 
	 * @param dataBytes
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	private static String toHex(byte[] dataBytes) throws NoSuchAlgorithmException {
		BigInteger bigInteger = new BigInteger(1, dataBytes);
		String hexString = bigInteger.toString(16);
		int paddingLength = (dataBytes.length * 2) - hexString.length();
		if(paddingLength > 0) {
			hexString = String.format("%0" + paddingLength + "d", 0) + hexString;
		}
		
		return hexString;
	}
	
	/**
	 * Returns the bytes for the specified hex-string <code>hexString</code>.
	 * 
	 * @param hexString
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	private static byte[] fromHex(String hexString) throws NoSuchAlgorithmException {
		byte[] hexBytes = new byte[hexString.length() / 2];
		for(int i = 0; i < hexBytes.length; i++) {
			hexBytes[i] = (byte) Integer.parseInt(hexString.substring(2 * i, 2 * i + 2), 16);
		}
		
		return hexBytes;
	}
	
	/**
	 * 
	 * @param password
	 * @param salt
	 * @param iterations
	 * @param keyLength
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	private static byte[] pbkdf2(char[] password, byte[] salt, int iterations, int keyLength) throws NoSuchAlgorithmException {
		try {
			PBEKeySpec keySpec = new PBEKeySpec(password, salt, iterations, keyLength * 8);
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(PBKDF2_WITH_HMAC_SHA512);
			return keyFactory.generateSecret(keySpec).getEncoded();
		} catch(InvalidKeySpecException ex) {
			throw new IllegalStateException("Invalid SecretKeyFactory", ex);
		}
	}
	
	/**
	 * 
	 * @param password
	 * @param keyLength
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String pbkdf2String(String password, int keyLength) throws NoSuchAlgorithmException {
		String pbkdf2String = null;
		char[] passChars = password.toCharArray();
		byte[] salt = getSalt();
		byte[] hashBytes = pbkdf2(passChars, salt, ITERATIONS, keyLength);
		pbkdf2String = (ITERATIONS + ":" + toHex(salt) + ":" + toHex(hashBytes));
		
		return pbkdf2String;
	}
	
	/**
	 * 
	 * @param originalPassword
	 * @param storedPassword
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	public static boolean validatePassword(String originalPassword, String storedPassword) throws NoSuchAlgorithmException, InvalidKeySpecException {
		String[] parts = storedPassword.split(":");
		int iterations = Integer.parseInt(parts[0]);
		byte[] salt = fromHex(parts[1]);
		byte[] hash = fromHex(parts[2]);
		byte[] pbkdf2Hash = pbkdf2(originalPassword.toCharArray(), salt, iterations, hash.length);
		
		int difference = hash.length ^ pbkdf2Hash.length;
		for(int i = 0; i < hash.length && i < pbkdf2Hash.length; i++) {
			difference |= hash[i] ^ pbkdf2Hash[i];
		}
		
		return (difference == 0);
	}
	
	/**
	 * authenticate
	 * 
	 * @param args
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {
		String originalPassword = "password";
		String pbkdf2String = pbkdf2String(originalPassword, KEY_LENGTH);
		System.out.println(pbkdf2String);
		
		boolean matched = validatePassword(originalPassword, pbkdf2String);
		System.out.println(matched);
		
		matched = validatePassword("password1", pbkdf2String);
		System.out.println(matched);
	}
	
}