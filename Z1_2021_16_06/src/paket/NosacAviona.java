package paket;

public class NosacAviona extends Plovilo{
	public Radar radar;
	public Torpedo torpedo;
	public Raketa raketa;
	public boolean imaRaketniStit;
	public boolean imaStitTorpedo;
	
	public NosacAviona(String id, int x, int y, boolean raketniStit, boolean torpedoStit) {
		super(id,x,y);
		this.imaRaketniStit = raketniStit;
		this.imaStitTorpedo = torpedoStit;
	}
	
}
