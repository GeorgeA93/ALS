package com.george.als.utils;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

import com.george.als.main.GamePanel;
import com.george.als.world.Map;

/**
 * This class holds all of the images and icons that are used when drawing the simulation
 * It also contains the generateImage and generateImageIcon functions that load images from memory
 * @author Georges Beast
 *
 */

public class Content {

	// IMAGE PATH
	private static final String IMAGESFOLDER = "com/george/als/icons/";

	// The images
	public static Image herbivorveImage;
	public static Image carnivorveImage;
	public static Image socialImage;

	public static Image appleImage;
	public static Image meatImage;
	public static Image poisonImage;
	public static Image rockImage;
	public static Image treeImage;
	public static Image bushImage;
	public static Image caveImage;
	
	// The image icons
	public static ImageIcon run;
	public static ImageIcon stop;
	public static ImageIcon pause;
	public static ImageIcon reset;
	
	
	/**
	 * Loads all the images by making calls to
	 * GenerateImage and generateImageIcon
	 */
	public static void load(){
		herbivorveImage = generateImage(IMAGESFOLDER
				+ "bug.png");
		carnivorveImage = generateImage(IMAGESFOLDER
				+ "bug1.png");
		socialImage = generateImage(IMAGESFOLDER + "bug2.png");

		appleImage = generateImage(IMAGESFOLDER + "apple.png");
		meatImage = generateImage(IMAGESFOLDER
				+ "meatImage.png");
		poisonImage = generateImage(IMAGESFOLDER
				+ "poisonImage.png");
		rockImage = generateImage(IMAGESFOLDER + "rock.png");
		treeImage = generateImage(IMAGESFOLDER + "tree.png");
		bushImage = generateImage(IMAGESFOLDER + "bush.png");
		caveImage = generateImage(IMAGESFOLDER + "nest.png");
		
		run = generatImageIcon(IMAGESFOLDER + "run.png");
		stop = generatImageIcon(IMAGESFOLDER + "stop.png");
		pause = generatImageIcon(IMAGESFOLDER + "pause.png");
		reset = generatImageIcon(IMAGESFOLDER + "reset.png");
	}

	/**
	 * Loads an image 
	 * @param imageFileName the image path to load
	 * @return the image
	 */
	private static Image generateImage(String imageFileName) {
		URL imageURL = Map.class.getClassLoader().getResource(imageFileName);
		return (new ImageIcon(imageURL).getImage());
	}

	/**
	 * Loads an image icon
	 * @param iconFileName the icon path to load
	 * @return the icon
	 */
	private static ImageIcon generatImageIcon(String iconFileName) {
		URL imageURL = GamePanel.class.getClassLoader().getResource(
				iconFileName);			
		Image img = new ImageIcon(imageURL).getImage();
		Image newImage = img.getScaledInstance(68, 68, java.awt.Image.SCALE_SMOOTH);
		return (new ImageIcon(newImage));
	}

}
