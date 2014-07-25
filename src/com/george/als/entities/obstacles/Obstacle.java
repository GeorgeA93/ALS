package com.george.als.entities.obstacles;

/**
 * This class is the base class for an obstacle
 * It holds all the information about obstacles on the map
 * The x and y position of the obstacle
 * The type of obstacle
 * @author Georges Beast
 *
 */
public abstract class Obstacle {
	
	private int x, y;
	private String type;
	
	/**
	 * 
	 * @return the x coordinate of the obstacle
	 */
	public int getX(){
		return this.x;
	}
	
	/**
	 * 
	 * @return the y coordinate of the obstacle
	 */
	public int getY(){
		return this.y;
	}
	
	/**
	 * 
	 * @return the type of obstacle
	 */
	public String getType(){
		return this.type;
	}
	
	/**
	 * The default constructor for an obstacle
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @param type the type of obstacle
	 */
	public Obstacle(int x, int y, String type){
		this.x = x;
		this.y = y;
		this.type = type;
	}

}
