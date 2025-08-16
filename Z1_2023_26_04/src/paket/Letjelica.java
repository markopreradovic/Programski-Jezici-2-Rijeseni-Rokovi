package paket;
import java.util.*;
public class Letjelica extends Thread{
	public PM pogonskiModul;
	public NM navigacioniModul;
	public KM komunikacioniModul;
	
	public Letjelica(List<Motor> motori, int koord) {
		initMotori(motori,koord);
		start();
	}
	
	@Override
    public void run() {
        pogonskiModul.start();
        navigacioniModul.start();
        komunikacioniModul.start();
    }
	
	public void initMotori(List<Motor> motori, int koord) {
		pogonskiModul = new PM(motori);
		navigacioniModul = new NM(koord);
		komunikacioniModul = new KM();
	}
}
