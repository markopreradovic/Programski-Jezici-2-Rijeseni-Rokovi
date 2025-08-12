package paket;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

	public static Random random = new Random();
	
	public static void main(String[] args) {
		
		HashSet<Film> grupa1 = new HashSet<>();
		HashSet<Film> grupa2 = new HashSet<>();
		
		
		for(int i=0;i<10;i++) {
			grupa1.add(new Film());
		}
		for(int i=0;i<10;i++) {
			grupa2.add(new Film());
		}
		
		//1. Spajanje grupa
		grupa2.stream().forEach(f -> grupa1.add(f));
		grupa2.clear();
		
		System.out.println("Ukupan broj filmova" + grupa1.size());
		
		Set<String> razlicitiReziseri = grupa1.stream().map(film -> film.reziser).collect(Collectors.toSet());
		System.out.println("Razliciti reziseri" + razlicitiReziseri);
		
		//2. Filtriranje grupe filmova - filtrirati i grupisati filmove po zanru
		Map<Film.Zanr, List<Film>> filmoviPoZanru = grupa1.stream().collect(Collectors.groupingBy(film -> film.zanr));
		System.out.println("Filmovi grupisani po zanru \n");
		filmoviPoZanru.forEach((zanr, filmovi) -> {
			System.out.println("\n" + zanr + "\n");
			filmovi.forEach(System.out::println);
		});
		
		//3. Sortiranje filmova po godini izdavanja
List<Film> sortiraniFilmovi = grupa1.stream().sorted((f1, f2) -> Integer.compare(f2.godinaIzdavanja, f1.godinaIzdavanja)).collect(Collectors.toList());
		
		System.out.println("\nSortirani filmovi po godini izadanja:");
		sortiraniFilmovi.forEach(System.out::println);
		
		//4. Sumiranje godina izdavanja
		int sumaGodina = grupa1.stream().filter(film -> film.zanr == Film.Zanr.DOKUMENTARAC && film.godinaIzdavanja % 5 == 0).map(film -> film.godinaIzdavanja).reduce(0, Integer::sum);
		
		System.out.println("Suma godina izdavanja za dokumentarce (godina deljiva sa 5): " + sumaGodina);
		
		//5 Prikaz filma sa najkracim i najduzim imenom
		int randomIndexZaNajkraci = random.nextInt(grupa1.size());
		Film[] filmovi = new Film[grupa1.size()];
		grupa1.toArray(filmovi);
		filmovi[randomIndexZaNajkraci].naziv = "F";
				
		int randomIndexZaNajduzi = random.nextInt(grupa1.size());
		while (randomIndexZaNajduzi == randomIndexZaNajkraci) {
			randomIndexZaNajduzi = random.nextInt(grupa1.size());
		}
		grupa1.toArray(filmovi);
		filmovi[randomIndexZaNajduzi].naziv = "Filmmmmmmmmmmmmmmmmmmmmmmmm";
				
				
				
		Optional<Film> najkraciNaziv = grupa1.stream().min(Comparator.comparingInt(film -> film.naziv.length()));
		Optional<Film> najduziNaziv = grupa1.stream().max(Comparator.comparingInt(film -> film.naziv.length()));
		najkraciNaziv.ifPresent(film -> System.out.println("\nFilm sa najkracim nazivom: " + film));
		najduziNaziv.ifPresent(film -> System.out.println("Film sa najduzim nazivom: " + film));
	}

}
