package paket;

import java.util.ArrayList;
import java.util.TreeSet;
import java.util.stream.Stream;

public class Main {

	static TreeSet<Student> studenti = new TreeSet<>();
    static ArrayList<Student> nagradjeni = new ArrayList<>();
    static boolean END = false;
	
    public static void main(String[] args ) {
    	
    	for(int i=0;i<2000;i++) {
    		studenti.add(new Student());
    	}
    	
    	try {
    		int redniBroj=1;
    		for(int i=0;i<50;i++) {
    			Nit nit = new Nit(redniBroj);
    		}
    		
    		while (!Main.END) {
                Thread.sleep(100);
            }
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	System.out.println("Pronadjeni su ");
    	nagradjeni.forEach(System.out::println);
    	
    }
}
