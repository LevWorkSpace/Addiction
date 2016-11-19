package global.interfaces;

import java.util.ArrayList;

import global.classes.DailyUsageEntry;

public interface IRecordProvider {
	DailyUsageEntry getTodayRecord();
	ArrayList<DailyUsageEntry> getRecordsForThisMonth();
}
