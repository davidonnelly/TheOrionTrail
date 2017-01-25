/**
 * @author Brock Soicher, David Donnelly, Kevin Keables
 * The Orion Trail
 * April 24, 2015 - June 3, 2015
 */

public class StarAlnilam extends Star
{
	private String imageFileName;
	private String mapFileName;
	/**
	 * constructs the star
	 */
	public StarAlnilam()
	{
		name = "Alnilam";
		apparentMagnitude = 1.7;
		xCoord = 500;
		yCoord = 600;
		imageFileName = "Alnilam.png";
		mapFileName = "YAHAlnilam.png";
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
