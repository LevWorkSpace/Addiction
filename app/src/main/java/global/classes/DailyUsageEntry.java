package global.classes;

import java.sql.Date;

import global.utilities.DateUtility;

public class DailyUsageEntry {
	public String Id;
	public Date CreatedDate;
	public Integer NumberOfUnlock;
	public Date TimeInUse;

	public DailyUsageEntry(Date created_date, Integer number_of_unlock, Date time_in_use) {
		CreatedDate = created_date;
		NumberOfUnlock = number_of_unlock;
		TimeInUse = time_in_use;
	}

	public DailyUsageEntry(String created_date, String number_of_unlock, String time_in_use) {
		CreatedDate = Date.valueOf(created_date);
		NumberOfUnlock = Integer.valueOf(number_of_unlock);
		TimeInUse = Date.valueOf(time_in_use);
	}

	public DailyUsageEntry(String created_date, String number_of_unlock, String time_in_use, String id) {
		CreatedDate = Date.valueOf(created_date);
		NumberOfUnlock = Integer.valueOf(number_of_unlock);
		TimeInUse = Date.valueOf(time_in_use);
		Id = id;
	}
	
	public String getTimeInUse() {
		return String.valueOf(TimeInUse);
	}
	
	public String getCreatedDate() {
		return String.valueOf(CreatedDate);
	}
	
	public Integer getNumberOfUnlock() {
		return NumberOfUnlock;
	}

	public String toString() {
		return "CreatedDate: " + CreatedDate + ". TimeInUse: " + TimeInUse + ". NumberOfUnlock: " + NumberOfUnlock;
	}

	public static DailyUsageEntry getBlankRecord() {
		return new DailyUsageEntry(DateUtility.getTodayDate(), "0", DateUtility.getTodayDate());
	}
}
