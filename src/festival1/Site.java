package festival1;

/**
 * 
 * @author NATTA & COAVOUX
 *
 */
public class Site {
	// Le guichet lie au site.
	public Guichet guichet;
	// L'arret du site.
	public Arret arret;
	// Le numero du site (son identifiant).
	public int nSite;
	
	public Site(int n)
	{
		this.guichet = new Guichet(n);
		this.arret = new Arret(n);
		this.nSite = n;
	}
}