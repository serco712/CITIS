package app.view;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import app.model.Line;

public class LineTable extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	
	private List<Line> lines;
	private String[] cols = {"ID", "Tipo de transporte"};
	
	public LineTable(List<Line> lines) {
		this.lines = lines;
		
	}
	
	@Override
	public int getRowCount() {
		return lines.size();
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
	
		if (x < 0 || x >= lines.size())
			throw new IllegalArgumentException("The row is not found");
		
		switch (y) {
		case 0:
			return lines.get(x).getId();
		case 1:
			return lines.get(x).getTransport().toString();
		default:
			return null;
		}
	}

}
