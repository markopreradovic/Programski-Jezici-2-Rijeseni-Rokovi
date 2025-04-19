package paket;
import java.util.*;


public class DrugaNit extends Thread{
	List<Posiljka> arr1 = new ArrayList<Posiljka>();
	List<Posiljka> arr2 = new ArrayList<Posiljka>();
	List<Posiljka> arr3 = new ArrayList<Posiljka>();
	
	public DrugaNit(List<Posiljka> arr1, List<Posiljka> arr2, List<Posiljka> arr3) {
		this.arr1 = arr1;
		this.arr2 = arr2;
		this.arr3 = arr3;
	}
	
	@Override
	public void run() {
		Paket<Posiljka> paket1 = new Paket<Posiljka>();
		Paket<Posiljka> paket2 = new Paket<Posiljka>();
		Paket<Posiljka> paket3 = new Paket<Posiljka>();
		
		Random rand = new Random();
		List<Integer> pos = new ArrayList<Integer>(); //cuva 5 jedinstvenih random indeksa
		
		for(int i=0;i<5;i++) {
			int position = 0;
			
			do {
				position = rand.nextInt(arr1.size());
			}while(pos.contains(position));
			
			pos.add(position);
		}
		
		for (Integer i : pos){
			paket1.addPosiljka(arr1.get(i));
			paket2.addPosiljka(arr2.get(i));
			paket3.addPosiljka(arr3.get(i));
		}
		
		TrecaNit tn1 = new TrecaNit(paket1);
		TrecaNit tn2 = new TrecaNit(paket2);
		TrecaNit tn3 = new TrecaNit(paket3);
		
		tn1.start();
		tn2.start();
		tn3.start();
	}
}
