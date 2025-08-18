import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main{
	
	static Scanner scan = new Scanner(System.in);
	public static ArrayList<Proizvod> proizvodi;
	
	
	public static void main(String[] args){
	
		//Deserijalizacija
		String fajlIn = "";
		String currentDir = System.getProperty("user.dir");
		File[] files = new File(currentDir).listFiles();
		assert files != null;
		for(File f : files){
			if(f.getPath().endsWith(".ser")){
				System.out.println(f.getPath());
				fajlIn = f.getPath(); //poslednji je najnoviji?
			}
		}
		
		if(!fajlIn.isEmpty()){
			try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fajlIn))){
				proizvodi = (ArrayList<Proizvod>) ois.readObject();
			} catch(IOException | ClassNotFoundException e ){
				e.printStackTrace();
			}
		} else{
			proizvodi = new ArrayList<>();
		}
		
		//MAIN
		String input = "";
		while(!"END".equals(input)){
			System.out.println();
            System.out.println("a1. Pregled svih proizvoda");
            System.out.println("b. Pregled jednog proizvoda po sifri");
            System.out.println("c. Dodavanje proizvoda");
            System.out.println("d. Brisanje jednog proizvoda po sifri");
            System.out.println("e. Spisak svih proizvoda grupsianih po tipu");
            System.out.println("f. spisak svih prouzvoda čija je cijena u zadatom opsegu od x do y");
			System.out.print("Unesite opciju ");
            input = scan.nextLine();
			
			switch(input){
				case "a1" : {
					proizvodi.forEach(System.out::println);
					break;
				}
				case "b" : {
					String idd = scan.nextLine();
					proizvodi.stream().filter(p -> p.id.equals(idd)).forEach(System.out::println);
					break;
				}
				case "c" : {
					System.out.print("Unesite id ");
                    String id = scan.nextLine();
                    System.out.print("Unesite naziv ");
                    String naziv = scan.nextLine();
                    System.out.print("Unesite opis ");
                    String opis = scan.nextLine();
                    System.out.print("Unesite cijenu ");
                    int cijena = scan.nextInt();
                    System.out.print("Unesite tip (0-1)");
                    int tip = scan.nextInt();
                    Tip tipInput = Tip.KNJIGA;
                    if (tip == 1) {
                        tipInput = Tip.SVESKA;
                    }
                    Proizvod proizvod = new Proizvod(id, naziv, opis, cijena, tipInput);
                    proizvodi.add(proizvod);
                    System.out.println("Proizvod je dodan.");
                    break;
				}
				case "d" : {
					String id = scan.nextLine();
					Proizvod pr = proizvodi.stream().filter(p -> p.id.equals(id)).limit(1).collect(Collectors.toList()).get(0);
					proizvodi.remove(pr);
					break;
				}
				case "e" : {
					proizvodi.stream().collect(Collectors.groupingBy(p -> p.tip)).forEach( (k,v) -> {
					System.out.println(k + "" + v);
					});
					break;
				}
				
				case "f" : {
					int min = Integer.parseInt(scan.nextLine());
					int max = Integer.parseInt(scan.nextLine());
					proizvodi.stream().filter(p -> p.cijena < max && p.cijena > min).forEach(System.out::println);
					break;
				}
			}
		}
		
		//SERIJALIZACIJA
		ArrayList<Proizvod> copyProizvodi = new ArrayList<>(proizvodi);
		File outFile = new File("proizvodi_" + new Date().getTime() + ".ser");
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(outFile))) {
				oos.writeObject(copyProizvodi);
				System.out.println("Snimljeno " + copyProizvodi.size() + " proizvoda u fajl: " + outFile.getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
}