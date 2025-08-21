import java.util.*;
import java.util.concurrent.*;
public class Simulacija {
	
	public BlockingQueue<Poruka> buffer = new LinkedBlockingQueue<>();
	public List<Radnik> radnici = new ArrayList<>();
	private volatile boolean zaustavljeno = false; 
	
	public static void main(String[] args){
		
		List<String> maticniBrojevi = new ArrayList<>();
		radnici.add(new Radnik("Marko", "Petrović", "1001", 50000, buffer, Arrays.asList("1002", "1003")));
        radnici.add(new Radnik("Ana", "Jovanović", "1002", 45000, buffer, Arrays.asList("1001", "1003")));
        radnici.add(new Radnik("Stefan", "Nikolić", "1003", 48000, buffer, Arrays.asList("1001", "1002")));
		
		for(Radnik r : radnici){
			maticniBrojevi.add(r.jmb);
			r.start();
		}
		
		Teleekran teleekran = new Teleekran();
		Nadzornik nadzornik = new Nadzornik("Veliki", "Brat", "2001", buffer, radnici, teleekran);
        Prepravljac prepravljac = new Prepravljac("Ministrium", "Istine", "3001", buffer, teleekran);
		
		nadzornik.start();
		prepravljac.start();
		
		komandniInterfejs();
		
	}
	
	//PREPRAVITI SAMO OD OVE TACKE PA DALJE
	
	private void komandniInterfejs() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nKomande: PORUKA <tekst>, PAUZA, NASTAVAK, ZAUSTAVI");
        
        while (!zaustavljeno) {
            System.out.print("> ");
            String komanda = scanner.nextLine().trim();
            
            if (komanda.equalsIgnoreCase("ZAUSTAVI")) {
                zaustaviSimulaciju();
                break;
            } else if (komanda.equalsIgnoreCase("PAUZA")) {
                pauzirajSimulaciju();
            } else if (komanda.equalsIgnoreCase("NASTAVAK")) {
                nastaviSimulaciju();
            } else if (komanda.toLowerCase().startsWith("poruka ")) {
                String tekst = komanda.substring(7);
                nadzornik.posaljiPorukunaNaTeleekran(tekst);
            } else {
                System.out.println("Nepoznata komanda!");
            }
        }
        scanner.close();
    }
    
    private void pauzirajSimulaciju() {
        if (!pauza) {
            pauza = true;
            pauzaVreme = System.currentTimeMillis();
            System.out.println("Simulacija je pauzirana.");
            
            // Pokreni timer za automatsko zaustavljanje nakon minute
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (pauza) {
                        System.out.println("\nPauza je duže od minute - simulacija se automatski zaustavlja!");
                        zaustaviSimulaciju();
                        System.exit(0);
                    }
                }
            }, 60000); // 60 sekundi
        }
    }
    
    private void nastaviSimulaciju() {
        if (pauza) {
            long trajanjePauze = System.currentTimeMillis() - pauzaVreme;
            pocetakSimulacije += trajanjePauze; // Dodajemo vreme pauze
            pauza = false;
            System.out.println("Simulacija je nastavljena.");
        }
    }
    
    private void zaustaviSimulaciju() {
        System.out.println("\n=== ZAUSTAVLJANJE SIMULACIJE ===");
        zaustavljeno = true;
        
        // Zaustavljamo sve entitete
        for (Radnik radnik : radnici) {
            radnik.zaustaviRad();
        }
        nadzornik.zaustaviRad();
        prepravac.zaustaviRad();
        teleekran.zaustavi();
        
        // Prekidamo sve niti
        for (Thread nit : niti) {
            nit.interrupt();
        }
        
        // Čekamo da se niti završe
        for (Thread nit : niti) {
            try {
                nit.join(2000); // Čekamo max 2 sekunde po niti
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Vraćamo interrupt status
            }
        }
        
        // Prikaz konačnih rezultata
        prikaziKonacneRezultate();
    }
    
    private void prikaziKonacneRezultate() {
        long trajanjeMS = System.currentTimeMillis() - pocetakSimulacije;
        long sekunde = trajanjeMS / 1000;
        
        System.out.println("\n=== SPISAK SVIH RADNIKA ===");
        for (Radnik radnik : radnici) {
            System.out.println(radnik);
        }
        
        System.out.println("\nVreme trajanja simulacije: " + sekunde + " sekundi");
        
        System.out.println("\n=== PORUKE U BUFFERU ===");
        for (Poruka poruka : buffer) {
            System.out.println(poruka);
        }
    }
	
}