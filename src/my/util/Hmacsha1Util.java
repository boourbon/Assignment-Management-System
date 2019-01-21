package my.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class Hmacsha1Util
{
	public static byte[] encrypt(byte[] data, String key)
	{
		String HMAC_SHA1_ALGORITHM = "HmacSHA1";
		try
		{
			// get an hmac_sha1 key from the raw key bytes
			SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(),
					HMAC_SHA1_ALGORITHM);

			// get an hmac_sha1 Mac instance and initialize with the signing key
			Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
			mac.init(signingKey);

			// compute the hmac on input data bytes
			byte[] rawHmac = mac.doFinal(data);	
			return rawHmac;
		} 
		catch (Exception e)
		{
		}
		return null;
	}
	
	public  static String encrypt(String text, String key)
	{
		try
		{
			byte[] data = encrypt(text.getBytes("UTF-8"), key);
			return HexUtil.to(data);
		} catch (UnsupportedEncodingException e)
		{
			return "";
		}
		
	}
}
