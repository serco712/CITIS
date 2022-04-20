package app.control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import app.factories.Factory;
import app.factories.MainFactory;
import app.model.business.CITISMap;
import app.model.business.CITISObserver;
import app.model.business.line.ASLine;
import app.model.business.station.ASStation;
import app.model.business.transport.ASTransport;
import app.model.business.user.ASUser;
import app.model.layers.integration.line.LineDatabaseDAO;
import app.view.LineTable;
import app.view.TableWindow;

public class Controller {
	
	private static final String FILE_NAME = "data.txt";
	
	private MainFactory fact;
	
	private CITISMap ct;
	
	private ASLine al;
	
	public Controller(MainFactory f, CITISMap cm) throws Exception {
		if (f == null)
			throw new IllegalArgumentException("La factor�a no puede ser nula");
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
			/*
			for(ASLine l : ct.getLines()) {
				StringBuilder str = new StringBuilder();
				str.append(l.getTypeId() + ' ' + l.getId() + ' ' + l.getTransport().toString() + ' '
						+ l.getColor().getRed() + ' ' + l.getColor().getGreen() + ' ' + l.getColor().getBlue());
				br.append(str.toString() + '\n');
			}
			*/
			
			for(ASStation s : ct.getStations()) {
				StringBuilder str = new StringBuilder();
				str.append(s.getTypeId() + ' ' + s.getId() + ' ' + s.getName() + ' ' + s.getX() + ' ' + s.getY() + ' ' + s.getNumLines());
				for(ASLine l : s.getLines())
					str.append(' ' + l.getId());
				br.append(str.toString() + '\n');
			}
			
			for(ASTransport t : ct.getTransports()) {
				StringBuilder str = new StringBuilder();
				str.append(t.getTypeId() + ' ' + t.getId() + ' ' + t.getEnrollment() + ' ' + t.getTime());
				str.append(' ' + t.getModel() + ' ' + t.getTime() + ' ' + t.getType().toString());
				str.append(t.getLine());
				br.append(str.toString() + '\n');
			}
			
			for(ASUser u : ct.getUsers()) {
				br.append(u.getTypeId() + ' ' + u.getName() + ' ' + u.getSurname() + 
						' ' + u.getEmail() + ' ' + u.getPassword());
			}
			br.close();
		}
		catch (IOException e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public void executeOption(int option) {
		List<ASStation> stationList;
		List<ASLine> lineList;
		List<ASTransport> transportList;
		switch (option) {
		case ControllerChoices.List_Lines:
			ASLine al  = new ASLine();
			lineList = al.searchLines();
			new TableWindow(new LineTable(lineList), "Listado de Lineas");
			break;
		}
	}
	public void addObserver(CITISObserver co) {
		ct.addObserver(co);
	}
	
	public void removeObserver(CITISObserver co) {
		ct.removeObserver(co);
	}
}

