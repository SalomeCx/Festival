package festival2;

/**
 * 
 * @author NATTA & COAVOUX
 * Classe principale ou le programme est lance.
 */
public class Festival {
	// Le nombre de sites.
	static final int nSites = 7;
	// Le nombre de navettes.
	static final int nNavettes = 1;
	// Le nombre de festivaliers.
	static final int nMaxF = 100;
	// Le nombre maximum de guichets par site.
	static final int nMaxG = 12;
	// Le nombre maximum de billets par site.
	static final int nMaxB = 50;
	// Le nombre de places max dans chaque navette.
	static final int navetteP = 52;
	// La vitesse maximale possible pour les navettes.
	static final int vitMaxNav = 110;
	// La vitesse minimale possible pour les navettes.
	static final int vitMinNav = 110;
	
	private Site[] sites = new Site[nSites];
	private Navette[] navettes = new Navette[nNavettes];
	private Festivalier[] fest = new Festivalier[nMaxF];
	private int nFest = 0;
	
	/**
	 * Creer un nouveau festivalier. 
	 * @return true si le festivalier a bien ete cree, false sinon.
	 */
	private boolean nouveauFest(Site a, Site[] carte) {
		Site depart;
	
		if(nFest == nMaxF) {
			System.out.println("Le nombre maximum de festivaliers est atteint.");
			return false;
		}
	
		depart = sites[(int)(Math.random() * nSites)];
		fest[nFest] = new Festivalier(nFest, depart, a, carte);
		nFest++;
	
		return true;
	}
	
	/**
	 * Constructeur du festival.
	 */
	Festival() {
		int i;
	
		// Creer les sites.
		for(i = 0; i < nSites; i++) {
			int k = (int) (Math.random() * nMaxG) + 1;
			int j = (int) (Math.random() * nMaxB) + 1;
			sites[i] = new Site(i, j, k);
		}
		
		// Creer les navettes.
		for(i = 0; i < nNavettes; i++) {
			int vit = (int) (Math.random() * (vitMaxNav - vitMinNav) + vitMinNav);
			navettes[i] = new Navette(navetteP, i, sites, vit);
			navettes[i].setDaemon(true);
			navettes[i].start();
		}
		
		// Creer les festivaliers.
		while(nouveauFest(sites[nSites-1], sites));
		for(i = 0; i < nFest; i++)
			fest[i].start();

	}
	
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		
		new Festival();
		
		// Calculer le temps d'execution du programme.
		while (Thread.activeCount() != (nNavettes + 1)) {
			
		}
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println("Tous les festivaliers ont atteint le festival en " + totalTime + " ms.");
	}
}