package com.george.als.world;

import java.util.ArrayList;
import java.util.Random;

import com.george.als.entities.food.Apple;
import com.george.als.entities.food.Food;
import com.george.als.entities.food.Meat;
import com.george.als.entities.food.Poison;
import com.george.als.entities.lifeforms.ALifeForm;
import com.george.als.entities.nest.Nest;
import com.george.als.entities.obstacles.Bush;
import com.george.als.entities.obstacles.Obstacle;
import com.george.als.entities.obstacles.Rock;
import com.george.als.entities.obstacles.Tree;
import com.george.als.utils.UserInput;

/**
 * This calls holds all of the information about the current map
 * It contains all the bugs, food, obctacles and nests on the map
 * and is used to update each one.
 * It also contains the methods for creating random maps for new configurations
 * @author Georges Beast
 *
 */
public class Map {

	private char[][] gameMap;
	private int mapWidth, mapHeight;
	private ArrayList<Food> food;
	private ArrayList<ALifeForm> bugs;
	private ArrayList<Obstacle> obstacles;
	private ArrayList<Nest> nests;
	private int maxNests = 100;

	private Random random = new Random();

	private int maxBugs;

	/**
	 * 
	 * @return the width of the map
	 */
	public int getMapWidth() {
		return this.mapWidth;
	}

	/**
	 * 
	 * @return the height of the map
	 */
	public int getMapHeight() {
		return this.mapHeight;
	}

	/**
	 * 
	 * @return the bugs on the map
	 */
	public ArrayList<ALifeForm> getBugs() {
		return this.bugs;
	}
	

	/**
	 * 
	 * @return the food on the map
	 */
	public ArrayList<Food> getFood() {
		return this.food;
	}

	/**
	 * 
	 * @return the obstacles on the map
	 */
	public ArrayList<Obstacle> getObstacles() {
		return this.obstacles;
	}

	/**
	 * 
	 * @return the nests on the map
	 */
	public ArrayList<Nest> getNests() {
		return this.nests;
	}

	/** 
	 * 
	 * @return the map as a char array
	 */
	public char[][] getMap() {
		return this.gameMap;
	}

	/**
	 * Sets the char array of the map
	 * @param gameMap the new char array
	 */
	public void setMap(char[][] gameMap) {
		this.gameMap = gameMap;
	}

	/**
	 * Sets the width of the map
	 * @param mapWidth
	 */
	public void setMapWidth(int mapWidth) {
		this.mapWidth = mapWidth;
	}

	/**
	 * Sets the height of the map
	 * @param mapHeight
	 */
	public void setMapHeight(int mapHeight) {
		this.mapHeight = mapHeight;
	}

	/**
	 * Sets the bugs on the map
	 * @param bugs
	 */
	public void setBugs(ArrayList<ALifeForm> bugs) {
		this.bugs = bugs;
	}
	
	/**
	 * Sets the food on the map
	 * @param food
	 */
	public void setFood(ArrayList<Food> food){
		this.food = food;
	}
	
	/**
	 * Sets the obstacles on the map
	 * @param obstacles
	 */
	public void setObstacles(ArrayList<Obstacle> obstacles){
		this.obstacles = obstacles;
	}
	
	/**
	 * Sets the nest on the map
	 * @param nests
	 */
	public void setNests(ArrayList<Nest> nests){
		this.nests = nests;
	}
	

	/**
	 * Default Class Constructor
	 * Used when reseting the map
	 * Or when loading a map from a file
	 */
	public Map() {
		this.mapWidth = 0;
		this.mapHeight = 0;
		gameMap = new char[mapWidth][mapHeight];
		initMap();
		maxBugs = 0;
		bugs = new ArrayList<ALifeForm>();
		food = new ArrayList<Food>();
		obstacles = new ArrayList<Obstacle>();
		nests = new ArrayList<Nest>();
	}

	/**
	 * Class Constructor for a new USer defined configuration
	 * @param x the map width
	 * @param y the map height
	 */
	public Map(int x, int y) {
		this.mapWidth = x;
		this.mapHeight = y;
		gameMap = new char[mapWidth][mapHeight];
		initMap();
		maxBugs = UserInput.getMaxBugs();
		bugs = UserInput.getBugs(maxBugs, this);
		food = new ArrayList<Food>();
		obstacles = new ArrayList<Obstacle>();
		nests = new ArrayList<Nest>();
		createMap();
		populateFood();
		populateObstacles();
		populateNests();
	}
	
	/**
	 * Sets the map char array so each value is set to ' '
	 */
	private void initMap(){
		for(int y = 0; y <mapHeight; y ++){
			for(int x = 0; x < mapWidth; x++){
				gameMap[x][y] = ' ';
			}
		}
	}


	/**
	 * Adds the food from the char array to 
	 * the Food Array List
	 */
	public void populateFood() {
		for (int y = 0; y < mapHeight; y++) {
			for (int x = 0; x < mapWidth; x++) {
				if (gameMap[x][y] == 'A') {
					Food apple = new Apple(x, y, random.nextInt(10), "Apple",
							this);
					food.add(apple);
				} else if (gameMap[x][y] == 'M') {
					Food meat = new Meat(x, y, random.nextInt(10), "Meat", this);
					food.add(meat);
				} else if (gameMap[x][y] == 'P') {
					Food poison = new Poison(x, y, random.nextInt(10) - 11,
							"Poison", this);
					food.add(poison);
				}
			}
		}
	}


	/**
	 * Adds the obstacles from the char array to
	 * the Obstacles ArrayList
	 */
	public void populateObstacles() {
		for (int y = 0; y < mapHeight; y++) {
			for (int x = 0; x < mapWidth; x++) {
				if (gameMap[x][y] == 'R') {
					Obstacle rock = new Rock(x, y, "Rock");
					obstacles.add(rock);
				}
				if (gameMap[x][y] == 'T') {
					Obstacle tree = new Tree(x, y, "Tree");
					obstacles.add(tree);
				}
				if (gameMap[x][y] == 'U') {
					Obstacle bush = new Bush(x, y, "Bush");
					obstacles.add(bush);
				}
			}
		}
	}


	/**
	 * Adds the nests from the char array to
	 * the Nests ArrayList
	 */
	public void populateNests() {
		for (int y = 0; y < mapHeight; y++) {
			for (int x = 0; x < mapWidth; x++) {
				if (gameMap[x][y] == 'N') {
					nests.add(new Nest(x, y, this));			
				}
			}
		}
		
		for(int i = 0; i < nests.size(); i ++){
			for(int j = 0; j < bugs.size(); j ++){
				if(bugs.get(j).getHome() == null){
					if(nests.get(i).getBugs().size() < 2){
						nests.get(i).addBug(bugs.get(j));
						nests.get(i).initBugs();
					}					
				}
			}
		}
	}

	/**
	 * Creates a new Random map
	 * @see generateMapInfo(int x, int y)
	 */
	private void createMap() {
		for (int y = 0; y < mapHeight; y++) {
			for (int x = 0; x < mapWidth; x++) {
				char mapInfo = generateMapInfo(x, y);	
				if(mapInfo == 'N'){
					gameMap[x][y] = mapInfo;	
					gameMap[x + 1][y] = 'Z';	
					gameMap[x][y + 1] = 'Z';	
					gameMap[x + 1][y +  1] = 'Z';	
				} else {
					gameMap[x][y] = mapInfo;	
				}						

			}
		}
	}

	/**
	 * Generates random numbers to return chars
	 * A = Apple
	 * M = Meat
	 * P = Poison 
	 * T = Tree
	 * U = Bush
	 * N = Nest
	 * @param x the x coordinate of where to place the char
	 * @param y the y coordinate of where to place the char
	 * @return the char
	 */
	private char generateMapInfo(int x, int y) {
		if(gameMap[x][y] == ' '){
			int result = random.nextInt(30);
			if (result == 1) {
				return 'A';
			}
			if (result == 2) {
				return 'M';
			}
			if (result == 3) {
				return 'P';
			}
			if (result == 4) {
				return 'R';
			}
			if (result == 5) {
				return 'T';
			}
			if (result == 6) {
				return 'U';
			}
			if (random.nextInt(150) == 1) {
				if(x + 1 < mapWidth - 1 && x - 1 > 0 && y + 1 < mapHeight - 1 && y - 1 > 0){
					if(gameMap[x + 1][y] == ' ' || gameMap[x][y + 1] == ' ' || gameMap[x + 1][y + 1] == ' '){
						if (maxNests != 0) {
							maxNests -= 1;
							return 'N';
						}
					}
				}				
			}
		}
		return ' ';
	}


	/**
	 * Updates the bugs, food and nests on the map
	 */
	public void update() {
		// update the bugs
		for (int i = 0; i < bugs.size(); i++) {
			bugs.get(i).update();
		}

		// update the food
		for (int i = 0; i < food.size(); i++) {
			food.get(i).update();
		}
		
		//update the nests
		for(int i = 0; i < nests.size(); i ++){
			nests.get(i).update();
		}
	}

}
