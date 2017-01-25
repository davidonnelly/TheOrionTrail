/**
 * @author Brock Soicher, David Donnelly, Kevin Keables
 * The Orion Trail
 * April 24, 2015 - June 3, 2015
 */

public class EncounterWelcome implements Encounter{
	private BuildGUI theGUI;
	private Game theGame;
	
	/**
	 * constructs and displays the encounter
	 * @param aGame
	 */
	public EncounterWelcome(Game aGame){
		theGame = aGame;
		theGUI = new BuildGUI(this, new Ship());
		theGUI.getShip().setGame(theGame);
		theGUI.display();
	}
	public void setNewShip(){
		theGUI.setShip(new Ship());
	}
	
	public BuildGUI getGUI(){
		return theGUI;
	}

	@Override
	/**
	 * enacts option 1
	 */
	public void option1() {
		theGUI.getFrame().createBuild();
	}

	@Override
	/**
	 * enacts option 2
	 */
	public void option2() {
		theGUI.getFrame().dispose();
	}

}
