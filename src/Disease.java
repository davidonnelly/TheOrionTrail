/**
 * @author Brock Soicher, David Donnelly, Kevin Keables
 * The Orion Trail
 * April 24, 2015 - June 3, 2015
 */

public class Disease {
	private int type;
	
	/**
	 * constructs the disease
	 * @param i sets the type of disease
	 */
	public Disease( int i ){
		//CONSTRUCTOR
		type = i;
		// 1 = space Dysentery
		// 2 = space Typhoid Fever
		// 3 = space Measles
		// 4 = space Cholera
	}
	
	/**
	 * returns the disease's name
	 * @return
	 */
	public String getDiseaseName(){
		//RETURNS STRING DISEASE NAME
		switch( type ){
		case 1:
			return "Space Dysentery";
		case 2:
			return "Space Typhoid Fever";
		case 3:
			return "Space Measles";
		case 4:
			return "Space Cholera";
		default:
			return "Healthy";
		}
	}
	
	/**
	 * gives a person the disease
	 * @param p
	 */
	public void giveDisease(Person p){
		//GIVES PERSON PARAMETER DISEASE
		p.addDisease(getDiseaseName());
		switch( type ){
		case 1:
			p.setDiseaseDamage(30);
			break;
		case 2:
			p.setDiseaseDamage(20);
			break;
		case 3:
			p.setDiseaseDamage(5);
			break;
		case 4:
			p.setDiseaseDamage(10);
			break;
		default:
			break;
		}
	}
	
	/**
	 * removes the disease from the person
	 * @param p
	 */
	public void removeDisease(Person p){
		//REMOVES DISEASE FROM PERSON PARAMETER
		switch(type){
		case 1:
			p.setDiseaseDamage(p.getDiseaseDamage()-30);
			break;
		case 2:
			p.setDiseaseDamage(p.getDiseaseDamage()-20);
			break;
		case 3:
			p.setDiseaseDamage(p.getDiseaseDamage()-5);
			break;
		case 4:
			p.setDiseaseDamage(p.getDiseaseDamage()-10);
			break;
		default:
			break;
		}
	}

}
