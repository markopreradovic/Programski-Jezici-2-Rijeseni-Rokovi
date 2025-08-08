package paket;

import java.util.ArrayList;
import java.util.Random;
import java.lang.InterruptedException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;


public class Main {

	public static Random random = new Random();
	
	public static ArrayList<Korisnik> saunaKorisnici = new ArrayList<>();
	public static ArrayList<Korisnik> masazaKorisnici = new ArrayList<>();
	public static ArrayList<Korisnik> bazenKorisnici = new ArrayList<>();
	
	public static ArrayList<ObicniKorisnik> obicniKorisnici = new ArrayList<>();
	
	private static void rasporediKorisnike() {
		ArrayList<Korisnik> sviKorisnici = new ArrayList<>();
		
		//Generisemo i dodajemo obicne korisnike
		for(int i=0;i<15;i++) {
			sviKorisnici.add(new ObicniKorisnik());
		}
		//Generisemo i dodajemo firma korisnike
		for(int i=0;i<15;i++) {
			sviKorisnici.add(new FirmaKorisnik());
		}
		
		//Firmine korisnike direktno dodajemo prvo u listu saunakorisnici jer imaju prioritet
		//Obicne korisnike dodajemo u saunaObicniKorisnici, pa tek nakon sto se sve firmini korisnici smjeste, dodajemo iz saunaObicniKorisnici u glavnu listu
		ArrayList<Korisnik> saunaObicniKorisnici = new ArrayList<>();
		
		int j = 30; //ukupno 30 ljudi
		for(int i=0;i<10;) {
			int randomIndex = random.nextInt(j);
			if(sviKorisnici.get(randomIndex) instanceof FirmaKorisnik) {
				sviKorisnici.get(randomIndex).usluga = 0;
				saunaKorisnici.add(sviKorisnici.get(randomIndex));
				sviKorisnici.remove(randomIndex);
				i++;
				j--;
			} else if(sviKorisnici.get(randomIndex) instanceof ObicniKorisnik) {
				sviKorisnici.get(randomIndex).usluga = 0;
				saunaObicniKorisnici.add(sviKorisnici.get(randomIndex));
				sviKorisnici.remove(randomIndex);
				i++;
				j--;
			}
		}
		
		
		//Dodajemo sad preostale (obicne korisnike) u glavnu listu za saunu
		for(int i=0;i<saunaObicniKorisnici.size();i++) {
			saunaKorisnici.add(saunaObicniKorisnici.get(i));
		}
		
		//Smjestanje 5 korisnika za masazu
		for(int i=0;i<5;i++) {
			int randomIndex = random.nextInt(j); //j jer je toliko radnika ostalo (Poceli smo od 30, pa za svaki smjesteni smanjujemo za 1)
			sviKorisnici.get(randomIndex).usluga = 1;
			masazaKorisnici.add(sviKorisnici.get(randomIndex));
			sviKorisnici.remove(randomIndex);
			j--;
		}
		
		//Smjestanje korisnika za bazen
		for(int i = 14;i>0;i--) {
			sviKorisnici.get(i).usluga = 2;
			bazenKorisnici.add(sviKorisnici.get(i));
			sviKorisnici.remove(i);
		}
	}
	
	public static void zapisiUDatoteku(ObicniKorisnik obicniKorisnik) {
		try (PrintWriter pw = new PrintWriter(new FileWriter(obicniKorisnik.ime + "_" + obicniKorisnik.prezime + ".txt"))){
			pw.write("Otkazana sauna jer nema kupon, kupon=" + obicniKorisnik.kupon);
		} catch(IOException ex) {
			System.out.println("Greska prilikom upisa u fajl");
		}
	}
	
	public static void main(String[] args) {
		rasporediKorisnike();
		
		// Kreiraju se tri niti koje paralelno rade sa korisnicima različitih usluga, nema potrebe da nijedna klasa nasljeđuje Thread
		
		Runnable zadatak1 = new Runnable() {
			@Override
			public void run() {
				while(!saunaKorisnici.isEmpty()) {
					Korisnik korisnik = saunaKorisnici.get(0);
					if(korisnik instanceof ObicniKorisnik) {
						ObicniKorisnik obicniKorisnik = (ObicniKorisnik) korisnik;
						if(!obicniKorisnik.kupon) {
							System.out.println(korisnik + "OTKAZUJE saunu zbog nevazeceg kupona");
							zapisiUDatoteku(obicniKorisnik);
						} else {
							System.out.println(korisnik + "je usao u saunu");
						}
						obicniKorisnici.add(obicniKorisnik);
					} else {
						System.out.println(korisnik + "je usao u saunu");
					}
					saunaKorisnici.remove(korisnik);
					try {
						Thread.sleep(3000);
					} catch(InterruptedException ex) {
						ex.printStackTrace();
					}
				}
			}
		};
		Thread nit1 = new Thread(zadatak1);
		nit1.start();
		
		Runnable zadatak2 = new Runnable() {
			@Override
			public void run() {
				while (!masazaKorisnici.isEmpty()) {
					Korisnik korisnik = masazaKorisnici.get(0);
					if (korisnik instanceof ObicniKorisnik) {
						ObicniKorisnik obicniKorisnik = (ObicniKorisnik) korisnik;
						obicniKorisnik.novac -= 10.0;
						System.out.println(korisnik + " je USAO na masazu!");
						obicniKorisnici.add(obicniKorisnik);
					}
					else {
						System.out.println(korisnik + " je USAO na masazu!");
					}
					masazaKorisnici.remove(0);
					try {
						Thread.sleep(3000);
					} catch (InterruptedException ex) {
						ex.printStackTrace();
					}
				}
			}
		};
		Thread nit2 = new Thread(zadatak2);
		nit2.start();
		
		Runnable zadatak3 = new Runnable() {
			@Override
			public void run() {
				while (!bazenKorisnici.isEmpty()) {
					Korisnik korisnik = bazenKorisnici.get(0);
					if (korisnik instanceof ObicniKorisnik) {
						ObicniKorisnik obicniKorisnik = (ObicniKorisnik) korisnik;
						obicniKorisnik.novac -= 5.0;
						System.out.println(korisnik + " je USAO na bazen!");
						obicniKorisnici.add(obicniKorisnik);
					}
					else {
						System.out.println(korisnik + " je USAO na bazen!");
					}
					bazenKorisnici.remove(0);
					try {
						Thread.sleep(2000);
					} catch (InterruptedException ex) {
						ex.printStackTrace();
					}
				}
			}
		};
		Thread nit3 = new Thread(zadatak3);
		nit3.start();
		
		try {
			nit1.join();
			nit2.join();
			nit3.join();
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		
		for (ObicniKorisnik obicniKorisnik : obicniKorisnici) {
			System.out.print(obicniKorisnik.novac + "KM; ");
		}
	}
}

		
		
		
		
		
		
		
		
		
	
