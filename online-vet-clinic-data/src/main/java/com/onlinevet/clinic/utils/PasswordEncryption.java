package com.onlinevet.clinic.utils;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PasswordEncryption {

	public static String encrypt(String password) throws NoSuchAlgorithmException {

		final MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		messageDigest.reset();
		messageDigest.update(password.getBytes(Charset.forName("UTF_8")));
		final byte[] resultByte = messageDigest.digest();

		return Base64.getEncoder().encodeToString(resultByte);

	}
}