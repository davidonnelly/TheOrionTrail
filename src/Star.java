import java.util.ArrayList;

/**
 * @author Brock Soicher, David Donnelly, Kevin Keables
 * The Orion Trail
 * April 24, 2015 - June 3, 2015
 */

public abstract class Star 
{
	protected String name;
	protected double apparentMagnitude;
	protected ArrayList<Star> connectedStars = new ArrayList<Star>();
	protected int xCoord;
	protected int yCoord;
	protected boolean hasShop, friendly;
	/**
	 * constructs the star
	 */
	public Star()
	{
		
	}
	
	/**
	 * enacts option 1
	 * @return
	 */
	public Star option1()
	{
		return connectedStars.get(0);
	}
	/**
	 * enacts option 2
	 * @return
	 */
	public Star option2()
	{
		return connectedStars.get(1);
	}
	
	/**
	 * enacts option 3
	 * @return
	 */
	public Star option3()
	{
		if (connectedStars.size() > 2)
		{
			return connectedStars.get(2);
		} 
		return null;
	}
	/**
	 * returns image file name
	 * @return
	 */
	public String getImageFileName()
	{
		return "";
	}
	/**
	 * returns map file name
	 * @return
	 */
	public String getMapFileName()
	{
		return "";
	}
	/**
	 * adds a connection
	 * @param s
	 */
	public void addConnection(Star s)
	{
		connectedStars.add(s);
	}
	/**
	 * returns the connections of a star
	 * @return
	 */
	public ArrayList<Star> getConnections()
	{
		return connectedStars;
	}
	/**
	 * returns the name of a star
	 * @return
	 */
	public String getName()
	{
		return name;
	}
	/**
	 * returns the apparent magnitude of the star
	 * @return
	 */
	public double getApparentMagnitude()
	{
		return apparentMagnitude;
	}
	/**
	 * returns if it has a shop
	 * @return
	 */
	public boolean hasShop()
	{
		return hasShop;
	}
	/**
	 * returns if it is friendly
	 * @return
	 */
	public boolean isFriendly()
	{
		return friendly;
	}
	/**
	 * returns x coordinate
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
	 * returns option 1 name
	 * @return
	 */
	public String option1Name()
	{
		return connectedStars.get(0).getName();
	}
	/**
	 * returns option 2 name
	 * @return
	 */
	public String option2Name()
	{
		return connectedStars.get(1).getName();
	}
	/**
	 * returns option 3 name
	 * @return
	 */
	public String option3Name()
	{
		if (connectedStars.size() > 2)
		{
		return connectedStars.get(2).getName();
		}
		return "";
	}
}
