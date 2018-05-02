package com.rkouchoo.util;

import java.awt.Image;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.rkouchoo.fm.FileManager;

public class ImageLoader {

	static public ArrayList<Image> loadIconImages(FileManager fileManager) throws Exception {
		URL urlBig = fileManager.getClass().getResource("fm-icon-32x32.png");
		URL urlSmall = fileManager.getClass().getResource("fm-icon-16x16.png");
		ArrayList<Image> images = new ArrayList<Image>();
		images.add(ImageIO.read(urlBig));
		images.add(ImageIO.read(urlSmall));
		return images;
	}

}
