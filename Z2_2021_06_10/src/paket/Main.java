package paket;
import java.util.*;
import java.io.*;
import java.util.stream.*;
import java.nio.*;
import java.nio.file.*;
import java.util.Map.*;

public class Main{
	public static void main(String[] args){
		Search search = new Search();
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("as", "pogledaj");
		map.put("jedan", "dva");
		
		try{
			search.findPatternsInFiles(Arrays.asList("sta ima", "lijep da", "ostalo je"), args[0], args[1]);
			search.countWords(args[0]);
			search.changeWords(map, args[0]);
		}catch (Exception e){
		}
	}
}

class Search{
	public List<File> findPatternsInFiles(List<String> patterns, String ... arrPath) throws Exception{
		List<File> arrFile = new ArrayList<File>();
		
		for (String i : arrPath){
			List<String> lines = Files.readAllLines(Paths.get(i));
			
			String komp = "";
			for (String j : lines){
				komp += j + " ";
			}
		
			//zbog white space na kraju
			komp = komp.substring(0, komp.length() - 1);
			
			//razdvajam recenice
			String []sp = komp.split("\\.");
			
			List<String> found = new ArrayList<String>();
			for (String j : sp){
				String a1 = j.toUpperCase();
				for (String k : patterns){
					String a2 = k.toUpperCase();
					if (a1.indexOf(a2) != -1){
						found.add(j);
					}
				}
			}
			
			File f = new File(i);
			PrintWriter pw = new PrintWriter(System.getProperty("user.dir") + File.separator + "search" + File.separator + f.getName() + ".search.txt");
			
			for (String j : found){
				pw.println(j);
			}
			
			pw.close();
			
			File ff = new File(System.getProperty("user.dir") + File.separator + "search" + File.separator + f.getName() + ".search.txt");
			arrFile.add(ff);
		}
		
		return arrFile;
	}
	
	public void countWords(String path) throws Exception{
		List<String> arr = Files.readAllLines(Paths.get(path));
		
		//System.out.println(arr.size());
		List<String> words = new ArrayList<String>();
		
		for (String i : arr){
			String[]sp = i.split(" ");
			
			for (String j : sp)
				words.add(j);
		}
		
		words.stream().distinct().forEach(
			t-> {
				System.out.print(t + " - ");
				System.out.println(words.stream().filter(k -> k.equals(t)).count());
			}
		);
	}
	
	public void changeWords(Map<String, String> map, String path) throws Exception{
		List<String> arr = Files.readAllLines(Paths.get(path));
		
		List<String> res = new ArrayList<String>();
		for (String i : arr){
			String []sp = i.split(" ");
			
			String ress = "";
			for (String j : sp){
				boolean flag = false;
				for (Entry <String, String> entry : map.entrySet()){
					if (j.equals(entry.getKey())){
						ress += entry.getValue() + " ";
						flag = true;
					}
				}
				
				if (!flag){
					ress += j + " ";
				}
			}
			
			if (ress.endsWith(" "))
				ress = ress.substring(0, ress.length() - 1);
				
			res.add(ress);
		}
		
		File f = new File(path);
		f.delete();
		
		PrintWriter pw = new PrintWriter(path);
		
		for (String i : res){
			pw.println(i);
		}
		
		pw.close();
	}
}