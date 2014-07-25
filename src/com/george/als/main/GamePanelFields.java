package com.george.als.main;

import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import com.george.als.display.Display;
import com.george.als.simulation.Simulation;

/**
 * This is the base class for a GamePanel, it extends JPanel and holds all of 
 * the data for a GamePanel
 * @author Georges Beast
 *
 */
public abstract class GamePanelFields extends JPanel{
	private static final long serialVersionUID = 1L;
	
	// AUTHOR AND APP INFO
	protected static final String AUTHORINFO = "<html>Made by George Allen<br>A Computer Science student at the University of Reading</html>";
	protected static final String APPINFO = "Artificial Life Simulator V3.5";

	// THREAD INFORMATION
	protected static boolean running;
	protected boolean paused = false;
	protected Thread simulationThread;
	protected static boolean displayAtEachIteration = false;
	protected Simulation simulation;
	protected static int cycles = 1000;

	// PANELS
	protected JPanel informationPanel;
	protected Display simulationPanel;

	protected JMenuBar jmbMenu;
	// FILE MENU
	protected static JMenu jmFile;
	protected static JMenuItem jmiNew;
	protected static JMenuItem jmiOpen;
	protected static JMenuItem jmiSave;
	protected static JMenuItem jmiSaveAs;
	protected static JMenuItem jmiExit;

	// VIEW MENU
	protected static JMenu jmView;
	protected static JMenuItem jmiDisplayLifeForm;
	protected static JMenuItem jmiDisplayInfoMap;

	// EDIT MENU
	protected static JMenu jmEdit;
	protected static JMenuItem jmiModifyLifeForm;
	protected static JMenuItem jmiRemoveLifeForm;
	protected static JMenuItem jmiAddLifeForm;

	// SIMULATION MENU
	protected static JMenu jmSimulation;
	protected static JMenuItem jmiRunSimulation;
	protected static JMenuItem jmiStopSimulation;
	protected static JMenuItem jmiPauseSimulation;
	protected static JMenuItem jmiRestSimulation;
	protected static JMenuItem jmiDisplayMapAtEachIteration;

	// HELP MENU
	protected static JMenu jmHelp;
	protected static JMenuItem jmiDisplayAuthorInfo;
	protected static JMenuItem jmiDisplayAppInfo;

	// SIMULATION TOOL BAR
	protected static JToolBar jtbSimulation;
	protected static JButton jbRun;
	protected static JButton jbStop;
	protected static JButton jbPause;
	protected static JButton jbReset;
	protected static JButton jbDisplayMapAtEachIteration;
	protected static JButton jbSpawnRandomBugs;

	// LABELS
	protected static JLabel jlBugInfo;
	protected static JLabel jlMapInfo;
	protected static JLabel jlCycles;
	protected static JLabel jlRunning;

	// IMAGE PATH
	protected final static String IMAGESFOLDER = "com/george/als/icons/";

	// INFORMATION ABOUT OPEN FILES, SO WE CAN SAVE BACK TO THEM
	protected String mapFileOpen = "";
	protected String bugFileOpen = "";

	public static boolean spawnRandomBugs = true;
	
	public GamePanelFields(){
		//set double Buffereing to true
		super(true);
	}

	

}
