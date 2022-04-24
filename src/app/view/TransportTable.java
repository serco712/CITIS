package app.view;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import app.model.business.transport.ASTransport;

public class TransportTable extends JTable {
	
	private static final long serialVersionUID = 1L;
	
	private List<ASTransport> transports;
	private String[] cols = {"ID", "Matricula", "Modelo", "Tipo de transporte", "Linea"};
	
	public TransportTable(List<ASTransport> t) {
		transports = t;
	}
	
	@Override
	public int getRowCount() {
		return transports.size();
	}

	@Override
	public int getColumnCount() {
		return cols.length;
	}
	
	public String getColumnName(int column) {
		if (column >= cols.length || column < 0)
			throw new IllegalArgumentException("The column is not found");
		
		return cols[column];
	}

	@Override
	public Object getValueAt(int x, int y) {
		if (y < 0 || y >= cols.length)
			throw new IllegalArgumentException("The column is not valid");
		
		if (x < 0 || x >= transports.size())
			throw new IllegalArgumentException("The row is not found");
		
		switch(y) {
		case 0:
			return transports.get(x).getId();
		case 1:
			return transports.get(x).getEnrollment();
		case 2:
			return transports.get(x).getModel();
		case 3:
			return transports.get(x).getType().toString();
		case 4:
			return transports.get(x).getLine();
		default:
			return null;
		}
	}
	
}
