package app.view;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import app.model.business.line.ASLine;

public class StationLinesTable extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;
	
	private List<ASLine> _lines;
	private String[] cols = {"Linea", "Tipo Transporte"};
	
	public StationLinesTable(List<ASLine> lines) {
		_lines = lines;
	}
	
	@Override
	public int getColumnCount() {
		return cols.length;
	}

	@Override
	public int getRowCount() {
		return _lines.size();
	}

	@Override
	public Object getValueAt(int x, int y) {
		if (y < 0 || y >= cols.length)
			throw new IllegalArgumentException("The column is not valid");
		
		if (x < 0 || x >= _lines.size())
			throw new IllegalArgumentException("The row is not found");
		
		switch(y) {
		case 0:
			return _lines.get(x).getShortName();
		case 1:
			return _lines.get(x).getTransport().toString();
		}
		return null;
	}

	public String getColumnName(int column) {
		if (column >= cols.length || column < 0)
			throw new IllegalArgumentException("The column is not found");
		
		return cols[column];
	}
}
