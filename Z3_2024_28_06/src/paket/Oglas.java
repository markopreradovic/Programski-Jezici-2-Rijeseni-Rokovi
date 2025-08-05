package paket;

public class Oglas {

	private static final String[] gradovi = { "BanjaLuka", "Sarajevo", "Beograd", "Zagreb" };
	
	public String naziv;
	public String kratakOpis;
	public String datum;
	public int vrijemeTrajanja;
	public int plata;
	public int godinaIskustva;
	public boolean remote;
	public String grad;
	public Kategorija kategorija;
	
	public static int redniBroj = 1;
	
	public Oglas() {
		this.naziv = "Naziv" + redniBroj;
		this.kratakOpis = "Opis " + redniBroj;
		this.datum = (Main.random.nextInt(31) + 1) + ".01." + Main.random.nextInt(2) + 2024;
		this.vrijemeTrajanja = Main.random.nextInt(6) + 1;
		this.plata = Main.random.nextInt(1000) + 1000;
		this.godinaIskustva = Main.random.nextInt(4);
		this.remote = Main.random.nextInt(100) < 50;
		
		int randomGrad = Main.random.nextInt(4);
		this.grad=gradovi[randomGrad];
		
		int randomKategorija = Main.random.nextInt(5);
		if (randomKategorija == 0) {
			this.kategorija = Kategorija.IT;
		} else if (randomKategorija == 1) {
			this.kategorija = Kategorija.EKONOMIJA;
		} else if (randomKategorija == 2) {
			this.kategorija = Kategorija.MEDICINA;
		} else if (randomKategorija == 3) {
			this.kategorija = Kategorija.NOVINARSTVO;
		} else {
			this.kategorija = Kategorija.PRAVO;
		}
		redniBroj++;
	}
	
	@Override
	public String toString() {
		return "Oglas{naziv=" + this.naziv + ", opisPosla=" + this.kratakOpis + ", datum=" + this.datum + ", vrijemeTrajanja=" + this.vrijemeTrajanja + ", plata=" + this.plata + "KM, radnoIskustvo=" + this.godinaIskustva + " godina, nacinRada=" + (this.remote ? "remote" : "sjediste") + ", grad=" + this.grad + ", kategorija=" + this.kategorija + "}";
	}
	
	public enum Kategorija {
		IT, EKONOMIJA, MEDICINA, NOVINARSTVO, PRAVO
	}
	
}
