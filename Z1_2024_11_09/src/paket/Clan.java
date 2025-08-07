package paket;

public class Clan extends Thread {

	public String ime;
	public String prezime;
	public int godineRada;
	
	public static int redniBroj = 1;
	
	public Clan() {
		this.ime = "Ime" + redniBroj;
		this.prezime = "Prezime" + redniBroj;
		this.godineRada = Main.random.nextInt(5) + 1;
	}
	
}
