import java.util.Random;
public class Nadzornik extends Stanovnik  {

	public BlockingQueue<Poruka> buffer;
	public List<Radnik> radnici;
	public Teleekran teleekran;
	public boolean aktivni = true;
	
	public Nadzornik(String ime, String prezime, String jmb, BlockingQueue<Poruka> buffer, List<Radnik> radnici, Teleekran t){
		super(ime,prezime,jmb);
		this.buffer = buffer;
		this.radnici = radnici;
		this.teleekran = t;
	}
	
	public void zaustaviRad(){
		aktivni = false;
	}
	
	@Override
	public void run(){
		String[] kljucneRijeci =  { "sloboda", "ljubav", "neznanje", "nesto"};
		while(aktivni){
			try{
				Thread.sleep(new Random().nextInt(5000) + 3000);
				
				String kljucnaRijec = kljucneRijeci[new Random().nextInt(kljucneRijeci.length)];
				List<String> pronadjeniRadnici = pretraziPoruke(kljucnaRijec);
				
				if(!pronadjeniRadnici = pretraziPoruke(kljucnaRijec)) {
					for(String s : pronadjeniRadnici){
						int kazna = new Random().nextInt(901) + 100;
						umanjiPlatu(s,kazna);
						teleekran.posaljiPoruku("Radniku " + jmbPosiljaoca + 
                            ": Zbog neadekvatnog ponašanja, vaša plata će biti umanjena za " + kazna + ".");
					}
				}
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public List<String> pretraziPoruke(String kljucna){
		List<String> rezultat = new ArrayList<>();
		List<String> poruke = new ArrayList<>(buffer);
		
		for(Poruka poruka : poruke){
			if(poruka.tekst.toLowerCase().contains(kljucna.toLowerCase())){
				if(!rezultat.contains(poruka.jmbPosiljaoca)){
					rezultat.add(poruka.jmbPosiljaoca);
				}
			}
		}
		
		if(!rezultat.isEmpty()) 
			System.out.println("Nadzornik pronasao radnike sa kljucnom rijeci " + kljucna + " " + rezultat);
		
		return rezultat;
	}
	
	public void umanjiPlatu(String mb, int iznos){
		for(Radnik radnik : radnici) {
			if(mb == radnik.jmbPosiljaoca){
				radnik.plata -= iznos;
				break;
			}
		}
	}
	
	public void posaljiPorukunaNaTeleekran(String poruka) {
        teleekran.posaljiPoruku("Nadzornik: " + poruka);
    }
}