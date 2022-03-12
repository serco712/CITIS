package app.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import app.model.CITISObject;

public class DataFile {
	
	private static final String FILE_NAME = "data.txt";
	
	private Factory fact;
	
	public DataFile(Factory f) throws Exception {
		f = fact;
		loadData();
	}
	
	public void loadData() throws Exception {
		try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
			String str = br.readLine();
			String[] parameters;
			CITISObject o;
			while(str != null) {
				parameters = str.toLowerCase().trim().split(" ");
				Factory f = fact.searchFactory(parameters[0]);
				if(f != null) 
					o = f.createObject(parameters);
				str = br.readLine();
			}
			br.close();
		}
		catch (IOException ie) {
			throw new Exception(ie.getMessage());
		}
	}
}

