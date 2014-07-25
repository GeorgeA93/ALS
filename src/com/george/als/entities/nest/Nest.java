package com.george.als.entities.nest;

import java.util.ArrayList;
import java.util.Random;

import com.george.als.entities.lifeforms.ALifeForm;
import com.george.als.entities.lifeforms.Carnivore;
import com.george.als.entities.lifeforms.Herbivorve;
import com.george.als.main.GamePanelFields;
import com.george.als.world.Map;

/**
 * This class holds all the information about the nests on the map
 * The x and y position of the nests
 * What map it is on
 * The bugs that live in the nest
 * @author Georges Beast
 *
 */
public class Nest {
	
	private int x, y;
	private Map map;
	private ArrayList<ALifeForm> bugs = new ArrayList<ALifeForm>();
	private Random random = new Random();
	
	/**
	 * 
	 * @return the x coordinate of the nest
	 */
	public int getX(){
		return this.x;
	}
	
	/**
	 * 
	 * @return the y coordinate of the nest
	 */
	public int getY(){
		return this.y;
	}
	
	/**
	 * 
	 * @return the bugs in the nest
	 */
	public ArrayList<ALifeForm> getBugs(){
		return this.bugs;
	}
	
	/**
	 * Adds a bug to the nest
	 * @param bug the bug to be added
	 */
	public void addBug(ALifeForm bug){
		bugs.add(bug);
	}
	
	/**
	 * Default Constructor
	 * @param x the x coordinate of the nest
	 * @param y the y coordinate of the nest
	 * @param map the map the nest is on
	 */
	public Nest(int x, int y, Map map){
		this.x = x;
		this.y = y;
		this.map = map;
	}
	
	/**
	 * Initializes the bugs in the nest
	 * by setting the x and y values to those of the nests
	 * it then sets the bugs nest to this
	 */
	public void initBugs(){
		char[][] tempMap = map.getMap();
		
		for(int i = 0; i < bugs.size(); i++){
			bugs.get(i).setX(this.x);
			bugs.get(i).setY(this.y);
			bugs.get(i).setHome(this);
			tempMap[x][y] = 'B';
			map.setMap(tempMap);
		}
	}
	
	/**
	 * updates the nest
	 */
	public void update(){
		
		char[][] temp = map.getMap();
		temp[x][y] = 'N';
		map.setMap(temp);
		
		if(bugs.size() != 0){
			for(int i =0; i < bugs.size(); i++){
				if(bugs.get(i).getInNest() == false){
					if(bugs.get(i).getX() == x && bugs.get(i).getY() == y && bugs.get(i).getEnergy() < 50){
						bugs.get(i).setInNest(true);		
					} 
				}
				if(bugs.get(i).getInNest() == true){
					if(bugs.get(i).getEnergy() > 100){
						bugs.get(i).setInNest(false);
						bugs.get(i).setX(x - 1);
						bugs.get(i).setY(y);
						
						
					} 
				}
			}
		} else {
			//if the user has specified they want random bug spawning try and spawn a bug
			//if the nest is empty 
			if(GamePanelFields.spawnRandomBugs){
				if(random.nextInt(2) == 1){
					if(random.nextInt(2) == 0){
						Carnivore bug = new Carnivore("Random", "C", map);
						ArrayList<ALifeForm> bugsTemp = map.getBugs();
						bugsTemp.add(bug);
						map.setBugs(bugsTemp);
						this.addBug(bug);
						this.initBugs();
					}else if(random.nextInt(2) == 1){
						Herbivorve bug = new Herbivorve("Random", "H", map);
						ArrayList<ALifeForm> bugsTemp = map.getBugs();
						bugsTemp.add(bug);
						map.setBugs(bugsTemp);
						this.addBug(bug);
						this.initBugs();
					}
				}
			}			
		}
	}
	
	

}
