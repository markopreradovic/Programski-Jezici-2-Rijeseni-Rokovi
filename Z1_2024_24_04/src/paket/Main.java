package paket;

import java.util.*;
import java.io.*;
import java.time.LocalDateTime;

public class Main {

	public static Random random = new Random();
	public static Object lockObject = new Object();
	
	public static Object[][] mapa = new Object[20][10];
	
	public static int brojPodignutihKnjiga;
	public static long vrijemeTrajanjaSimulacije;

	
	public static void rasporediKnjige(int brojKnjiga) {
		int brojKnjigaPoVrsti = brojKnjiga / 3;
		
		for (int i=0;i<brojKnjigaPoVrsti;i++) {
			int randomX = random.nextInt(10);
			int randomY = random.nextInt(20);
			if(mapa[randomX][randomY] == null) {
				mapa[randomX][randomY] = new Beletristika();
				i++;
			}
		}
		
		for (int i=0;i<brojKnjigaPoVrsti;i++) {
			int randomX = random.nextInt(10);
			int randomY = random.nextInt(20);
			if(mapa[randomX][randomY] == null) {
				mapa[randomX][randomY] = new DjecaKnjiga();
				i++;
			}
		}
		
		for (int i=0;i<brojKnjigaPoVrsti;i++) {
			int randomX = random.nextInt(10);
			int randomY = random.nextInt(20);
			if(mapa[randomX][randomY] == null) {
				mapa[randomX][randomY] = new StrucnaLiteratura();
				i++;
			}
		}
	}
	
	public static void main(String[] args) {
		
		try {
			if(args.length != 1 || Integer.parseInt(args[0]) < 30) {
				System.out.println("GRESKA: Niste unijeli broj knjiga ILI je broj knjiga manji od 30!");
			}
		} catch (NumberFormatException ex) {
			System.out.println("Greska: mora se unijeti int!");
			return;
		}
		
		
		int brojKnjiga = Integer.parseInt(args[0]);
		rasporediKnjige(brojKnjiga);
		
		ArrayList<Korisnik> korisnici = new ArrayList<>();
		for(int i=0;i<10;i++) {
			korisnici.add(new Korisnik(i));
		}
		
		long pocetakSimulacije = System.currentTimeMillis();
		for(Korisnik k : korisnici) {
			k.start();
		}
		
		try {
			for (Korisnik korisnik : korisnici) {
				korisnik.join();
			}
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		long krajSimulacije = System.currentTimeMillis();
		
		vrijemeTrajanjaSimulacije = krajSimulacije - pocetakSimulacije;
		System.out.printf("\nProcenat podignutih knjiga: %.2f%s", ((double) brojPodignutihKnjiga / (double) brojKnjiga), "%");
		
		try (PrintWriter pw = new PrintWriter("statistikaSimulacija.txt")) {
			pw.println("Datum simulacije: " + LocalDateTime.now());
			pw.printf("Procenat podignutih knjiga: %.2f%s\n", ((double) brojPodignutihKnjiga / (double) brojKnjiga), "%");
			pw.println("Vrijeme trajanja simulacije: " + (double) vrijemeTrajanjaSimulacije / 1000 + "s.");
			
		} catch (IOException ex) {
			System.out.println("GRESKA pri upisu u fajl 'statistikaSimulacija.txt'");
		}
	}

}
