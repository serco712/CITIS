package app.view;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import app.misc.TimeADT;
import app.misc.Triplet;
import app.model.business.line.ASLine;

public class ScheduleTable extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	
	private List<Triplet<ASLine, TimeADT, String>> _schedule;
	private String[] cols = {"Linea", "Tipo de transporte", "Hora salida", "Notas"};

	public ScheduleTable(List<Triplet<ASLine, TimeADT, String>> schedule, int option) {
		_schedule = schedule;
		
		switch(option) {
		case AdminOperations.Consult:
			break;
		case AdminOperations.Delete:
			
			break;
		case AdminOperations.Modify:
			
			break;
		}
	}
	
	@Override
	public int getRowCount() {
		return _schedule.size();
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
		
		if (x < 0 || x >= _schedule.size())
			throw new IllegalArgumentException("The row is not found");
		
		switch (y) {
		case 0:
			return _schedule.get(x).getFirst().getShortName();
		case 1:
			return _schedule.get(x).getFirst().getTransport().toString();
		case 2:
			return _schedule.get(x).getSecond().toString();
		case 3:
			return _schedule.get(x).getThird().toString();
		default:
			return null;
		}
	}
}
