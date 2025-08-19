public class Pivo extends Artikal implements ArtikalZaPlus18{
	
	public Pivo(){
		super();
		cijena = 1.0;
	}
	
	public String toString(){
		return "Pivo" + super.toString();
	}
}