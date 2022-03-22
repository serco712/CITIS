package app.view;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import app.model.Transport;

public class TransportTable extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;
	
	private List<Transport> transports;
	private String[] cols = {};
	
	public TransportTable(List<Transport> t) {
		transports = t;
	}
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
