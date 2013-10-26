package festival;

/**
 * 
 * @author NATTA & COAVOUX
 *
 */
public class Arret {
	public int nArret;
	public int nAttente;
	
	public Arret(int n) {
		this.nArret = n;
		this.nAttente = 0;
	}
	
	public synchronized void emmener(Navette n) {
		if ((nAttente > 0) && (n.nbPlaces > 0))
		{
			int i = Math.min(nAttente, n.nbPlaces);
			nAttente -= i;
			n.nbPlaces -= i;
			System.out.println("La navette " + n.nNavette + " prend " + i + " personnes sur le site " + nArret);
		}
	}
	
	public synchronized void deposer(Navette n) {
		System.out.println("La navette " + n.nNavette + " a depose " + (Festival.navetteP - n.nbPlaces) + " personnes sur le site " + nArret);
		while (n.nbPlaces != Festival.navetteP) {
			n.nbPlaces++;
			try {
				notify();
			} catch (Exception e) {}
		}
	}
	
	public synchronized void attendreNavette() {
		nAttente++;

		// Simuler l'attente du trajet dans la navette:
		// Une fois la navette arrivée au dernier site, elle notifiera autant de
		// festivaliers qu'il y avait de personnes dans la navette.
		try {
			wait();
		} catch (Exception e) {}
	}
}
