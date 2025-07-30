package paket;

public class TramvajBezVagona extends Tramvaj implements BaterijskoPunjenjeInterface {

	public int nivoBaterije;
	
	public TramvajBezVagona() {
		super();
		this.nivoBaterije = Main.random.nextInt(51) + 50;
	}
	
	@Override 
	public void smanjiBateriju(int kolicina) {
		this.nivoBaterije -= kolicina;
	}
	
	@Override
	public String toString() {
		return "TramvajBezVagona{naziv=" + this.naziv + ", kapacitet=" + this.kapacitet + ", trenutniBrojPutnika=" + this.trenutniBrojPutnika + ", trajanjeSimulacije=" + this.trajanjeSimulacije + ", putnici=" + this.putnici + ", nivoBaterije=" + this.nivoBaterije + "}";
	}
	
}
