package festival1;

/**
 * 
 * @author NATTA & COAVOUX
 *
 */
public class Navette extends Thread{
	// Le nombre de places libres dans la navette.
	public int nbPlaces;
	// Le numero de la navette (son identifiant).
	public int nNavette;
	// La liste des sites a parcourir.
	public Site[] tSite;
	// La liste des festivaliers dans la navette
	private Festivalier[] tfest;
	private int nfest;
	
	public Navette(int np, int nn, Site[] t) {
		this.nbPlaces = np;
		this.nNavette = nn;
		this.tSite = t;
		this.tfest = new Festivalier[Festival.navetteP];
		this.nfest = 0;
	}
	
	/**
	 * Faire rentrer un festivalier dans la navette.
	 * Les tests sur la capacite restante de la navette devront etre faits avant d'appeler cette fonction.
	 * Cette fonction doit etre appelee par une fonction synchronized.
	 * @param fe le festivalier a ajouter a la navette.
	 * @return le numero de place du festivalier dans la navette.
	 */
	public int addFestivalier(Festivalier fe) {
		tfest[nfest] = fe;
		int place = nfest++;
		return place;
	}
	
	/**
	 * Faire descendre un festivalier de la navette.
	 * Cette fonction doit etre appelee par une fonction synchronized.
	 * @param place le numero de place du festivalier.
	 */
	public void delFestivalier(int place) {
		for (int i = place; i < (nfest - 2); i++) {
			tfest[i] = tfest[i + 1];
		}
		tfest[nfest - 1] = null;
		nfest--;
	}
	
	/**
	 * Appele par une navette lorsqu'elle depose les festivaliers.
	 */
	public synchronized void deposer() {
		int i = nfest;
		while (nfest != 0) {
			// On "reveille" le festivalier 
			tfest[nfest - 1].arret();
			delFestivalier(nfest - 1);
		}
		if (i == 1)
			System.out.println(i + " festivalier est descendu de la navette " + nNavette);
		else if (i != 0)
			System.out.println(i + " festivaliers sont descendus de la navette " + nNavette);
	}
	
	public void run()
	{
		// Chaque navette commence a un site aleatoire.
		int n = (int) (Math.random() * Festival.nSites);
		
		while (true)
		{
			int placeNavette = tSite[n].arret.aller(this);

			if (n != (Festival.nSites - 1)) {
				// Simulation du temps de deplacement de la navette. (d'un site n au site (n+1).)
				try {
					sleep(100);
				} catch (Exception e) {}
			}
			else
			{
				// Simulation du temps de deplacement de la navette (du dernier site au premier site.)
				try {
					sleep(n*100);
				} catch (Exception e) {}
				
				deposer();
			}
		
			// Simulation de l'attente de la navette a l'arret.
			try {
				sleep(1000);
			} catch (Exception e) {}
			
			tSite[n].arret.partir(placeNavette);
			
			n = (n+1)%(Festival.nSites);
		}
	}
	
	
}