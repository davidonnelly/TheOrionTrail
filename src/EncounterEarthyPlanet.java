import java.io.FileNotFoundException;

/**
 * @author Brock Soicher, David Donnelly, Kevin Keables
 * The Orion Trail
 * April 24, 2015 - June 3, 2015
 */

public class EncounterEarthyPlanet implements Encounter 
{
	private String textFile;
	private String textOpt;
	private BuildGUI gui;
	private String modifyer;
	
	/**
	 * constructs and displays the encounter
	 * @param inputGUI
	 */
	public EncounterEarthyPlanet(BuildGUI inputGUI)
	{
		gui = inputGUI;
		gui.setEncounter(this);
		try {
			gui.mainBuild("encounter/earthyPlanet.txt","options/earthyPlanetOp.txt");
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
				gui.getShip().getPeople().get(i).increaseSanity((int)((roll-50)/5));
				modifyer = "All crewmembers' sanity increased by " + (int)((roll-50)/5);
			}
			gui.setEncounter(new Result(gui, "results/earthyPlanet/goodAirGuitar.txt", modifyer));
		} else {
			//everyones sanity goes down in relation to how bad the roll is
			int damage = (50 - roll);
			int person = (int)Math.random() * gui.getShip().getPeople().size();
			modifyer = gui.getShip().getPeople().get(person).getName() + " health decreased by " + damage;
			gui.getShip().getPeople().get(person).decreaseHealth(damage);
			gui.setEncounter(new Result(gui, "results/earthyPlanet/badAirGuitar.txt", modifyer));
		}
	}

	@Override
	/**
	 * enacts option 2
	 */
	public void option2() 
	{
		// TODO Auto-generated method stub
		int roll = (int)(100*Math.random());
		
		if (roll>50){
			//everyone's sanity goes up a bit in relation to how good the role is
			for(int i=0; i<gui.getShip().getPeople().size(); i++){
				gui.getShip().getPeople().get(i).increaseSanity((int)((roll-50)/5));
				modifyer = "All crewmembers' sanity increased by " + (int)((roll-50)/5);
			}
			gui.setEncounter(new Result(gui, "results/earthyPlanet/goodEarPlugs.txt", modifyer));
		} else {
			//everyones sanity goes down in relation to how bad the roll is
			
			int damage = (50 - roll);
			modifyer = "The ship takes " + damage + " damage";
			gui.getShip().setHullHealth(gui.getShip().getHullHealth() - (damage));
			gui.setEncounter(new Result(gui, "results/earthyPlanet/badEarPlugs.txt", modifyer));
		}
	}

}
