package paket;

import java.io.File;

public class Main {

	
	private static int brojObrisanihFajlova;
	
	public static void deleteFilesRecursively(File directory, String key) {
		File[] files = directory.listFiles();
		
		if(files==null) {
			return;
		}
		
		for(File file : files) {
			if(file.isDirectory()) {
				deleteFilesRecursively(file,key);
			} else {
				String fileNameWithoutExtension = file.getName().split("\\.")[0];
				if(fileNameWithoutExtension.equalsIgnoreCase(key.toLowerCase())) {
					if(file.delete()) {
						System.out.println("Obrisan fajl " + fileNameWithoutExtension);
						brojObrisanihFajlova++;
					} else {
						System.out.println("Neuspjesno brisanje fajla");
					}
				}
			}
			
		}
	}
	
	public static void main(String[] args) {
		
		if(args.length < 2) {
			System.out.println("Nedovoljno argumenata");
		}
		
		String rootDirectoryPath = args[0];
		String key = args[1];
		
		File rootDirectory = new File(rootDirectoryPath);

		if(!rootDirectory.exists() || !rootDirectory.isDirectory()) {
            System.out.println("GRESKA: Putanja ne postoji ili putanja ne vodi do foldera!");
            return;
		}
		
		deleteFilesRecursively(rootDirectory, key);
		
		System.out.println("Broj obrisanih: " + brojObrisanihFajlova);
	}

}
