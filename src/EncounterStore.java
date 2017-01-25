import java.io.FileNotFoundException;

/**
 * @author Brock Soicher, David Donnelly, Kevin Keables
 * The Orion Trail
 * April 24, 2015 - June 3, 2015
 */

public class EncounterStore implements Encounter {
	private BuildGUI theGUI;
	private String modifyer;
	
	/**
	 * constructs and displays the encounter
	 * @param theUI
	 */
	public EncounterStore(BuildGUI theUI){
		theGUI = theUI;
		theGUI.setEncounter(this);
		modifyer = "";
		try {
			theGUI.mainBuild("encounter/store.txt","options/storeOp.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	/**
	 * enacts option 1
	 */
	public void option1() {
		int generalRoll = (int)(100*Math.random());
		
		if(generalRoll > 80){
			theGUI.storeBuild(new Store(1,theGUI.getShip().getCargo()));
		}
		else if(generalRoll > 50){
			theGUI.storeBuild(new Store(1+(Math.random()),theGUI.getShip().getCargo()));
		}
		else if(generalRoll >= 13 && generalRoll<=20){
			theGUI.storeBuild(new Store(3,theGUI.getShip().getCargo()));
		}
		else{
			theGUI.storeBuild(new Store(2+(2*Math.random()),theGUI.getShip().getCargo()));
		}
		
	}

	@Override
	/**
	 * enacts option 2
	 */
	public void option2() {
		int generalRoll = (int)(100*Math.random());
		
		if(generalRoll> 20){
			theGUI.setEncounter(new Result(theGUI, "results/store/continue.txt", modifyer));
		}
		else if(generalRoll == 13){
			int damage = (int)(20*Math.random());
			theGUI.getShip().setHullHealth(theGUI.getShip().getHullHealth()-damage);
			modifyer = "Your ship recieves " + damage + " damage.";
			theGUI.setEncounter(new Result(theGUI, "results/store/attack.txt", modifyer));
		}
		else{
			int roll = (int)(15*Math.random());
			for(int i=0; i<theGUI.getShip().getPeople().size(); i++){
				theGUI.getShip().getPeople().get(i).decreaseSanity(roll);
			}
			modifyer = "All crewmembers' sanity decreased by " + roll;
			theGUI.setEncounter(new Result(theGUI, "results/store/sad.txt", modifyer));
		}
	}
}
