package festival2;

/**
 * 
 * @author NATTA & COAVOUX
 *
 */
public class Arret {
	// Le numero de l'arret (similaire a nSite.)
	public int nArret;
	public int nAttente;
	// La navette courante qui attend a l'arret.
	public Navette curNav;
	
	// Utile pour la question bonus seulement. Represente le numero de la navette
	// prioritaire sur l'arret: sert a empecher les navettes de se doubler.
	public int prioritaire;
	
	/**
	 * Constructeur d'un arret
	 * @param n le numero de l'arret, egal au numero du site sur lequel il est implante.
	 */
	public Arret(int n) {
		this.nArret = n;
		this.nAttente = 0;
		this.curNav = null;
		this.prioritaire = 0;
	}
	
	/**
	 * Appele par un festivalier qui essaie de monter dans la navette courante.
	 */
	public synchronized void monter(Festivalier fest) {
		// S'il y a une navette et qu'elle n'est pas pleine.
		while ((curNav == null) || (curNav.nbPlaces == 0)) {
			try {
				wait();
			} catch (Exception e) {}
		}
		curNav.addFestivalier(fest);
	}

	
	/**
	 * Appele par une navette pour se rendre au prochain arret.
	 * Un fois arrivee, elle doit notifier les festivaliers qui attendent a l'arret.
	 * @param n la navette qui doit aller a l'arret.
	 */
	public synchronized void aller(Navette n) {
		while ((curNav != null) && (prioritaire != n.nNavette)) {
			try {
				wait();
			} catch (Exception e) {}
		}
		curNav = n;
		notifyAll();
	}
	
	/**
	 * Appele par une navette des qu'elle repart de l'arret ou elle est.
	 * Elle doit notifier les autres navettes.
	 * @param n la navette qui part de cet arret.
	 */
	public synchronized void partir(Navette n) {
		curNav = null;
		prioritaire = (n.nNavette + 1)% Festival.nNavettes;
		notifyAll();
	}
}