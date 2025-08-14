package paket;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
public class Main {

	public static ArrayList<String> putanje = new ArrayList<>();
	public static String ekstenzija;
	
	public static void obidji(File root) {
		if (root.isFile() && root.getPath().endsWith(ekstenzija)) {
			putanje.add(root.getPath());
		} else if(root.isDirectory()) {
			File[] list = root.listFiles();
			for(File f : list) {
				if(f.isDirectory()) obidji(f);
				else if (f.isFile() && f.getPath().endsWith(ekstenzija))
					putanje.add(f.getPath());
			}
		}
	}
	
	public static void main(String[] args) {
		if (args.length < 3) {
			System.out.println("Nedovoljno argumenata");
			return;
		}
		ekstenzija = args[2];
		
		//Obrada
		File root = new File(args[0]);
		obidji(root);
		
		//Ispis
		putanje.forEach(System.out::println);
		
		//Kopiranje
		Path destDir = Paths.get(args[1]);
		destDir.toFile().mkdir();
		
		for (String f : putanje) {
		    try {
		        Path source = Paths.get(f);
		        Path target = destDir.resolve(source.getFileName());
		        Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		}
	}
	
}
