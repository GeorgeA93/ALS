package com.george.als.utils;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.george.als.entities.lifeforms.ALifeForm;
import com.george.als.entities.lifeforms.Carnivore;
import com.george.als.entities.lifeforms.Herbivorve;
import com.george.als.world.Map;

/**
 * This class holds all the methods to get data from the user
 * Each function uses a JOptionPane to get user input and return data
 * @author Georges Beast
 *
 */
public class UserInput {

	/**
	 * Asks the user how many bugs they want to add to the map
	 * @return the number of bugs
	 */
	public static int getMaxBugs() {
		try{
			int maxBugs =  Integer
					.parseInt(JOptionPane
							.showInputDialog("How many bugs to do you want to add?"));
			
			if(maxBugs > 0 && maxBugs <= 20 ){
				return maxBugs;
			}
			JOptionPane.showMessageDialog(null, "The number of bugs must be greater than 0 and less than 20");
			return getMaxBugs();
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "The value needs to be a number");
			return getMaxBugs();
		}				
				
	}

	/**
	 * Asks the user to input the information for each bug
	 * @param numBugs the number of bugs to be added
	 * @param map  the map they will be added to
	 * @return the ArrayList of bugs
	 */
	public static ArrayList<ALifeForm> getBugs(int numBugs, Map map) {
		ArrayList<ALifeForm> bugs = new ArrayList<ALifeForm>();
		for (int i = 0; i < numBugs; i++) {
			String type = JOptionPane
					.showInputDialog("What type of bug do you want: Carnivore (C), Herbivore (H)");
			String name = JOptionPane
					.showInputDialog("What is the name of the bug");
			if (type.equals("C") || type.equals("c")) {
				ALifeForm temp = new Carnivore(name, "C", map);
				bugs.add(temp);
			} else if (type.equals("H") || type.equals("h")) {
				ALifeForm temp = new Herbivorve(name, "H", map);
				bugs.add(temp);
			}  else {
				i--;
			}
		}
		return bugs;
	}

	/**
	 * Asks the user for the bug information and adds it to the map
	 * @param map the map for the bugs to be added to
	 */
	public static void addBug(Map map) {
		String type = JOptionPane
				.showInputDialog("What type of bug do you want: Carnivore (C), Herbivore (H)");
		String name = JOptionPane
				.showInputDialog("What is the name of the bug");
		if (type.equals("C") || type.equals("c")) {
			ALifeForm temp = new Carnivore(name, "C", map);
			map.getBugs().add(temp);
		} else if (type.equals("H") || type.equals("h")) {
			ALifeForm temp = new Herbivorve(name, "H", map);
			map.getBugs().add(temp);
		} 
	}

	/**
	 * Asks the user for the information about the bug to be modified
	 * @param map the map to search for the bug
	 */
	public static void modifyBug(Map map) {
		try{
			String name = JOptionPane
					.showInputDialog("What is the name of the life form you want to modify");
			try{
				String dataToModify = JOptionPane
						.showInputDialog("What do you want to modify. Type: Name or Energy");
				ALifeForm bug = null;
				for (int i = 0; i < map.getBugs().size(); i++) {
					if (map.getBugs().get(i).getName().equals(name)) {
						bug = map.getBugs().get(i);
					}
				}
				if (dataToModify.equals("Name")) {
					String newName = JOptionPane.showInputDialog("Type the new name");
					bug.setName(newName);
				} else if (dataToModify.equals("Energy")) {
					int newEnergy = Integer.parseInt(JOptionPane
							.showInputDialog("Type the new Energy"));
					bug.setEnergy(newEnergy);
				}
			} catch (NullPointerException e){
				JOptionPane.showMessageDialog(null, "Please type a option");
			}
		} catch (NullPointerException e){
			JOptionPane.showMessageDialog(null, "Please type a valid name");
		}		
	}

	/**
	 * Asks the user for the bug to be removed
	 * @param map the map to search for the bug
	 */
	public static void removeLifeForm(Map map) {
		String name = JOptionPane
				.showInputDialog("What is the name of the life form you want to remove?");
		for (int i = 0; i < map.getBugs().size(); i++) {
			if (map.getBugs().get(i).getName().equals(name)) {
				char[][] temp = map.getMap();
				temp[map.getBugs().get(i).getX()][map.getBugs().get(i).getY()] = ' ';
				map.setMap(temp);
				map.getBugs().remove(i);
			}
		}
	}

	/**
	 * Asks the user how wide they want the map
	 * @return the map width
	 */
	public static int getMapX() {
		try{
			int x =  Integer
					.parseInt(JOptionPane
							.showInputDialog("How big do you want the map in x coordinates?"));
			
			if(x <= 150 && x >= 30){
				return x;
			}
			JOptionPane.showMessageDialog(null, "The X value must be less that 150, and greater than 29");
			return getMapX();
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "The X value must be a number!");
			return getMapX();
		}		
	}

	/**
	 * Asks the user for the height of the map
	 * @return the map height
	 */
	public static int getMapY() {
		
		try{
			int y = Integer
					.parseInt(JOptionPane
							.showInputDialog("How big do you want the map in y coordinates?"));

			if(y <= 150 && y >= 30){
				return y;
			}
			JOptionPane.showMessageDialog(null, "The Y value must be less that 150, and greater that 29");
			return getMapY();
		} catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "The Y value must be a number!");
			return getMapY();
		}		
	}
}
