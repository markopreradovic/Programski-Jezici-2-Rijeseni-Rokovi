package paket;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Stack;
public class Main {

	public static Object lockObject = new Object();
	public static int dodanihKnjiga = 0;
	public static int uklonjenihKnjiga = 0;
	
	public static Stack<Knjiga> stek = new Stack<>();
	
	public static long vrijemeIzvrsavanja = 20000;
	
	public static void main(String[] args) {
		
		DodavanjeNiti dodavanjeNiti = new DodavanjeNiti();
		BrisanjeNiti brisanjeNiti = new BrisanjeNiti();
		
		dodavanjeNiti.start();
		brisanjeNiti.start();
		
		long startTime = System.currentTimeMillis();
		while ((System.currentTimeMillis() - startTime) < vrijemeIzvrsavanja) {
            
        }
		
		dodavanjeNiti.setKrajVremena();
		brisanjeNiti.setKrajVremena();
		
		try {
			dodavanjeNiti.join();
			brisanjeNiti.join();
		}catch(InterruptedException ex) {
			ex.printStackTrace();
		}
		
		System.out.println("\nDODANIH KNJIGA = " + dodanihKnjiga);
        System.out.println("\nOBRISANIH KNJIGA = " + uklonjenihKnjiga);

        File txtFajl = new File("knjigeSaSteka.txt");
        try (PrintWriter pw = (new PrintWriter(txtFajl))) {
            while (!stek.isEmpty()) {
                pw.println(stek.pop());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
	}

}
