public class Cigarete extends Artikal implements ArtikalZaPlus18{
	
	public Cigarete(){
		super();
		cijena = 5.0;
	}
	
	public String toString(){
		return "Cigarete" + super.toString();
	}
}