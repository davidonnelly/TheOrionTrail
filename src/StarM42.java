/**
 * @author Brock Soicher, David Donnelly, Kevin Keables
 * The Orion Trail
 * April 24, 2015 - June 3, 2015
 */

public class StarM42 extends Star
{
	private String imageFileName;
	private String mapFileName;
	/**
	 * constructs the star
	 */
	public StarM42()
	{
		name = "M42";
		apparentMagnitude = 4.0;
		xCoord = 500;
		yCoord = 700;
		imageFileName = "M42.png";
		mapFileName = "YAHM42.png";
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
