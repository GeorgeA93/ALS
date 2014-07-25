package com.george.als.data;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.george.als.entities.food.Food;
import com.george.als.entities.lifeforms.ALifeForm;
import com.george.als.entities.nest.Nest;
import com.george.als.entities.obstacles.Obstacle;
import com.george.als.world.Map;

/**
 * The SaveFile Class is used to save map and bug data to 
 * an open file or a user defined file.
 * It synchs the maps information and attempts to save the files
 * @author Georges Beast
 */
public class SaveFile {
	private Map map;
	private ArrayList<ALifeForm> bugs;
	private ArrayList<Food> food;
	private ArrayList<Obstacle> obstacles;
	private ArrayList<Nest> nests;
	String mapFileName;
	String bugsFileName;
	

	/**
	 * The default constructor
	 * @param map the map to save
	 * @param mapFileName the map file to save to
	 * @param bugsFileName the bug file to save to
	 */
	public SaveFile(Map map, String mapFileName,
			String bugsFileName) {
		this.map = map;
		this.bugs = map.getBugs();
		this.food = map.getFood();
		this.obstacles = map.getObstacles();
		this.nests = map.getNests();
		this.mapFileName = mapFileName;
		this.bugsFileName = bugsFileName;
		
	}
	
	/**
	 * This method is called and first synchs the map and saves it
	 * Then it saves the bug data
	 */
	public void save(){
		syncMapCharArray();
		saveBugsFile();
	}
	
	/**
	 * Synchs the map char array so it can be saved to a file
	 * Then calls the saveMap() method
	 */
	private void syncMapCharArray(){
		char[][] tempMap = map.getMap();
		
		//synch the map data to the temporary array
		for (int y = 0; y < map.getMapHeight(); y++) {
			for (int x = 0; x < map.getMapWidth(); x++) {
					if(syncBug(x, y)){
						tempMap[x][y] = 'B';
					} else if(syncFood(x, y)){
						if(foodAt(x,y).getType().equals("Meat")){
							tempMap[x][y] = 'M';
						} else if(foodAt(x,y).getType().equals("Apple")){
							tempMap[x][y] = 'A';
						} else if(foodAt(x,y).getType().equals("Poison")){
							tempMap[x][y] = 'P';
						}						
					} else if(syncObstacle(x, y)){
						if(obsAt(x,y).getType().equals("Rock")){
							tempMap[x][y] = 'R';
						} else if(obsAt(x,y).getType().equals("Tree")){
							tempMap[x][y] = 'T';
						} else if(obsAt(x,y).getType().equals("Bush")){
							tempMap[x][y] = 'U';
						}	
					} else if(syncNest(x, y)){
						tempMap[x][y] = 'N';
					} else {
						tempMap[x][y] = ' ';
					}
			}
		}				
		
		//save the new data
		saveMapFile(tempMap);
	}
	
	/**
	 * Gets the food at a particular point
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @return the food at the point, or null if no food is found
	 */
	private Food foodAt(int x, int y){
		for(int i = 0; i < food.size(); i++){
			if(food.get(i).getX() == x && food.get(i).getY() == y){
				return food.get(i);				
			} 
		}	
		return null;
	}
	
	/**
	 * Gets the obstacle at a particular point
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @return the obsatcle at the point, or null if no obstacle is found
	 */
	private Obstacle obsAt(int x, int y){
		for(int i = 0; i < obstacles.size(); i++){
			if(obstacles.get(i).getX() == x && obstacles.get(i).getY() == y){
				return obstacles.get(i);						
			} 
		}	
		return null;
	}
	
	
	private boolean syncBug(int x, int y){
		for(int i = 0; i < bugs.size(); i++){
			if(bugs.get(i).getX() == x && bugs.get(i).getY() == y){
				return true;						
			} 
		}	
		return false;
	}
	
	private boolean syncFood(int x, int y){
		for(int i = 0; i < food.size(); i++){
			if(food.get(i).getX() == x && food.get(i).getY() == y){
				return true;						
			} 
		}	
		return false;
	}
	
	private boolean syncNest(int x, int y){
		for(int i = 0; i < nests.size(); i++){
			if(nests.get(i).getX() == x && nests.get(i).getY() == y){
				return true;						
			} 
		}	
		return false;
	}
	
	private boolean syncObstacle(int x, int y){
		for(int i = 0; i < obstacles.size(); i++){
			if(obstacles.get(i).getX() == x && obstacles.get(i).getY() == y){
				return true;						
			} 
		}	
		return false;
	}
	

	private void saveMapFile(char[][] gameMap) {
		try {
			FileWriter saveFile = new FileWriter(mapFileName + ".txt");
			for (int y = 0; y < map.getMapHeight(); y++) {
				for (int x = 0; x < map.getMapWidth(); x++) {
					if (x == map.getMapWidth() - 1) {
						saveFile.write("\n");
					} else {
						saveFile.write(gameMap[x][y]);
					}

				}
			}

			saveFile.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void saveBugsFile() {
		try {
			FileWriter saveFile = new FileWriter(bugsFileName + ".txt");

			for (int i = 0; i < bugs.size(); i++) {
				saveFile.write("\n");
				saveFile.write("Name: " + bugs.get(i).getName() + "\n");
				saveFile.write("Type: " + bugs.get(i).getType() + "\n");
				saveFile.write("X: " + bugs.get(i).getX() + "\n");
				saveFile.write("Y: " + bugs.get(i).getY() + "\n");
				saveFile.write("Energy: " + bugs.get(i).getEnergy());
				saveFile.write("\n");
			}
			saveFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}		
	
}
