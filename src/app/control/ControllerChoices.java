package app.control;

public class ControllerChoices {
	// List Commands
	public static final int Table_Lines = 1;
	public static final int Table_Stations = 2;
	public static final int Table_Transports = 3;
	public static final int Table_Agencies = 4;
	public static final int Table_Times = 5;
	
	// Check Data Commands
	public static final int Check_UserData = 1;
	public static final int Check_UserExists = 2;
	public static final int Check_CorrectId = 3;
	public static final int Check_AgencyExists = 4;
	public static final int Check_StationExists = 5;
	public static final int Check_TripExists = 6;
	public static final int Check_ScheduleCoincidences = 7;
	public static final int Check_Permission_MenuOperations = 8;
	public static final int Check_Specific_Trip_Exists = 9;
	public static final int Check_User_Guest = 10;
	
	// Add Commands
	public static final int Add_User = 1;
	public static final int Add_Station = 2;
	public static final int Add_Line = 3;
	public static final int Add_Transport = 4;
	public static final int Add_Stop_Time = 5;
	public static final int Add_Trip = 6;
	public static final int Add_Specific_Trip = 7;
	
	// Delete Commands
	public static final int Delete_User = 1;
	public static final int Delete_Trip = 2;
	
	// Find Commands
	public static final int Find_User = 1;
	public static final int Find_Calendar_Ids = 2;
	public static final int Find_Last_Sequence_Id = 3;
	public static final int Find_Trips = 4;
	public static final int Find_Next_Time = 5;
	
	// Update Commands
	public static final int Update_User = 1;
	
	// Administrator Commands
	public static final int Admin_Consult = 1;
	public static final int Admin_Delete = 2;
	public static final int Admin_Modify = 3;
}
