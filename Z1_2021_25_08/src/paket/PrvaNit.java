package paket;
import java.util.*;
import java.util.stream.*;
import java.io.*;

public class PrvaNit extends Thread {
	
	public List<Posiljka> arr = new ArrayList<Posiljka>();
	
	public PrvaNit(List<Posiljka> arr) {
		this.arr = arr;
	}
	
	@Override
	public void run() {
		List<Posiljka> arr1 = arr.stream().filter(t -> t instanceof Pismo).collect(Collectors.toList());
		List<Posiljka> arr2 = arr.stream().filter(t -> t instanceof Razglednica).collect(Collectors.toList());
		List<Posiljka> arr3 = arr.stream().filter(t -> t instanceof VPosiljka).collect(Collectors.toList());
		
		serialization(arr1);
		serialization(arr2);
		serialization(arr3);
		
		DrugaNit dn = new DrugaNit(arr1,arr2,arr3);
		dn.start();
	}
	
	
	//sortira u zasebne fajlove
	private void serialization(List<Posiljka> arr) {
		String loc = "Razglednica";
		if (arr.get(0) instanceof Pismo) loc = "Pismo";
		else if (arr.get(0) instanceof VPosiljka) loc = "VPosiljka";
		
		for (Posiljka i : arr) {
			try {
				FileOutputStream fout = new FileOutputStream(System.getProperty("user.dir") + File.separator + loc + File.separator + System.currentTimeMillis() + ".ser");
				ObjectOutputStream out = new ObjectOutputStream(fout);
				
				out.writeObject(i);
				
				out.flush();
				out.close();
				
				Thread.sleep(20);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
