import java.util.ArrayList;

/**
 * @author Brock Soicher, David Donnelly, Kevin Keables
 * The Orion Trail
 * April 24, 2015 - June 3, 2015
 */

public class StarRigel extends Star 
{
	private String imageFileName;
	private String mapFileName;
	/**
	 * constructs the star
	 */
	public StarRigel()
	{
		name = "Rigel";
		apparentMagnitude = 0.12;
		xCoord = 700;
		yCoord = 900;
		imageFileName = "Rigel.png";
		mapFileName = "YAHRigel.png";
	}
	@Override
	/**
	 * returns the image file name
	 */
	public String getImageFileName()
	{
		return imageFileName;
	}
	@Override
	/**
	 * returns the map file name
	 */
	public String getMapFileName()
	{
		return mapFileName;
	}
}
