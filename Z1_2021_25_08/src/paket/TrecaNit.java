package paket;
import java.util.*;


public class TrecaNit extends Thread {
	
	Paket<Posiljka> paket = null;
	public static Object o = new Object();
	
	public TrecaNit(Paket<Posiljka> paket) {
		this.paket = paket;
	}
	
	@Override
	public void run() {
		if (paket.tezina >= 15) {
			List<Posiljka> slanje1 = new ArrayList<Posiljka>();
			List<Posiljka> slanje2 = new ArrayList<Posiljka>();
			List<Posiljka> slanje3 = new ArrayList<Posiljka>();

			//Dijeli u tri dijela( od pocetka do prve trecine, od prve trecine do druge i od trece pa do kraja)
			for (int i=0;i<paket.arr.size() / 3;i++)
				slanje1.add(paket.arr.get(i));
			for (int i=paket.arr.size() / 3;i<paket.arr.size() / 3 * 2;i++)
				slanje2.add(paket.arr.get(i));
			for (int i=paket.arr.size() / 3 * 2;i<paket.arr.size();i++)
				slanje3.add(paket.arr.get(i));
			try{
				ispis(slanje1);
				Thread.sleep(3000);
				ispis(slanje2);
				Thread.sleep(3000);
				ispis(slanje3);
			}catch (Exception e){
				e.printStackTrace();
			}
			
			
		} else paket.slanje();
	}
	
	public static void ispis(List<Posiljka> arr){
		if (arr.size() == 0)
			return;
		
		synchronized(o){
			System.out.println("==================================");
			System.out.println("SLANJE POSILJKI: ");
			for (Posiljka i : arr)
				System.out.println(i);
			System.out.println("==================================");
		}
	}
	
}
