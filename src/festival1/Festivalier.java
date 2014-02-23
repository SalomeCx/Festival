package festival1;

/**
 * 
 * @author NATTA & COAVOUX
 *
 */
public class Festivalier extends Thread {
	// Stopper le thread
	private boolean stop = false;
	
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

	public void arret() {
        stop = true;
    }
	
	public void run() {
		// Simuler le temps de prendre une place
		int i = (int) (Math.random() * 10000) + 1;
		try {
			sleep(i);
		} catch (Exception e) {}
		depart.guichet.prendrePlace();

		// Seuls les festivaliers deja sur le lieu du festival s'arretent directement.
		if (depart.nSite != (Festival.nSites - 1)) {
			depart.arret.monter(this);
			
			// Le festivalier s'endort regulierement jusqu'a ce que
			// la navette dans laquelle il est le reveille en changeant la valeur de "stop".
			while(!stop) {
				try {
					sleep(100);
				} catch (Exception e) {}
		    } 
		}
	}

}