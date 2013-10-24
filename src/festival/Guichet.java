package festival;

public class Guichet {
	public int nGuichet;
	public int nPlacesVendues;
	
	public Guichet(int ng) {
		this.nGuichet = ng;
		this.nPlacesVendues = 0;
	}
	
	public synchronized void prendrePlace() {
		nPlacesVendues++;
		System.out.println("Le guichet " + nGuichet + " a vendu " + nPlacesVendues + " places.");
	}
}
