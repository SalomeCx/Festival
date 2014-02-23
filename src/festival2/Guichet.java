package festival2;

/**
 * 
 * @author NATTA & COAVOUX
 *
 */
public class Guichet {
	// Le numero du guichet.
	public int nGuichet;
	// Le nombre de places vendues par le guichet.
	public int nPlacesVendues;
	
	/**
	 * Constructeur d'un guichet
	 * @param ng le numero du guichet. Les deux dernier numeros representent le numero du guichet sur le site, 
	 * le(s) premier(s) numero(s) represente le numero du site.
	 */
	public Guichet(int ng) {
		this.nGuichet = ng;
		this.nPlacesVendues = 0;
	}
	
}