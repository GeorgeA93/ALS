package com.george.als.main;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;

import com.george.als.data.SaveFile;
import com.george.als.display.Display;
import com.george.als.simulation.Simulation;
import com.george.als.utils.Content;
import com.george.als.utils.UserInput;

/**
 * This class extends GamePanelFields and is in charge of creating and running the simulation
 * It also handles the user input by using an ActionListener
 * 
 * @author Georges Beast
 *
 */
public class GamePanel extends GamePanelFields implements Runnable,
		ActionListener {
	private static final long serialVersionUID = 1L;

	/**
	 * Class Constructor
	 * @param frame  sets the panels frame
	 */
	public GamePanel(JFrame frame) {
		super();
		
		//Load the content
		Content.load();
		
		//set the layout
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		//create the panels
		informationPanel = new JPanel(new GridLayout());
		simulationPanel = new Display();

		//create the menu bar
		jmbMenu = new JMenuBar();

		// FILE MENU
		jmFile = new JMenu("File");
		jmFile.setMnemonic(KeyEvent.VK_F);
		jmFile.getAccessibleContext().setAccessibleDescription("File");
		jmbMenu.add(jmFile);
		// add the menu items
		jmiNew = new JMenuItem("New");
		jmiNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				ActionEvent.ALT_MASK));
		jmiOpen = new JMenuItem("Open");
		jmiOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
				ActionEvent.ALT_MASK));
		jmiSave = new JMenuItem("Save");
		jmiSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				ActionEvent.ALT_MASK));
		jmiSaveAs = new JMenuItem("Save As");
		jmiSaveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
				ActionEvent.ALT_MASK));
		jmiExit = new JMenuItem("Exit");
		jmiExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,
				ActionEvent.ALT_MASK));
		jmFile.add(jmiNew);
		jmFile.add(jmiOpen);
		jmFile.add(jmiSave);
		jmFile.add(jmiSaveAs);
		jmFile.add(jmiExit);

		// VIEW MENU
		jmView = new JMenu("View");
		jmView.setMnemonic(KeyEvent.VK_V);
		jmView.getAccessibleContext().setAccessibleDescription("New");
		jmbMenu.add(jmView);
		// add the menu items
		jmiDisplayLifeForm = new JMenuItem("Display Life Form Information");
		jmiDisplayLifeForm.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,
				ActionEvent.ALT_MASK));
		jmiDisplayInfoMap = new JMenuItem("Display Map Information");
		jmiDisplayInfoMap.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M,
				ActionEvent.ALT_MASK));
		jmView.add(jmiDisplayLifeForm);
		jmView.add(jmiDisplayInfoMap);

		// EDIT MENU
		jmEdit = new JMenu("Edit");
		jmEdit.setMnemonic(KeyEvent.VK_E);
		jmEdit.getAccessibleContext().setAccessibleDescription("Edit");
		jmbMenu.add(jmEdit);
		// add the menu items
		jmiModifyLifeForm = new JMenuItem("Modify Life Form");
		jmiModifyLifeForm.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M,
				ActionEvent.ALT_MASK));
		jmiRemoveLifeForm = new JMenuItem("Remove Life Form");
		jmiRemoveLifeForm.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,
				ActionEvent.ALT_MASK));
		jmiAddLifeForm = new JMenuItem("Add Life Form");
		jmiAddLifeForm.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
				ActionEvent.ALT_MASK));
		jmEdit.add(jmiModifyLifeForm);
		jmEdit.add(jmiRemoveLifeForm);
		jmEdit.add(jmiAddLifeForm);

		// SIMULATION MENU
		jmSimulation = new JMenu("Simulation");
		jmSimulation.setMnemonic(KeyEvent.VK_S);
		jmEdit.getAccessibleContext().setAccessibleDescription("Simulation");
		jmbMenu.add(jmSimulation);
		// add the menu items
		jmiRunSimulation = new JMenuItem("Run");
		jmiRunSimulation.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,
				ActionEvent.ALT_MASK));
		jmiStopSimulation = new JMenuItem("Stop");
		jmiStopSimulation.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				ActionEvent.ALT_MASK));
		jmiPauseSimulation = new JMenuItem("Pause");
		jmiPauseSimulation.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,
				ActionEvent.ALT_MASK));
		jmiRestSimulation = new JMenuItem("Reset");
		jmiRestSimulation.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,
				ActionEvent.ALT_MASK));
		jmiDisplayMapAtEachIteration = new JMenuItem(
				"Display Map at Each Iteration");
		jmiDisplayMapAtEachIteration.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_D, ActionEvent.ALT_MASK));
		jmSimulation.add(jmiRunSimulation);
		jmSimulation.add(jmiStopSimulation);
		jmSimulation.add(jmiPauseSimulation);
		jmSimulation.add(jmiRestSimulation);
		jmSimulation.add(jmiDisplayMapAtEachIteration);

		// HELP MENU
		jmHelp = new JMenu("Help");
		jmHelp.setMnemonic(KeyEvent.VK_H);
		jmHelp.getAccessibleContext().setAccessibleDescription("Help");
		jmbMenu.add(jmHelp);
		// add the menu items
		jmiDisplayAuthorInfo = new JMenuItem("Display Author Information");
		jmiDisplayAuthorInfo.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_U, ActionEvent.ALT_MASK));
		jmiDisplayAppInfo = new JMenuItem("Display Application Information");
		jmiDisplayAppInfo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,
				ActionEvent.ALT_MASK));
		jmHelp.add(jmiDisplayAppInfo);
		jmHelp.add(jmiDisplayAuthorInfo);

		// SIMULATION TOOLBAR
		jtbSimulation = new JToolBar(JToolBar.VERTICAL);
		jbRun = new JButton();
		jbRun.setIcon(Content.run);
		jbStop = new JButton();
		jbStop.setIcon(Content.stop);
		jbPause = new JButton();
		jbPause.setIcon(Content.pause);
		jbReset = new JButton();
		jbReset.setIcon(Content.reset);
		jbDisplayMapAtEachIteration = new JButton("Iteration Off");
		jbSpawnRandomBugs = new JButton("Random On");
		jtbSimulation.add(jbRun);
		jtbSimulation.add(jbStop);
		jtbSimulation.add(jbPause);
		jtbSimulation.add(jbReset);
		jtbSimulation.add(jbDisplayMapAtEachIteration);
		jtbSimulation.add(jbSpawnRandomBugs);
		jtbSimulation.setFloatable(false);

		// LABELS
		jlBugInfo = new JLabel("Bug Info: ");
		jlBugInfo.setVerticalAlignment(JLabel.TOP);
		jlMapInfo = new JLabel("Map Info:");
		jlMapInfo.setVerticalAlignment(JLabel.TOP);
		jlCycles = new JLabel("Cycles Left: " + cycles);
		jlCycles.setVerticalAlignment(JLabel.BOTTOM);
		jlRunning = new JLabel("Running: " + String.valueOf(running));
		jlRunning.setVerticalAlignment(JLabel.BOTTOM);

		// add the components to the panel
		informationPanel.add(jtbSimulation);
		informationPanel.add(jlBugInfo);
		informationPanel.add(jlMapInfo);
		informationPanel.add(jlCycles);
		informationPanel.add(jlRunning);

		informationPanel.setPreferredSize(new Dimension(200, 200));

		this.add(informationPanel);
		this.add(simulationPanel);

		frame.setJMenuBar(jmbMenu);

		// add action listeners
		// FILE
		jmiNew.addActionListener(this);
		jmiOpen.addActionListener(this);
		jmiSave.addActionListener(this);
		jmiSaveAs.addActionListener(this);
		jmiExit.addActionListener(this);
		// VIEW
		jmiDisplayLifeForm.addActionListener(this);
		jmiDisplayInfoMap.addActionListener(this);
		// EDIT
		jmiModifyLifeForm.addActionListener(this);
		jmiRemoveLifeForm.addActionListener(this);
		jmiAddLifeForm.addActionListener(this);
		// SIMULATION
		jmiRunSimulation.addActionListener(this);
		jmiStopSimulation.addActionListener(this);
		jmiPauseSimulation.addActionListener(this);
		jmiRestSimulation.addActionListener(this);
		jmiDisplayMapAtEachIteration.addActionListener(this);
		// HELP
		jmiDisplayAuthorInfo.addActionListener(this);
		jmiDisplayAppInfo.addActionListener(this);
		// SIMULATION TOOLBAR
		jbRun.addActionListener(this);
		jbStop.addActionListener(this);
		jbPause.addActionListener(this);
		jbReset.addActionListener(this);
		jbDisplayMapAtEachIteration.addActionListener(this);
		jbSpawnRandomBugs.addActionListener(this);

		setFocusable(true);
		requestFocus();		

	}

	
	/**
	 *  Runs the simulation thread, this is started by setting running to true and
	 *  starting the thread. 
	 */
	@Override
	public void run() {
		while (true) {
			while (running && cycles != 0) {
				if (!paused) {
					jlRunning.setText("Running: " + String.valueOf(running));
					update();
					if (displayAtEachIteration) {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					cycles--;
					jlCycles.setText("Cycles Left: " + cycles);
					if (cycles == 0 || !running) {
						running = false;
						cycles = 1000;
						jlRunning
								.setText("Running: " + String.valueOf(running));
					}
				}
			}
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	
	/**
	 * Updates the simulation and rapaints it to the screen
	 */
	public void update() {
		simulation.update();
		displayBugInfo();
		repaint();
	}

	/**
	 * 
	 * @param e  The action that has been performed
	 */
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("New")) {
			createNewConfig();
		} else if (e.getActionCommand().equals("Open")) {
			loadOldConfig();
		} else if (e.getActionCommand().equals("Save")) {
			saveConfig();
		} else if (e.getActionCommand().equals("Save As")) {
			saveConfigAs();
		} else if (e.getActionCommand().equals("Exit")) {
			System.exit(0);
		} else if (e.getActionCommand().equals("Run") || e.getSource() == jbRun) {
			if (simulation == null) {
				JOptionPane.showMessageDialog(null,
						"Please create or load a config");
			} else {
				running = true;
				paused = false;
				if (simulationThread == null) {
					simulationThread = new Thread(this);
					simulationThread.start();
				}
			}
		} else if (e.getActionCommand().equals("Stop")
				|| e.getSource() == jbStop) {
			if (simulation == null) {
				JOptionPane.showMessageDialog(null,
						"Please create or load a config");
			} else {
				running = false;
			}
		} else if (e.getActionCommand().equals("Pause")
				|| e.getSource() == jbPause) {
			if (simulation == null) {
				JOptionPane.showMessageDialog(null,
						"Please create or load a config");
			} else {
				paused = true;

			}
		} else if (e.getActionCommand().equals("Reset")
				|| e.getSource() == jbReset) {
			if (simulation == null) {
				JOptionPane.showMessageDialog(null,
						"Please create or load a config");
			} else {
				reset();
			}
		} else if (e.getActionCommand().equals("Display Map at Each Iteration")
				|| e.getSource() == jbDisplayMapAtEachIteration) {
			if (displayAtEachIteration) {
				displayAtEachIteration = false;
				jbDisplayMapAtEachIteration.setText("Iteration Off");
			} else {
				displayAtEachIteration = true;
				jbDisplayMapAtEachIteration.setText("Iteration On");
			}
		} else if(e.getSource() == jbSpawnRandomBugs ){
			if (spawnRandomBugs) {
				spawnRandomBugs = false;
				jbSpawnRandomBugs.setText("Random Off");
			} else {
				spawnRandomBugs = true;
				jbSpawnRandomBugs.setText("Random On");
			}			
		}else if (e.getActionCommand().equals("Add Life Form")) {
			if (simulation == null) {
				JOptionPane.showMessageDialog(null,
						"Please create or load a config");
			} else {
				addLifeForm();
			}

		} else if (e.getActionCommand().equals("Modify Life Form")) {
			if (simulation == null) {
				JOptionPane.showMessageDialog(null,
						"Please create or load a config");
			} else {
				modifyLifeForm();
			}
		} else if (e.getActionCommand().equals("Remove Life Form")) {
			if (simulation == null) {
				JOptionPane.showMessageDialog(null,
						"Please create or load a config");
			} else {
				removeLifeForm();
			}

		} else if (e.getActionCommand().equals("Display Life Form Information")) {
			if (simulation == null) {
				JOptionPane.showMessageDialog(null,
						"Please create or load a config");
			} else {
				displayBugInfo();
			}

		} else if (e.getActionCommand().equals("Display Map Information")) {
			if (simulation == null) {
				JOptionPane.showMessageDialog(null,
						"Please create or load a config");
			} else {
				jlMapInfo.setText("Map Info: "
						+ simulation.getWorld().getMap().getMapWidth() + ", "
						+ simulation.getWorld().getMap().getMapHeight());
			}
		} else if (e.getActionCommand().equals("Display Author Information")) {
			JOptionPane.showMessageDialog(this, AUTHORINFO);

		} else if (e.getActionCommand().equals(
				"Display Application Information")) {
			JOptionPane.showMessageDialog(this, APPINFO);
		}

	}

	// HELPER METHODS

	/**
	 * Resets the simulation back to null and clears the screen
	 */
	private void reset() {
		simulation = new Simulation(1);
		simulationPanel.setMap(null);
		update();
	}

	/**
	 * Adds a life form to the simulation
	 * @see UserInput.addBug()
	 */
	private void addLifeForm() {
		UserInput.addBug(simulation.getWorld().getMap());
		update();
	}

	/**
	 * modifies a life form in the current simulation
	 * @see UserInput.modifyBug()
	 */
	private void modifyLifeForm() {
		UserInput.modifyBug(simulation.getWorld().getMap());
		update();
	}

	/**
	 * removes a life form from the simulation
	 * @see UserInput.removeLifeForm()
	 */
	private void removeLifeForm() {
		UserInput.removeLifeForm(simulation.getWorld().getMap());
		update();
	}

	/**
	 * Creates a new configuration, with user defined constraints
	 */
	private void createNewConfig() {
		simulation = new Simulation();
		simulationPanel.setMap(simulation.getWorld().getMap());

	}

	/**
	 * displays the info about the bugs currently in the world
	 */
	private void displayBugInfo() {
		String info = simulation.getWorld().getMap().getBugs().toString();
		info = convertToMultiline("Bug info: " + info);
		jlBugInfo.setText(info);
	}

	/**
	 * Loads an old configuration from a file
	 */
	private void loadOldConfig() {
		String mapPath = JOptionPane
				.showInputDialog("What is the map file you want to load");
		String bugPath = JOptionPane
				.showInputDialog("What is the bug file you want to load");		
		mapFileOpen = mapPath;
		bugFileOpen = bugPath;		
		if(!mapPath.equals("") && !bugPath.equals("") && mapPath != null && bugPath != null){
			simulation = new Simulation(mapPath, bugPath);
			simulationPanel.setMap(simulation.getWorld().getMap());
		}
	}

	
	/**
	 * Saves the configuration to the open file
	 * If no file is open the default save path is
	 * ALSmap and ALSfood
	 */
	private void saveConfig() {
		String mapPath, bugPath;
		if (mapFileOpen.equals("")  && bugFileOpen.equals("") ) {
			mapPath = "ALSmap";
			bugPath = "ALSbug";			
		} else {
			mapPath = mapFileOpen;
			bugPath = bugFileOpen;			
		}
		SaveFile fileWriter = new SaveFile(simulation.getWorld().getMap(), mapPath, bugPath);
		fileWriter.save();
		JOptionPane.showMessageDialog(null, "Saved the file successfully");
	}

	/**
	 * Saves the configuration to a user defined file
	 */
	private void saveConfigAs() {
		String mapPath = JOptionPane
				.showInputDialog("What is the map file you want to save");
		String bugPath = JOptionPane
				.showInputDialog("What is the bug file you want to save");
		SaveFile fileWriter = new SaveFile(simulation.getWorld().getMap(), mapPath, bugPath);
		fileWriter.save();
		JOptionPane.showMessageDialog(null, "Saved the file successfully");
	}

	/**
	 * Converts a string to multiline so it can be used in a multiline label
	 * @param orig the original string to be converted
	 * @return
	 */
	private static String convertToMultiline(String orig) {
		return "<html>" + orig.replaceAll("\n", "<br>");
	}

}
