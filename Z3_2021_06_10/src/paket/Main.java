package paket;

import java.util.*;
import java.util.Random;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public class Main {

	public static Random random = new Random();
	
	public static void main(String[] args) {
		
		List<Oglas> oglasi = new ArrayList<Oglas>();
		
		for(int i=0;i<100;i++) {
			oglasi.add(new Oglas());
		}
		
		//1 Ukupan broj objavljenih oglasa u jednom danu (za svaki datum pojedinacno)
		Map<String,Long> brojPoDatumu = oglasi.stream().collect(Collectors.groupingBy(o -> o.datum , Collectors.counting()));
		brojPoDatumu.forEach( (datum,broj) -> {
			System.out.println("Datum " + datum + " broj oglasa " + broj);
		});
		
		//2 Prosjecnu ponudjenu platu u kategoriji IT
		System.out.println("Prosjecna plata u IT:");
		double prosjek = oglasi.stream()
		        .filter(s -> s.kategorija == Oglas.Kategorija.IT)
		        .mapToDouble(s -> s.plata)
		        .average()
		        .orElse(0.0);
		System.out.println(prosjek);
		
		//3 Najcesci grad u kome se nudi posao
		String najcesci;
		najcesci = oglasi.stream().collect(Collectors.groupingBy(t -> t.grad, Collectors.counting()))  //Map grad,brojPojave
				.entrySet().stream()
				.max(Map.Entry.comparingByValue())
				.map(Map.Entry::getKey)
				.orElse("Nema oglasa");
		System.out.println("Najcesci grad u kome se nudi posao:" + najcesci);
		
		//4  Prikaz svih oglasa grupisanih po mjesecima (mjesec odrediti iz datuma)
		Map<String, List<Oglas>> oglMj = oglasi.stream()
				.collect(Collectors.groupingBy(o -> o.datum.split("\\.")[1]));
		oglMj.forEach( (s,t) -> {
			System.out.println("Mjesec : " + s + "Oglasi ");
			t.forEach(o -> System.out.println(o.naziv + " " + o.datum));
		});
		
		//5 Prikaz svih oglasa sortiranih po vremenu trajanja u opadajucem redoslijedu
		List<Oglas> sortirani;
		sortirani = oglasi.stream()
				.sorted(Comparator.comparingInt((Oglas o) -> o.vrijemeTrajanja).reversed())
				.collect(Collectors.toList());
		sortirani.forEach( System.out::println);
		
		//6 Prikaz najbolje placenog posla za svaku kategoriju
		Map<Oglas.Kategorija, Oglas> najboljePlaceni = oglasi.stream()
		        .collect(Collectors.toMap(
		                o -> o.kategorija,
		                o -> o,
		                BinaryOperator.maxBy(Comparator.comparingDouble(o -> o.plata))
		        ));
		
		najboljePlaceni.forEach( (kategorija,oglas) -> {
			System.out.println("Kategorija " + kategorija + "Oglas " + oglas);
		});
		
		
		//7 Prosjecan broj godina radnom iskustva ukupno i posebno za svaku kategoriju
		
		double prosjekUkupno = oglasi.stream().mapToDouble(o -> o.godinaRada).average().orElse(0.0);
		System.out.println("Prosjek godina rada UKUPNO" + prosjekUkupno );
		
		Map<Oglas.Kategorija, Double> mapProsj = oglasi.stream()
		        .collect(Collectors.groupingBy(
		                o -> o.kategorija,
		                Collectors.averagingDouble(o -> o.godinaRada)
		        ));
		
		mapProsj.forEach( (kategorija,prosj) ->{
			System.out.println("Kategorija" + kategorija + "Prosjek" + prosj);
		});
		
		
				
	}
}



class Oglas{
	public String naziv;
	public String kratakOpis;
	public String datum;
	public int vrijemeTrajanja;
	public double plata;
	public int godinaRada;
	public String grad;
	public Kategorija kategorija;
	
	public static int broj = 1;
	
	public enum Kategorija {
		IT, EKONOMIJA, MEDICINA, NOVINARSTVO, PRAVO
	}
	
	public Oglas() {
		this.naziv = "Naziv" + broj;
		this.kratakOpis = "Opis" + broj;
		this.datum = Integer.toString(Main.random.nextInt(30)+1) + ".01.2024";
		this.vrijemeTrajanja = Main.random.nextInt(50) + 1;
		this.plata = Main.random.nextDouble(1000.0) + 1000;
		this.godinaRada = Main.random.nextInt(5)+1;
		this.grad = "Grad" + broj;
		this.kategorija = Kategorija.values()[Main.random.nextInt(Kategorija.values().length)];
		broj ++;
	}
	
	@Override
	public String toString() {
	    return "Oglas {" +
	            "Naziv='" + naziv + '\'' +
	            ", Kratak opis='" + kratakOpis + '\'' +
	            ", Datum='" + datum + '\'' +
	            ", Vrijeme trajanja=" + vrijemeTrajanja + " dana" +
	            ", Plata=" + String.format("%.2f", plata) +
	            ", Godina rada=" + godinaRada +
	            ", Grad='" + grad + '\'' +
	            ", Kategorija=" + kategorija +
	            '}';
	}

	
	
}