package paket;

public class Proizvod implements Element{

	public String sifra;
	public String naziv;
	public int kolicina;
	public double cijena;
	
	public Proizvod(String sifra, String naziv, int kolicina, double cijena) {
		this.sifra = sifra;
		this.naziv = naziv;
		this.kolicina = kolicina;
		this.cijena = cijena;
	}
	
}
