

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;

public class Parser{
	
	private static ArrayList<Rilevation> reclist;
	private static ArrayList<Rilevation> sentlist;
	
	public static ArrayList<Rilevation> populateList(String filename) throws NumberFormatException, IOException{
		ArrayList<Rilevation> list = new ArrayList<Rilevation>();
		BufferedReader br = new BufferedReader(new FileReader(filename));
		if(br != null){
			StringBuilder sb = new StringBuilder();
		    String line = br.readLine();
			while (line != null){
				String delims = "---";
				String[] tokens = line.split(delims);
				Rilevation rilev = new Rilevation(Integer.parseInt(tokens[0]), Long.parseLong(tokens[1]));
				list.add(rilev);
				line = br.readLine();
			}
			br.close();
		}
		return list;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException{
		int sum = 0;
		reclist = populateList("rlog.txt");
		sentlist = populateList("slog.txt");
		for(Rilevation rec_elem : reclist){
			for(Rilevation sent_elem : sentlist){
				if(rec_elem.getPayload() == sent_elem.getPayload()){
					sum = sum + (int)(rec_elem.getTime() - sent_elem.getTime());
				}
			}
		}
		int minlen = reclist.size();
		System.out.println("Medium delta: " + (float)(sum/minlen));
		System.out.println("Mismatching packets: " + (sentlist.size()-reclist.size()));
	}

}