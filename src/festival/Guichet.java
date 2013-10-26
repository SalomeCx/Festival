package festival;

/**
 * 
 * @author NATTA & COAVOUX
 *
 */
public class Guichet {
	// Le numero du guichet, c'est-à-dire le numéro du site sur lequel il est.
	public int nGuichet;
	// Le nombre de places vendues par le guichet.
	public int nPlacesVendues;
	
	public Guichet(int ng) {
		this.nGuichet = ng;
		this.nPlacesVendues = 0;
	}
	
	/**
	 * Simuler le fait de vendre des places.
	 * Appelé par les festivaliers.
	 */
	public synchronized void prendrePlace() {
		nPlacesVendues++;
		System.out.println("Le guichet " + nGuichet + " a vendu " + nPlacesVendues + " places.");
	}
}
