package com.project.util;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class Util {

	private static final int IMG_WIDTH = 30;
	private static final int IMG_HEIGHT = 30;
	
	public static String encriptarPassword(String password) {
		return encriptarPassword(password, "SHA1");
	}
	
	public static String encriptarPassword(String password, String method) {
		MessageDigest md = null;
		byte[] buffer, digest;
		String hash = "";

		try {
			buffer = password.getBytes();
			md = MessageDigest.getInstance(method);
			md.update(buffer);
			digest = md.digest();

			for (byte aux : digest) {
				int b = aux & 0xff;
				if (Integer.toHexString(b).length() == 1)
					hash += "0";
				hash += Integer.toHexString(b);
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return hash;
	}
	
	private static BufferedImage resizeImage(BufferedImage originalImage,int type) {
		BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT,type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
		g.dispose();
		return resizedImage;
	}
}
