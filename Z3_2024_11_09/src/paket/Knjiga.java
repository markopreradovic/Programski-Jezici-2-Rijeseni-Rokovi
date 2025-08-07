package paket;

public class Knjiga {
	
	public String naslov;
	public String autor;
	public int brojStranica;
	public int godinaIzdanja;
	
	public static int redniBroj = 1;
	
	public Knjiga() {
		this.naslov = "Naslov" + redniBroj;
		this.autor = "Autor" + redniBroj;
		this.brojStranica = redniBroj;
		this.godinaIzdanja = redniBroj;
		redniBroj++;
	}
	
	 @Override
	    public String toString() {
	        return "Knjiga{naslov=" + this.naslov + ", autor=" + this.autor + ", brojStranica=" + this.brojStranica
	                + ", godinaIzdanja=" + this.godinaIzdanja + "}";
	    }
	
}
