package app.launcher;

import java.util.Scanner;

import app.control.Controller;
import app.factories.Factory;
import app.factories.MainFactory;
import app.model.CITISMap;
import app.model.Line;
import app.model.Station;
import app.model.Transport;
import app.view.InitWindow;

public class Main {
	
	private static final int MIN_OPTION = 0;
	
	private static final int MAX_OPTION = 4;
	
	private static final String WELCOME_MSG = "Bienvenido a CITIS";
	
	private static final String ENDING_MSG = "Gracias por utilizar la aplicacion";
	
	private static MainFactory ft;
	
	private static CITISMap cm;
	
	private static Controller df;
	
	public static int menu() {
		int option = -1;
		do {
			StringBuilder str = new StringBuilder();
			str.append("Seleccione una de las siguientes opciones: " + '\n');
			str.append("1 - Consultar líneas disponibles" + '\n');
			str.append("2 - Consultar paradas disponibles" + '\n');
			str.append("3 - Consultar transportes disponibles" + '\n');
			str.append("4 - Añadir nuevo CITISObject" + '\n');
			str.append("0 - Salir" + '\n');
			str.append("Seleccione una opción: ");
			System.out.print(str.toString());
			Scanner sc = new Scanner(System.in);
			System.out.println("");
			option = Integer.parseInt(sc.nextLine());
			sc.close();
		}
		while (option < MIN_OPTION && option > MAX_OPTION);
		return option;
	}
	
	public static void start() {
		try {
			System.out.println(WELCOME_MSG);
			ft = new MainFactory();
			cm = new CITISMap();
			df = new Controller(ft, cm);
			new InitWindow(cm, df);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void endExecution() {
		try {
			System.out.println(ENDING_MSG);
			df.saveData();
			System.exit(1);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void showLines() {
		//new TableWindow(new LineTable(cm.getLines()), "Lines");
		System.out.println("-----------------------------------");
		System.out.println("LISTADO DE LÍNEAS");
		System.out.println("-----------------------------------");
		for(Line l : cm.getLines())
			System.out.println(l.toString());
		System.out.println("-----------------------------------");
		System.out.println("");
	}
	
	
	public static void showStations() {
		//new TableWindow(new StationTable(cm.getStations()), "Stations");
		System.out.println("-----------------------------------");
		System.out.println("LISTADO DE ESTACIONES");
		System.out.println("-----------------------------------");
		for(Station s : cm.getStations())
			System.out.println(s.toString());
		System.out.println("-----------------------------------");
		System.out.println("");
	}
	
	public static void showTransports() {
		System.out.println("-----------------------------------");
		System.out.println("LISTADO DE TRANSPORTES");
		System.out.println("-----------------------------------");
		for(Transport t : cm.getTransports())
			System.out.println(t.toString());
		System.out.println("-----------------------------------");
		System.out.println("");
		
	}
	
	public static void createNewCITISObject() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Inserte los datos del nuevo objeto que desee crear: ");
		String[] str = sc.nextLine().trim().split(" ");
		Factory f = ft.searchFactory(str[0]);
		if(f != null) {
			f.createObject(str, cm);
			System.out.println("El objeto se ha creado correctamente");
		}
		sc.close();
			
	}
	
	public static void main(String[] args) {
		int option;
		start();
		option = menu();
		while (option != 0) {
			switch(option) {
			case 1:
				showLines();
				break;
			case 2:
				showStations();
				break;
			case 3:
				showTransports();
				break;
			case 4:
				createNewCITISObject();
				break;
			}
			option = menu();
		}
		endExecution();
	}
}