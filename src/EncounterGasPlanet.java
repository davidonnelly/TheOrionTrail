import java.io.FileNotFoundException;

/**
 * @author Brock Soicher, David Donnelly, Kevin Keables
 * The Orion Trail
 * April 24, 2015 - June 3, 2015
 */

public class EncounterGasPlanet implements Encounter {
	private BuildGUI gui;
	private String modifyer;
	
	/**
	 * constructs and displays the encounter
	 * @param inputGUI
	 */
	public EncounterGasPlanet(BuildGUI inputGUI)
	{
		gui = inputGUI;
		gui.setEncounter(this);
		try {
			gui.mainBuild("encounter/gasPlanet.txt","options/gasPlanetOp.txt");
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
			gui.setEncounter(new Result(gui, "results/gasPlanet/goodLaugh.txt", modifyer));
		} else {
			//everyones sanity goes down in relation to how bad the roll is
			int damage = (50 - roll);
			int num = (int)(Math.random() * gui.getShip().getPeople().size());
			gui.getShip().getPeople().get(num).decreaseSanity(damage);
			modifyer = gui.getShip().getPeople().get(num).getName() + "'s Sanity has decreased by " + damage;
			gui.setEncounter(new Result(gui, "results/gasPlanet/badLaugh.txt", modifyer));
		}
	}

	@Override
	/**
	 * enacts option 2
	 */
	public void option2() 
	{
		int roll = (int)(100*Math.random());
		
		if (roll>50){
			//everyone's sanity goes up a bit in relation to how good the role is
			for(int i=0; i<gui.getShip().getPeople().size(); i++){
				gui.getShip().getPeople().get(i).increaseSanity((int)((roll-50)/5));
				modifyer = "All crewmembers' sanity increased by " + (int)((roll-50)/5);
			}
			gui.setEncounter(new Result(gui, "results/gasPlanet/goodWindow.txt", modifyer));
		} else if(roll>40){
			//poisonous gas leaks in
			modifyer = "";
			for(int i=0; i<gui.getShip().getPeople().size(); i++){
				int generalRoll = (int)((80*Math.random()));
				if(generalRoll<20 && generalRoll>18){
					Disease d = new Disease((int)(3*Math.random())+1);
					d.giveDisease(gui.getShip().getPeople().get(i));
					Disease c = new Disease((int)(3*Math.random())+1);
					c.giveDisease(gui.getShip().getPeople().get(i));
					if(modifyer.equals("")){
						modifyer = modifyer + gui.getShip().getPeople().get(i).getName() + " has gotten " + c.getDiseaseName() + " and " + d.getDiseaseName();
					} else{
						modifyer = modifyer + "\n" + gui.getShip().getPeople().get(i).getName() + " has gotten " + c.getDiseaseName() + " and " + d.getDiseaseName();
					}
				}
				else if(generalRoll<=10){
					Disease d = new Disease((int)(3*Math.random()+1));
					d.giveDisease(gui.getShip().getPeople().get(i));
					if(modifyer.equals("")){
						modifyer = modifyer + gui.getShip().getPeople().get(i).getName() + " has gotten " + d.getDiseaseName();
					}else{
						modifyer = modifyer + "\n" + gui.getShip().getPeople().get(i).getName() + " has gotten " + d.getDiseaseName();
					}
				}
			}
			gui.setEncounter(new Result(gui, "results/gasPlanet/sick.txt", modifyer));
			
		} else if(roll >= 35){
			//nothing happens/someone gets tackled
			int p = (int)Math.random()*(gui.getShip().getPeople().size()-1);
			while(!gui.getShip().getPeople().get(p).alive()){
				p = (int)((gui.getShip().getPeople().size()-1)*Math.random());
			}
			int num = (int)(20*Math.random());
			modifyer = gui.getShip().getPeople().get(p).getName() + "'s health has decreased by " + num;
			gui.getShip().getPeople().get(p).decreaseHealth(num);
		
		} else {
			//someone gets sucked out of the ship
			int p = (int)((gui.getShip().getPeople().size()-1)*Math.random());
			while(!gui.getShip().getPeople().get(p).alive()){
				p = (int)((gui.getShip().getPeople().size()-1)*Math.random());
			}
			modifyer = gui.getShip().getPeople().get(p).getName() + " has been sucked out a window";
			gui.getShip().getPeople().get(p).kill();
			gui.setEncounter(new Result(gui, "results/gasPlanet/badWindow.txt", modifyer));
		}
	}
}
