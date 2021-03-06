package festival1;

/**
 * 
 * @author NATTA & COAVOUX
 * Classe principale ou le programme est lance.
 */
public class Festival {
	// Le nombre de sites.
	static final int nSites = 7;
	// Le nombre de navettes.
	static final int nNavettes = 10;
	// Le nombre de festivaliers.
	static final int nMaxF = 100;
	// Le nombre de places max dans chaque navette.
	static final int navetteP = 52;

	
	private Site[] sites = new Site[nSites];
	private Navette[] navettes = new Navette[nNavettes];
	private Festivalier[] fest = new Festivalier[nMaxF];
	private int nFest = 0;
	
	/**
	 * Creer un nouveau festivalier. 
	 * @return true si le festivalier a bien ete cree, false sinon.
	 */
	private boolean nouveauFest(Site a) {
		Site depart;
	
		if(nFest == nMaxF) {
			System.out.println("Le nombre maximum de festivaliers est atteint.");
			return false;
		}
	
		depart = sites[(int)(Math.random() * nSites)];
		fest[nFest] = new Festivalier(nFest, depart, a);
		nFest++;
	
		return true;
	}
	
	Festival() {
		int i;
	
		// Creer les sites.
		for(i = 0; i < nSites; i++)
			sites[i] = new Site(i);
		
		// Creer les navettes.
		for(i = 0; i < nNavettes; i++) {
			navettes[i] = new Navette(navetteP, i, sites);
			navettes[i].setDaemon(true);
			navettes[i].start();
		}
		
		// Creer les festivaliers.
		while(nouveauFest(sites[nSites-1]));
		for(i = 0; i < nFest; i++)
			fest[i].start();

	}
	
	public static void main(String[] args) {
		new Festival();
	}
}