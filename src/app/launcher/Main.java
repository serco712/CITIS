package app.launcher;

import java.util.Scanner;

import app.data.DataFile;
import app.data.Factory;
import app.data.MainFactory;
import app.model.CITISMap;
import app.model.Line;
import app.model.Station;
import app.model.Transport;

public class Main {
	
	private static final int MIN_OPTION = 0;
	
	private static final int MAX_OPTION = 6;
	
	private static final String WELCOME_MSG = "Bienvenido a CITIS";
	
	private static final String ENDING_MSG = "Gracias por utilizar la aplicacion";
	
	private static MainFactory ft;
	
	private static CITISMap cm;
	
	public static int menu() {
		int option = -1;
		do {
			StringBuilder str = new StringBuilder();
			str.append("Seleccione una de las siguientes opciones: " + '\n');
			str.append("1 - Consultar lineas disponibles" + '\n');
			str.append("2 - Consultar paradas disponibles" + '\n');
			str.append("3 - Consultar transportes disponibles" + '\n');
			str.append("4 - Anadir nuevo CITISObject" + '\n');
			str.append("0 - Salir" + '\n');
			str.append("Seleccione una opcion: ");
			System.out.print(str.toString());
			Scanner sc = new Scanner(System.in);
			System.out.println("");
			option = Integer.parseInt(sc.nextLine());
		}
		while (option < MIN_OPTION && option > MAX_OPTION);
		return option;
	}
	
	public static void start() {
		try {
			ft = new MainFactory();
			cm = new CITISMap();
			DataFile df = new DataFile(ft, cm);
			df.loadData();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void showLines() {
		System.out.println("-----------------------------------");
		System.out.println("LISTADO DE LINEAS");
		System.out.println("-----------------------------------");
		for(Line l : cm.getLines())
			System.out.println(l.toString());
		System.out.println("-----------------------------------");
		System.out.println("");
	}
	
	
	public static void showStations() {
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
		String[] str = sc.nextLine().trim().split(" ");
		Factory f = ft.searchFactory(str[0]);
		if(f != null)
			f.createObject(str, cm);
	}
	
	public static void main(String[] args) {
		int option;
		System.out.println(WELCOME_MSG);
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
		System.out.println(ENDING_MSG);
	}
}