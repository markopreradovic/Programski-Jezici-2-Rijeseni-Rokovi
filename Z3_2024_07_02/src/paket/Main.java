package paket;
import java.util.*;
import java.util.stream.*;


public class Main {

	public static Random random = new Random();
	
	public static void main(String[] args) {
		
		LinkedList<Automobil> automobili1 = new LinkedList<>();
		for(int i=0;i<10;i++) {
			automobili1.add(new Automobil());
		}
		
		LinkedList<Automobil> automobili2 = new LinkedList<>();
		for(int i=0;i<10;i++) {
			automobili2.add(new Automobil());
		}
		
		LinkedList<Automobil> automobili3 = new LinkedList<>();
		automobili3.addAll(automobili1);
		automobili3.addAll(automobili2);
		
		// Ispis svih automobila 
		System.out.println("====================================");
	    automobili3.forEach(System.out::println);
	    System.out.println("====================================");
		
	    
	    //Spajanje grupa automobila koji su SUV-ovi - spojiti dvije grupe automobila, tako što se 
	    //spajaju u novu listu i pri tom se spajaju samo SUV-ovi čija je snaga motora veća od 150KS
	    
		System.out.println("\na. ----------------------------------------");
		automobili3.stream().filter(automobil -> (automobil.tip == Automobil.Tip.SUV && automobil.snagaMotora>150)).collect(Collectors.toList()).forEach(automobil -> System.out.println("\n" + automobil));
		//Stream.concat(automobili1.stream(), automobili2.stream()).filter(automobil -> (automobil.tip == Automobil.Tip.SUV && automobil.snagaMotora > 150)).collect(Collectors.toList()).forEach(automobil -> System.out.println("\n" + automobil));

		//Sortiranje grupe automobila po snazi motora od veće ka manjoj - sortirati ih i ispisati na konzoli korištenjem stream-a.
		System.out.println("\nSortirana 1. grupa automobila:");
		automobili1.stream().sorted(Comparator.comparingInt(automobil -> automobil.snagaMotora)).forEach(automobil -> System.out.println(automobil));
		
		
		//Sumiranje kapaciteta vrata svih automobila iz grupe koji su tipa hatchback i imaju više od
		//dvoje vrata korištenjem Function interfejsa.
		System.out.println("\nc. ----------------------------------------");
		int suma = automobili3.stream().filter(a -> Automobil.Tip.HATCHBACK == a.tip && a.brojVrata>2).mapToInt(a -> a.brojVrata).sum();
		System.out.println("Broj vrata: " + suma);

		
		//Prikazivanje automobila koji je najbliži prosječnoj snazi motora korištenjem stream-a.
		System.out.println("\nd. ----------------------------------------");
		OptionalDouble prosjecnaSnagaMotora = automobili3.stream().mapToInt(a -> a.snagaMotora).average();
		System.out.println("Prosjecna snaga motora: " + prosjecnaSnagaMotora.getAsDouble());
		
		Optional<Automobil> auto = automobili3.stream().min(Comparator.comparingDouble(a -> Math.abs( (double) a.snagaMotora - prosjecnaSnagaMotora.getAsDouble())));
		System.out.println("Auto sa snagom motora najblizom prosjecnoj: " + auto.get());

	
	}

}
