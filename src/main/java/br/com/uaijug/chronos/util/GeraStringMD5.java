package br.com.uaijug.chronos.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * The Class GeraStringMD5.
 */
public class GeraStringMD5 {
	
	/**
	 * Md5.
	 *
	 * @param senha the senha
	 * @return the string
	 */
	public static String md5(String code) {
	
		String sen = "";
		MessageDigest md = null;
		
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		BigInteger hash = new BigInteger(1, md.digest(code.getBytes()));
		sen = hash.toString(16);
		return sen;
	
	}
	
	public static String getHashByRandomCode(){
		return md5(RandomCode.generatorRandomCode());
	}
}
