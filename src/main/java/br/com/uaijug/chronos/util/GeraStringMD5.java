/*
 * 
 */
package br.com.uaijug.chronos.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


// TODO: Auto-generated Javadoc
/**
 * The Class GeraStringMD5.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
public class GeraStringMD5 {
	
	/**
	 * Md5.
	 *
	 * @param code the code
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
	
	/**
	 * Gets the hash by random code.
	 *
	 * @return the hash by random code
	 */
	public static String getHashByRandomCode(){
		return md5(RandomCode.generatorRandomCode());
	}
}
