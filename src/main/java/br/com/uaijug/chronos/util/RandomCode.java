package br.com.uaijug.chronos.util;

import java.util.UUID;

public class RandomCode {

	public static String generatorRandomCode() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().substring(0, 20);
	}
	
	public String generationRandomCode() {
		return generatorRandomCode();
	}
}
