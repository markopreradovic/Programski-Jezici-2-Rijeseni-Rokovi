package paket;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

	public static HashSet<Knjiga> knjige = new HashSet<>();
	public static HashSet<Knjiga> knjige1 = new HashSet<>();
	
	
	
	public static void main(String[] args) {
		
		//Dodavanje Knjiga
		Knjiga.Zanr[] zanrovi = Knjiga.Zanr.values();
        Random rand = new Random();
        for (int i = 0; i < 20; i++) {
            int broj = i + 1;
            Knjiga k = new Knjiga("Naslov" + broj, "Pisac" + broj, rand.nextInt(30) + 1990, zanrovi[rand.nextInt(zanrovi.length)]);
            Knjiga k2 = new Knjiga("Naslov" + broj, "Pisac" + broj, rand.nextInt(30) + 1990, zanrovi[rand.nextInt(zanrovi.length)]);
            knjige.add(k);
            knjige1.add(k2);
        }
        
        //1. Spajanje grupe knjiga, nakon spajanja ispisati broj knjiga u grupi i sve razlicite pisce
        knjige1.stream().forEach( k -> {
        	knjige.add(k);
        });
        knjige1.clear();
        System.out.println("Broj knjiga u drugoj kolekciji" + knjige1.size());
        long brPisaca = knjige.stream().map(k -> k.pisac).distinct().count();
        System.out.println("Broj razlicitih pisaca" + brPisaca);
        
        //2. Filtriranje grupe knjiga po zanru
        knjige.stream().collect(Collectors.groupingBy(k -> k.zanr)).entrySet().forEach(System.out::println);
        
        //3. Sortiranje grupe knjiga po godini izdavanja od najvece ka najmanjoj
        knjige.stream().sorted((k1,k2) -> k2.godinaIzdavanja - k1.godinaIzdavanja ).forEach(System.out::println);
        
        //4. Sumirati godine izdavanja svih knjiga iz grupe zanra putopis gdje je godina djeljiva sa 3 koristenjem Function interfejsa
        int godinaSum;
        godinaSum = knjige.stream().filter(k -> k.zanr == Knjiga.Zanr.PUTOPIS && k.godinaIzdavanja%3==0).mapToInt(k -> k.godinaIzdavanja).sum();
        System.out.println("Sumirana godina " + godinaSum);
        
        //5. Prikazati knjigu sa najkracim naslovom i najduzim naslovom
        System.out.println("Najkraći naslov " + knjige.stream().min(Comparator.comparing(k -> k.naslov.length())).get().naslov);
        System.out.println("Najduži naslov " + knjige.stream().max(Comparator.comparing(k -> k.naslov.length())).get().naslov);

	}
	
	
}
