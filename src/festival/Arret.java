package festival;

public class Arret {
	public int nArret;
	public int nAttente;
	
	public Arret(int n) {
		this.nArret = n;
		this.nAttente = 0;
	}
	
	public synchronized int emmener(int nv) {
		int tmp = nv;
		if ((nAttente > 0) && (nv > 0))
		{
			int i = Math.min(nAttente, tmp);
			nAttente -= i;
			tmp -= i;
		}
		return tmp;
	}
	
	public synchronized int deposer(int n) {
		return (Festival.navetteP - n);
	}
}
