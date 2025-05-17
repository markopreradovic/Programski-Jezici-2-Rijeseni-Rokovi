package paket;

public class Knjiga {
	
	public String naslov;
	public String autor;
	public int brojStranica;
	public int godinaIzdavanja;
	
	public static int redniBroj=1;
	
	public Knjiga() {
		this.naslov = "Naslov" + redniBroj;
		this.autor = "Autor" + redniBroj;
		this.brojStranica = Main.random.nextInt(101) + 100;
		this.godinaIzdavanja = Main.random.nextInt(11) + 2015;
		redniBroj++;
	}
	
}
