package my.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AesUtil
{
	public static String keyPadding(String key)
	{
		int len = key.length();
		if(len == 16) 
			return key;
		else if(len >16) 
			return key.substring(0,16);
		else
		{
			String s = key + "0000000000000000";
			return key.substring(0,16);	
		}
	}
	public static String encrypt(String plain, String key) throws Exception
	{
		key = keyPadding(key);
		
	    byte[] b = key.getBytes("US-ASCII");  
	    SecretKeySpec sks = new SecretKeySpec(b, "AES");  
	      
	    Cipher c = Cipher.getInstance("AES/ECB/PKCS5Padding");  
	    c.init(Cipher.ENCRYPT_MODE, sks);  
	      
	    byte [] input = plain.getBytes("UTF-8");
	    byte [] output = null;
	    output = c.doFinal(input);
	    
	    //output = c.update(input);
//	    for(int i=0; i<input.length; i+=8)
//	    {
//	    	 output = c.update(input, i, 16);
//	    }	     
	    
	    return HexUtil.to(output);	
	    
	}
	
	public static String decrypt(String cipherText, String key) throws Exception
	{
		key = keyPadding(key);
		
	    byte[] keyBytes = key.getBytes();  
	    SecretKeySpec sks = new SecretKeySpec(keyBytes, "AES");  
	      
	    Cipher c = Cipher.getInstance("AES/ECB/PKCS5Padding");  
	    c.init(Cipher.DECRYPT_MODE, sks);  
	      
	    byte[] data = HexUtil.from(cipherText);
	    byte [] output = c.doFinal(data); //c.update(data);  
	      
	    return new String(output, "UTF-8");
	    
	}

	
	public static void main(String[] args)
	{
		String haha = "阿发你好 { abcfhrhyry }";
		String key = "2012389011223344";
		
		try
		{
			String cipherText = encrypt(haha, key);
			System.out.println("cipherText: " + cipherText);
			
			String plainText = decrypt(cipherText, key);
			System.out.println("plainText: " + plainText);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
	


}
