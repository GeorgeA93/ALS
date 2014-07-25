package com.george.als.world;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.george.als.data.ReadFile;
import com.george.als.entities.lifeforms.ALifeForm;
import com.george.als.utils.UserInput;

/**
 * This class holds the map and is in charge of loading old maps and creating
 * new ones
 * 
 * @author Georges Beast
 * 
 */
public class World {

	private Map map;

	/**
	 * 
	 * @return the current map
	 */
	public Map getMap() {
		return this.map;
	}

	/**
	 * Sets the map to a new map
	 * 
	 * @param map
	 */
	public void setMap(Map map) {
		this.map = map;
	}

	/**
	 * Default Constructor for creating a user defined world
	 */
	public World() {
		createMap();
	}

	/**
	 * Constructor used for reseting the world
	 * 
	 * @param reset
	 */
	public World(int reset) {
		map = new Map();
	}

	/**
	 * Constructor used to load a world from a file
	 * 
	 * @param mapFile
	 *            the map file
	 * @param bugFile
	 *            the bug file
	 */
	public World(String mapFile, String bugFile) {
		ReadFile fileReader = new ReadFile();
		map = new Map();
		char[][] temp = fileReader.loadMap(mapFile + ".txt");
		if(temp != null){
			this.map.setMap(temp);
			this.map.setMapWidth(fileReader.getMapX(mapFile + ".txt"));
			this.map.setMapHeight(fileReader.getMapY(mapFile + ".txt"));
			ArrayList<ALifeForm> bugs = fileReader.loadBugs(bugFile + ".txt", map);
			if(bugs != null){
				this.map.setBugs(bugs);
			}		
			this.map.populateFood();
			this.map.populateObstacles();
			this.map.populateNests();
		} else {
			JOptionPane.showMessageDialog(null, "File details inccorect, please create a new config");
			createMap();
		}
		
	}

	/**
	 * Creates a new map
	 * 
	 * @see UserInput
	 * @see UserInput
	 */
	public void createMap() {
		int x = UserInput.getMapX();
		int y = UserInput.getMapY();
		map = new Map(x, y);
	}

	/**
	 * Updates the current Map
	 */
	public void update() {
		map.update();
	}

}
