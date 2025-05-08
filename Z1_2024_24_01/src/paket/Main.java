package paket;
import java.util.*;

public class Main {
	
	public static Random random = new Random();
	public static Object lockObject = new Object();
	
	public static void main(String[] args) {
		long pocetakSimulacije = System.currentTimeMillis();
		
		Zaposleni radnikProdaje = new RadnikProdaje();
		Zaposleni radnikNabavke = new RadnikNabavke();
		
		ArrayList<Zaposleni> radnici = new ArrayList<>();
		radnici.add(radnikNabavke);
		radnici.add(radnikProdaje);
		
		Zaposleni racunovodja = new Racunovodja(radnici);
		
		//Pocetak simulacije
		radnikProdaje.start();
		radnikNabavke.start();
		racunovodja.start();
		
		String unos = "";
		Scanner scan = new Scanner(System.in);
		while(!unos.equalsIgnoreCase("kraj")) {
			unos = scan.nextLine();
		}
		
		//Prekidamo izvrsavanje svih niti
		radnikProdaje.radi = false;
		radnikNabavke.radi = false;
		racunovodja.radi = false;
		
		try {
			radnikProdaje.join();
			radnikNabavke.join();
			racunovodja.join();
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		
		System.out.println("Broj uradjenih zadataka radnika prodaje: " + radnikProdaje.uradjeniZadaci.size());
		System.out.println("Broj uradjenih zadataka radnika nabavke: " + radnikNabavke.uradjeniZadaci.size());
		System.out.println("Broj uradjenih zadataka racunovodje: " + racunovodja.uradjeniZadaci.size());
		long krajSimulacije = System.currentTimeMillis();
		System.out.println("Vrijeme trajanja simulacije: " + (double) (krajSimulacije - pocetakSimulacije) / 1000 + "s");
	}
	
}
