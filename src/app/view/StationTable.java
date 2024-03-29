package app.view;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import app.control.Controller;
import app.model.business.line.ASLine;
import app.model.business.station.ASStation;

public class StationTable extends JTable {

	private static final long serialVersionUID = 1L;
	
	private Controller _ctrl;
	
	public StationTable(List<ASStation> stations, Controller ctrl, String id, boolean config) {
		super(new StationTableModel(stations, id));
		_ctrl = ctrl;
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2) {
					new StationWindow(stations.get(StationTable.this.getSelectedRow()), _ctrl, config);
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			
		});
	}
}

class StationTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;
	private List<ASStation> stations;
	private String[] cols = {"ID", "Nombre", "Tipo de transporte", "Linea"};

	public StationTableModel(List<ASStation> stations, String id) {
		this.stations = stations;
		if (id != null) {
			setFilter(stations, id);
		}
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
			StringBuilder str = new StringBuilder();
			str.append("[");
			for (ASLine l : stations.get(x).getLines())
				str.append(l.getId()).append(",");
			if(str.length() >= 2)
				str.setLength(str.length() - 1);
			str.append("]");
			return str.toString();
		default:
			return null;
		}
	}
	
	public void setFilter(List<ASStation> stations, String id) {
		List<ASStation> stAux = new ArrayList<ASStation>();
		for (ASStation as: stations) {
			if (!as.getId().contains(id)) {
				stAux.add(as);
			}
		}
		
		if (!stAux.equals(stations)) stations.removeAll(stAux);
		fireTableDataChanged();
	}
}
