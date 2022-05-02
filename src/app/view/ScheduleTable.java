package app.view;

import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import app.control.Controller;
import app.control.ControllerChoices;
import app.misc.Pair;
import app.misc.TimeADT;
import app.model.business.line.ASLine;

public class ScheduleTable extends AbstractTableModel {
	private static final long serialVersionUID = 1L;	
	private List<Pair<Pair<ASLine, TimeADT>, Pair<String, String>>> _schedule;
	private String[] cols = {"Linea", "Tipo de transporte", "Hora salida", "Destino", "Calendario"};

	public ScheduleTable(List<Pair<Pair<ASLine, TimeADT>, Pair<String, String>>> schedule) {
		_schedule = schedule;
	}
	
	public void operations(Controller ctrl, int option, int row) {
		switch(option) {
		case ControllerChoices.Admin_Consult:
			break;
		case ControllerChoices.Admin_Delete:
			Pair<Pair<ASLine, TimeADT>, Pair<String, String>> obj = _schedule.get(row);
			ctrl.adminOperation(2, obj.getFirst().getFirst(), obj.getFirst().getSecond(), obj.getSecond().getFirst(), obj.getSecond().getSecond());
			ImageIcon icon = new ImageIcon("resources/success.png");
			JOptionPane.showMessageDialog(null, "Se ha eliminado con exito",
					"Eliminar horario", JOptionPane.DEFAULT_OPTION, icon);
			break;
		case ControllerChoices.Admin_Modify:
			
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
			return _schedule.get(x).getFirst().getFirst().getShortName();
		case 1:
			return _schedule.get(x).getFirst().getFirst().getTransport().toString();
		case 2:
			return _schedule.get(x).getFirst().getSecond().toString();
		case 3:
			return _schedule.get(x).getSecond().getFirst().toString();
		case 4:
			return _schedule.get(x).getSecond().getSecond().toString();
		default:
			return null;
		}
	}
	
	@Override
	public boolean isCellEditable(int x, int y) {
		if (y == 1) return false;
		return true;
	}
	
	@Override
	public void setValueAt(Object obj, int x, int y) {
		if (y < 0 || y >= cols.length)
			throw new IllegalArgumentException("The column is not valid");
		
		if (x < 0 || x >= _schedule.size())
			throw new IllegalArgumentException("The row is not found");
		
		switch (y) {
		case 0:
			_schedule.get(x).getFirst().getFirst().setShortName(_schedule.get(x).getFirst().getFirst().getShortName(), (String) obj);
		case 1:
			
		case 2:
			//_schedule.get(x).getFirst().getSecond().toString();
		case 3:
			//return _schedule.get(x).getSecond().getFirst().toString();
		case 4:
			//return _schedule.get(x).getSecond().getSecond().toString();
		}
	}
}
