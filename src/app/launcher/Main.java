package app.launcher;

import java.util.Scanner;

import app.data.DataFile;
import app.data.MainFactory;
import app.model.CITISMap;

public class Main {
	
	private static final int MIN_OPTION = 0;
	
	private static final int MAX_OPTION = 4;
	
	private static final String WELCOME_MSG = "Bienvenido a CITIS";
	
	private static final String ENDING_MSG = "Gracias por utilizar la aplicacion";
	
	private static MainFactory ft;
	
	private static CITISMap cm;
	
	public static int menu() {
		int option = -1;
		Scanner sc = new Scanner(System.in);
		do {
			StringBuilder str = new StringBuilder();
			str.append("Seleccione una de las siguientes opciones: " + '\n');
			str.append("1 - Consultar lineas disponibles" + '\n');
			str.append("2 - Consultar paradas disponibles" + '\n');
			str.append("3 - Anadir nueva linea" + '\n');
			str.append("4 - Anadir nueva parada" + '\n');
			str.append("0 - Salir" + '\n');
			str.append("Seleccione una opcion: ");
			System.out.println(str.toString());
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
	
	public static void main(String[] args) {
		int option;
		System.out.println(WELCOME_MSG);
		start();
		option = menu();
		while (option != 0) {
			switch(option) {
			case 1:
				// TODO show available lines
				break;
			case 2:
				// TODO show available stops.
				break;
			case 3:
				// TODO add new line.
				break;
			case 4:
				// TODO add new stop.
			}
		}
		System.out.println(ENDING_MSG);
	}

}
