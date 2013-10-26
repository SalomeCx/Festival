package festival;

/**
 * 
 * @author NATTA & COAVOUX
 *
 */
public class Site {
	// Le guichet lié au site.
	public Guichet guichet;
	// L'arrêt du site.
	public Arret arret;
	// Le numéro du site (son identifiant).
	public int nSite;
	
	public Site(int n)
	{
		this.guichet = new Guichet(n);
		this.arret = new Arret(n);
		this.nSite = n;
	}
}
