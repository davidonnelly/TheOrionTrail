import java.io.FileNotFoundException;

/**
 * @author Brock Soicher, David Donnelly, Kevin Keables
 * The Orion Trail
 * April 24, 2015 - June 3, 2015
 */

public class EncounterDyingStar implements Encounter {
	private String textFile;
	private String textOpt;
	private BuildGUI gui;
	private String modifyer;
	
	/**
	 * constructs and displays the encounter
	 * @param inputGUI
	 */
	public EncounterDyingStar(BuildGUI inputGUI)
	{
		gui = inputGUI;
		gui.setEncounter(this);
		try {
			gui.mainBuild("encounter/dyingStar.txt","options/dyingStarOp.txt");
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
	int roll = (int)(100*Math.random());
		
		if (roll>50){
			//everyone's sanity goes up a bit in relation to how good the role is
			for(int i=0; i<gui.getShip().getPeople().size(); i++){
				gui.getShip().getPeople().get(i).increaseHealth((int)((roll-50)/5));
				modifyer = "All crewmembers' health increased by " + (int)((roll-50)/5);
			}
			gui.setEncounter(new Result(gui, "results/dyingStar/goodAttendFuneral.txt", modifyer));
		} else {
			//everyones sanity goes down in relation to how bad the roll is
			int damage = (50 - roll);
			modifyer = "Your ship has taken " + damage + " damage";
			gui.getShip().setHullHealth(gui.getShip().getHullHealth() - damage);
			gui.setEncounter(new Result(gui, "results/dyingStar/badAttendFuneral.txt", modifyer));
		}
	}

	@Override
	/**
	 * enacts option 2
	 */
	public void option2() 
	{
		// TODO Auto-generated method stub
		modifyer = "All crew members have died in the vaccuum of space";
		for (int i = 0; i < gui.getShip().getPeople().size(); i++)
		{
			gui.getShip().getPeople().get(i).kill();
		}
		gui.setEncounter(new Result(gui, "results/dyingStar/picket.txt", modifyer));
	}
}
