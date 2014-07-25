package com.george.als.data;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.george.als.entities.food.Apple;
import com.george.als.entities.food.Food;
import com.george.als.entities.food.Meat;
import com.george.als.entities.food.Poison;
import com.george.als.entities.lifeforms.ALifeForm;
import com.george.als.entities.lifeforms.Carnivore;
import com.george.als.entities.lifeforms.Herbivorve;
import com.george.als.entities.nest.Nest;
import com.george.als.entities.obstacles.Bush;
import com.george.als.entities.obstacles.Obstacle;
import com.george.als.entities.obstacles.Rock;
import com.george.als.entities.obstacles.Tree;
import com.george.als.world.Map;

/**
 * This class is in charge of reading text files from memory
 * It is used to load the map and bug from a file
 * This is done through the methods loadMap and loadBugs
 * @author Georges Beast
 *
 */
public class ReadFile {
	
	/**
	 * Class Constructor
	 */
	public ReadFile() {
	}
	
	/**
	 * This method loads a map from memory
	 * @param mapFileName the map to load
	 * @return a char array of the map information
	 */
	public char[][] loadMap(String mapFileName) {
		char[][] map = new char[0][0];
		int count = 0;
		int y = 0;
		try {
			count = count(mapFileName);			
		} catch (IOException e1) {		
			JOptionPane.showMessageDialog(null, "File not found");
			return null;
		}
		try {
			FileReader fileReader = new FileReader(mapFileName);
			BufferedReader inputFile = new BufferedReader(fileReader);
			String str = inputFile.readLine();
			map = new char[str.length()][count];
			while (str != null) {
				char[] line = new char[str.length()];
				line = str.toCharArray();
				for(int x = 0; x < line.length; x ++){
					map[x][y] = line[x];
				}
				str = inputFile.readLine();
				y += 1;
			}
			inputFile.close();

		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "File not found");
			return null;
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "File not found");
			return null;
		}
		
		JOptionPane.showMessageDialog(null, "Opened the map successfully");
		return map;
	}
	
	/**
	 * This function finds the width of the map that we want to load
	 * @param mapFileName the map file
	 * @return the width of the map file
	 */
	public int getMapX(String mapFileName){
		int mapx = 0;
		try {
			FileReader fileReader = new FileReader(mapFileName);
			BufferedReader inputFile = new BufferedReader(fileReader);
			String str = inputFile.readLine();
			mapx = str.length();
			inputFile.close();
		} catch (FileNotFoundException e) {
			return 0;
		} catch (IOException e) {
			return 0;
		}
		
		return mapx;
	}
	
	/**
	 * This function finds the height of the map we want to load
	 * @param mapFileName the map file
	 * @return the height of the map
	 */
	public int getMapY(String mapFileName){
		int mapy = 0;
		try {
			mapy = count(mapFileName);
			
		} catch (IOException e) {
			return 0;
		}
		return mapy;
	}

	/**
	 * This function loads the bugs from a text file
	 * @param bugsFileName the file to load
	 * @param map the map to add the bugs too
	 * @return the array list of bugs
	 */
	public ArrayList<ALifeForm> loadBugs(String bugsFileName, Map map) {
		ArrayList<ALifeForm> bugs = new ArrayList<ALifeForm>();
		String name = " ", type = " ";
		int x = 0, y = 0, energy = 0;
		boolean canAddName = false, canAddType = false, canAddX = false, canAddY = false, canAddEnergy = false;
		try {
			FileReader fileReader = new FileReader(bugsFileName);
			BufferedReader inputFile = new BufferedReader(fileReader);
			String str = inputFile.readLine();
			while (str != null) {
				if (str.contains("Name: ")) {
					name = str.substring(6);
					canAddName = true;
				}else if(str.contains("Type: ")) {
					type = str.substring(6);
					canAddType = true;
				} else if (str.contains("X: ")) {
					x = Integer.parseInt(str.substring(3));
					canAddX = true;
				} else if (str.contains("Y: ")) {
					y = Integer.parseInt(str.substring(3));
					canAddY = true;
				} else if (str.contains("Energy: ")) {
					energy = Integer.parseInt(str.substring(8));
					canAddEnergy = true;
				}
				str = inputFile.readLine();

				if (canAddName && canAddX&& canAddType && canAddY && canAddEnergy) {
					if(type.equals("C")){
						ALifeForm temp = new Carnivore(name, type, map);
						temp.setX(x);
						temp.setY(y);
						temp.setEnergy(energy);
						bugs.add(temp);
					}
					if (type.equals("H")){
						ALifeForm temp = new Herbivorve(name, type, map);
						temp.setX(x);
						temp.setY(y);
						temp.setEnergy(energy);
						bugs.add(temp);
					}
					canAddName = canAddType = canAddX = canAddY = canAddEnergy = false;
				}
			}
			inputFile.close();

		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "File not found");
			return null;
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "File not found");
			return null;
		}

		JOptionPane.showMessageDialog(null, "Opened the bugs file successfully");
		return bugs;
	}
	
	/**
	 * This function returns the number of chars in a file
	 * @param filename the file to check
	 * @return the number of chars
	 * @throws IOException
	 */
	public int count(String filename) throws IOException {
	    InputStream is = new BufferedInputStream(new FileInputStream(filename));
	    try {
	        byte[] c = new byte[1024];
	        int count = 0;
	        int readChars = 0;
	        boolean empty = true;
	        while ((readChars = is.read(c)) != -1) {
	            empty = false;
	            for (int i = 0; i < readChars; ++i) {
	                if (c[i] == '\n') {
	                    ++count;
	                }
	            }
	        }
	        return (count == 0 && !empty) ? 1 : count;
	    } catch (FileNotFoundException e) {
	    	return 0;
	    }finally {	   
	        is.close();
	    }
	}

}
