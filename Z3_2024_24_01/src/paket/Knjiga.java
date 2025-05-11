package paket;



public class Knjiga {

	String naslov;
	Autor autor;
	
	public static int redniBroj = 1;
	
	public Knjiga() {
		naslov = "Naslov" + redniBroj++;
		autor = new Autor();
	}
	
	@Override
	public String toString() {
		return "Knjiga{naslov = " + naslov + ", autor = " + autor + "}\n";
	}
	
}
