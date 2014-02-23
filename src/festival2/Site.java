package festival2;

/**
 * 
 * @author NATTA & COAVOUX
 *
 */
public class Site {
	// Le guichet lie au site.
	public Guichet[] guichet;
	// L'arret du site.
	public Arret arret;
	// Le numero du site (son identifiant).
	public int nSite;
	// Le nombre de billets max disponibles sur le site.
	public int nBillets;
	
	/**
	 * Constructeur d'un site
	 * @param nsite le numero du site
	 * @param nbil le nombre de billets disponibles sur le site
	 * @param nbguich le nombre de guichets sur le site
	 */
	public Site(int nsite, int nbil, int nbguich)
	{
		this.arret = new Arret(nsite);
		this.nSite = nsite;
		this.guichet = new Guichet[nbguich];
		for (int i = 0; i < nbguich; i++) {
			this.guichet[i] = new Guichet(nsite*100 + i);
		}
		this.nBillets = nbil;
	}
	
	/**
	 * Simuler le fait de vendre des places.
	 * Appele par les festivaliers.
	 */
	public synchronized boolean prendrePlace(Guichet ng) {
		if (nBillets > 0) {
			ng.nPlacesVendues++;
			nBillets--;
			if (ng.nPlacesVendues == 1)
				System.out.println("Le guichet " + ng.nGuichet + " a vendu " + ng.nPlacesVendues + " place.");
			else
				System.out.println("Le guichet " + ng.nGuichet + " a vendu " + ng.nPlacesVendues + " places.");
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Verifie que deux sites sont bien les memes.
	 * @param n le site a comparer avec this.
	 * @return true si les deux sites sont les memes, false sinon.
	 */
	public boolean equals(Site n){
		return (this.nSite == n.nSite);
	}
}