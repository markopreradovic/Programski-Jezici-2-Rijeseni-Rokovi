package paket;

public class Zadatak {

	public String naslov;
	public String opis;
	public int prioritet;
	Vrsta vrsta;
	
	public static int redniBroj = 1;
	
	public Zadatak() {
		this.naslov = "Naslov" + redniBroj;
		this.opis = "Opis" + redniBroj;
		this.prioritet = Main.random.nextInt(5) + 1;
		
		int randomVrsta = Main.random.nextInt(3);
		switch(randomVrsta) {
		 case 0:
			this.vrsta = Vrsta.TASK;
		 case 1:
			this.vrsta = Vrsta.STORY;
		 case 2:
			this.vrsta = Vrsta.BUG;
		 default:
		}
	}
	
	enum Vrsta{
		TASK, STORY, BUG
	}
}
