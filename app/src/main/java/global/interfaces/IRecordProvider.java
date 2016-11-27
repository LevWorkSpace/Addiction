package global.interfaces;

import java.util.ArrayList;
import java.util.List;

import global.classes.DailyUsageEntry;

public interface IRecordProvider {
	DailyUsageEntry getTodayDailyUsageRecord();
	ArrayList<DailyUsageEntry> getDailyUsageRecordsForThisMonth();
	ArrayList<DailyUsageEntry> getAllDailyUsageRecords();
	void saveDailyUsageRecords(DailyUsageEntry dailyUsageEntry);
	void saveDailyUsageRecords(List<DailyUsageEntry> dailyUsageEntries);
	void clearDailyUsageTable();
}
