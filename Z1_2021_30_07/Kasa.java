import java.util.*;
import java.util.concurrent.*;
import java.io.*;
public class Kasa extends Thread implements Serializable{
	
	PriorityBlockingQueue<Kupac> kupci = new PriorityBlockingQueue<>();
	ArrayList<String> neuspjesneKupovine = new ArrayList<>();
	public static int usluzenoKupaca = 0;
	public String naziv;
	public TipKase tip;
	public boolean fizickoLice;
	public static int serial = 1;
	
	
	public Kasa(TipKase tip, boolean fizickoLice){
		this.naziv = "Kasa" + serial++;
		this.tip = tip;
		this.fizickoLice = fizickoLice;
	}
	
	@Override
	public void run(){
		while(usluzenoKupaca < 40) {
			System.out.println("Usluzeno je " + usluzenoKupaca + " kupaca");
			try{
				Kupac kupac = kupci.take();
				double trosak = kupac.kupljeno.stream().mapToDouble(a -> a.cijena).sum();
				boolean imaPivoIliCigareta = kupac.kupljeno.stream().filter(a -> a instanceof ArtikalZaPlus18).count() > 0;
				if((kupac instanceof Maloljetno ) && imaPivoIliCigareta){
					ser(kupac,this);
				} else if(kupac.iznos < trosak){
					ser(kupac,this);
				} else {
					System.out.println(kupac + " na kasi" + naziv + " Cijena: " + trosak);
					kupac.iznos -= trosak;
				}
				synchronized(this){
					usluzenoKupaca++;
				}
				Thread.sleep(1000);
			} catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public void ser(Kupac kupac, Kasa kasa){
		String ime = ((kupac instanceof Maloljetno ? "child_" : "buyer_")) + System.currentTimeMillis();
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ime))){
			oos.writeObject(kupac);
			oos.writeObject(kasa);
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	public String toString(){
		return tip + " " + fizickoLice;
	}
	
}