package festival2;

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
	
	public int vitesseMax;
	
	/**
	 * Constructeur d'une navette
	 * @param np le nombre de places total dans la navette
	 * @param nn le numero de la navette
	 * @param t la liste des sites sur son parcours
	 */
	public Navette(int np, int nn, Site[] t, int vit) {
		this.nbPlaces = np;
		this.nNavette = nn;
		this.tSite = t;
		this.tfest = new Festivalier[Festival.navetteP];
		for (int i = 0; i < tfest.length; i++) {
			this.tfest[i] = null;
		}
		this.vitesseMax = vit;
	}
	
	/**
	 * Faire rentrer un festivalier dans la navette.
	 * Les tests sur la capacite restante de la navette devront etre faits avant d'appeler cette fonction
	 * @param fe le festivalier a ajouter a la navette.
	 */
	public synchronized void addFestivalier(Festivalier fe) {
		for (int i = 0; i < tfest.length; i++) {
			if (tfest[i] == null) {
				tfest[i] = fe;
				nbPlaces--;
				break;
			}
		}	
	}
	
	/**
	 * Faire descendre un festivalier de la navette.
	 * @param place le numero de place du festivalier.
	 */
	public synchronized void delFestivalier(int place) {
		tfest[place] = null;
		nbPlaces++;
	}
	
	/**
	 * Appele par une navette lorsqu'elle depose les festivaliers.
	 */
	public synchronized void deposer(Site n) {
		// Compteur du nombre de personnes descendues pour le print.
		int cpt = 0;
		
		for (int i = 0; i < tfest.length; i++) {
			if ((tfest[i] != null) && (n.equals(tfest[i].destination))){
				cpt++;
				// On "reveille" le festivalier 
				tfest[i].setStop(true);
				delFestivalier(i);
			}
		}
		if (cpt == 1)
			System.out.println("Navette " + nNavette + " a depose 1 Festivalier au Site " + n.nSite);
		else if (cpt != 0)
			System.out.println("Navette " + nNavette + " a depose " + cpt + " Festivaliers au Site " + n.nSite);
	}
	
	public void run()
	{
		// Chaque navette commence a un site aleatoire.
		//int n = (int) (Math.random() * Festival.nSites);
		int n = 0;
		
		while (true)
		{
			tSite[n].arret.aller(this);
			// tmp sert pour le temps de deplacement de la navette d'un site a l'autre.
			int tmp = n;
			if (n != (Festival.nSites - 1)) {
				tmp = 1;
			}
			// Simulation du temps de deplacement de la navette.
			try {
				sleep((int) tmp*(10000/vitesseMax));
			} catch (Exception e) {}
				
			deposer(tSite[n]);
		
			// Simulation de l'attente de la navette a l'arret.
			try {
				sleep(1000);
			} catch (Exception e) {}
			
			tSite[n].arret.partir(this);
			
			n = (n+1)%(Festival.nSites);
			
		}
	}
	
	
}