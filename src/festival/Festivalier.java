package festival;

/**
 * 
 * @author NATTA & COAVOUX
 *
 */
public class Festivalier extends Thread {
	// L'identifiant du festivalier.
	public int nFest;
	// Le site de départ du festivalier.
	public Site depart;
	
	public Festivalier(int n, Site d) {
		this.nFest = n;
		this.depart = d;
	}
	
	public void run() {

		depart.guichet.prendrePlace();
		// Simuler le temps de prendre une place
		try {
			sleep(100);
		} catch (Exception e) {}
	
		if (depart.nSite != (Festival.nSites - 1)) {
			depart.arret.attendreNavette();
		}		
	}

}
