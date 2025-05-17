package paket;

import java.util.*;
import java.io.*;

public class JSONParser {

	public HashMap<String, String> load(File file){
		HashMap<String, String> hashMap = new HashMap<>();
		
		try(BufferedReader br = new BufferedReader(new FileReader(file))){
			// preskacemo {
			br.readLine();
			
			String linija;
			
			while(!(linija = br.readLine()).contains("}")) {
				this.obradiLiniju(linija, hashMap);
			}
		} catch (IOException ex) {
			System.out.println("Greska prilikom citanja fajla");
		}
		
		return hashMap;
	}
	
	public void obradiLiniju(String linija, HashMap<String, String> hashMap) {
		String[] par = linija.split(": ");
		String polja = par[0].replaceAll("\"", "").trim();
		String vrijednost = par[1].replaceAll("[\",]", "").trim();
		hashMap.put(polja, vrijednost);
		//System.out.println("POLJE: " + polje + "\nVRIJEDNOST: " + vrijednost);
	}
	
	
}
