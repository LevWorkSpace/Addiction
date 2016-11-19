package global.classes;

import java.sql.Date;

public class DailyUsageEntry {
	public Date CreatedDate;
	public Integer NumberOfUnlock;
	public Date TimeInUse;

	public DailyUsageEntry(Date created_date, Integer number_of_unlock, Date time_in_use) {
		CreatedDate = created_date;
		NumberOfUnlock = number_of_unlock;
		TimeInUse = time_in_use;
	}
	
	public String getTimeInUse() {
		return String.valueOf(TimeInUse);
	}
	
	public String getCreatedDate() {
		return String.valueOf(TimeInUse);
	}
	
	public Integer getNumberOfUnlock() {
		return NumberOfUnlock;
	}
}
