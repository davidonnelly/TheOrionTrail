import java.io.FileNotFoundException;

/**
 * @author Brock Soicher, David Donnelly, Kevin Keables
 * The Orion Trail
 * April 24, 2015 - June 3, 2015
 */

public class EncounterCrazedPerson implements Encounter
{
	private String textFile;
	private String textOpt;
	private BuildGUI theGUI;
	private String modifyer;
	private Person crazy;
	
	/**
	 * constructs and displays the encounter
	 * @param pDawg
	 * @param gui
	 */
	public EncounterCrazedPerson(Person pDawg, BuildGUI gui)
	{
		crazy = pDawg;
		theGUI = gui;
		theGUI.setEncounter(this);
		try {
			theGUI.mainBuild("encounter/crazedPerson.txt","options/crazedPersonOp.txt");
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
		
		if (roll>75){
			//everyone's sanity goes up by 35 to counteract the fact that everyone is probably at no sanity, so this will help
			for(int i=0; i<theGUI.getShip().getPeople().size(); i++){
				theGUI.getShip().getPeople().get(i).increaseSanity(35);
				modifyer = "All crewmembers' sanity increased by 35";
			}
			theGUI.setEncounter(new Result(theGUI, "results/crazedPerson/allBetter.txt", modifyer));
		}
		
		else if(roll<=10){
			//crew member gets killed by crazy person
			
			int num = (int)((theGUI.getShip().getPeople().size()-1)*Math.random());
			   while(!theGUI.getShip().getPeople().get(num).alive()){
			    num = (int)((theGUI.getShip().getPeople().size()-1)*Math.random());
			   }
			theGUI.getShip().getPeople().get(num).kill();
			theGUI.getShip().ejectFromAirlock(crazy);
			modifyer = theGUI.getShip().getPeople().get(num).getName() + "and" + crazy.getName() + " have been killed.";
			theGUI.setEncounter(new Result(theGUI, "results/crazedPerson/crewGetKilled.txt", modifyer));
		}
		
		else{
			//everyones sanity goes down in relation to how bad the roll is
			for(int i=0; i<theGUI.getShip().getPeople().size(); i++){
				theGUI.getShip().getPeople().get(i).decreaseSanity((int)((50-roll)/2));
				modifyer = "All crewmembers' sanity decreased by " + (int)((50-roll)/2);
			}
			theGUI.setEncounter(new Result(theGUI, "results/crazedPerson/kindaBetter.txt", modifyer));
		}
	}

	@Override
	/**
	 * enacts option 2
	 */
	public void option2() 
	{
		int roll = (int)(100*Math.random());
		
		if (roll>=35){
			//crazy person is launched out airlock
			theGUI.getShip().ejectFromAirlock(crazy);
			modifyer = crazy.getName() + " has been killed.";
			theGUI.setEncounter(new Result(theGUI, "results/emptySpace/byeBye.txt", modifyer));
		}
		else{
			
			int num = (int)((theGUI.getShip().getPeople().size()-1)*Math.random());
			   while(!theGUI.getShip().getPeople().get(num).alive()){
			    num = (int)((theGUI.getShip().getPeople().size()-1)*Math.random());
			   }
			   theGUI.getShip().getPeople().get(num).kill();
			
			//everyones sanity goes down in relation to how bad the roll is
			theGUI.getShip().ejectFromAirlock(crazy);
			modifyer = crazy.getName() + "and" + theGUI.getShip().getPeople().get(num).getName() + " has been killed.";
			theGUI.setEncounter(new Result(theGUI, "results/crazedPerson/uhOh.txt", modifyer));
		}
	}

}
