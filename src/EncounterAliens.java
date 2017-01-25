import java.io.FileNotFoundException;

/**
 * @author Brock Soicher, David Donnelly, Kevin Keables
 * The Orion Trail
 * April 24, 2015 - June 3, 2015
 */

public class EncounterAliens implements Encounter {
	private String textFile;
	private String textOpt;
	private BuildGUI theGUI;
	private String modifyer;
	private int number = (int)(100*Math.random());
	private boolean isET;
	
	/**
	 * constructs and displays the encounter
	 * @param GUI
	 */
	public EncounterAliens(BuildGUI GUI){
		theGUI = GUI;
		theGUI.setEncounter(this);
		number = (int)(100*Math.random());
		if(number >= 60){
			isET = true;
		}else{
			isET = false;
		}
		try {
			if(isET){
			theGUI.mainBuild("encounter/alienEncounter.txt","options/alienEncounterOp.txt");
			}
			else
			{
				theGUI.mainBuild("encounter/alienEncounter1.txt","options/alienEncounterOp1.txt");
			}
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
		if(isET)
		{
			if (roll>75){
				//everyone's sanity goes up by how good the roll is
				for(int i=0; i<theGUI.getShip().getPeople().size(); i++){
					theGUI.getShip().getPeople().get(i).increaseSanity((int)((roll-75)*4));
				}
				modifyer = "All crewmembers' sanity increased by " + ((int)((roll-75)*4));
				theGUI.setEncounter(new Result(theGUI, "results/alienEncounter/awesome.txt", modifyer));
			}
		
			else if(roll<=15){
				//E.T. attacks and damages the ship
				theGUI.getShip().setHullHealth(theGUI.getShip().getHullHealth() - ((15-roll)*2));
				modifyer = "The ship has taken " + ((15-roll)*2) + " damage";
				theGUI.setEncounter(new Result(theGUI, "results/alienEncounter/attack.txt", modifyer));
			}
		
			else{
				//everyones sanity goes down in relation to how bad the roll is
				for(int i=0; i<theGUI.getShip().getPeople().size(); i++){
					theGUI.getShip().getPeople().get(i).decreaseSanity((int)((75-roll)/7));
					modifyer = "All crewmembers' sanity decreased by " + (int)((75-roll)/7);
				}
				theGUI.setEncounter(new Result(theGUI, "results/alienEncounter/tooBad.txt", modifyer));
			}
		}
		else
		{
			if (roll> 60){
				//goes to a store
				modifyer = "You will access their store";
				theGUI.storeBuild(new Store(1.25,theGUI.getShip().getCargo()));
				theGUI.setEncounter(new Result(theGUI, "results/alienEncounter/trader", modifyer));
			}
		
			else if(roll<=20){
				//Turns out to be pirates attacks and damages the ship
				theGUI.getShip().setHullHealth(theGUI.getShip().getHullHealth() - ((20-roll)*2));
				modifyer = "The ship has taken " + ((20-roll)*2) + " damage";
				theGUI.setEncounter(new Result(theGUI, "results/alienEncounter/attack1.txt", modifyer));
			}
		
			else{
				//Nothing happens
				modifyer = "Nothing happens";
				theGUI.setEncounter(new Result(theGUI, "results/alienEncounter/nothing.txt", modifyer));
			}
		}
	}

	@Override
	/**
	 * enacts option 2
	 */
	public void option2() {
		int roll = (int)(100*Math.random());
		if(isET)
		{
			if (roll>75){
				//everyone's sanity goes up by how good the roll is
				for(int i=0; i<theGUI.getShip().getPeople().size(); i++){
					theGUI.getShip().getPeople().get(i).increaseSanity((int)((roll-75)/2));
					modifyer = "All crewmembers' sanity increased by " + ((int)((roll-75)/2));
				}
				theGUI.setEncounter(new Result(theGUI, "results/alienEncounter/awesome.txt", modifyer));
			}
		
			else if(roll<=20){
				//E.T. attacks and damages the ship
				theGUI.getShip().setHullHealth(theGUI.getShip().getHullHealth() - ((20-roll)*2));
				modifyer = "The ship has taken " + ((10-roll)*2) + " damage";
				theGUI.setEncounter(new Result(theGUI, "results/alienEncounter/attack.txt", modifyer));
			}
		
			else{
				//everyones sanity goes down in relation to how bad the roll is
				for(int i=0; i<theGUI.getShip().getPeople().size(); i++){
					theGUI.getShip().getPeople().get(i).decreaseSanity((int)((75-roll)/2));
					modifyer = "All crewmembers' sanity decreased by " + (int)((75-roll)/2);
				}
				theGUI.setEncounter(new Result(theGUI, "results/alienEncounter/tooBad.txt", modifyer));
			}
		}
		else
		{
			if (roll>65){
				//Ship takes damage from the fight
				theGUI.getShip().setHullHealth(theGUI.getShip().getHullHealth() - ((roll-65)*2));
				modifyer = "The ship has taken " + ((roll-65)*2) + " damage.";
				theGUI.setEncounter(new Result(theGUI, "results/alienEncounter/loseFight.txt", modifyer));
			}
		
			else if(roll<=30){
				//You win unscathed and get bonus cargo
				 int cell = (int)(10*Math.random());
				 int parts = (int)(10*Math.random());
				 int tangs = (int)(10*Math.random());
				 int woolong = (int)(10*Math.random());
				theGUI.getShip().setHullHealth(theGUI.getShip().getHullHealth() - ((30-roll)*2));
				modifyer = "You have taken " + cell * 2 + " cells," + parts * 2 + " parts," + tangs * 2 + " tang, and " + woolong * 2 + " woolongs.";
				theGUI.setEncounter(new Result(theGUI, "results/alienEncounter/wonFight.txt", modifyer));
				theGUI.getShip().getCargo().addEnergyCells(cell * 2);
				theGUI.getShip().getCargo().addRepairParts(parts * 2); 
				theGUI.getShip().getCargo().addTangRations(tangs * 2);
				theGUI.getShip().getCargo().addWoolongs(woolong * 2);
			}
		
			else{
				 int cell = (int)(10*Math.random());
				 int parts = (int)(10*Math.random());
				 int tangs = (int)(10*Math.random());
				 int woolong = (int)(10*Math.random());
				theGUI.getShip().setHullHealth(theGUI.getShip().getHullHealth() - ((roll-30)*2));
				modifyer =  "The ship has taken " + ((roll-30)*2) + " damage.  Also," + cell * 2 + " cells," + parts * 2 + " parts," + tangs * 2 + " tang, and " + woolong * 2 + " woolongs.";
				theGUI.setEncounter(new Result(theGUI, "results/alienEncounter/tookDamage.txt", modifyer));
				theGUI.getShip().getCargo().addEnergyCells(cell);
				theGUI.getShip().getCargo().addRepairParts(parts);
				theGUI.getShip().getCargo().addTangRations(tangs);
				theGUI.getShip().getCargo().addWoolongs(woolong);
		}
	}
}
}
