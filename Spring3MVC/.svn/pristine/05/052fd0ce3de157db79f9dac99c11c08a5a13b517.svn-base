package com.test;

import java.io.File;

public class TestingIMG {
	public static void main(String[] args) {
		File dir = new File(
				"C:\\Users\\Francisco\\Desktop\\PFC\\Spring3MVC\\src\\main\\webapp\\images\\storage\\exercises\\Triceps");
		File[] imgs = dir.listFiles();
		if (imgs == null)
			System.out.println("No hay ficheros en el directorio especificado");

		for (int x = 0; x < imgs.length; x++) {
			File img = imgs[x];
			String name = img.getName();
			String modi = name.replace(" ", "_");
			String path = img.getPath();
			File f2 = new File(path.substring(0, path.length() - name.length()) + modi);
			img.renameTo(f2);
			System.out.println(img.getName());
		}
	}
}
