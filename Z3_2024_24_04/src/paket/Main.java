// Komande za kompajliranje i kreiranje JAR fajla:
//
// 1. Kompajliranje:
// javac -d bin -sourcepath src src/paket/Main.java
//
// 2. Kreiranje JAR fajla:
// jar cfe skriveni.jar paket.Main -C bin/ .
//
// 3. Pokretanje JAR fajla:
// java -jar skriveni.jar <putanja_do_root_direktorijuma>

package paket;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.*;


public class Main {

	public static int brojSkrivenihFajlova = 0;
	public static int ukupanBrojFajlova = 0;
	
	public static void main(String[] args) {
		
		if(args.length != 1) {
			System.out.println("Greska format mora biti: java main <putanja_do_root_direktorijuma>");
			return;
		}
		
		String rootDirektorijum = args[0];
		Path putanjaDoRootDirektorijuma = Paths.get(rootDirektorijum);
		if(!Files.exists(putanjaDoRootDirektorijuma) || !Files.isDirectory(putanjaDoRootDirektorijuma)) {
			System.out.println("Greska: direktorijum ne postoji / putanja ne vodi do direktorijuma");
			return;
		}
		
		File txtFajl = new File(rootDirektorijum + "/txtFajl.txt");
		try {
			if(!txtFajl.exists()) {
				if(txtFajl.createNewFile()) {
					System.out.println(txtFajl + " je uspjesno kreiran!");
				}	
			} else {
				System.out.println("Fajl vec postoji");
			}
			obradiDirektorijum(putanjaDoRootDirektorijuma);
		} catch(IOException ex) {
			System.out.println("Greska prilikom kreiranja txt fajla");
			return;
		}
		upisiStatistiku(putanjaDoRootDirektorijuma.toString());


	}
	
	public static void obradiDirektorijum(Path putanjaRootDirektorijuma) throws IOException {
		Files.walk(putanjaRootDirektorijuma).forEach(path -> {
			try {
				if(Files.isHidden(path)) {
					brojSkrivenihFajlova++;
					upisiUTxtFajl(path, putanjaRootDirektorijuma.toString());
				}
				if(Files.isRegularFile(path)) 
					ukupanBrojFajlova++;
				
			} catch(IOException ex) {
				System.out.println("Greska prilikom obrade direktorijuma");
			}
		});
	}
	
	public static void upisiUTxtFajl(Path path, String rootDirektorijum) {
		try (PrintWriter pw = new PrintWriter(new FileWriter(rootDirektorijum + "/txtFajl.txt", true))){
			pw.println(path);
		} catch(IOException ex) {
			System.out.println("Greska prilikom pisanja fajla " + path);
		}
	}
	
	public static void upisiStatistiku(String rootDirektorijum) {
		try (PrintWriter pw = new PrintWriter(new FileWriter(rootDirektorijum + "/txtFajl.txt", true))){
			pw.println("Ukupan broj fajlova: " + ukupanBrojFajlova);
			pw.println("Broj skrivenih fajlova" + brojSkrivenihFajlova);
			pw.printf("Procenat skrivenih fajlova: %.2f%s", ((double) brojSkrivenihFajlova / (double) ukupanBrojFajlova), "%");
		} catch(IOException ex) {
			System.out.println("Greska prilikom pisanja fajla");
		}
	}

}
