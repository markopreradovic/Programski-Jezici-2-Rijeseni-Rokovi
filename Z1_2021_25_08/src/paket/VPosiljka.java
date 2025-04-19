package paket;
import java.io.*;
public class VPosiljka extends Posiljka implements Serializable {

	public Valuta valuta;
	public static int num = 0;
	
	public VPosiljka() {
		super("VPosiljka pri_" + num, "VPosiljka pos_" + num, 10,100);
		
		valuta = Posiljka.getRandomValuta();
		
		num++;
	}
	
}
