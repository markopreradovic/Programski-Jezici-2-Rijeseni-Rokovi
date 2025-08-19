import java.io.*;

public abstract class Artikal implements Serializable{
	public double cijena;
	public boolean kupljen = false;
	
	public Artikal(){
		
	}
	
	public Artikal(double cijena){
		this.cijena = cijena;
	}
	
	@Override
	public String toString(){
		return "" + cijena;
	}
}