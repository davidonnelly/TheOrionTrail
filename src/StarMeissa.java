/**
 * @author Brock Soicher, David Donnelly, Kevin Keables
 * The Orion Trail
 * April 24, 2015 - June 3, 2015
 */
public class StarMeissa extends Star
{
	private String imageFileName;
	private String mapFileName;
	/**
	 * constructs the star
	 */
	public StarMeissa()
	{
		name = "Meissa";
		apparentMagnitude = 3.54;
		xCoord = 500;
		yCoord = 100;
		imageFileName = "Meissa.png";
		mapFileName = "YAHMeissa.png";
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
