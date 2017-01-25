/**
 * @author Brock Soicher, David Donnelly, Kevin Keables
 * The Orion Trail
 * April 24, 2015 - June 3, 2015
 */

public class StarSaiph extends Star
{
	private String imageFileName;
	private String mapFileName;
	/**
	 * constructs the star
	 */
	public StarSaiph()
	{
		name = "Saiph";
		apparentMagnitude = 2.06;
		xCoord = 200;
		yCoord = 900;
		imageFileName = "Saiph.png";
		mapFileName = "YAHSaiph.png";
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
