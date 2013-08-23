package com.test;

import java.io.File;

public class ToLowerCase {

	public static void main(String[] args) {
		File dir = new File(
				"C:\\Users\\Francisco\\Desktop\\ejercicios\\triceps");
		File[] imgs = dir.listFiles();
		if (imgs == null)
			System.out.println("No hay ficheros en el directorio especificado");

		for (int x = 0; x < imgs.length; x++) {
			File img = imgs[x];
			String name = img.getName().substring(0, img.getName().length() - 4);
			name = name.toLowerCase();
			String primeraLetra = name.substring(0,1).toUpperCase();
			name = primeraLetra.concat(name.substring(1,name.length()));
			String path = img.getPath();
			path = path.substring(0, path.length() - (name.length() + 4));
			System.out.println(name+".png");
			File f2 = new File(path + name + ".png");
			img.renameTo(f2);
		}
	}
}
