package com.test;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TestingIMGResize {

	private static final int IMG_WIDTH = 30;
	private static final int IMG_HEIGHT = 30;

	public static void main(String[] args) {
		try {
			BufferedImage originalImage = ImageIO.read(new File("C:\\Users\\Francisco\\Desktop\\PFC\\Spring3MVC\\src\\main\\webapp\\images\\storage\\exercises\\Abdominales\\00f7a4dc6cd295b88839967e44bed9d6e3d394c1.png"));
			int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();

			BufferedImage resizeImageJpg = resizeImage(originalImage, type);
			ImageIO.write(resizeImageJpg,"jpg",	new File("C:\\Users\\Francisco\\Desktop\\PFC\\Spring3MVC\\src\\main\\webapp\\images\\storage\\exercises\\Abdominales\\pacoli_jpg.jpg"));

			BufferedImage resizeImagePng = resizeImage(originalImage, type);
			ImageIO.write(resizeImagePng,"png",new File("C:\\Users\\Francisco\\Desktop\\PFC\\Spring3MVC\\src\\main\\webapp\\images\\storage\\exercises\\Abdominales\\pacoli_png.jpg"));

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	private static BufferedImage resizeImage(BufferedImage originalImage,int type) {
		BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT,type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
		g.dispose();
		return resizedImage;
	}
}
