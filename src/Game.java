import java.io.FileNotFoundException;

/**
 * @author Brock Soicher, David Donnelly, Kevin Keables
 * The Orion Trail
 * April 24, 2015 - June 3, 2015
 */

public class Game {
	//private Ship theShip;
	private EncounterWelcome myEncounter;
	private BuildGUI theGUI;
	
	/**
	 * creates the game
	 */
	public Game(){
		Universe u = new Universe();
		u.initiate();
	}
	
	/**
	 * starts the game
	 */
	public void play(){
		myEncounter = new EncounterWelcome(this);
		theGUI = myEncounter.getGUI();
		//theShip = theGUI.getShip();
	}
	
	/**
	 * displays the game's win condition
	 */
	public void win(){
		theGUI.restart(myEncounter, "encounter/win.txt");
		theGUI.setEncounter(myEncounter);
		/*try {
			theGUI.getFrame().mainBuild("encounter/win.txt", "options/endOp.txt");
			theGUI.setEncounter(myEncounter);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}*/
	}
	
	/**
	 * displays the game's lose condition
	 */
	public void lose(){
		theGUI.restart(myEncounter, "encounter/lose.txt");
		theGUI.setEncounter(myEncounter);
		/*try {
			theGUI.getFrame().mainBuild("encounter/lose.txt", "options/endOp.txt");
			theGUI.setEncounter(myEncounter);
			//myEncounter.setNewShip();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}*/
	}
}
