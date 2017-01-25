/**
 * @author Brock Soicher, David Donnelly, Kevin Keables
 * The Orion Trail
 * April 24, 2015 - June 3, 2015
 */

public class Cargo {
	private int repairParts;
	private int woolongs;
	private double tangRations;
	private int energyCells;
	
	/**
	 * constructs the cargo
	 * @param rP repair parts
	 * @param wL woolongs
	 * @param tR tang rations
	 * @param eC energy cells
	 */
	public Cargo( int rP, int wL, int tR, int eC){
		repairParts = rP;
		woolongs = wL;
		tangRations = tR;
		energyCells = eC;
	}
	
	/**
	 * adds repair parts
	 * @param rP
	 */
	public void addRepairParts(int rP){
		repairParts += rP;
	}
	/**
	 * sets repair parts
	 * @param rP
	 */
	public void setRepairParts(int rP){
		repairParts = rP;
	}
	/**
	 * returns repair parts
	 * @return
	 */
	public int getRepairParts(){
		return repairParts;
	}
	
	/**
	 * adds woolongs
	 * @param w
	 */
	public void addWoolongs(int w){
		woolongs += w;
	}
	/**
	 * sets woolongs
	 * @param w
	 */
	public void setWoolongs(int w){
		woolongs = w;
	}
	/**
	 * returns woolongs
	 * @return
	 */
	public int getWoolongs(){
		return woolongs;
	}
	
	/**
	 * adds tang rations
	 * @param t
	 */
	public void addTangRations(int t){
		tangRations += t;
	}
	/**
	 * sets tang rations
	 * @param t
	 */
	public void setTangRations(double t){
		tangRations = t;
	}
	/**
	 * returns tang rations
	 * @return
	 */
	public int getTangRations(){
		return (int)tangRations;
	}
	
	/**
	 * adds energy cells
	 * @param e
	 */
	public void addEnergyCells(int e){
		energyCells += e;
	}
	/**
	 * sets energy cells
	 * @param e
	 */
	public void setEnergyCells(int e){
		energyCells = e;
	}
	/**
	 * returns energy cells
	 * @return
	 */
	public int getEnergyCells(){
		return energyCells;
	}

}
