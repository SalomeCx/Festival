package festival0;

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
	
	/**
	 * Appele par une Navette pour prendre le maximum de festivaliers a un arret.
	 * @param n la navette qui va prendre les festivaliers.
	 */
	public synchronized void emmener(Navette n) {
		if ((nAttente > 0) && (n.nbPlaces > 0))
		{
			int i = Math.min(nAttente, n.nbPlaces);
			nAttente -= i;
			n.nbPlaces -= i;
			if (i == 1)
				System.out.println("La navette " + n.nNavette + " prend 1 personne sur le site " + nArret);
			else if (i != 0)
				System.out.println("La navette " + n.nNavette + " prend " + i + " personnes sur le site " + nArret);
		}
	}
	
	/**
	 * Appele par une navette pour deposer les festivaliers sur le dernier site.
	 * @param n la navette qui depose les festivaliers.
	 */
	public synchronized void deposer(Navette n) {
		if ((Festival.navetteP - n.nbPlaces) == 1)
			System.out.println("La navette " + n.nNavette + " a depose 1 personne sur le site final.");
		else if ((Festival.navetteP - n.nbPlaces) != 0)
			System.out.println("La navette " + n.nNavette + " a depose " + (Festival.navetteP - n.nbPlaces) + " personnes sur le site final.");
		// On notifie autant de festivaliers qu'il y a de places prises dans la navette.
		// Les festivaliers sont pris au hasard parmi les festivaliers qui dorment.
		while (n.nbPlaces != Festival.navetteP) {
			n.nbPlaces++;
			try {
				notify();
			} catch (Exception e) {}
		}
	}
	
	/**
	 * Appele par un festivalier, des qu'il veut attendre une navette.
	 */
	public synchronized void attendreNavette() {
		nAttente++;
	}
	
	/**
	 * Appele par un festivalier. Simule l'attente a l'arret et le trajet dans la navette.
	 */
	public synchronized void arriver() {
		// Une fois la navette arrivee au dernier site, elle notifiera autant de
		// festivaliers qu'il y avait de personnes dans la navette.
		try {
			wait();
		} catch (Exception e) {}
	}
}