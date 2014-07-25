package com.george.als.entities.food;

import com.george.als.world.Map;

public class Meat extends Food{

	/**
	 * Default constructor for meat
	 * @param x the x coordinate of the food
	 * @param y the y coordinate of the food
	 * @param energyRegen the energy regen amount of the food
	 * @param type the type of food
	 * @param map the map the food belongs to
	 */
	public Meat(int x, int y, int energyRegen, String type, Map map) {
		super(x, y, energyRegen, type, map);
	}


}
