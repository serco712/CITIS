package app.launcher;

import javax.swing.SwingUtilities;

import app.control.Controller;
import app.factories.MainFactory;
import app.model.business.CITISMap;
import app.view.InitWindow;

public class Main {
	
	private static MainFactory ft;
	
	private static CITISMap cm;
	
	private static Controller df;
	
	public static void start() {
		try {
			ft = new MainFactory();
			cm = new CITISMap();
			df = new Controller(ft, cm);
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					new InitWindow(cm, df);
				}
				
			});
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		start();
	}
}