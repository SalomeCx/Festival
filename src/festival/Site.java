package festival;

public class Site {
	public Guichet guichet;
	public Arret arret;
	public int nSite;
	
	public Site(int n)
	{
		this.guichet = new Guichet(n);
		this.arret = new Arret(n);
		this.nSite = n;
	}
}
