package paket;

public class Main {

	
	public static void main(String[] args) {
		
		if(args.length != 4 || !"-d".equals(args[0]) || !"-l".equals(args[2])) {
			System.out.println("Pogresan unos!");
			return;
		}
		
		String putanja = args[1];
		int duzina;
		try {
			duzina = Integer.parseInt(args[3]);
		} catch(NumberFormatException ex) {
			System.out.println("Greska, duzina rijeci mora biti int");
			return;
		}
		
		FileSearcher f = new FileSearcher(putanja, duzina);
		
		f.pretraziFajlove();
		f.zapisiRezultateUFajl();
		f.ispisiRezultate();
		
	}

}
