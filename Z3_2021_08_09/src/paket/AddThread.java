package paket;
import java.util.Random;
public class AddThread extends Thread{
	Random rand = new Random();
    UlancaniStek<Integer> stek;
    int number;
    
    public AddThread(UlancaniStek<Integer> stek, int number) {
    	this.stek = stek;
    	this.number = number;
    }
    
    @Override
    public void run() {
    	int vrijednost;
    	for(int i=0;i<number;i++) {
    		if(StackMain.END) break;
    		synchronized(stek) {
    			vrijednost = rand.nextInt(100);
    			stek.push(vrijednost);
    		}
    		try {
    			sleep(rand.nextInt(100) + 200);
    		} catch(InterruptedException ex) {
    			ex.printStackTrace();
    		}
            System.out.println("Dodano na stek " + vrijednost);

    	}
    }
}
