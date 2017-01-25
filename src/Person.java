/**
 * @author Brock Soicher, David Donnelly, Kevin Keables
 * The Orion Trail
 * April 24, 2015 - June 3, 2015
 */

public class Person {
	private int health;
	private String diseased;
	private int sanity;
	private int diseaseDamage = 0;
	private int food; 
	private boolean dead;
	private String healthStatus;
	private String hungerStatus;
	private String sanityStatus;
	private String disease;
	private String name;
	private int sanityLoss;
	private int hunger;
	private static boolean someoneAlive;
	private static Ship theShip;
	
	/**
	 * constructs the person
	 * @param myName
	 */
	public Person(String myName)
	{
		health = 100;
		diseased = "No diseases";
		sanity = 100;
		food = 100;
		dead = false;
		healthStatus = "Healthy";
		sanityStatus = "Sane";
		hungerStatus = "Well fed";
		name = myName;
		disease = "";
	}
	
	/**
	 * sets the ship
	 * @param aShip
	 */
	public void setShip(Ship aShip){
		theShip = aShip;
	}
	
	/**
	 * returns the name of the person
	 * @return
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * increases the person's health
	 * @param num
	 */
	public void increaseHealth(int num)
	{
		health =  health + num;
	}
	
	/**
	 * decerases the person's health
	 * @param num
	 */
	public void decreaseHealth(int num)
	{
		health = health - num;
		if(health <= 0)
		{
			dead = true;
		}
	}
	
	/**
	 * returns the person's health
	 * @return
	 */
	public int getHealth()
	{
		return health;
	}
	
	/**
	 * increases sanity
	 * @param num
	 */
	public void increaseSanity(int num){
		sanity += num;
		if( sanity > 100){
			sanity = 100;
		}
	}
	/**
	 * decreases sanity
	 * @param num
	 */
	public void decreaseSanity(int num){
		sanity -= num;
		if( sanity < 0){
			sanity = 0;
		}
	}
	
	/**
	 * increases sanity level
	 * @param num
	 */
	public void increaseSanityLevel(int num)
	{
		sanityLoss =  sanityLoss + num;
	}
	
	/**
	 * decreases sanity level
	 * @param num
	 */
	public void decreaseSanityLevel(int num)
	{
		sanityLoss = sanityLoss - num;
	}
	
	/**
	 * returns sanity
	 * @return
	 */
	public int getSanity()
	{
		return sanity;
	}
	
	/**
	 * increases food level
	 * @param num
	 */
	public void increaseFoodLevel(int num)
	{
		food =  food + num;
		if (food > 100){
			food = 100;
		}
	}
	
	/**
	 * increases hunger level
	 * @param num
	 */
	public void setHungerLevel(int num)
	{
		hunger = num;
	}
	
	/**
	 * returns food
	 * @return
	 */
	public int getFood()
	{
		return food;
	}
	
	/**
	 * sets disease damage
	 * @param num
	 */
	public void setDiseaseDamage(int num)
	{
		diseaseDamage = num;
	}
	/**
	 * returns disease damage
	 * @return
	 */
	public int getDiseaseDamage()
	{
		return diseaseDamage;
	}
	
	/**
	 * returns disease status
	 * @return
	 */
	public String getDiseaseStatus(){
		if(alive()){
			if(diseaseDamage > 0){
				return "Diseased";
			}
			else{
				return "Healthy";
			}
		}
		return "DEAD";
	}
	
	/**
	 * adds a disease name
	 * @param name
	 */
	public void addDisease(String name){
		if (!disease.contains(name)){
			if(!disease.equals("")){
				disease = disease + ", " + name;
			}
			else{
				disease = disease + name;
			}
		}
	}
	/**
	 * removes a disease name
	 * @param name
	 */
	public void removeDisease(String name){
		if( disease.contains(name)){
			if( disease.contains(", "+name)){
				disease.replaceFirst(", "+name, "");
			}
			else{
				disease.replaceFirst(name, "");
			}
		}
	}
	
	/**
	 * returns the disease name
	 * @return
	 */
	public String getDiseases(){
		if( diseaseDamage <= 0){
			return "None";
		}
		if(!alive()){
			return "DEAD";
		}
		return disease;
	}
	
	/**
	 * returns the hunger status
	 * @return
	 */
	public String getHungerStatus()
	{
		return hungerStatus;
	}
	
	/**
	 * returns the health status
	 * @return
	 */
	public String getHealthStatus()
	{
		return healthStatus;
	}
	
	/**
	 * returns the sanity status
	 * @return
	 */
	public String getSanityStatus()
	{
		return sanityStatus;
	}
	
	/**
	 * returns true if alive, false if dead
	 * @return
	 */
	public boolean alive(){
		return !dead;
	}
	
	/**
	 * increments the day and returns false if the person died that day
	 * @return
	 */
	public boolean nextDay() //boolean is if survived
	{
		if(dead == false)
		{
			health = health - diseaseDamage;
			sanity = sanity - sanityLoss;
			food = food - hunger;
			
			if(theShip.getCargo().getTangRations()>=1){
				theShip.getCargo().setTangRations(theShip.getCargo().getTangRations()-.4);
				hunger = 0;
			}
			else{
				hunger += 10;
			}
			
			if(food>75 && food<= 100)
			{
				hungerStatus = "Well fed";
			}
			else if(food<75 && food>= 50)
			{
				hungerStatus = "Fed";
			}
			else if(food<50 && food>=25)
			{
				hungerStatus = "Hungry";
			}
			else if(food <25 && food>=0)
			{
				hungerStatus = "Starving";
			}
			else{
				hungerStatus = "Starving";
				food = 0;
			}
			
			if(health>75 && health<= 100)
			{
				healthStatus = "Healthy";
			}
			else if(health<75 && health>= 50)
			{
				healthStatus = "Well";
			}
			else if(health<50 && health>=25)
			{
				healthStatus = "Hurt";
			}
			else if(health <25)
			{
				healthStatus = "Dying";
			}
			if(diseaseDamage>0)
			{
				diseased = "Diseased";
			}
			
			if(sanity>75 && sanity<= 100)
			{
				sanityStatus = "No issues";
			}
			else if(sanity<75 && sanity>= 50)
			{
				sanityStatus = "Sane";
			}
			else if(sanity<50 && sanity>=25)
			{
				sanityStatus = "Battered";
			}
			else if(sanity<25){
				sanityStatus = "Insane";
			}
			else if(food <25)
			{
				sanityStatus = "Insane";
			}
			if(food <=0)
			{
				food = 0;
				health = health - 5;
				sanity = sanity - 5;
			}
			if(sanity<=0)
			{
				sanity = 0;
				health = health -5;
			}
			
			if(health<=0)
			{
				dead = true;
			}
			if(dead){
				return false;
			}
		}
		return true;
	}
	/**
	 * kills the person
	 */
	public void kill()
	{
		dead = true;
		for(int j=0; j<theShip.getPeople().size(); j++){
			theShip.getPeople().get(j).decreaseSanity(20);
		}
	}
	/**
	 * returns a string of the person
	 */
	public String toString()
	{
		return name + ": " + healthStatus + " " + sanityStatus + " " + hungerStatus;
	}
}
