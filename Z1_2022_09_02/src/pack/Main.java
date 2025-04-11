package pack;
import java.util.*;

public class Main {

	public static List<Vozilo> arr = new ArrayList<Vozilo>();
	public static ParserElemenata pe = new ParserElemenata();
	public static Polje[] map = null;
	public static ParserPolja pp = new ParserPolja();
	
	public static void main(String[] args) {
		
		pe.getElements();
		pp.getMapuKretanja();
		
		pe.startAll();

	}

}
