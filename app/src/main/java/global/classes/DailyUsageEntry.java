package global.classes;

import android.util.Log;

import java.text.ParseException;
import java.util.Date;

import global.utilities.DateUtility;

public class DailyUsageEntry {
	private String id;
	private String createdDate;
	private Integer numberOfUnlock;
	private Float timeInUse;
	private Date unlockedDate;

	public DailyUsageEntry(Date createdDate, Integer numberOfUnlock, Float timeInUse, Date unlockedDate) {
		this.createdDate = DateUtility.toString(createdDate);
		this.numberOfUnlock = numberOfUnlock;
		this.timeInUse = timeInUse;
		this.unlockedDate = unlockedDate;
	}

	public DailyUsageEntry(String createdDate, String numberOfUnlock, String timeInUse, String unlockedDate) {
		this.createdDate = createdDate;
		this.numberOfUnlock = Integer.valueOf(numberOfUnlock);
		this.timeInUse = Float.valueOf(timeInUse);
		this.unlockedDate = DateUtility.parseDateTime(unlockedDate);
	}

	public DailyUsageEntry(String createdDate, String numberOfUnlock, String timeInUse, String unlockedDate, String id) {
		this.id = id;
		this.createdDate = createdDate;
		this.numberOfUnlock = Integer.valueOf(numberOfUnlock);
		this.timeInUse = Float.valueOf(timeInUse);
		this.unlockedDate = DateUtility.parseDateTime(unlockedDate);
	}

	public String getTimeInUse() {
		return String.valueOf(timeInUse);
	}
	
	public String getCreatedDate() {return createdDate;}
	
	public Integer getNumberOfUnlock() {
		return numberOfUnlock;
	}

	public String getUnlockedDate() {
		return DateUtility.toStringTime(unlockedDate);
	}

	public String toString() {
		return "CreatedDate: " + createdDate + ". TimeInUse: " + timeInUse + ". NumberOfUnlock: " + numberOfUnlock + ". UnlockedDate: " + unlockedDate;
	}

	public void phoneUnlocked() {
		numberOfUnlock++;
		unlockedDate = new Date();
	}

	public void phoneLocked() {
		if(unlockedDate != null) {
			Date now = new Date();
			long timeInMilliSec = now.getTime() - unlockedDate.getTime();
			this.timeInUse += (float)timeInMilliSec / 1000 / 60;
		}
	}

	public static DailyUsageEntry getBlankRecord() {
		return new DailyUsageEntry(DateUtility.getTodayDate(), "0", "0.0", null);
	}

	public String getId() {
		return id;
	}
}
