package com.george.als.entities.food;

import java.util.Random;

import com.george.als.entities.obstacles.Obstacle;
import com.george.als.world.Map;

/**
 * The base class for all the food on the map
 * It also updates the food, and is in charge of regenerating food
 * It holds the x and y positions of the food
 * The type of food
 * The amount of energy the food gives to the bug
 * And the map the food is on
 * @author Georges Beast
 */
public abstract class Food {

	private int x,  y;
	private int energyRegen;
	private String type;
	private Map map;
	private Random random = new Random();

	/**
	 * 
	 * @return the x coordinate of the food
	 */
	public int getX(){
		return this.x;
	}
	
	/**
	 * 
	 * @return the y coordinate of the food
	 */
	public int getY(){
		return this.y;
	}
	
	/**
	 * 
	 * @return the energy regen amount of the food
	 */
	public int getEnergyRegen(){
		return this.energyRegen;
	}
	
	/**
	 * 
	 * @return the type of food
	 */
	public String getType(){
		return this.type;
	}
	
	/**
	 * Default constructor for food
	 * @param x the x coordinate of the food
	 * @param y the y coordinate of the food
	 * @param energyRegen the energy regen amount of the food
	 * @param type the type of food
	 * @param map the map the food belongs to
	 */
	public Food(int x, int y, int energyRegen, String type, Map map){
		this.x = x;
		this.y = y;
		this.energyRegen = energyRegen;
		this.type = type;
		this.map = map;
	}
	
	/**
	 * Updates the food
	 * There is a 1 in 300 chance that an apple will spawn next to a tree
	 * It checks if the space next to the tree is clear 
	 * Then it adds a new apple to the maps food ArrayList
	 */
	public void update(){
		char[][] gameMap = map.getMap();
		if(random.nextInt(50) == 1){
			for(int i = 0; i < map.getObstacles().size(); i ++){
				Obstacle obstacle = map.getObstacles().get(i);
				if(obstacle.getType().equals("Tree")){
					if(random.nextInt(100) == 1){
						//check above the tree
						if(obstacle.getY() > 0){
							if(gameMap[obstacle.getX()][obstacle.getY() - 1] == ' '){
								Apple apple = new Apple(obstacle.getX(), obstacle.getY() - 1, random.nextInt(10), "Apple",map);
								map.getFood().add(apple);
								gameMap[obstacle.getX()][obstacle.getY() - 1] = 'A';
							}
						}
						//check below the tree
						if(obstacle.getY() < map.getMapHeight() - 1){
							if(gameMap[obstacle.getX()][obstacle.getY() + 1] == ' '){
								Apple apple = new Apple(obstacle.getX(), obstacle.getY() + 1, random.nextInt(10), "Apple",map);
								map.getFood().add(apple);
								gameMap[obstacle.getX()][obstacle.getY() + 1] = 'A';
							}
						}									
						//check to the left of the tree
						if(obstacle.getX() > 0){
							if(gameMap[obstacle.getX() - 1][obstacle.getY()] == ' ' ){
								Apple apple = new Apple(obstacle.getX()  - 1, obstacle.getY(), random.nextInt(10), "Apple",map);
								map.getFood().add(apple);
								gameMap[obstacle.getX() - 1][obstacle.getY()] = 'A';
							}
						}						
						//check to the right of the tree
						if( obstacle.getX() < map.getMapWidth() - 1){
							if(gameMap[obstacle.getX() + 1][obstacle.getY()] == ' '){
								Apple apple = new Apple(obstacle.getX() + 1, obstacle.getY(), random.nextInt(10), "Apple",map);
								map.getFood().add(apple);
								gameMap[obstacle.getX() + 1][obstacle.getY()] = 'A';
							}
						}
						
					}
				}
			}
		}
		
	}
	
}
