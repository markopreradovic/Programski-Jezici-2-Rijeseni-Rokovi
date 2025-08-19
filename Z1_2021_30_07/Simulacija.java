import java.util.*;
import java.io.*;
public class Simulacija {
	
	public static ArrayList<Kasa> kase = new ArrayList<>();
	public static ArrayList<Kupac> kupci = new ArrayList<>();
	public static ArrayList<Artikal> artikli = new ArrayList<>();
	
	public static int BR_ARTIKAL = 200;
	public static int UKUPNO = BR_ARTIKAL * 6;
	
	public static void main(String[] args){
		initKase();
		initKupci();
		initArtikli();
		
		try{
			for(Kupac k : kupci)
				k.start();
			for(Kasa k : kase)
				k.start();
			
			for(Kupac k : kupci)
				k.join();
			for(Kasa k : kase)
				k.join();
			
			System.out.println("Simulacija završena!");
			System.out.println("Neuspješne kupovine:");
			deser();
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private static void deser(){
		File[] files = new File(".").listFiles();
		for(File f : files){
			if(f.getName().endsWith(".ser")){
				try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))){
					Kupac kupac = (Kupac) ois.readObject();
					Kasa kasa = (Kasa) ois.readObject();
					System.out.println("Kupac " + kupac + "Kasa " + kasa);
				} catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void initKase(){
		for(int i=0;i<2;i++){
			kase.add(new Kasa(TipKase.KOLICA,true));
			kase.add(new Kasa(TipKase.KORPA,true));
			kase.add(new Kasa(TipKase.SAMOSTALNA_KUPOVINA,false));
		}
		kase.forEach(System.out::println);
	}
	
	public static void initKupci(){
		for(int i=0;i<10;i++){
			kupci.add(new PravnoLice());
			kupci.add(new Dijete());
			kupci.add(new Odrasli(true));
			kupci.add(new Odrasli(false));
		}
		kupci.forEach(System.out::println);
	}
	
	public static void initArtikli(){
		for(int i=0;i<BR_ARTIKAL;i++){
			artikli.add(new Hljeb());
			artikli.add(new Mlijeko());
			artikli.add(new Cigarete());
			artikli.add(new Pivo());
			artikli.add(new Sok());
			artikli.add(new Slatkis());
		}
	}
}