import java.io.FileNotFoundException;
import java.io.FileWriter;

/**
 * @author Brock Soicher, David Donnelly, Kevin Keables
 * The Orion Trail
 * April 24, 2015 - June 3, 2015
 */

public class Result implements Encounter{
	private BuildGUI theGUI;
	private Ship theShip;
	private Boolean someoneAlive;
	
	/**
	 * builds and displays the result, also incrementing the days for each person and adding diseases accordingly
	 * @param gui
	 * @param resultFileName
	 * @param modifyer
	 */
	public Result(BuildGUI gui, String resultFileName, String modifyer){
		theGUI = gui;
		theShip = theGUI.getShip();
		theGUI.setEncounter(this);
		int modifyerLines = 1;
		
		//advances the day
		for(int i=0; i<theGUI.getShip().getPeople().size(); i++){
			if(!theGUI.getShip().getPeople().get(i).nextDay()){
				if(modifyerLines < 3){ //can only add so many lines before gui glitches
					modifyer = modifyer + "\n" + theGUI.getShip().getPeople().get(i).getName() + " has died";
					modifyerLines ++;
				}
				for(int j=0; j<theGUI.getShip().getPeople().size(); j++){
					theGUI.getShip().getPeople().get(j).decreaseSanity(20);
				}
			}
		}
	
		//checks for whether people get diseases
		for(int i=0; i<theGUI.getShip().getPeople().size(); i++){
			if(theGUI.getShip().getPeople().get(i).alive()){
				int generalRoll = (int)((100*Math.random()));
				if(generalRoll<20 && generalRoll>18){
					Disease d = new Disease((int)(3*Math.random())+1);
					d.giveDisease(theGUI.getShip().getPeople().get(i));
					Disease c = new Disease((int)(3*Math.random())+1);
					c.giveDisease(theGUI.getShip().getPeople().get(i));
					if(modifyerLines < 3){
						modifyer = modifyer + "\n" + theGUI.getShip().getPeople().get(i).getName() + " has gotten " + c.getDiseaseName() + " and " + d.getDiseaseName();
						modifyerLines ++;
					}
				}
				else if(generalRoll<=10){
					Disease d = new Disease((int)(3*Math.random()+1));
					d.giveDisease(theGUI.getShip().getPeople().get(i));
					if(modifyerLines < 3){
						modifyer = modifyer + "\n" + theGUI.getShip().getPeople().get(i).getName() + " has gotten " + d.getDiseaseName();
						modifyerLines ++;
					}
				}
			}
		}
		
		//builds the result GUI
		try {
			theGUI.mainBuild(resultFileName,"options/resultOp.txt", modifyer);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	//overload for no modifyer
	/**
	 * overloads for no modifyer
	 * @param gui
	 * @param fileName
	 */
	public Result(BuildGUI gui, String fileName){
		theGUI = gui;
		theShip = theGUI.getShip();
		theGUI.setEncounter(this);
		
		try {
			theGUI.mainBuild(fileName,"options/resultOp.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	

	@Override
	/**
	 * enacts option 1
	 */
	public void option1() {
		someoneAlive = false;
		for(int i=0; i<theShip.getPeople().size(); i++){
			if(theShip.getPeople().get(i).alive()){
				someoneAlive = true;
			}
		}
		if(!someoneAlive){
			theShip.getGame().lose();
		}
		else{
			theGUI.statusBuild();
		}
	}

	@Override
	/**
	 * enacts option 2
	 */
	public void option2() {
		//Map
		someoneAlive = false;
		for(int i=0; i<theShip.getPeople().size(); i++){
			if(theShip.getPeople().get(i).alive()){
				someoneAlive = true;
			}
		}
		if(!someoneAlive){
			theShip.getGame().lose();
		}
		else{
			theGUI.mapBuild();
		}
	}
	
	public void advanceDay(){
		for(int i=0; i<theGUI.getShip().getPeople().size(); i++){
			theGUI.getShip().getPeople().get(i).nextDay();
		}
	}
}
