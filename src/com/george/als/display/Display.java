package com.george.als.display;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.george.als.utils.Content;
import com.george.als.world.Map;

/**
 * The Display Class is in charge of drawing the simulation to the screen
 * It extends JPanel, and draws the map, bugs, food, obstacles and nests to this JPanel * 
 * @author Georges Beast
 *
 */
public class Display extends JPanel {

	private static final long serialVersionUID = 1L;

	private Map map;

	/**
	 * Default Constructor for the Display
	 * Sets the JPanels dimensions
	 */
	public Display() {	
		this.setPreferredSize(new Dimension(600, 600));
	}
	
	/**
	 * Sets the map the display panel will draw
	 * @param map the new map to draw
	 */
	public void setMap(Map map){
		this.map = map;
	}

	/**
	 * The display panel paint method
	 * This method draws in this order:
	 * Background, bugs, nests, food, obstacles
	 * @param g the graphics objects
	 * @see Graphics
	 */
	public void paint(Graphics g) {
		// check the map has data
		if(map != null) {		

			// set the width and height to draw the map
			int width = 900;
			int height = 700;
			int cellWidth = width / map.getMapWidth();
			int cellHeight = height / map.getMapHeight();
			int xOffset = 0;
			int yOffset = 50;

			//draw the background grass
			Color grass = new Color(0f, 0.5f, 0f, 1f);
			g.setColor(grass);
			g.fillRect(xOffset, yOffset, width, height);
			
			// draw the bugs
			for (int i = 0; i < map.getBugs().size(); i++) {
				if(!map.getBugs().get(i).getInNest()){
					if (map.getBugs().get(i).getType().equals("H")) {
						g.setColor(Color.BLACK);
						g.drawString(map.getBugs().get(i).getName(), xOffset + (map.getBugs().get(i).getX() * cellWidth), yOffset + (map.getBugs().get(i).getY()) * cellHeight );
						g.drawImage(
								Content.herbivorveImage,
								xOffset + (map.getBugs().get(i).getX() * cellWidth),
								yOffset
										+ (map.getBugs().get(i).getY() * cellHeight),
								cellWidth, cellHeight, null);
					} else if (map.getBugs().get(i).getType().equals("C")) {
						g.setColor(Color.BLACK);
						g.drawString(map.getBugs().get(i).getName(), xOffset + (map.getBugs().get(i).getX() * cellWidth), yOffset + (map.getBugs().get(i).getY() ) * cellHeight );
						g.drawImage(
								Content.carnivorveImage,
								xOffset + (map.getBugs().get(i).getX() * cellWidth),
								yOffset
										+ (map.getBugs().get(i).getY() * cellHeight),
								cellWidth, cellHeight, null);
					} else if (map.getBugs().get(i).getType().equals("S")) {
						g.setColor(Color.BLACK);
						g.drawString(map.getBugs().get(i).getName(), xOffset + (map.getBugs().get(i).getX() * cellWidth), yOffset + (map.getBugs().get(i).getY() ) * cellHeight );
						g.drawImage(
								Content.socialImage,
								xOffset + (map.getBugs().get(i).getX() * cellWidth),
								yOffset
										+ (map.getBugs().get(i).getY() * cellHeight),
								cellWidth, cellHeight, null);
					}
				}			
			}
			
			//draw the nests
			for(int i = 0; i < map.getNests().size(); i ++){
				
				g.drawImage(Content.caveImage,
						xOffset + (map.getNests().get(i).getX() * cellWidth),
						yOffset
								+ (map.getNests().get(i).getY() * cellHeight),
						cellWidth * 2, cellHeight * 2, null);
			}

			// draw the food
			//draw the apples
			for (int i = 0; i < map.getFood().size(); i++) {
				if (map.getFood().get(i).getType().equals("Apple")) {
					g.drawImage(
							Content.appleImage,
							xOffset + (map.getFood().get(i).getX() * cellWidth),
							yOffset
									+ (map.getFood().get(i).getY() * cellHeight),
							cellWidth, cellHeight, null);
				}
			}
			
			//draw the meat
			for (int i = 0; i < map.getFood().size(); i++) {
				if (map.getFood().get(i).getType().equals("Meat")) {
					g.drawImage(
							Content.meatImage,
							xOffset + (map.getFood().get(i).getX() * cellWidth),
							yOffset
									+ (map.getFood().get(i).getY() * cellHeight),
							cellWidth, cellHeight, null);
				}
			}
			
			//draw the poison
			for (int i = 0; i < map.getFood().size(); i++) {
				if (map.getFood().get(i).getType().equals("Poison")) {
					g.drawImage(
							Content.poisonImage,
							xOffset + (map.getFood().get(i).getX() * cellWidth),
							yOffset
									+ (map.getFood().get(i).getY() * cellHeight),
							cellWidth, cellHeight, null);
				}
			}								

			// draw the obstacles
			for (int i = 0; i < map.getObstacles().size(); i++) {
				if (map.getObstacles().get(i).getType().equals("Rock")) {
					g.drawImage(
							Content.rockImage,
							xOffset
									+ (map.getObstacles().get(i).getX() * cellWidth),
							yOffset
									+ (map.getObstacles().get(i).getY() * cellHeight),
							cellWidth, cellHeight, null);
				}
				if (map.getObstacles().get(i).getType().equals("Tree")) {
					g.drawImage(
							Content.treeImage,
							xOffset
									+ (map.getObstacles().get(i).getX() * cellWidth),
							yOffset
									+ (map.getObstacles().get(i).getY() * cellHeight),
							cellWidth, cellHeight, null);
				}
				if (map.getObstacles().get(i).getType().equals("Bush")) {
					g.drawImage(
							Content.bushImage,
							xOffset
									+ (map.getObstacles().get(i).getX() * cellWidth),
							yOffset
									+ (map.getObstacles().get(i).getY() * cellHeight),
							cellWidth, cellHeight, null);
				}
			}
		}
	}

}
