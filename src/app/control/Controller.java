 package app.control;

import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import app.misc.Pair;
import app.misc.TimeADT;
import app.misc.Triplet;
import app.model.business.agency.ASAgency;
import app.model.business.line.ASLine;
import app.model.business.line.DTOLine;
import app.model.business.station.ASStation;
import app.model.business.station.DTOStation;
import app.model.business.trip.ASSpecificTrip;
import app.model.business.trip.ASTrip;
import app.model.business.trip.DTOSpecificTrip;
import app.model.business.trip.DTOTrip;
import app.model.business.user.ASUser;
import app.model.business.user.DTOUser;
import app.model.layers.integration.station.StationDatabaseDAO;
import app.view.LineTable;
import app.view.StationTable;
import app.view.TableWindow;

public class Controller {
	
	public Controller() {
		
	}
	
	public void tableOption(int option) {
		List<ASStation> stationList;
		List<ASLine> lineList;
		switch (option) {
		case ControllerChoices.Table_Lines:
			ASLine al  = new ASLine();
			lineList = al.searchLines();
			new TableWindow(new LineTable(lineList), "Listado de Lineas");
			break;
		case ControllerChoices.Table_Stations:
			ASStation as = new ASStation();
			stationList = as.searchStations();
			new TableWindow(new StationTable(stationList, this, null, false), "Listado de Estaciones");
			break;
		}
	}
	
	public boolean checkData(int option, String[] data) {
		ASLine l = new ASLine();
		ASAgency as = new ASAgency();
		ASStation ast = new ASStation();
		ASSpecificTrip astr = new ASSpecificTrip();
		
		switch(option) {
		case ControllerChoices.Check_UserData:
			return ASUser.getInstance().checkUserDataExists(data[0], data[1]);
		case ControllerChoices.Check_UserExists:
			return ASUser.getInstance().checkUserExists(data[0]);
		case ControllerChoices.Check_CorrectId:
			return l.findLine(data[0]);
		case ControllerChoices.Check_AgencyExists:
			return as.findAgency(data[0]);
		case ControllerChoices.Check_StationExists:
			return ast.findStation(data[0]);
		case ControllerChoices.Check_Permission_MenuOperations:
			return ASUser.getInstance().modify_permissions();
		case ControllerChoices.Check_Specific_Trip_Exists:
			return astr.findSpecificTrip(data[0]);
		case ControllerChoices.Check_User_Guest:
			return ASUser.getInstance().getRol() == 2;
		}
		return true;
	}
	
	public void addData(int option, Object transfer) {
		switch(option) {
		case ControllerChoices.Add_User:
			ASUser.resetInstance();
			ASUser.getInstance((DTOUser) transfer).createUser((DTOUser) transfer);
			break;
		case ControllerChoices.Add_Station:
			ASStation as = new ASStation();
			as.createStation((DTOStation) transfer);
			break;
		case ControllerChoices.Add_Line:
			ASLine al = new ASLine();
			al.createLine((DTOLine) transfer);
			break;
		case ControllerChoices.Add_Transport:
			break;
		case ControllerChoices.Add_Stop_Time:
			ASTrip at = new ASTrip();
			at.createStopTime((DTOTrip) transfer);
			break;
		case ControllerChoices.Add_Trip:
			ASTrip a = new ASTrip();
			a.createTrip((DTOTrip) transfer);
			break;
		case ControllerChoices.Add_Specific_Trip:
			ASSpecificTrip ast = new ASSpecificTrip();
			ast.createSpecificTrip((DTOSpecificTrip) transfer);
			break;
		}
	}
	
	public void deleteData(int option) {
		switch(option) {
		case ControllerChoices.Delete_User:
			ASUser.resetInstance();
			break;	
		}
	}
	
	public Object findData(int opt, String key) {
		ASSpecificTrip as = new ASSpecificTrip();
		ASTrip at = new ASTrip();
		switch (opt) {
		case ControllerChoices.Find_User:
			return ASUser.getInstance().findUser(key);
		case ControllerChoices.Find_Calendar_Ids:
			return as.findCalendarIds();
		case ControllerChoices.Find_Last_Sequence_Id:
			return at.findLastSequenceId(key);
		case ControllerChoices.Find_Trips:
			return at.findListTrips();
		case ControllerChoices.Find_Next_Time:
			return StationDatabaseDAO.getInstance().searchTimes(key);
		}
		return null;
	}
	
	public void updateData(int option, Object transfer) {
		switch(option) {
		case ControllerChoices.Update_User:
			ASUser.getInstance().updateData((DTOUser) transfer);
		}
	}
	
	public void registerUser(DTOUser user) {
		ASUser.resetInstance();
		ASUser.getInstance(user);
	}
	
	public void adminOperation(int option, Object t1, Object t2, Object t3, Object t4, Object t5, Object t6) {
		switch(option) {
		case ControllerChoices.Delete_Trip:
			ASLine.removeDepartureTime((ASLine) t1, (TimeADT) t2, (String) t3, (String) t4, (String) t5, (String) t6);
			break;
		}			
	}
	
	public List<ASStation> listStations() {
		ASStation as = new ASStation();
		return as.searchStations();
	}
	
	public List<ASLine> listLines() {
		ASLine al = new ASLine();
		return al.searchLines();
	}
	
	public List<ASLine> searchStationLines(String id) {
		ASStation as = new ASStation();
		return as.searchLines(id);
	}
	
	public List<Triplet<Pair<ASLine, TimeADT>, Pair<String, String>, String>> getScheduleList(String stop_id) {
		ASLine al = new ASLine();
		return al.searchDepartureTimes(stop_id);
	}
}

