package paket;

import java.io.File;
import java.nio.file.Files;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;


public class Main {

    static final String folder = "results";

	
	public static void main(String[] args) {
		if(args.length<2) {
			System.out.println("Nedovoljno argumenata");
			return;
		}
		
		new File(folder).mkdir();
		
		try {
			File file = new File(args[0]);
			if(file.isFile()) {
				System.out.println("FILE!");
				List<String> lines = Files.readAllLines(file.toPath());
				System.out.println("Nesortirano" + lines);
				List<String> sorted = lines.stream().sorted(String::compareTo).collect(Collectors.toList());
				System.out.println("Sortirano" + sorted);
				
				Map<String, List<String>> mapa = sorted.stream().collect(Collectors.groupingBy(s -> s.split("")[0]));
				
				for(Map.Entry<String, List<String>> entry : mapa.entrySet()) {
					String naziv = folder + File.separator + "sortirani" + entry.getKey() + ".txt";
					try(PrintWriter pw = new PrintWriter(new FileWriter(naziv))) {
						entry.getValue().forEach(s -> {
							pw.println(s);
						});
					} catch(Exception e) {
						e.printStackTrace();
					}
                    System.out.println(new File(naziv).getAbsolutePath() + " broj redova " + entry.getValue().size());
				}
				
			} else if(file.isAbsolute()) {
				System.out.print("DIRECTORY");
				File[] files = file.listFiles();
				ArrayList<File> fajlovi = new ArrayList<>(Arrays.asList(files));
				
				//Najveci fajl
				File najveci = fajlovi.stream().max(Comparator.comparingLong(File::length)).orElse(null);
				System.out.println("Najveci fajl " + najveci.getName());
				
				//Ukupna velicina prostora koju zauzimaju svi fajlovi
				Long prostor = fajlovi.stream().filter(File::isFile).mapToLong(File::length).sum();
				System.out.println("Ukupan prostor koji zauzimaju svi fajlovi " + prostor);
				
				//Prikaz fajlova grupisanih po ekstenziji sortiranih abecedno
				fajlovi.stream().collect(Collectors.groupingBy(f -> f.getName().split("\\.")[1])).entrySet()
				.stream().sorted( (e1,e2) -> {
					return e1.getKey().compareTo(e2.getKey());
				}).forEach(System.out::println);
				
				//Kreiranje mape koja cuva kao kljuc apsolutnu putanju a kao vrijednost fajl objekat
				Map<String, File> mapa = new HashMap<>();
				fajlovi.forEach(f -> mapa.put(f.getAbsolutePath(), f));
				mapa.entrySet().forEach(System.out::println);
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
