package com.george.als.simulation;

import com.george.als.world.World;

/**
 * This class is in charge of managing the world
 * It creates or loads worlds
 * The simulation is in charge of updating thw current world
 * @author Georges Beast
 *
 */
public class Simulation {
	
	private World world;
	
	/**
	 * @return the current World
	 */
	public World getWorld(){
		return this.world;
	}
	
	
	/**
	 * Class Constructor that is used for reseting the world
	 * @param reset
	 */
	public Simulation(int reset){
		world = new World(1);
	}
	
	/**
	 * Class Constructor for a new World
	 */
	public Simulation(){
		world = new World();
	}
	
	/**
	 * Class Constructor for loading an old world
	 * @param mapFile   the map file to load
	 * @param bugFile	the bug file to load
	 */
	public Simulation(String mapFile, String bugFile){
		world = new World(mapFile, bugFile);
	}
	
	/**
	 * Updates the world
	 */
	public void update(){
		world.update();
	}

}
