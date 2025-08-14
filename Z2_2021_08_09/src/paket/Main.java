package paket;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
public class Main {

	public static ArrayList<Student> lista = new ArrayList<>();
	
	public static void main(String[] args) {
		
		//Generisanje 80 studenata
		for(int i=0;i<80;i++) {
			lista.add(new Student());
		}
		
		//1. Ukupan broj studenata po godini
		Map<Integer, List<Student>> mapa;
		mapa = lista.stream().collect(Collectors.groupingBy(s -> s.godStudija));
		mapa.forEach( (key,value) -> System.out.println("Godina studija " + key + " broj studenata " + value.size()) );
		
		
		//2. Tri najcesca prezimena studenata
		System.out.println("Tri najcesca prezimena studenata \n" );
		lista.stream().collect(Collectors.groupingBy(s -> s.prezime)).entrySet()
			 .stream().sorted( (e1,e2) -> e2.getValue().size() - e1.getValue().size()).limit(3)
			 .forEach(entry -> System.out.println(entry.getKey() + " " + entry.getValue().size()));
		
		//3. Najcesce ime studenta
		System.out.println("Najcesce ime studenta");
		lista.stream().collect(Collectors.groupingBy(s -> s.ime)).entrySet()
			.stream().sorted( (e1,e2) -> e2.getValue().size() - e1.getValue().size()).limit(1)
			.forEach(entry -> System.out.println(entry.getKey() + " " + entry.getValue().size()));
		
		//4. Prikaz studenata grupisanih po godinama starosti na sljedeci nacin> 18-23, 24-29, 30-35
        System.out.println("Prikaz svih studenata grupisanih po godinama starosti na sljedeci nacin: 18 - 23, 24 - 29, 30 - 35");
        lista.stream().collect(Collectors.groupingBy( s -> {
        	int godina = 2022;
        	int broj = godina - s.godRodjenja;
        	 if (broj >= 18 && broj <= 23)
                 return "18 - 23 ";
             else if (broj >= 24 && broj <= 29)
                 return "24 - 29 ";
             else
                 return "30 -35 ";
        })).forEach((key2,value1) -> System.out.println(key2 + " " + value1));
        
        //5 i 8. Prikaz svih studenata grupisanih po prezimenu
        System.out.println("\nPrikaz svih studenata grupisanih po prezimenu");
        lista.stream().collect(Collectors.groupingBy(s -> s.prezime)).forEach( (key1,value) -> System.out.println(key1 + " " + value));
	
        System.out.println("\nPrikaz svih studenata grupisanih po prezimenu po opadajucem redoslijedu");
        lista.stream().collect(Collectors.groupingBy(s -> s.prezime)).entrySet().stream()
        	.sorted( (e1,e2) -> e2.getKey().compareTo(e1.getKey())).forEach(e -> System.out.println(e.getKey() + " " + e.getValue()));
        	
        	
        //6. Prikaz najboljeg studenta za svaku godinu studija
        System.out.println("\nPrikaz najboljeg studenta za svaku godinu studija");
        Map<Integer, List<Student>> map = lista.stream().collect(Collectors.groupingBy(s -> s.godStudija));
        map.forEach((key,stud) -> {
        	stud.sort((s1,s2) -> Double.compare(s1.prosjek, s2.prosjek));
        	System.out.println(key + "godina studina najbolji student" + stud.get(0));
        });
        
        //7. Prikaz svih razlicitih godina rodjenja u formatu godina1#godina2#.. koristenjem reduce metode
        System.out.println("Prikaz svih razlicitih godina rodenja u formatu godina1#godina2#...koristenjem reduce metode");
        String rez = lista.stream().mapToInt(s -> s.godRodjenja).distinct().sorted()
        		.mapToObj(String::valueOf).reduce("", (s1, s2) -> s1 + "#" + s2);
        System.out.println(rez);
	
	}
}
