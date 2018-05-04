package com.diebold.travellogger.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class SecurityUtil {

	public static String encrypt(String msg) throws Exception {
		String key = "Bar12345Bar12345"; // 128 bit key
		// Create key and cipher
		SecretKeySpec aesKey = new SecretKeySpec(key.getBytes(), "AES");
		Cipher cipher = Cipher.getInstance("AES");
		// encrypt the text
		cipher.init(Cipher.ENCRYPT_MODE, aesKey);
		byte[] encrypted = cipher.doFinal(msg.getBytes());

		StringBuilder sb = new StringBuilder();
		for (byte b : encrypted) {
			sb.append((char) b);
		}

		// the encrypted String
		String enc = sb.toString();
		return enc;
	}

	public static String decrypt(String enc) throws Exception {
		String key = "Bar12345Bar12345"; // 128 bit key
		// Create key and cipher
		SecretKeySpec aesKey = new SecretKeySpec(key.getBytes(), "AES");
		byte[] bb = new byte[enc.length()];
		for (int i = 0; i < enc.length(); i++) {
			bb[i] = (byte) enc.charAt(i);
		}
		Cipher cipher = Cipher.getInstance("AES");

		// decrypt the text
		cipher.init(Cipher.DECRYPT_MODE, aesKey);
		String decrypted = new String(cipher.doFinal(bb));
		return decrypted;
	}
}
