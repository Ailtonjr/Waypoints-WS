package com.waypoints.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CriptografiaUtil {

	private static final String CHAVE1 = "K<SA(12mkS-IU!@*C";
	private static final String CHAVE2 = "A@c_D)&*!@LASqoi*";

	public static String encrypt(String dataToEncrypt) {
		
		String encrypted;
		try {
			encrypted = MD5(CHAVE1 + dataToEncrypt + CHAVE2);
			encrypted = SHA1(dataToEncrypt + encrypted + MD5(CHAVE2 + SHA1(encrypted)));
			String chaveExtra = MD5(MD5(encrypted + CHAVE1 + CHAVE2) + dataToEncrypt); 
			encrypted = MD5(chaveExtra + SHA1(encrypted) + CHAVE1);
			return MD5(chaveExtra + encrypted);
		} catch (Exception e) {
			
		}
		return null;
	}

	private static String MD5(final String input) throws NoSuchAlgorithmException {
		final MessageDigest md = MessageDigest.getInstance("MD5");
		final byte[] messageDigest = md.digest(input.getBytes());
		final BigInteger number = new BigInteger(1, messageDigest);
		return String.format("%032x", number);
	}

	private static String SHA1(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest md;
		md = MessageDigest.getInstance("SHA-1");
		byte[] sha1hash = new byte[40];
		md.update(text.getBytes("iso-8859-1"), 0, text.length());
		sha1hash = md.digest();
		return convertToHex(sha1hash);
	}

	private static String convertToHex(byte[] data) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < data.length; i++) {
			int halfbyte = (data[i] >>> 4) & 0x0F;
			int two_halfs = 0;
			do {
				if ((0 <= halfbyte) && (halfbyte <= 9))
					buffer.append((char) ('0' + halfbyte));
				else
					buffer.append((char) ('a' + (halfbyte - 10)));
				halfbyte = data[i] & 0x0F;
			} while (two_halfs++ < 1);
		}
		return buffer.toString();
	}

	public static void main(String[] args) {
		System.out.println(encrypt("romulo"));
	}
	
}
