package festival;

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
	
	public Navette(int np, int nn, Site[] t) {
		this.nbPlaces = np;
		this.nNavette = nn;
		this.tSite = t;
	}
	
	public void run()
	{
		int n = 0;
		while (true)
		{
			if (n != (Festival.nSites - 1)) {
				int i = nbPlaces;
				int j = tSite[n].arret.emmener(i);
				System.out.println("La navette " + nNavette + " prend " + (nbPlaces - j) + " personnes sur le site " + n);
				nbPlaces = j;
				
				// Simulation de l'attente de la navette a l'arret.
				try {
					sleep(500);
				} catch (Exception e) {}
				
				// Simulation du temps de deplacement de la navette.
				try {
					sleep(300);
				} catch (Exception e) {}
			}
			
			else
			{
				int i = tSite[n].arret.deposer(nbPlaces);
				nbPlaces = Festival.navetteP;
				System.out.println("La navette " + nNavette + " a depose " + i + " personnes sur le site " + n);
				
				// Simulation de l'attente de la navette a l'arret.
				try {
					sleep(500);
				} catch (Exception e) {}
				
				// Simulation du temps de deplacement de la navette.
				try {
					sleep(n*300);
				} catch (Exception e) {}
			}
			n = (n+1)%(Festival.nSites);
		}
	}
	
	
}
