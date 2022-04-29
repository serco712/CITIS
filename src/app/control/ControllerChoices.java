package app.control;

public class ControllerChoices {
	// List Commands
	public static final int Table_Lines = 1;
	public static final int Table_Stations = 2;
	public static final int Table_Transports = 3;
	public static final int Table_Agencies = 4;
	
	// Check Data Commands
	public static final int Check_UserData = 1;
	public static final int Check_UserExists = 2;
	public static final int Check_CorrectId = 3;
	public static final int Check_AgencyExists = 4;
	public static final int Check_StationExists = 5;
	public static final int Check_TripExists = 6;
	public static final int Check_ScheduleCoincidences = 7;
	public static final int Check_Permission_MenuOperations = 8;
	
	// Add Commands
	public static final int Add_User = 1;
	public static final int Add_Station = 2;
	public static final int Add_Line = 3;
	public static final int Add_Transport = 4;
	public static final int Add_Schedule = 5;
	
	// Delete Commands
	public static final int Delete_User = 1;
	public static final int Delete_Trip = 2;
}
