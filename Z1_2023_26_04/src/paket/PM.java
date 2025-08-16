package paket;
import java.util.*;
public class PM extends Modul{

	public List<Motor> motori = new ArrayList<Motor>();
	public boolean status;
	public boolean raketniPogon = false;
	
	public PM(List<Motor> motori) {
		this.motori = motori;
		setDaemon(true);
	}
	
	@Override
	public void run() {
		try {
			motori.stream().forEach(m -> m.start());
			raketniPogon = true;
			while(!Main.STOP) {
                int brPokvarenih = (int) motori.stream().filter(m -> m.status.equals(Status.POKVAREN)).count();
				if(brPokvarenih > motori.size()/2) {
					status = true;
					
					synchronized(Main.poruke){
						Main.poruke.add(new Poruka(Prioritet.KRITICNO, NM.poruke.get(Prioritet.KRITICNO)));
						Main.STOP = true;
						System.out.println("Evakuacija");
						break;
					}
					
				}
				Thread.sleep(1000);
			}
			
			
		} catch(InterruptedException e) {
			this.currentThread().interrupt(); 
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
}
