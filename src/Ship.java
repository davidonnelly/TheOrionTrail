import java.util.ArrayList;

/**
 * @author Brock Soicher, David Donnelly, Kevin Keables
 * The Orion Trail
 * April 24, 2015 - June 3, 2015
 */

public class Ship 
{
	private int fuel, hullHealth, movementSpeed, xCoord, yCoord;
	private String name, location, condition;
	private Star currentStar, destinationStar;
	private boolean broken, upgraded, event;
	private ArrayList<Upgrade> upgrades;
	private Cargo cargo;
	private Game theGame;
	private int numOfJumps;
	private ArrayList<Person> people; 
	/**
	 * This constructs the ship with no parameters
	 */
	public Ship()
	{
		//DEFAULT CONSTRUCTOR
		cargo = new Cargo(0,50,10,10);
		fuel = 10;
		hullHealth = 100;
		movementSpeed = 100;
		name  = "Heart of Gold";
		condition = "Brand-Spanking-New!";
		broken = false;
		upgraded = false;
		people = new ArrayList<Person>();
		currentStar = Universe.rigel;
		destinationStar = Universe.rigel;
	}
	/**
	 * this constructs the ship with parameters
	 * @param nameIn
	 * @param fuelIn
	 * @param startingLocation
	 */
	public Ship(String nameIn, int fuelIn, Star startingLocation)
	{
		// MAX INPUT CONSTRUCTOR
		fuel = fuelIn;
		hullHealth = 100;
		movementSpeed = 100;
		name  = nameIn;
		condition = "Brand-Spanking-New!";
		broken = false;
		upgraded = false;
		location = startingLocation.getName();
		currentStar = startingLocation;
		xCoord = currentStar.getXCoord();
		yCoord = currentStar.getYCoord();
		//cargo = new Cargo();
		people = new ArrayList<Person>();
	}
	
	/**
	 * this adds a person to the ship
	 * @param p
	 */
	public void addPerson(Person p)
	{
			people.add(p);
	}
	/**
	 * returns fuel for the ship
	 * @return
	 */
	public int getFuel() 
	{
		// RETURNS FUEL
		return fuel;
	}
	/**
	 * sets the fuel
	 * @param fuelIn
	 */
	public void setFuel(int fuelIn) 
	{
		// SETS FUEL
		fuel = fuelIn;
	}
	/**
	 * returns the game
	 * @return
	 */
	public Game getGame(){
		return theGame;
	}
	/**
	 * sets the game
	 * @param aGame
	 */
	public void setGame(Game aGame){
		theGame = aGame;
	}
	/**
	 * returns the cargo
	 * @return
	 */
	public Cargo getCargo(){
		return cargo;
	}
	/**
	 * returns the hull health
	 * @return
	 */
	public int getHullHealth()
	{
		// RETURNS HULL HEALTH
		return hullHealth;
	}
	/**
	 * sets the hull health
	 * @param hullHealthIn
	 */
	public void setHullHealth(int hullHealthIn) 
	{
		// SET HULL HEALTH
		hullHealth = hullHealthIn;
		if (hullHealth <= 99)
		{
			condition = "Good";
		}
		if (hullHealth < 75)
		{
			condition = "Decent";
		}
		if (hullHealth < 50)
		{
			condition = "Clunker";
		}
		if (hullHealth < 25)
		{
			condition = "Severely Damaged";
		}
		if (hullHealth < 10)
		{
			condition = "Junk Pile";
		}
		if (hullHealth <= 0)
		{
			condition = "Busted";
			broken = true;
		}
	}
	/**
	 * returns true if has crew
	 * @return
	 */
	public boolean hasCrew()
	{
		// RETURNS TRUE IF PEOPLE ARRAYLIST IS NOT EMPTY
		return people.size() > 0;
	}
	/**
	 * returns the movement speed
	 * @return
	 */
	public int getMovementSpeed() 
	{
		// RETURNS MOVEMENT SPEED
		return movementSpeed;
	}
	/**
	 * sets the movement speed
	 * @param movementSpeedIn
	 */
	public void setMovementSpeed(int movementSpeedIn) 
	{
		// SETS MOVEMENT SPEED
		movementSpeed = movementSpeedIn;
	}
	/**
	 * returns the name of the ship
	 * @return
	 */
	public String getName() 
	{
		// RETURNS NAME OF SHIP
		return name;
	}
	/**
	 * sets the name of the ship
	 * @param nameIn
	 */
	public void setName(String nameIn) 
	{
		// SETS NAME OF SHIP
		name = nameIn;
	}
	/**
	 * returns the location of the ship
	 * @return
	 */
	public String getLocation() 
	{
		// RETURNS LOCATION NAME
		return location;
	}
	/**
	 * sets the location of the ship
	 * @param locationIn
	 */
	public void setLocation(String locationIn) 
	{
		// SETS LOCATION NAME
		location = locationIn;
	}
	/**
	 * returns the condition of the ship
	 * @return
	 */
	public String getCondition() 
	{
		// RETURNS STRING CONDITION OF SHIP
		return condition;
	}
	/**
	 * sets the condition of the ship
	 * @param conditionIn
	 */
	public void setCondition(String conditionIn)
	{
		// SETS CONDITION OF SHIP
		condition = conditionIn;
	}
	/**
	 * returns true if broken
	 * @return
	 */
	public boolean isBroken() 
	{
		// RETURNS TRUE IF BROKEN
		return broken;
	}
	/**
	 * sets if broken
	 * @param isBroken
	 */
	public void setBroken(boolean isBroken) 
	{
		// SETS BOOLEAN BROKEN
		broken = isBroken;
		if (isBroken)
		{
			condition = "busted";
		}
	}
	/**
	 * installs an upgrade
	 * @param upgradeIn
	 */
	public void install(Upgrade upgradeIn)
	{
		// ADDS UPGRADE
		upgrades.add(upgradeIn);
		upgraded = true;
	}
	/**
	 * returns true if has upgrade
	 * @return
	 */
	public boolean hasUpgrade()
	{
		// RETURNS TRUE IF UPGRADES > 0
		return upgraded;
	}
	/**
	 * returns true if has fuel
	 * @return
	 */
	public boolean hasFuel()
	{
		return fuel > 0;
	}
	/**
	 * returns x Coordinate
	 * @return
	 */
	public int getXCoord()
	{
		return xCoord;
	}
	/**
	 * returns y coordinate
	 * @return
	 */
	public int getYCoord()
	{
		return yCoord;
	}
	/**
	 * returns true if at a star
	 * @return
	 */
	public boolean isAtStar()
	{
		/*for (Star star : Universe.allStars)
		{
			if ((xCoord >= star.getXCoord() - movementSpeed || xCoord <= star.getXCoord() + movementSpeed) &&
					(yCoord >= star.getYCoord() - movementSpeed || yCoord <= star.getYCoord() + movementSpeed))
			{
				return true;
			}
		}
		return false;*/
		
		if(currentStar == destinationStar){
			return true;
		}
		return false;
	}
	/**
	 * moves between instances
	 * @param destination
	 */
	public void fly(Star destination){
		if(cargo.getEnergyCells() > 0){
			destinationStar = destination;
			if(numOfJumps>=3){
				currentStar = destination;
				numOfJumps = 0;
			}
			cargo.setEnergyCells(cargo.getEnergyCells()-1);
			if(currentStar == Universe.betelgeuse){
				theGame.win();
			}
			numOfJumps ++;
			//System.out.println("flying");
			setLocation("void of space");
			//move(movementSpeed, destination);
		}
		else{
			System.out.println("You lose");
		}
	}

	/**
	 * returns destination
	 * @return
	 */
	public Star getDestination()
	{
		return destinationStar;
	}
	/**
	 * consumes a fuel
	 * @param fuelUsed
	 */
	public void useFuel(int fuelUsed)
	{
		fuel -= fuelUsed;
	}
	/**
	 * moves the ship
	 * @param speed
	 * @param destination
	 */
	public void move(int speed, Star destination)
	{
		if (!broken && fuel > 0 && !event && getDistanceTo(destination) > speed)
		{	
			if (xCoord < destination.getXCoord())
			{
				xCoord += speed;
			}
			if (yCoord < destination.getYCoord())
			{
				yCoord += speed;
			}
			if (xCoord > destination.getXCoord())
			{
				xCoord -= speed;
			}
			if (yCoord > destination.getYCoord())
			{
				yCoord -= speed;
			}   
			useFuel(1);
		}
		if (fuel < 0 )
		{
			System.out.println("You're out of fuel");
		}
		if (xCoord == destination.getXCoord() && yCoord == destination.getYCoord())
		{
			dock(destination);
		}
	}
	/**
	 * returns distance to a star
	 * @param destination
	 * @return
	 */
	public int getDistanceTo(Star destination)
	{
		int xDistance = xCoord - destination.getXCoord();
		int yDistance = yCoord - destination.getYCoord();
		System.out.println("calculating distance");
		if (xDistance < 0)
		{
			xDistance *= -1;
		}
		if (yDistance < 0)
		{
			yDistance *= -1;
		}
		return (xDistance + yDistance);
	}
	/**
	 * retuns an array list of the crew
	 * @return
	 */
	public ArrayList<Person> getPeople()
	{
		return people;
	}
	/**
	 * docks at a star
	 * @param orbitingStar
	 */
	public void dock(Star orbitingStar)
	{
		System.out.println("docking");
		setLocation(orbitingStar.getName());
		setCurrentStar(orbitingStar);
	}
	/**
	 * prints the stats
	 */
	public void printStats()
	{
		System.out.println(name);
		System.out.println(condition);
		System.out.println(location);
		System.out.println(fuel);
	}
	/**
	 * ejects a crewmember from the airlock
	 * @param goner
	 */
	public void ejectFromAirlock(Person goner)
	{
		goner.kill();
	}
	/**
	 * returns current map name
	 * @return
	 */
	public String getCurrentMapName()
	{
		if(currentStar == destinationStar){
			return currentStar.getMapFileName();
		}
		else{
			return "YAHGeneric.png";
		}
	}
	/**
	 * returns current image name
	 * @return
	 */
	public String getCurrentImageName()
	{
		return currentStar.getImageFileName();
	}
	/**
	 * sets the destination star
	 * @param starIn
	 */
	public void setDestination(Star starIn)
	{
		destinationStar = starIn;
	}
	/**
	 * sets the current star
	 * @param starIn
	 */
	public void setCurrentStar(Star starIn)
	{
		setLocation(starIn.getName());
		xCoord = starIn.getXCoord();
		yCoord = starIn.getYCoord();
		currentStar = starIn;
	}
	/**
	 * returns the current star
	 * @return
	 */
	public Star getCurrentStar()
	{
		return currentStar;
	}
	/**
	 * returns true if at destination
	 * @return
	 */
	public boolean atDestination()
	{
		return currentStar.equals(Universe.betelgeuse);
	}
}
