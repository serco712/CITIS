package app.view;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import app.model.Line;
import app.model.Station;

public class StationTable extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	
	private List<Station> stations;
	private String[] cols = {"Id", "Name", "Transport name", "Lines"};

	public StationTable(List<Station> stations) {
		this.stations = stations;
	}
	
	@Override
	public int getRowCount() {
		return stations.size();
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
		
		if (x < 0 || x >= stations.size())
			throw new IllegalArgumentException("The row is not found");
		
		switch (y) {
		case 0:
			return stations.get(x).getId();
		case 1:
			return stations.get(x).getName();
		case 2:
			return stations.get(x).getTransport().toString();
		case 3:
			StringBuilder str = new StringBuilder("[");
			for (Line l : stations.get(x).getLines())
				str.append(l.getId()).append(",");
			str.setLength(str.length() - 1);
			str.append("]");
			return str.toString();
		default:
			return null;
		}
	}

}
