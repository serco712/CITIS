package app.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import app.model.CITISMap;
import app.model.Line;
import app.model.Station;
import app.model.Transport;

public class DataFile {
	
	private static final String FILE_NAME = "data.txt";
	
	private MainFactory fact;
	
	private CITISMap ct;
	
	public DataFile(MainFactory f, CITISMap cm) throws Exception {
		if (f == null)
			throw new IllegalArgumentException("La factoría no puede ser nula");
		else if (cm == null)
			throw new IllegalArgumentException("El mapa no puede ser nulo");
		
		fact = f;
		ct = cm;
	}
	
	public void loadData() throws Exception {
		try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
			String str = br.readLine();
			String[] parameters;
			while(str != null) {
				parameters = str.trim().split(" ");
				Factory f = fact.searchFactory(parameters[0]);
				if(f != null) 
					f.createObject(parameters, ct);
				str = br.readLine();
			}
			br.close();
		}
		catch (IOException ie) {
			throw new Exception(ie.getMessage());
		}
	}
	
	public void saveData() throws Exception {
		try (BufferedWriter br = new BufferedWriter(new FileWriter(FILE_NAME))) {
			for(Line l : ct.getLines()) {
				StringBuilder str = new StringBuilder();
				str.append(l.getTypeId() + ' ' + l.getId() + ' ' + l.getTransport().toString());
				br.append(str.toString() + '\n');
			}
			
			for(Station s : ct.getStations()) {
				StringBuilder str = new StringBuilder();
				str.append(s.getTypeId() + ' ' + s.getId() + ' ' + s.getName() + ' ' + s.getX() + ' ' + s.getY() + ' ' + s.getNumLines());
				for(Line l : s.getLines())
					str.append(' ' + l.getId());
				br.append(str.toString() + '\n');
			}
			
			for(Transport t : ct.getTransports()) {
				StringBuilder str = new StringBuilder();
				str.append(t.getTypeId() + ' ' + t.getId() + ' ' + t.getTime() + ' ' + t.getNumLines());
				for(Line l : t.getLines())
					str.append(' ' + l.getId());
				br.append(str.toString() + '\n');
			}
			br.close();
		}
		catch (IOException e) {
			throw new Exception(e.getMessage());
		}
	}
}

