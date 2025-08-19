import java.util.*;
import java.io.*;
import java.util.stream.*;


public abstract class Kupac extends Thread implements Serializable, Comparable<Kupac>{
	
	public ArrayList<Artikal> kupljeno;
	public String naziv;
	public double iznos;
	public boolean korpa; //true - korpa, false - kolica
	public boolean prioritet;
	
	static int serial = 1 ;
	
	public Kupac(){
		
	}
	
	public Kupac(double iznos){
		this.naziv = "Naziv" + serial;
		this.iznos = iznos;
		this.korpa = new Random().nextInt(2) == 1;
		this.kupljeno = new ArrayList<>();
	}
	
	public int compareTo(Kupac k){
		return Boolean.compare(k.prioritet, this.prioritet);
	}
	
	public abstract boolean isFizickoLice();

	public String toString(){
		return naziv + " iznos: " + iznos + " korpa: " + korpa + " " + kupljeno;
	}
	
	@Override
	public void run(){
		int i=0;
		while(i<20){
			int index = new Random().nextInt(Simulacija.UKUPNO);
			Artikal a = Simulacija.artikli.get(index); 
			synchronized(a){
				if(!a.kupljen){
					kupljeno.add(a);
					a.kupljen = true;
					i++;
				}
			}
		}
		
		List<Kasa> kase = Simulacija.kase.stream()
        .sorted(Comparator.comparingInt(k -> k.kupci.size()))
        .collect(Collectors.toList());
		
		
		//??????????????????????????????????
		for(Kasa kasa : kase){
			synchronized(kasa){
				if(this.isFizickoLice() && kasa.fizickoLice){
					if(korpa && (kasa.tip.equals(TipKase.KORPA) || kasa.tip.equals(TipKase.SAMOSTALNA_KUPOVINA))){
						kasa.kupci.put(this); break;
					} 
					else if(!korpa && (kasa.tip.equals(TipKase.KOLICA) || kasa.tip.equals(TipKase.SAMOSTALNA_KUPOVINA))){
						kasa.kupci.put(this); break;
					}
				} else if(!this.isFizickoLice() && !kasa.fizickoLice){
					if(korpa && (kasa.tip.equals(TipKase.KORPA) || kasa.tip.equals(TipKase.SAMOSTALNA_KUPOVINA))){
						kasa.kupci.put(this); break;
					}
					else if(!korpa && (kasa.tip.equals(TipKase.KOLICA) || kasa.tip.equals(TipKase.SAMOSTALNA_KUPOVINA))){
						kasa.kupci.put(this); break;
					}
				}
			}
		}
		
	}
}