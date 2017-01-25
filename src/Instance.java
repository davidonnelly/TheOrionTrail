/**
 * @author Brock Soicher, David Donnelly, Kevin Keables
 * The Orion Trail
 * April 24, 2015 - June 3, 2015
 */

public class Instance {
	private int planetRoll;
	private int generalRoll;
	private BuildGUI theGUI;
	private static int numberOfEncounters;
	private Star theStar;
	
	/**
	 * constructs the instance
	 * @param theUI
	 */
	public Instance(BuildGUI theUI){
		theGUI = theUI;
		numberOfEncounters = 0;
	}
	
	/**
	 * sets theStar
	 * @param aStar
	 */
	public void setStar(Star aStar){
		theStar = aStar;
	}
	
	/**
	 * runs and displays a random encounter
	 */
	public void randomEncounter(){
		theGUI.getShip().getCargo().setWoolongs(theGUI.getShip().getCargo().getWoolongs() + ((int)(20*Math.random())));
		for(int i = 0;i<theGUI.getShip().getPeople().size(); i++){
			if(theGUI.getShip().getPeople().get(i).getSanity()<25){
				theGUI.setEncounter(new EncounterCrazedPerson(theGUI.getShip().getPeople().get(i), theGUI));
				return;
			}
		}
		
		planetRoll = (int)(200*Math.random());
		theGUI.getShip().fly(theStar);
		
		numberOfEncounters ++;
		
		if( planetRoll <= 30 ){
			theGUI.setEncounter(new EncounterEmptySpace(theGUI));
		}
		else if( planetRoll <= 60 ){
			theGUI.setEncounter(new EncounterGasPlanet(theGUI));
		}
		else if( planetRoll <= 90 ){
			theGUI.setEncounter(new EncounterEarthyPlanet(theGUI));
		}
		else if( planetRoll <= 130 ){
			theGUI.setEncounter(new EncounterAliens(theGUI));
		}
		else if( planetRoll <= 175 ){
			theGUI.setEncounter(new EncounterStore(theGUI));
		}
		else if( planetRoll <= 190 ){
			theGUI.setEncounter(new EncounterDyingStar(theGUI));
		}
		else{
			theGUI.setEncounter(new EncounterWormhole(theGUI));
		}
	}
		
}

