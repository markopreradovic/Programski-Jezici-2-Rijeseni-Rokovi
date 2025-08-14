package paket;

public abstract class Adapter{

	static String PATH = "paket/PJ2_exam_data";
	public abstract void importData(String fileName);
	
	public void addObject(String[] elements) {
		
		if(elements.length!=6) return;
        // tf102,S8,2,1530,Samsung,Telefon
		
		try {
			Proizvod proizvod = new Proizvod(elements[0], elements[1], 
					Integer.parseInt(elements[2]), Double.parseDouble(elements[3]));
			Proizvodjac proizvodjac = new Proizvodjac(elements[4]);
			Vrsta vrsta = new Vrsta(elements[5]);
			Main.podaci.add(proizvod);
			Main.podaci.add(proizvodjac);
			Main.podaci.add(vrsta);
		} catch(NumberFormatException ex) {
			return;
		}
			
	}
	
}
