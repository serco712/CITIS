package app.view;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import app.misc.Pair;
import app.misc.TimeADT;
import app.model.business.line.ASLine;

public class TimeTable extends JTable {
	private static final long serialVersionUID = 1L;
	
	public TimeTable(List<Pair<TimeADT, String>> times) {
		super(new TimeTableModel(times));
	}
}

class TimeTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;
	
	private List<Pair<TimeADT, String>> _times;
	private String[] cols = {"Hora", "Notas"};
	
	public TimeTableModel(List<Pair<TimeADT, String>> times) {
		_times = times;
	}

	@Override
	public int getRowCount() {
		return _times.size();
	}

	@Override
	public int getColumnCount() {
		return cols.length;
	}

	@Override
	public Object getValueAt(int x, int y) {
		if (y < 0 || y >= cols.length)
			throw new IllegalArgumentException("The column is not valid");
		
		if (x < 0 || x >= _times.size())
			throw new IllegalArgumentException("The row is not found");
		
		switch(y) {
		case 0:
			return _times.get(x).getFirst();
		case 1:
			return _times.get(x).getSecond();
		}
		
		return null;
	}
	
	public String getColumnName(int column) {
		if (column >= cols.length || column < 0)
			throw new IllegalArgumentException("The column is not found");
		
		return cols[column];
	}

}
