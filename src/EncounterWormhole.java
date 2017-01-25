import java.io.FileNotFoundException;

/**
 * @author Brock Soicher, David Donnelly, Kevin Keables
 * The Orion Trail
 * April 24, 2015 - June 3, 2015
 */

public class EncounterWormhole implements Encounter {
	private String textFile;
	private String textOpt;
	private BuildGUI gui;
	private String modifyer;
	
	/**
	 * constructs and displays the encounter
	 * @param guiIn
	 */
	public EncounterWormhole(BuildGUI guiIn)
	{
		//CONSTRUCTOR
		gui = guiIn;
		gui.setEncounter(this);
		try {
			gui.mainBuild("encounter/wormhole.txt","options/wormholeOp.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	/**
	 * enacts option 1
	 */
	public void option1() 
	{
		// DOES OPTION ONE METHOD (DEPENDS ON WHAT PLAYER CHOOSES)
		int jump = (int)Math.floor(Math.random()*9);
		Star dest = Universe.allStars.get(jump);
		gui.getShip().setDestination(dest);
		gui.getShip().setCurrentStar(dest);
		modifyer = "You have exited the wormhole at " + dest.getName();
		gui.setEncounter(new Result(gui, "results/wormhole/wormholeYes.txt", modifyer));
	}

	@Override
	/**
	 * enacts option 2
	 */
	public void option2() 
	{
		// DOES OPTION TWO METHOD (DEPENDS ON WHAT PLAYER CHOOSES)
		modifyer = "You remain at " + gui.getShip().getLocation();
		gui.setEncounter(new Result(gui, "results/wormhole/wormholeNo.txt", modifyer));
	}
}
