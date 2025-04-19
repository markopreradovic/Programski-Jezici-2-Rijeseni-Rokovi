package paket;
import java.io.*;

public class Razglednica  extends Posiljka implements Serializable{
	public String sadrzaj;
	public TipFajla tip;
	
	public static int num = 0;
	
	public Razglednica() {
		super("Razglednica prim_" + num , "Razglenica pos_" + num, 1, 10);
		
		sadrzaj = "Sadrzaj_" + num;
		tip = Posiljka.getRandomTipFajla();
		
		num++;
	}
	
	@Override
	public String toString() {
		return super.toString() + "Sadrzaj: " + sadrzaj + " Tip: " + tip;
	}

}
