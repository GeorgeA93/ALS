package com.george.als.main;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * This class holds the applications main method
 * It is in charge of starting the application and creating the GamePanel
 * @author Georges Beast
 *
 */
public class Game {
	
	/**
	 * The main method starts the application as a runnable and creates a new JFrame.
	 * It then creates a new GamePanel and adds it to the frame.
	 * @param args
	 */
	public static void main(String[] args) {
		// starts the main thread for the GUI
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame window = new JFrame("Artificial Life Simulator");

				window.add(new GamePanel(window));

				window.setResizable(false);				
				window.pack();
				
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			    window.setBounds(0,0,screenSize.width, screenSize.height);
			    
				window.setLocationRelativeTo(null);
				window.setVisible(true);
				window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
								
			}
		});

	}
}
