/**
 * @author Brock Soicher, David Donnelly, Kevin Keables
 * The Orion Trail
 * April 24, 2015 - June 3, 2015
 */

public class StarBellatrix extends Star
{
	private String imageFileName;
	private String mapFileName;
	/**
	 * constructs the star
	 */
	public StarBellatrix()
	{
		name = "Bellatrix";
		apparentMagnitude = 1.64;
		xCoord = 700;
		yCoord = 100;
		imageFileName = "Bellatrix.png";
		mapFileName = "YAHBellatrix.png";
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
