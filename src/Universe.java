import java.util.ArrayList;

/**
 * @author Brock Soicher, David Donnelly, Kevin Keables
 * The Orion Trail
 * April 24, 2015 - June 3, 2015
 */

public class Universe 
{
	public static Star rigel, saiph, mintaka, alnilam, alnitak, bellatrix, betelgeuse, meissa, m42;
	public static ArrayList<Star> allStars;
	/**
	 * constructs the universe
	 */
	public Universe()
	{
		rigel = new StarRigel();
		saiph = new StarSaiph();
		mintaka = new StarMintaka();
		alnilam = new StarAlnilam();
		alnitak = new StarAlnitak();
		bellatrix = new StarBellatrix();
		betelgeuse = new StarBetelgeuse();
		meissa = new StarMeissa();
		m42 = new StarM42();
		allStars = new ArrayList<Star>();
	}
	/**
	 * initiates the universe
	 */
	public void initiate()
	{
		alnilam.addConnection(mintaka);
		alnilam.addConnection(alnitak);
		alnilam.addConnection(m42);
		alnitak.addConnection(alnilam);
		alnitak.addConnection(saiph);
		alnitak.addConnection(betelgeuse);
		bellatrix.addConnection(meissa);
		bellatrix.addConnection(mintaka);
		betelgeuse.addConnection(alnitak);
		betelgeuse.addConnection(meissa);
		m42.addConnection(alnilam);
		meissa.addConnection(bellatrix);
		meissa.addConnection(betelgeuse);
		mintaka.addConnection(alnilam);
		mintaka.addConnection(bellatrix);
		mintaka.addConnection(rigel);
		rigel.addConnection(mintaka);
		rigel.addConnection(saiph);
		saiph.addConnection(alnitak);
		saiph.addConnection(rigel);
		allStars.add(rigel);
		allStars.add(saiph);
		allStars.add(mintaka);
		allStars.add(alnilam);
		allStars.add(alnitak);
		allStars.add(bellatrix);
		allStars.add(betelgeuse);
		allStars.add(meissa);
		allStars.add(m42);
	}
}
