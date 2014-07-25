package com.george.als.entities.lifeforms;

import java.util.ArrayList;
import java.util.Random;

import com.george.als.entities.food.Food;
import com.george.als.entities.nest.Nest;
import com.george.als.utils.AStarPathFinder;
import com.george.als.utils.Node;
import com.george.als.world.Map;

/**
 * The base class for all life forms The class creates and updates all of the
 * life forms on the current map 
 * It holds the name and type of life form
 * The x and y positions of the life form
 * And all the other information that is used to update their positions on the map 
 * @author Georges Beast
 * 
 */
public abstract class ALifeForm {
	
	//the name of the life form
	private String name;
	//the type of the life form
	private String type;
	//the energy of the life form
	private int energy;
	//the x and y coordinate of the life form
	private int x, y;
	//the smelling distance of the life form
	private int maxSmellingDistance = 2;
	//if the life form is moving to food
	private boolean movingToFood = false;
	//the map the life form belongs to
	private Map map;
	//if the life form is in its nest
	private boolean inNest;
	//if the life form is moving to its nest
	private boolean movingToNest = false;
	//the life forms nest
	private Nest home;
	//the food the life form is moving to
	private Food foodToMoveTo;
	//the path finder used to access the A* path finding algorithm
	private AStarPathFinder pathFinder;
	//the array list of nodes that holds the path to the life forms nest
	private ArrayList<Node> pathToNest;
	//the array list of nodes that holds the path to the life forms food
	private ArrayList<Node> pathToFood;

	/**
	 * Gets the name of the bug
	 * @return the bugs name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Gets the type of bug
	 * @return the bugs type
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * Gets the bugs X position
	 * @return the bugs x coordinate
	 */
	public int getX() {
		return this.x;
	}

	/**
	 * Gets the bugs y position
	 * @return the bugs y coordinate
	 */
	public int getY() {
		return this.y;
	}

	/**
	 * Gets the bugs energy
	 * @return the bugs energy
	 */
	public int getEnergy() {
		return this.energy;
	}

	/**
	 * Gets the bugs smelling distance
	 * @return the bugs smelling distance
	 */
	public int getMaxSmellingDistance() {
		return this.maxSmellingDistance;
	}

	/**
	 * Gets if the bug is moving to food
	 * @return if the bug is moving to food
	 */
	public boolean getMovingToFood() {
		return this.movingToFood;
	}

	/**
	 * Gets the bugs home nest
	 * @return the bugs nest
	 */
	public Nest getHome() {
		return this.home;
	}

	/**
	 * Gets the map the bug belongs to
	 * @return the bugs map
	 */
	public Map getMap() {
		return this.map;
	}

	/**
	 * Gets if the bug is in its nest
	 * @return if the bug is in its nest
	 */
	public boolean getInNest() {
		return this.inNest;
	}

	/**
	 * Gets if the bug is moving to its nest
	 * @return if the bug is moving to its nests
	 */
	public boolean getMovingToNest() {
		return this.movingToNest;
	}

	/**
	 * Gets the food the bug needs to move to
	 * @return the food the bug is moving to
	 */
	public Food getFoodToMoveTo() {
		return this.foodToMoveTo;
	}

	/**
	 * Sets a bugs name
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * sets the bugs x position
	 * @param x the new x position
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * sets the bugs y position
	 * @param y the new y position
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * sets the bugs energy
	 * @param energy the new energy
	 */
	public void setEnergy(int energy) {
		this.energy = energy;
	}

	/**
	 * Sets if the bug is moving to food
	 * @param movingToFood the boolean value
	 */
	public void setMovingToFood(boolean movingToFood) {
		this.movingToFood = movingToFood;
	}

	/**
	 * Sets the bugs home nest
	 * @param home the new home nest
	 */
	public void setHome(Nest home) {
		this.home = home;
	}

	/**
	 * Sets if the bug is in its nest
	 * @param inNest the boolean value
	 */
	public void setInNest(boolean inNest) {
		this.inNest = inNest;
	}

	/**
	 * sets if the bug is moving to a nest
	 * @param movingToNest the boolean value
	 */
	public void setMovingToNest(boolean movingToNest) {
		this.movingToNest = movingToNest;
	}

	/**
	 * Sets the bugs food to move to
	 * @param foodToMoveTo the new food the bug should move to
	 */
	public void setFoodToMoveTo(Food foodToMoveTo) {
		this.foodToMoveTo = foodToMoveTo;
	}

	/**
	 * Contructor for creating a Herbivore or Carnivore
	 * 
	 * @param name
	 *            the name of the life form
	 * @param type
	 *            the type of the life form
	 * @param map
	 *            the map it belongs to
	 */
	public ALifeForm(String name, String type, Map map) {
		this.name = name;
		this.type = type;
		this.map = map;
		this.inNest = false;
		this.energy = (6 + (int) (Math.random() * ((10 - 6) + 1))) * 10;
		this.movingToNest = false;
		this.movingToFood = false;
		this.inNest = false;
		this.pathFinder = new AStarPathFinder(getMap());
		this.pathToNest = null;
		this.pathToFood = null;
		this.foodToMoveTo = null;
	}


	/**
	 * The direction the life form can move
	 */
	public enum Direction {
		north, south, east, west;
	}

	private Direction currentDirection;

	/**
	 * 
	 * @return the current direction the life form is moving
	 */
	public Direction getCurrentDirection() {
		return this.currentDirection;
	}

	/**
	 * Sets the current Direction
	 * 
	 * @param direction
	 *            the new direction the life form is to move
	 */
	public void setCurrentDirection(Direction direction) {
		this.currentDirection = direction;
	}

	/**
	 * The bug update method that is overriden by the different types of bug
	 */
	public void update() {
	}

	/**
	 * Check if the life form has a nest
	 */
	public void checkBugHasNest() {
		if (home == null) {
			if (map.getNests().size() > 0) {
				for (int i = 0; i < map.getNests().size(); i++) {
					if (home == null) {
						Nest nest = map.getNests().get(i);
						nest.addBug(this);
						home = nest;
					}
				}
			}
		}
	}

	// HELPER METHODS

	/**
	 * Moves the bug to a food item on the map Uses the A* search algorithm
	 * 
	 * @param food
	 *            the food to move to
	 * @see AStarPathFinder
	 */
	public void moveToFood(Food food) {
		int foodX = food.getX();
		int foodY = food.getY();
		pathToFood = pathFinder.findPath(getX(), getY(), foodX, foodY);
		if (pathToFood != null) {
			if (pathToFood.size() > 0) {
				int nodeX = pathToFood.get(pathToFood.size() - 1).getX();
				int nodeY = pathToFood.get(pathToFood.size() - 1).getY();
				if (getX() < nodeX)
					move(Direction.east);
				if (getX() > nodeX)
					move(Direction.west);
				if (getY() < nodeY)
					move(Direction.south);
				if (getY() > nodeY)
					move(Direction.north);
			}
			if (getX() == foodX && getY() == foodY) {
				pathToFood = null;
				movingToFood = false;
			}
		}

	}

	/**
	 * Moves the bug to a nest on the map Uses the A* start search algorithm
	 * 
	 * @param nest
	 *            the nest to move to
	 * @see AStarPathFinder
	 */
	public void moveToNest(Nest nest) {
		int nestX = nest.getX();
		int nestY = nest.getY();
		pathToNest = pathFinder.findPath(getX(), getY(), nestX, nestY);
		if (pathToNest != null) {
			if (pathToNest.size() > 0) {
				int nodeX = pathToNest.get(pathToNest.size() - 1).getX();
				int nodeY = pathToNest.get(pathToNest.size() - 1).getY();
				if (getX() < nodeX)
					move(Direction.east);
				if (getX() > nodeX)
					move(Direction.west);
				if (getY() < nodeY)
					move(Direction.south);
				if (getY() > nodeY)
					move(Direction.north);
			}
			if (getX() == nestX && getY() == nestY) {
				pathToNest = null;
				movingToNest = false;
			}
		}
	}


	/**
	 * Moves the bug in a direction This method is called by moveToFood,
	 * moveToNest, moveToParent
	 * 
	 * @param direction
	 *            the direction to move the bug
	 */
	public void move(Direction direction) {
		setCurrentDirection(direction);
		// if there is no obstacle, we can move
		if (!detectObstacle(getCurrentDirection())) {
			if (direction == Direction.north) {
				// move bug up
				setY(getY() - 1);
				// set the energy
				setEnergy(getEnergy() - 1);
			} else if (direction == Direction.south) {
				// move bug down
				setY(getY() + 1);
				// set the energy
				setEnergy(getEnergy() - 1);
			} else if (direction == Direction.east) {
				// move bug right
				setX(getX() + 1);
				// set the energy
				setEnergy(getEnergy() - 1);
			} else if (direction == Direction.west) {
				// move bug left
				setX(getX() - 1);
				// set the energy
				setEnergy(getEnergy() - 1);
			}
		} else {
			// if there is an obstacle we change the direction we are moving
			move(getRandomDirectionToMove());
		}
	}

	/**
	 * 
	 * @return the food at the bugs current position
	 */
	public Food getFood() {
		for (int i = 0; i < getMap().getFood().size(); i++) {
			if (getMap().getFood().get(i).getX() == getX()
					&& getMap().getFood().get(i).getY() == getY()) {
				Food food = getMap().getFood().get(i);
				return food;
			}
		}
		return null;
	}

	/**
	 * Gets the food at a particular position
	 * 
	 * @param x
	 *            the x coordinate
	 * @param y
	 *            the y coordinate
	 * @return the food at a the x and y coordinate
	 */
	public Food getFoodAt(int x, int y) {
		for (int i = 0; i < getMap().getFood().size(); i++) {
			if (getMap().getFood().get(i).getX() == x
					&& getMap().getFood().get(i).getY() == y) {
				return getMap().getFood().get(i);
			}
		}
		return null;
	}

	/**
	 * Checks if the bug is standing over food
	 * 
	 * @return true if standing over food, false if not
	 */
	public boolean overFood() {
		for (int i = 0; i < getMap().getFood().size(); i++) {
			if (getX() == getMap().getFood().get(i).getX()
					&& getY() == getMap().getFood().get(i).getY()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks for food in a give radius This radius is determined by
	 * maxSmellingDistance
	 * 
	 * @return the food item that is found
	 */
	public Food smellFood() {
		for (int i = 0; i < map.getFood().size(); i++) {
			// get the current food item
			Food f = map.getFood().get(i);
			// get the x and y coordinates of the food
			int fx = f.getX();
			int fy = f.getY();

			// get the x and y distance between the food and the bug
			int dx = Math.abs(fx - x);
			int dy = Math.abs(fy - y);

			// get the total distance
			double distance = Math.sqrt((dx * dx) + (dy * dy));
			// int distance = dx + dy;
			if (distance <= maxSmellingDistance) {
				if (this instanceof Herbivorve && f.getType().equals("Meat")) {
					continue;
				} else {
					return f;
				}
			}
		}
		return null;
	}

	/**
	 * Checks if the food the bug is moving to still exists on the map Prevents
	 * the bug searching for food that has been eaten
	 * 
	 * @param food
	 *            the food to check
	 * @return true if the food still exist, false if not
	 */
	public boolean checkFood(Food food) {
		for (int i = 0; i < map.getFood().size(); i++) {
			Food f = map.getFood().get(i);
			if (f == food) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Detects and obstacle one cell ahead of the bug in the given direction
	 * 
	 * @param direction
	 *            the direction to check
	 * @return true if there is an obstacle, false if not
	 */
	public boolean detectObstacle(Direction direction) {
		// create a temporary char array to access the gameMap information
		char[][] gameMap = getMap().getMap();
		// check the collision in the direction we are trying to move
		// if the next cell contains collision information we return true and we
		// cannot move
		// else we return false and we can move
		if (direction == Direction.north) {
			if (getY() == 0) {
				return true;
			}
			if (gameMap[getX()][getY() - 1] == 'T'
					|| gameMap[getX()][getY() - 1] == 'R'
					|| gameMap[getX()][getY() - 1] == 'U'
					|| gameMap[getX()][getY() - 1] == 'Z') {
				return true;
			} else {
				return false;
			}
		} else if (direction == Direction.south) {
			if (getY() == getMap().getMapHeight() - 1) {
				return true;
			}
			if (gameMap[getX()][getY() + 1] == 'T'
					|| gameMap[getX()][getY() + 1] == 'R'
					|| gameMap[getX()][getY() + 1] == 'U'
					|| gameMap[getX()][getY() + 1] == 'Z') {
				return true;
			} else {
				return false;
			}
		} else if (direction == Direction.east) {
			if (getX() == getMap().getMapWidth() - 1) {
				return true;
			}
			if (gameMap[getX() + 1][getY()] == 'T'
					|| gameMap[getX() + 1][getY()] == 'R'
					|| gameMap[getX() + 1][getY()] == 'U'
					|| gameMap[getX() + 1][getY()] == 'Z') {
				return true;
			} else {
				return false;
			}
		} else if (direction == Direction.west) {
			if (getX() == 0) {
				return true;
			}
			if (gameMap[getX() - 1][getY()] == 'T'
					|| gameMap[getX() - 1][getY()] == 'R'
					|| gameMap[getX() - 1][getY()] == 'U'
					|| gameMap[getX() - 1][getY()] == 'Z') {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	/**
	 * Generates a random direction to move by using random numbers *
	 * 
	 * @return the directio to move
	 * @see Random
	 */
	public Direction getRandomDirectionToMove() {
		Random random = new Random();
		int dir = random.nextInt(4);
		if (dir == 0 && getY() != 0) {
			return Direction.north;
		} else if (dir == 1 && getY() != getMap().getMapHeight() - 1) {
			return Direction.south;
		} else if (dir == 2 && getX() != 0) {
			return Direction.west;
		} else if (dir == 3 && getX() != getMap().getMapWidth() - 1) {
			return Direction.east;
		} else {
			return getRandomDirectionToMove();
		}
	}

	/**
	 * @return the bug information to a string
	 */
	public String toString() {
		return ("\n\nName:" + name + "\nPosition: " + x + "," + y
				+ "\nEnergy: " + energy);
	}

}
