package paket;

import java.util.*;

public class Zaposleni extends Thread{
	
	String ime;
	String prezime;
	int godineRada;
	int cijenaRada;
	ArrayList<String> uradjeniZadaci;
	
	boolean radi;
	int vrijemePauze;
	
	public static int redniBroj = 1;
	
	public Zaposleni() {
		ime = "Ime" + redniBroj;
		prezime = "Prezime" + redniBroj;
		int godineRada = Main.random.nextInt(5) + 1;
		int cijenaRada = Main.random.nextInt(51) + 50;
		uradjeniZadaci = new ArrayList<>();
		radi = false;
		vrijemePauze = Main.random.nextInt(16) + 5;
	}
	
}
