/**
 * @author Brock Soicher, David Donnelly, Kevin Keables
 * The Orion Trail
 * April 24, 2015 - June 3, 2015
 */

public class Store {
	//stock
	private int repairParts;
	private int tangRations;
	private int energyCells;
	private Cargo shipCargo;
	//private Upgrade shipUpgrade;
	
	//cost multiplier
	private double cost;
	
	/**
	 * constructs the store
	 * @param expence
	 * @param myCargo
	 */
	public Store(double expence, Cargo myCargo){
		cost = expence;
		shipCargo = myCargo;
		repairParts = (int)(((2/cost)*5)+(10*Math.random()));
		tangRations = (int)(((2/cost)*5)+(30*Math.random()));
		energyCells = (int)(((2/cost)*5)+(20*Math.random()));
	}
	
	/**
	 * buy generic
	 * @param num
	 */
	public void buy( int num ){
		switch(num){
		case 1:
			buyParts();
			break;
		case 2:
			buyTang();
			break;
		case 3:
			buyCell();
			break;
		case 4:
			buyUpgrade();
			break;
		default:
			//error
		}
	}
	
	/**
	 * buys parts
	 */
	public void buyParts(){
		if(repairParts > 0){
			if(shipCargo.getWoolongs()>= getPartPrice()){
				repairParts --;
				shipCargo.addRepairParts(1);
				shipCargo.addWoolongs(-(int)(6*cost));
			}
		}
	}
	
	/**
	 * buys tang rations
	 */
	public void buyTang(){
		if(tangRations > 0){
			if(shipCargo.getWoolongs()>= getTangPrice()){
				tangRations --;
				shipCargo.addTangRations(1);
				shipCargo.addWoolongs(-(int)(4*cost));
			}
		}
	}
	
	/**
	 * buys energy cells
	 */
	public void buyCell(){
		if(energyCells > 0){
			if(shipCargo.getWoolongs()>= getPartPrice()){
				energyCells --;
				shipCargo.addEnergyCells(1);
				shipCargo.addWoolongs(-(int)(5*cost));
			}
		}
	}
	
	/**
	 * buys an upgrade
	 */
	public void buyUpgrade(){
		//stuff
	}

	/**
	 * returns repair parts
	 * @return
	 */
	public int getRepairParts(){
		return repairParts;
	}
	/**
	 * returns tang rations
	 * @return
	 */
	public int getTangRations(){
		return tangRations;
	}
	/**
	 * returns energy cells
	 * @return
	 */
	public int getEnergyCells(){
		return energyCells;
	}
	/**
	 * returns cargo
	 * @return
	 */
	public Cargo getCargo(){
		return shipCargo;
	}
	
	/**
	 * returns part price
	 * @return
	 */
	public int getPartPrice(){
		return (int)(cost*6);
	}
	/**
	 * returns cell price
	 * @return
	 */
	public int getCellPrice(){
		return (int)(cost*5);
	}
	/**
	 * returns Tang Price
	 * @return
	 */
	public int getTangPrice(){
		return (int)(cost*4);
	}

}
