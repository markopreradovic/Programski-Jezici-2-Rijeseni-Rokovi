package paket;

public class Razarac extends Plovilo{
	public Radar radar;
	public Torpedo torpedo;
	public boolean imaRaketniStit;
	
	public Razarac(String id, int x, int y, boolean imaRaketniStit) {
		super(id,x,y);
		this.radar = radar;
		this.torpedo = torpedo;
		this.imaRaketniStit = imaRaketniStit;
	}
}
