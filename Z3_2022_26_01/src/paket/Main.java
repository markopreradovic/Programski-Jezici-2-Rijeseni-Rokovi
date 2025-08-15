package paket;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Main {
	

	public static void main(String[] args) throws Exception {
		
		String dir = args[0];
		
		Map<String, Integer> paketi = new HashMap<>();
		
		int ukupnoKlasa = 0;
		int ukupnoKoda = 0;
		int ukupnoKomentara = 0;
		
		//Prolazak kroz sve java fajlove
		for(File file : getAllJavaFiles(new File(dir))) {
			String[] rezultat = analizirajFajl(file);
			String paket = rezultat[0];
			int kod = Integer.parseInt(rezultat[1]);
			int komentar = Integer.parseInt(rezultat[2]);
			
			if(paket.isEmpty()) {
				 paketi.put(paket, paketi.getOrDefault(paket, 0) + 1);
			}
			ukupnoKoda += kod;
			ukupnoKomentara += komentar;
		}
		
		ukupnoKlasa = paketi.values().stream().mapToInt(i -> i).sum();
		
		
		//Upis rezultata
		PrintWriter w = new PrintWriter("rezultat_analize.txt");
        w.println("PAKETI I KLASE:");
        for (String paket : paketi.keySet()) {
            w.println(paket + " - " + paketi.get(paket) + " klasa");
        }
        w.println("\nUKUPNO:");
        w.println("Paketi: " + paketi.size());
        w.println("Klase: " + ukupnoKlasa);
        w.println("Linije koda: " + ukupnoKoda);
        w.println("Linije komentara: " + ukupnoKomentara);
        w.close();
	}
	
	public static List<File> getAllJavaFiles(File dir){
		List<File> javaFiles = new ArrayList<>();
		if(dir.listFiles()!=null) {
			for(File f : dir.listFiles()) {
				if(f.isDirectory()) {
					javaFiles.addAll(getAllJavaFiles(f));
				} else if (f.getName().endsWith(".java")) {
					javaFiles.add(f);
				}
			}
		}
		return javaFiles;
	}
	
	public static String[] analizirajFajl(File file) throws IOException{
		Scanner s = new Scanner(file);
		String paket = "";
		int kod = 0;
		int komentar = 0;
		boolean viselinijski = false;
		
		while(s.hasNextLine()) {
			String linija = s.nextLine().trim();
			
			if(linija.startsWith("package")) {
				paket = linija.replace("package ", "").replace(";", "");
			} else if(linija.startsWith("/*")) {
				viselinijski = true;
				komentar ++;
			} else if(linija.endsWith("*/")) {
				viselinijski = false;
				komentar ++;
			} else if(viselinijski || linija.startsWith("//")) {
				komentar ++;
			} else if(!linija.isEmpty()) {
				kod++;
			}
		}
		s.close();
		return new String[] { paket, kod + "", komentar + ""};
		}
	}
	

