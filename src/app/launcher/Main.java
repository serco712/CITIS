package app.launcher;

import javax.swing.SwingUtilities;


import app.control.Controller;
import app.view.InitWindow;

public class Main {
	
	private static Controller ctrl;
	
	public static void start() {
		try {
			ctrl = new Controller();
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					new InitWindow(ctrl);
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