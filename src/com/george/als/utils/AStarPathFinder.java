package com.george.als.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.george.als.world.Map;

/**
 * This class is used to calulcate a path from one point to another
 * It implements the A start path finding algorithm shown in findPath
 * The algorithm returns a list of nodes which a bug can follow
 * @author Georges Beast
 *
 */
public class AStarPathFinder {

	private ArrayList<Node> path = new ArrayList<Node>();
	private ArrayList<Node> openList = new ArrayList<Node>();
	private ArrayList<Node> closedList = new ArrayList<Node>();
	private Node currentNode;

	private Map map;
	private char[][] gameMap;

	/**
	 * Compares the fCosts of two nodes
	 */
	private Comparator<Node> sortNodes = new Comparator<Node>() {
		@Override
		public int compare(Node node0, Node node1) {
			if (node1.getFCost() < node0.getFCost()) {
				return +1;
			}
			if (node1.getFCost() > node0.getFCost()) {
				return -1;
			}
			return 0;
		}
	};

	/**
	 * Class constructor
	 * @param map the current map being used
	 */
	public AStarPathFinder(Map map) {
		this.map = map;
		this.gameMap = map.getMap();
	}

	/**
	 * Finds the path between to points
	 * @param xs the x starting point
	 * @param ys the y starting point
	 * @param xe the x ending point
	 * @param ye the y ending point
	 * @return the list of nodes to get from A to B
	 * @see Node
	 */
	public ArrayList<Node> findPath(int xs, int ys, int xe, int ye) {
		currentNode = new Node(xs, ys, null, 0, getDistance(xs,ys, xe, ye));
		openList.add(currentNode);

		while (openList.size() > 0) {
			// sort the open nodes by their fCost
			Collections.sort(openList, sortNodes);
			currentNode = openList.get(0);
			
			if (currentNode.getX() == xe && currentNode.getY() == ye) {
				//reconstruct the path
				while(currentNode.getParent() != null){
					path.add(currentNode);
					currentNode = currentNode.getParent();
				}
				openList.clear();
				closedList.clear();
				return path;
			}
			
			openList.remove(currentNode);
			closedList.add(currentNode);

			// loop through the adjacent tiles
			for (int i = 0; i < 9; i++) {
				// skip these tiles to avoid diagonal movement
				if (i == 0)continue;
				if (i == 2)continue;
				if (i == 6)continue;
				if (i == 8)continue;
				// skip this tile as its the middle tile
				if (i == 4)continue;

				// get the current location
				int x = currentNode.getX();
				int y = currentNode.getY();

				// get the adjacency we want to check
				int xd = (i % 3) - 1;
				int yd = (i / 3) - 1;
				
				char currentChar = ' ';
				
				if(x + xd >= 0 && x + xd <= map.getMapWidth() - 1 && y + yd >= 0 && y + yd <= map.getMapHeight() - 1){
					currentChar = gameMap[x + xd][y + yd];
				} else {
					continue;
				}
				
				
				// if the current char is a bush or a tree we want to skip these
				// as they have collision
				if (currentChar == 'R')continue;
				if (currentChar == 'T')continue;
				if (currentChar == 'U') continue;
				if (currentChar == 'Z') continue;
				
				//calculate the gCost and hCost of the adjacent tile
				double gCost = currentNode.getGCost() + getDistance(currentNode.getX(), currentNode.getY(), x +xd, y + yd);
				double hCost = getDistance(x + xd, y + yd, xe, ye);
				
				//create the new node
				Node node = new Node(x + xd, y + yd, currentNode, gCost, hCost);
				
				if(isNodeInList(closedList, x + xd, y + yd) && gCost >= node.getGCost()) continue;
				if(!isNodeInList(openList, x + xd, y + yd) || gCost < node.getGCost()) openList.add(node);
			}
		}
		closedList.clear();
		return null;
	}
	
	/**
	 * Checks if the x and y coordinates are inside a list
	 * @param list the list to check
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @return true if its in the list, false if not
	 */
	private boolean isNodeInList(ArrayList<Node> list, int x, int y){
		for(Node n : list){
			if(n.getX() == x && n.getY() == y){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Gets the distance between two points
	 * @param xs the x coordinate of the first point
	 * @param ys the y coordinate of the first point
	 * @param xe the x coordinate of the second point
	 * @param ye the y coordinate of the second point
	 * @return a double, the distance between the points
	 */
	private double getDistance(int xs, int ys, int xe, int ye) {
		double dx = xs - xe;
		double dy = ys - ye;
		return Math.sqrt(dx * dx + dy * dy);
	}

}
