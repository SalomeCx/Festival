package festival;

/**
 * 
 * @author NATTA & COAVOUX
 *
 */
public class Navette extends Thread{
	// Le nombre de places libres dans la navette.
	public int nbPlaces;
	// Le numéro de la navette (son identifiant).
	public int nNavette;
	// La liste des sites à parcourir.
	public Site[] tSite;
	
	public Navette(int np, int nn, Site[] t) {
		this.nbPlaces = np;
		this.nNavette = nn;
		this.tSite = t;
	}
	
	public void run()
	{
		// Chaque navette commence à un site aléatoire.
		int n = (int) (Math.random() * Festival.nSites);
		
		while (true)
		{
			if (n != (Festival.nSites - 1)) {
				tSite[n].arret.emmener(this);
				
				// Simulation du temps de déplacement de la navette. (d'un site n au site (n+1).)
				try {
					sleep(100);
				} catch (Exception e) {}
			}
			
			else
			{
				tSite[n].arret.deposer(this);				
				
				// Simulation du temps de déplacement de la navette (du dernier site au premier site.)
				try {
					sleep(n*100);
				} catch (Exception e) {}
			}
			n = (n+1)%(Festival.nSites);
			
			// Simulation de l'attente de la navette à l'arrêt.
			try {
				sleep(500);
			} catch (Exception e) {}
		}
	}
	
	
}
