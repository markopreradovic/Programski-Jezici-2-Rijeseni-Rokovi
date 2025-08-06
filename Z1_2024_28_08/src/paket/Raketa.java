package paket;

public class Raketa {

	private int xKoordinata;
	private int yKoordinata;
	
	public Raketa(int xRaketa, int yRaketa) {
		this.xKoordinata = xRaketa;
		this.yKoordinata = yRaketa;
	}
	
	@Override
    public String toString() {
        return "Raketa{xKoordinata=" + this.xKoordinata + ", yKoordinata=" + this.yKoordinata + "}";
    }
	
}
