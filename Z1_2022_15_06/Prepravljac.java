public class Prepravljac extends Stanovnik {
	
	public BlockingQueue<Poruka> buffer;
	public Teleekran teleekran;
	public boolean aktivni = true;
	
	private Map<String,String> zamjene = new HashMap<>();
	
	public Prepravljac(String ime, String prezime, String jmb, BlockingQueue<Poruka> buffer, Teleekran teleekran){
		super(ime,prezime,jmb);
		this.buffer = buffer;
		this.teleekran = teleekran;
		
		//Tabela zamjene
		zamjene.put("ljubav", "mržnja");
        zamjene.put("sloboda", "nadzor");
        zamjene.put("neznanje", "moć");
        zamjene.put("nešto", "neštodrugo");
        zamjene.put("mir", "rat");
        zamjene.put("istina", "laž");
        zamjene.put("sreća", "tuga");
	}

	@Override
	public void run(){
		while(aktivni){
			try{
				//Uzimamo sve poruke iz buffera
				List<Poruka> poruke = new ArrayList<>();
				buffer.drainTo(poruke);
				
				for(Poruka poruka : poruke){
					String originalniTekst = poruka.tekst;
					String prepravljenTekst = prepravi(originalniTekst);
					int brojIzmjena = brojIzmjena(originalniTekst,prepravljenTekst);
					
					poruka.tekst = prepravljenTekst;
					buffer.offer(poruka);
					if(brojIzmjena > 0) {
						teleekran.posaljiPoruku("Prepravljač " + jmb + 
                            " izvršio " + brojIzmjena + " izmjena u poruci");
					}
				}
			}
		}
	}
	
	public int brojIzmjena(String original, String prepravljeno) {
        String[] originalne = original.split("\\s+");
        String[] prepravljene = prepravljeno.split("\\s+");
        
        int izmjene = 0;
        int minDuzina = Math.min(originalne.length, prepravljene.length);
        
        for (int i = 0; i < minDuzina; i++) {
            if (!originalne[i].equals(prepravljene[i])) {
                izmjene++;
            }
        }
        return izmene;
    }
	
	public String prepravi(String poruka){
		String rezultat = poruka;
		for(Map.Entry<String,String> entry > zamjene.entrySet()){
			rezultat = rezultat.replaceAll("\\b" + entry.getKey() + "\\b", entry.getValue());
		}
		return rezultat;
	}
	
}