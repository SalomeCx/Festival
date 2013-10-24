package festival;

/**
 * 
 * @author NATTA & COAVOUX
 *
 */

public class Festival {
	static final int nSites = 7;
	static final int nNavettes = 10;
	static final int nMaxF = 100;
	static final int navetteP = 52;
	
	private Site[] sites = new Site[nSites];
	private Navette[] navettes = new Navette[nNavettes];
	private Festivalier[] fest = new Festivalier[nMaxF];
	private int nFest = 0;
	
	private boolean nouveauFest() {
	
		Site depart;
	
		if(nFest == nMaxF) {
			System.out.println("Le nombre maximum de festivaliers est atteint.");
			return false;
		}
	

		depart = sites[(int)(Math.random() * nSites)];

		fest[nFest] = new Festivalier(nFest, depart);
		nFest++;
	
		return true;
	}
	
	Festival() {
		
		int i;
	
		for(i = 0; i < nSites; i++)
			sites[i] = new Site(i);
	
		while(nouveauFest());
	
		for(i = 0; i < nSites; i++) {
			navettes[i] = new Navette(navetteP, i, sites);
			//navettes[i].setDaemon(true);
			navettes[i].start();
		}
	
		for(i = 0; i < nFest; i++)
			fest[i].start();
	
	}
	
	
	public static void main(String[] args) {
		new Festival();
	}
}

