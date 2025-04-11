package pack;

public class Polje {
	public TipPolja tip;
	public int gubitak;
	
	public Polje(TipPolja tip, int gubitak) {
		this.tip = tip;
		this.gubitak = gubitak;
	}
	
	@Override
	public String toString() {
		return "Tip: " + tip + " Gubitak: " + gubitak;
	}
}
