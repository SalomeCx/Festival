package festival0;

/**
 * 
 * @author NATTA & COAVOUX
 *
 */
public class Festivalier extends Thread {
	// L'identifiant du festivalier.
	public int nFest;
	// Le site de depart du festivalier.
	public Site depart;
	public Site arrivee;
	
	public Festivalier(int n, Site d, Site a) {
		this.nFest = n;
		this.depart = d;
		this.arrivee = a;
	}
	
	public void run() {
		
		// Simuler le temps de prendre une place
		int i = (int) (Math.random() * 10000) + 1;
		try {
			sleep(i);
		} catch (Exception e) {}
		depart.guichet.prendrePlace();

		if (depart.nSite != (Festival.nSites - 1)) {
			// Augmenter le compteur des festivaliers qui attendent a l'arret
			depart.arret.attendreNavette();
			// S'endormir au dernier arret en attendant qu'une navette reveille le festivalier.
			arrivee.arret.arriver();
		}
		
	}

}