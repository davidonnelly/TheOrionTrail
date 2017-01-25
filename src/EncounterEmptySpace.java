import java.io.FileNotFoundException;

/**
 * @author Brock Soicher, David Donnelly, Kevin Keables
 * The Orion Trail
 * April 24, 2015 - June 3, 2015
 */

public class EncounterEmptySpace implements Encounter {
	private BuildGUI theGUI;
	private String modifyer; //line that states the modifiers of the file that will be added to the text of the result
	
	/**
	 * constructs and displays the encounter
	 * @param gui
	 */
	public EncounterEmptySpace(BuildGUI gui){
		theGUI = gui;
		theGUI.setEncounter(this);
		try {
			theGUI.mainBuild("encounter/emptySpace.txt","options/emptySpaceOp.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	/**
	 * enacts option 1
	 */
	public void option1() {
		int roll = (int)(100*Math.random());
		
		if (roll>50){
			//everyone's sanity goes up a bit in relation to how good the role is
			for(int i=0; i<theGUI.getShip().getPeople().size(); i++){
				theGUI.getShip().getPeople().get(i).increaseSanity((int)((roll-50)/5));
				modifyer = "All crewmembers' sanity increased by " + (int)((roll-50)/5);
			}
			theGUI.setEncounter(new Result(theGUI, "results/emptySpace/goodContiplateDeath.txt", modifyer));
		}
		
		else if(roll<=3){
			//crew member commits suicide
			int num = (int)((theGUI.getShip().getPeople().size()-1)*Math.random());
			while(!theGUI.getShip().getPeople().get(num).alive()){
				num = (int)((theGUI.getShip().getPeople().size()-1)*Math.random());
			}
			theGUI.getShip().getPeople().get(num).kill();
			modifyer = theGUI.getShip().getPeople().get(num).getName() + " has commited suicide.";
			theGUI.setEncounter(new Result(theGUI, "results/emptySpace/suicide.txt", modifyer));
		}
		
		else{
			//everyones sanity goes down in relation to how bad the roll is
			for(int i=0; i<theGUI.getShip().getPeople().size(); i++){
				theGUI.getShip().getPeople().get(i).decreaseSanity((int)((50-roll)/2));
				modifyer = "All crewmembers' sanity decreased by " + (int)((50-roll)/2);
			}
			theGUI.setEncounter(new Result(theGUI, "results/emptySpace/badContiplateDeath.txt", modifyer));
		}
	}

	@Override
	/**
	 * enacts option 2
	 */
	public void option2() {
		int roll = (int)(100*Math.random());
		
		if (roll>50){
			//you have a good cry and everyone feels better
			for(int i=0; i<theGUI.getShip().getPeople().size(); i++){
				theGUI.getShip().getPeople().get(i).increaseSanity((int)((roll-50)/15));
				modifyer = "All crewmembers' sanity increased by " + (int)((roll-50)/15);
			}
			theGUI.setEncounter(new Result(theGUI, "results/emptySpace/goodCry.txt", modifyer));
		}
		
		else if(roll<=10){
			//cry so much it damages the ship
			theGUI.getShip().setHullHealth(theGUI.getShip().getHullHealth()-((10-roll)*2));
			modifyer = "Ship's hull recieves " + ((10-roll)*2) + " dammage";
			theGUI.setEncounter(new Result(theGUI, "results/emptySpace/waterDamage.txt", modifyer));
		}
		
		else{
			//you have a bad cry and everyone feels worse
			for(int i=0; i<theGUI.getShip().getPeople().size(); i++){
				theGUI.getShip().getPeople().get(i).decreaseSanity((int)((50-roll)/3));
				modifyer = "All crewmembers' sanity decreased by " + (int)((50-roll)/3);
			}
			theGUI.setEncounter(new Result(theGUI, "results/emptySpace/badCry.txt", modifyer));
		}
	}

}
