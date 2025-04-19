package paket;
import java.util.*;
public class Main {

	public static void main(String[] args) {
		List<Posiljka> arr = new ArrayList<Posiljka>();
		
		for(int i=0; i<15; i++) {
			arr.add(new Pismo());
			arr.add(new Razglednica());
			arr.add(new VPosiljka());
		}

		

		PrvaNit pn = new PrvaNit(arr);
		pn.start();
	}

}
