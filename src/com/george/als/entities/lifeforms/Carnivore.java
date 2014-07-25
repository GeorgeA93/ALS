package com.george.als.entities.lifeforms;

import java.util.Random;

import com.george.als.entities.food.Food;
import com.george.als.entities.food.Meat;
import com.george.als.world.Map;

public class Carnivore extends ALifeForm {

	/**
	 * Default Constructor for a Carnivore
	 * @param name the name of the bug
 	 * @param type the type of bug
	 * @param map the map it belongs to
	 */
	public Carnivore(String name, String type, Map map) {
		super(name, type, map);
	}

	/**
	 * Updates the bug
	 * @see ALifeForm
	 */
	public void update() {		
		//if the bug has energy we want to move
		if(getEnergy() > 0){
			
			//check if the bug has a nest, if not assign it one
			checkBugHasNest();
			
			//if the bug is over food we want the bug to eat the food
			//we then remove the food from the map
			if (overFood() == true) {
				Food food = getFood();
				setEnergy(getEnergy() + food.getEnergyRegen());				
				if(food == getFoodToMoveTo()){
					setFoodToMoveTo(null);
					setMovingToFood(false);
				}
				
				//set the char in the map array to ' '
				char[][] temp = getMap().getMap();
				temp[food.getX()][food.getY()] = ' ';
				getMap().setMap(temp);
				
				//remove the food
				getMap().getFood().remove(food);
			}
			
			//if the bug has less than 50 energy we want to set moveToNest to true
			//if the bug has more than 50 energy we want to set moveTonest to false
			if(getEnergy() < 50){				
				setMovingToNest(true);
				setMovingToFood(false);
			} else if(getEnergy() >= 50){			
				setMovingToFood(true);
				setMovingToNest(false);
			}

			//if we are moving to a nest we want to calulate the path to the nest
			//and move the bug along the path
			if(getMovingToNest()){
				if(getHome() != null){
					moveToNest(getHome());
				}				
			}
			
			//if the bug is in a nest we want to update its energy
			if (getInNest() == true) {
				setMovingToNest(false);
				setEnergy(getEnergy() + 50);
			}
			

			//if the bug is moving to food we want to find the path to the food and 
			//calculate the path to the food
			//we need to check if the food is still on the map
			//if the food has gone or doesnt exist we want to move in a random direction
			if(getMovingToFood()){
				if(getFoodToMoveTo() == null){
					setFoodToMoveTo(smellFood());
				}
				if(getFoodToMoveTo() != null){
					if(checkFood(getFoodToMoveTo())){
						moveToFood(getFoodToMoveTo());
					} else {
						setMovingToFood(false);		
						setFoodToMoveTo(null);
						move(getRandomDirectionToMove());
					}
				} else {					
					setMovingToFood(false);		
					move(getRandomDirectionToMove());
				}				
			}								
		
		//if the bug for some reason has no energy
		//we want to remove it and place it with meat
		} else {
			Random random = new Random();
			getMap().getFood().add(new Meat(getX(), getY(), random.nextInt(10) , "Meat", getMap()));
			getMap().getBugs().remove(this);
		}		
	}


}
