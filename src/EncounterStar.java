import java.io.FileNotFoundException;

/**
 * @author Brock Soicher, David Donnelly, Kevin Keables
 * The Orion Trail
 * April 24, 2015 - June 3, 2015
 */

public class EncounterStar implements Encounter{
	private BuildGUI theGUI;
	private Star theStar;
	
	/**
	 * constructs and displays the encounter
	 * @param theUI
	 */
	public EncounterStar(BuildGUI theUI){
		theGUI = theUI;
		//theStar = Universe.rigel;
		theStar = theGUI.getShip().getCurrentStar();
		
		try {
			theGUI.mainBuild("encounter/star.txt", theStar.option1Name(), theStar.option2Name(), 1);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	/**
	 * enacts option 1
	 */
	public void option1() {
		theGUI.getInstance().setStar(theStar.option1());
		theGUI.getInstance().randomEncounter();
	}

	@Override
	/**
	 * enacts option 2
	 */
	public void option2() {
		theGUI.getInstance().setStar(theStar.option2());
		theGUI.getInstance().randomEncounter();
	}

}
