package festival;

public class Festivalier extends Thread {
	public int nFest;
	public Site depart;
	
	public Festivalier(int n, Site d) {
		this.nFest = n;
		this.depart = d;
	}
	
	public void run() {
		// Simuler le temps de prendre une place
		try {
			sleep(500);
		} catch (Exception e) {}
		
		depart.guichet.prendrePlace();
		
		if (depart.nSite != (Festival.nSites - 1)) {
			// Attendre la navette.
			synchronized(depart.arret) {
				depart.arret.nAttente++;
			}
		}
		
		
			
		
	}

}
