package paket;

import java.nio.file.*;
import java.io.*;       
import java.util.*;     
import java.util.stream.*;

public class Main {

	public static Path subfolderPath; // Putanja do podfoldera koji će se kreirati u root folderu

	public static void main(String[] args) {

		long pocetakMjerenja = System.currentTimeMillis(); 

		
		if (args.length != 2) {
			System.out.println("Format: java Main <putanja_do_root_foldera> <naziv_podfoldera>");
			return; 
		}

		Path rootPath = Paths.get(args[0]); // Prvi argument – root direktorijum
		String subfolderName = args[1];     // Drugi argument – ime podfoldera
		subfolderPath = Paths.get(rootPath.toString() + "/" + subfolderName); // Kreira punu putanju do podfoldera

		// Pokušaj kreiranja podfoldera unutar root foldera
		try {
			Files.createDirectory(subfolderPath);
		} catch(IOException ex) {
			ex.printStackTrace(); 
		}

		// Mapa u kojoj se čuvaju liste fajlova po ekstenzijama (npr. "txt" -> [file1, file2])
		HashMap<String, List<Path>> pathMap = new HashMap<>();

		try {
			// Rekurzivno prolazi kroz sve fajlove i foldere počevši od rootPath,
			// sakuplja sve putanje u listu
			List<Path> paths = Files.walk(rootPath).collect(Collectors.toList());

			for(Path path : paths) {
				if(!Files.isDirectory(path)) { // Ako je fajl (ne direktorijum)
					String onlyName = path.getFileName().toString(); // Ime fajla bez putanje
					String fileName = path.toString(); // Puna putanja do fajla
					
					// Uzimanje ekstenzije fajla (nakon posljednje tačke)
					String extension = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());

					// Dodaje fajl u listu unutar mape za njegovu ekstenziju
					pathMap.computeIfAbsent(extension, k -> new ArrayList<>()).add(path);

					// Kopira fajl u odgovarajući folder unutar podfoldera (po ekstenziji)
					placeInFolder(extension, path, onlyName);
				}
			}
		} catch(IOException ex) {
			ex.printStackTrace(); // Greška prilikom obrade fajlova
		}

		// Ispis statistike za svaku grupu fajlova po ekstenziji
		for (Map.Entry<String, List<Path>> entry : pathMap.entrySet()) {
			System.out.println("============================");
			String extension = entry.getKey();              // Ekstenzija (npr. "txt")
			int numOfFiles = entry.getValue().size();       // Broj fajlova te ekstenzije
			System.out.println("Broj fajlova " + extension + " ekstenzije: " + numOfFiles);
			
			long velicinaFoldera = 0; // Ukupna veličina svih fajlova te ekstenzije

			try {
				// Računa veličinu svakog fajla i sabira
				for(Path path : entry.getValue()) {
					velicinaFoldera += Files.size(path);
				}
			} catch (IOException ex) {
				System.out.println("GRESKA prilikom racunanja velicine foldera");
				return;
			}

			// Ispis ukupne i prosječne veličine fajlova
			System.out.println("Velicina oldera: " + velicinaFoldera + "bytes");
			System.out.println("Prosjecna velicina fajla: " + ((double) velicinaFoldera / (double) entry.getValue().size()) + " bytes"); 
		}

		long krajMjerenja = System.currentTimeMillis(); // Kraj merenja vremena
		System.out.println("Vrijeme izvrsavanja programa = " + ((double)(krajMjerenja - pocetakMjerenja)/1000) + "s"); // Trajanje programa u sekundama
	}

	// Pomoćna metoda koja fajl premješta u novi folder po njegovoj ekstenziji
	private static void placeInFolder(String extension, Path sourcePath, String onlyName) throws IOException {
		Path folderPath = Paths.get(subfolderPath.toString() + "/" + extension); // Npr. .../subfolder/txt

		// Ako folder za tu ekstenziju ne postoji – kreiraj ga
		if (!Files.exists(folderPath)) {
			Files.createDirectory(folderPath);
		}

		// Postavi destinaciju kopiranja fajla u novi folder
		Path destinationPath = Paths.get(folderPath.toString() + "/" + onlyName);

		// Kopira fajl u odgovarajući folder
		Files.copy(sourcePath, destinationPath);
	}
}
