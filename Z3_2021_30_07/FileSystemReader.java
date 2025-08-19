import java.io.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;


public class FileSystemReader{
	
	public void ocitaj(QuoteStorage qs, String s){
		qs.quotes.clear();
		File file = new File(Main.PATH);
		File quotes[] = file.listFiles();
		for(File f : quotes){
			try{
				if(f.getName().contains(s)){
					List<String> content = Files.readAllLines(f.toPath());
					for(String f : content){
						QuoteStorage.quotes.add(content);
					}
				}
			} catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	
}