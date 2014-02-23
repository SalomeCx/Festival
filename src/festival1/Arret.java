package festival1;

/**
 * 
 * @author NATTA & COAVOUX
 *
 */
public class Arret {
	// Le numero de l'arret (similaire a nSite.)
	public int nArret;
	public int nAttente;
	// Les navettes qui attendent a l'arret.
	public Navette curNav[];
	// Le nombre de navettes qui attendent.
	public int nNavettes;
	
	public Arret(int n) {
		this.nArret = n;
		this.nAttente = 0;
		this.curNav = new Navette[Festival.nNavettes];
		this.nNavettes = 0;
	}
	
	/**
	 * Appele par un festivalier qui essaie de monter dans la navette courante.
	 */
	public synchronized void monter(Festivalier fest) {
		// le festivalier est-il dans une navette?
		boolean in = false;
		// tant qu'il n'y a pas de navette, le festivalier s'endort.
		// une fois reveille, il essaie de monter dans une navette.
		while (nNavettes == 0 || !in) {
			try {
				wait();
			} catch (Exception e) {}
			if (nNavettes != 0) {
				for (int i = 0; i < nNavettes; i++) {
					if (curNav[i].nbPlaces != 0) {
						curNav[i].addFestivalier(fest);
						curNav[i].nbPlaces--;
						in = true;
						break;
					}
				}
			}
		}		
	}

	
	/**
	 * Appele par une navette pour se rendre au prochain arret.
	 * Un fois arrivee, elle doit notifier les festivaliers qui attendent a l'arret.
	 */
	public synchronized int aller(Navette n) {
		curNav[nNavettes] = n;
		int ret = nNavettes;
		nNavettes++;
		System.out.println("Navette " + n.nNavette + " arrive à l'arrêt " + this.nArret);
		notifyAll();
		return ret;
	}
	
	/**
	 * Appele par une navette des qu'elle repart de l'arret ou elle est.
	 */
	public synchronized void partir(int place) {
		for (int i = place; i < (nNavettes - 2); i++) {
			curNav[i] = curNav[i + 1];
		}
		curNav[nNavettes - 1] = null;
		nNavettes--;
	}
}