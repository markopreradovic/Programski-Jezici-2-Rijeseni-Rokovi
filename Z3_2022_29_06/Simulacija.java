import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Simulacija{
	
	static Boolean END = false;
	static Red red = new Red();
	
public static void main(String[] args) throws FileNotFoundException{
	
	if(args.length <2) {
		return;
	}
	
	Long postotak = 0L;
	try{
		postotak = Long.parseLong(args[0]);
		if(postotak < 0 || postotak > 100){
			return;
		}
	} catch (Exception e){
		e.printStackTrace();
	}
	
	AddThread addThread = new AddThread(postotak);
	RemoveThread removeThread = new RemoveThread(100-postotak);
	
    try{
		Thread.sleep(60000); //1 minut simulacije
	} catch(Exception e) {
		e.printStackTrace();
	}
	
	END = true;
	
	try{
		addThread.join();
		removeThread.join();
	} catch(InterruptedException e){
		e.printStackTrace();
	}
	
	// Ispisivanje preostalih studenta iz reda u fajl
    String fajl = "src/pismeni.R_2022_06_29/Z03/fajl.txt";
    PrintWriter pw = new PrintWriter(fajl);
    red.studenti.forEach(pw::println);
    }
	
}
	
