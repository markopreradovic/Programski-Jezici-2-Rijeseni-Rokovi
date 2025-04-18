package pack;
import java.nio.file.*;
import java.nio.*;
import java.util.*;
import java.util.stream.*;
import java.io.*;
import java.util.function.*;

public class Main {

	public static void main(String[] args) {
		
		List<Movie> arr = new ArrayList<Movie>();
		
		try {
			List<String> lines = Files.readAllLines(Paths.get(System.getProperty("user.dir") + File.separator + "movies.txt"));
			
			for(String i : lines) {
				if (i.startsWith("movie_id"))
					continue;
				
				String modi = i.substring(0, i.length() - 7);
				arr.add(new Movie(modi));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		//a - lista filmova grupisanih po godini objavljivanja
		Map<String, List<Movie>> map1 = arr.stream().collect(Collectors.groupingBy(Movie::getGodina));
		
		//b - lista filmova cija je ocjena veca ili jednaka zadatom broju
		//lambda izraz za provjeru broja tj ocjene
		Predicate <Double> pre = (n) -> {
			return n>=7.5;
		};
		List<Movie> arr1 = arr.stream().filter(t -> pre.test(t.voteAverage)).collect(Collectors.toList());
		
		//c - lista filmova ciji je budzet veci od 10 miliona
		List<Movie> arr2 = arr.stream().filter(t -> t.budget >= 10_000_000).collect(Collectors.toList());
		
		//d - lista filmova koji su objavljeni u prvoj deceniji 2000-tih
		List<Movie> arr3 = arr.stream().filter(t -> t.firstDecade(t.date.split("-")[0])).collect(Collectors.toList());
		
		//e - lista filmova koji su grupisani po ocjeni u formatu npr 6.0 - 6.99, 7.0 - 7.99 itd
		Map<String, List<Movie>> map2 = arr.stream().collect(Collectors.groupingBy(Movie::getGodinaPair));
		
		//f - izracunati prosjecnu ocjenu filmova snimljenih 90tih godina
		System.out.println(arr.stream().filter(t -> t.nine90s()).mapToDouble(t -> t.voteAverage).average().getAsDouble());
		
		//g - izracunati ukupni budzet filmova snimljenih 80tih godina
		OptionalDouble aa = arr.stream().filter(t -> t.eight80s()).mapToLong(t -> t.budget).average();
		System.out.println(aa.getAsDouble());

	}

}

class Movie{
	public int id;
	public String title;
	public Long budget;
	public String homePage;
	public String overview;
	public double popularity;
	public String date;
	public Long revenue;
	public int runtime;
	public String status;
	public String tagline;
	public double voteAverage;
	public int voteCount;
	
	public Movie(String info) throws Exception {
		String []sp = info.split("###");
		
		if(sp.length < 13) {
			id = Integer.parseInt(sp[0]);
			title = sp[1];
			budget = Long.parseLong(sp[2]);
			homePage = sp[3];
			overview = sp[4];
			popularity = Double.parseDouble(sp[5]);
			date = sp[6];
			revenue = Long.parseLong(sp[7]);
			runtime = Integer.parseInt(sp[8]);
			status = sp[9];
			tagline = sp[10];
			voteAverage = Double.parseDouble(sp[11]);
			voteCount = 0;
		} else {
			id = Integer.parseInt(sp[0]);
			title = sp[1];
			budget = Long.parseLong(sp[2]);
			homePage = sp[3];
			overview = sp[4];
			popularity = Double.parseDouble(sp[5]);
			date = sp[6];
			revenue = Long.parseLong(sp[7]);
			runtime = Integer.parseInt(sp[8]);
			status = sp[9];
			tagline = sp[10];
			voteAverage = Double.parseDouble(sp[11]);
			voteCount = Integer.parseInt(sp[12]);
		}
	}
	
	public String getGodina() {
		return date.split("-")[0];
	}
	
	public boolean firstDecade(String value) {
		try {
			int val = Integer.parseInt(value);
			if (val >=2000 && val <=2009) {
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean nine90s(){
		try{
			int val = Integer.parseInt(date.split("-")[0]);
			
			if (val >= 1990 && val <= 1999)
				return true;
			
			return false;
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean eight80s(){
		try{
			int val = Integer.parseInt(date.split("-")[0]);
			
			if (val >= 1980 && val <= 1989)
				return true;
			
			return false;
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return false;
	}
	
	public String getGodinaPair(){
		if (voteAverage >= 6.0 && voteAverage <= 6.99)
			return "6.0-6.99";
		else if (voteAverage >= 7.0 && voteAverage <= 7.99)
			return "7.0-7.99";
		else if (voteAverage >= 8.0 && voteAverage <= 8.99)
			return "8.0-8.99";
		else if (voteAverage >= 9.0 && voteAverage <= 10.0)
			return "9.0-10.0";
		
		return "";
	}
}
