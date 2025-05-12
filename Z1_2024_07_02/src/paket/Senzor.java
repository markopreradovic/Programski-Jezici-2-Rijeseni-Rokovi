package paket;

public class Senzor extends Komponenta{

	String vrsta;
	int rasponDetekcije;
	
	public Senzor(String vrsta) {
		super();
		this.vrsta = vrsta;
		this.rasponDetekcije = Main.random.nextInt(51) + 50;
	}
	
	@Override
	public String toString() {
		return "Senzor{id=" + id + ", proizvodjac=" + proizvodjac + ", godinaProizvodnje=" + godinaProizvodnje + ", vrsta=" + vrsta + ", rasponDetekcije=" + rasponDetekcije + "}";
	}
	
}
