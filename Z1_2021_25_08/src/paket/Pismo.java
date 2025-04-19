package paket;
import java.io.*;
public class Pismo extends Posiljka implements Serializable {
	
	private static int num = 0;
	
	public Pismo() {
		super("Pismo prim_" + num , " Pismo pos_" + num, 1, 20);
		num++;
	}

}
