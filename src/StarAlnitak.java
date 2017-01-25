/**
 * @author Brock Soicher, David Donnelly, Kevin Keables
 * The Orion Trail
 * April 24, 2015 - June 3, 2015
 */

public class StarAlnitak extends Star
{
	private String imageFileName;
	private String mapFileName;
	/**
	 * constructs the star
	 */
	public StarAlnitak()
	{
		name = "Alnitak";
		apparentMagnitude = 2.05;
		xCoord = 200;
		yCoord = 600;
		imageFileName = "Alnitak.png";
		mapFileName = "YAHAlnitak.png";
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
