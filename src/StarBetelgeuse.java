/**
 * @author Brock Soicher, David Donnelly, Kevin Keables
 * The Orion Trail
 * April 24, 2015 - June 3, 2015
 */

public class StarBetelgeuse extends Star
{
	private String imageFileName;
	private String mapFileName;
	/**
	 * constructs the star
	 */
	public StarBetelgeuse()
	{
		name = "Betelgeuse";
		apparentMagnitude = 0.5;
		xCoord = 200;
		yCoord = 100;
		imageFileName = "Betelgeuse.png";
		mapFileName = "YAHBetelgeuse.png";
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
