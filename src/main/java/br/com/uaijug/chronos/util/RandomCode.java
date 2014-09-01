/*
 * 
 */
package br.com.uaijug.chronos.util;

import java.util.UUID;

// TODO: Auto-generated Javadoc
/**
 * The Class RandomCode.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
public class RandomCode {

	/**
	 * Generator random code.
	 *
	 * @return the string
	 */
	public static String generatorRandomCode() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().substring(0, 20);
	}
	
	/**
	 * Generation random code.
	 *
	 * @return the string
	 */
	public String generationRandomCode() {
		return generatorRandomCode();
	}
}
