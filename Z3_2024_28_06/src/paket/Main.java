package paket;

import java.util.*;
import java.util.stream.*;

public class Main {

	public static Random random = new Random();
	
	public static void main(String[] args) {
		
		ArrayList<Oglas> oglasi = new ArrayList<>();
		for(int i=0;i<60;i++) {
			oglasi.add(new Oglas());
		}
		
		//1
		System.out.println("Ukupan broj oglasa po datumima \n");
		oglasi.stream()
	      .collect(Collectors.groupingBy(o -> o.datum, Collectors.counting())) //grupisanje elemenata po datumu, prebroji oglase, rezultat je Map
	      .forEach((datum, count) -> System.out.println(datum + ": " + count)); //prolazak kroz map i ispis
		
		//2
		Oglas.Kategorija kategorija1 = Oglas.Kategorija.IT;
		Oglas.Kategorija kategorija2 = Oglas.Kategorija.EKONOMIJA;
		Oglas.Kategorija kategorija3 = Oglas.Kategorija.MEDICINA;
		Oglas.Kategorija kategorija4 = Oglas.Kategorija.NOVINARSTVO;
		Oglas.Kategorija kategorija5 = Oglas.Kategorija.PRAVO;
		
		double prosjecnaPlata1;
		prosjecnaPlata1 = oglasi.stream()
							.filter(o -> o.kategorija == kategorija1)
							.mapToInt(o -> o.plata).average().orElse(0);
		System.out.println("\nProsjecna plata u kategoriji " + kategorija1 + " je: " + prosjecnaPlata1);

		
		double prosjecnaPlata2;
		prosjecnaPlata2 = oglasi.stream()
							.filter(o -> o.kategorija == kategorija2)
							.mapToInt(o -> o.plata).average().orElse(0);
		System.out.println("\nProsjecna plata u kategoriji " + kategorija2 + " je: " + prosjecnaPlata2);
		
		double prosjecnaPlata3;
		prosjecnaPlata3 = oglasi.stream()
							.filter(o -> o.kategorija == kategorija3)
							.mapToInt(o -> o.plata).average().orElse(0);
		System.out.println("\nProsjecna plata u kategoriji " + kategorija3 + " je: " + prosjecnaPlata3);
		
		double prosjecnaPlata4;
		prosjecnaPlata4 = oglasi.stream()
							.filter(o -> o.kategorija == kategorija4)
							.mapToInt(o -> o.plata).average().orElse(0);
		System.out.println("\nProsjecna plata u kategoriji " + kategorija4 + " je: " + prosjecnaPlata4);
		
		double prosjecnaPlata5;
		prosjecnaPlata5 = oglasi.stream()
							.filter(o -> o.kategorija == kategorija5)
							.mapToInt(o -> o.plata).average().orElse(0);
		System.out.println("\nProsjecna plata u kategoriji " + kategorija5 + " je: " + prosjecnaPlata5);
		
		//3
		String gradSaNajvisePoslova = oglasi.stream()
			    .collect(java.util.stream.Collectors.groupingBy(o -> o.grad, java.util.stream.Collectors.counting())) //Grupisanje po o.grad → pravi se mapa Map<String, Long> (grad → broj oglasa).
			    .entrySet().stream()
			    .max(java.util.Map.Entry.comparingByValue()) //Pronalazi par sa najvećom vrednošću (najviše oglasa).
			    .map(java.util.Map.Entry::getKey) //Ako postoji max unos, iz njega se uzima ključ, tj. naziv grada.
			    .orElse("Nema oglasa");

		//4
		System.out.println("\nOglasi grupisani po godinama:");
		oglasi.stream()
	    .collect(Collectors.groupingBy(o -> o.datum.split("\\.")[2])) //Razdvaja datum po tački (.)
	    .forEach((godina, oglasiUGodini) -> {
	        System.out.println("Godina " + godina + ": ");
	        oglasiUGodini.forEach(System.out::println);
	    });

		
		//5
		System.out.println("\nOglasi sortirani po vremenu trajanja:");
		oglasi.stream()
		.sorted(Comparator.comparingInt( o -> o.vrijemeTrajanja)).forEach(System.out::println);
		
		//6
		System.out.println("\nNajbolje placeni poslovi po kategorijama:");
		oglasi.stream()
	    .collect(Collectors.groupingBy(
	        o -> o.kategorija,
	        Collectors.maxBy(Comparator.comparingInt(o -> o.plata))
	    ))
	    .forEach((kategorija, najboljiOglas) -> {
	        System.out.println(kategorija + ": " + najboljiOglas.orElse(null));
	    });

		
		//7 
		double prosjecnoRadnoIskustvo = oglasi.stream()
										.mapToInt(o -> o.godinaIskustva).average().orElse(0); //izvlacim svaku vrijednost (godIsk) iz svakog objekta / pretvara objekat u int na neki nacin zatim average racuna
		System.out.println("\nProsjecno radno iskustvo: " + prosjecnoRadnoIskustvo + " godina");
		
		//8
		oglasi.stream()
	    .collect(
	        Collectors.groupingBy(
	            o -> o.kategorija,                                            // grupiši po kategoriji
	            Collectors.partitioningBy(
	                o -> o.remote,                                            // podeli na remote / ne-remote
	                Collectors.counting()                                     // prebroj koliko ima u svakoj grupi
	            )
	        )
	    )
	    .forEach((kategorija, map) -> {
	        long remoteCount = map.getOrDefault(true, 0L);                  // broj remote poslova
	        long ukupno = remoteCount + map.getOrDefault(false, 0L);        // ukupno poslova u kategoriji
	        double procenat = ukupno > 0 ? (remoteCount * 100.0 / ukupno) : 0;  // procenat remote poslova

	        System.out.printf("%s: %.2f%% remote\n", kategorija, procenat);
	    });

	}
	
}
