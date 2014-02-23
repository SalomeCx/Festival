package festival2;

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
	// De base, la destination est le festival.
	// Si un client n'a pas pu avoir de ticket, il se dirigera vers le site suivant.
	public Site destination;
	// La "carte" des navettes: l'ensemble des sites couverts par les navettes.
	public Site[] carte;
	
	/**
	 * Constructeur d'un festivalier
	 * @param n le numero du festivalier
	 * @param d le site de depart du festivalier
	 * @param a le site de destination du festivalier. Par defaut, cette valeur est le site du festival.
	 * La destination est changee si un festivalier ne trouve pas de place sur son site de depart.
	 * Le site destination devient alors le site suivant son site de depart.
	 * @param carte la "carte" du trajet des navettes.
	 */
	public Festivalier(int n, Site d, Site a, Site[] carte) {
		this.nFest = n;
		this.depart = d;
		this.destination = a;
		this.carte = carte;
	}

	/**
	 * Utilise pour le voyage en navette. Si b = true, c'est que le festivalier doit
	 * se reveiller et sortir de la navette. 
	 * @param b le booleen a changer.
	 */
	public void setStop(boolean b) {
        stop = b;
    }
	
	public void run() {
		// Simuler le temps de prendre une place
		int i = (int) (Math.random() * 10000) + 1;
		try {
			sleep(i);
		} catch (Exception e) {}
		
		// Un guichet au hasard
		int j = (int) (Math.random() * depart.guichet.length);
		// Le festivalier a-t-il pu avoir sa place?
		boolean trouverPlace = depart.prendrePlace(depart.guichet[j]);
		
		// Certains festivaliers trouvent leur place directement.
		if (trouverPlace) {
			// Seuls les festivaliers deja sur le lieu du festival s'arretent directement.
			if (depart.nSite != (Festival.nSites - 1)) {
				depart.arret.monter(this);
				while(!stop) {
					try {
						sleep(100);
					} catch (Exception e) {}
				} 
			}
			else {
				System.out.println("Festivalier " + nFest + " est deja sur le site du festival.");
			}
		}
		
		else {
			Site current = depart;
			Site next = carte[(current.nSite + 1)%(Festival.nSites)];
			// Tant qu'on ne trouve pas de place, on avance vers le prochain site.
			// Si le festivalier parcours tous les sites et ne trouve pas de place,
			// il rentre chez lui.
			while (!trouverPlace && !(next.equals(depart))) {
				// Avancer.
				destination = next;
				current.arret.monter(this);
				
				while(!stop) {
					try {
						sleep(50);
					} catch (Exception e) {}
				}
				setStop(false);
				
				current = next;
				next = carte[(current.nSite + 1)%(Festival.nSites)];
				
				j = (int) (Math.random() * current.guichet.length);
				trouverPlace = current.prendrePlace(current.guichet[j]);
			}
			
			if (trouverPlace){
				if (current.nSite != (Festival.nSites - 1)) {
					destination = carte[Festival.nSites - 1];
					current.arret.monter(this);
					while(!stop) {
						try {
							sleep(100);
						} catch (Exception e) {}
					}
				}
				else
					System.out.println("Festivalier " + nFest + " est deja sur le site du festival.");
			}
			else {
				System.out.println("Le festivalier " + nFest + " n'a pas pu trouver de place et rentre chez lui.");
			}
		}
	}

}