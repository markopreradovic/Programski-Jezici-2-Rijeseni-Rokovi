import java.util.Random;

public class VojniHelikopter extends Helikopter implements Vojni{
	
	TipNaoruzanja naoruzanje;
	
	public VojniHelikopter(){
		super();
		setNaoruzanje(TipNaoruzanja.MITRALJEZ);
		endPos = new Random().nextInt(Simulacija.DIM);
	}
	
	 public void setNaoruzanje(TipNaoruzanja tip) {
        this.naoruzanje = tip;
    }

    public void setTransponder(boolean t) {
        transponder = t;
    }
	
}