package dataBaseModule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import dataBaseModule.RecordContract.RecordEntry;
import global.classes.DailyUsageEntry;
import global.interfaces.IRecordProvider;
import global.utilities.DateUtility;

public class RecordsProviderSQL implements IRecordProvider {
	private static final String DAILY_WHERE_CLAUSE = RecordEntry.COLUMN_NAME_CRATED_DATE + "=?";
	private static final String MONTHLY_WHERE_CLAUSE = RecordEntry.COLUMN_NAME_CRATED_DATE + ">=?";
	private static final String[] DAILY_CLAUSE_ARGUMENTS = {DateUtility.getTodayDate()};
	private static final String[] MONTHLY_CLAUSE_ARGUMENTS = {DateUtility.getStartMonthDate()};

	private final String LOG_TAG = "SQL_LOG";

	private SQLDataBaseHelper SQLDbHelper;
	
	private static RecordsProviderSQL Self;
	
	public static RecordsProviderSQL getInstance(Context context) {
		if (Self == null) {
			Self = new RecordsProviderSQL(context);
		}
		return Self;
	}

	private RecordsProviderSQL(Context context) {
		SQLDbHelper = new SQLDataBaseHelper(context);
		initDataBaseIfNotExist();
	}

	@Override
	public DailyUsageEntry getTodayDailyUsageRecord() {
		return getWrapDailyUsageRecordsWithClause(DAILY_WHERE_CLAUSE, DAILY_CLAUSE_ARGUMENTS).get(0);
	}

	@Override
	public ArrayList<DailyUsageEntry> getDailyUsageRecordsForThisMonth() {
		return getWrapDailyUsageRecordsWithClause(MONTHLY_WHERE_CLAUSE, MONTHLY_CLAUSE_ARGUMENTS);
	}

	@Override
	public ArrayList<DailyUsageEntry> getAllDailyUsageRecords() {
		return getWrapDailyUsageRecordsWithClause(null, null);
	}

	@Override
	public void saveDailyUsageRecords(DailyUsageEntry dailyUsageEntry) {
        SQLiteDatabase db = SQLDbHelper.getWritableDatabase();
        saveDailyUsageRecord(dailyUsageEntry, db);
        db.close();
	}

	@Override
	public void saveDailyUsageRecords(List<DailyUsageEntry> dailyUsageEntries) {
        SQLiteDatabase db = SQLDbHelper.getWritableDatabase();
        for (DailyUsageEntry singleRecord : dailyUsageEntries) {
            saveDailyUsageRecord(singleRecord, db);
        }
        db.close();
    }
	
	@Override
	public void clearDailyUsageTable() {
		SQLiteDatabase db = SQLDbHelper.getWritableDatabase();
		db.execSQL("delete from "+ RecordEntry.TABLE_NAME);
		db.close();
	}

	private ArrayList<DailyUsageEntry> getWrapDailyUsageRecordsWithClause(String whereClause, String[] clauseArguments) {
		SQLiteDatabase db = SQLDbHelper.getReadableDatabase();

		String[] projection = {
			RecordEntry._ID,
			RecordEntry.COLUMN_NAME_CRATED_DATE,
			RecordEntry.COLUMN_NAME_TIME_IN_USE,
			RecordEntry.COLUMN_NAME_NUMBER_OF_UNLOCK,
		    };
		
		String sortOrder =
				RecordEntry.COLUMN_NAME_CRATED_DATE + " DESC";

		Cursor cursor = db.query(
				RecordEntry.TABLE_NAME,  // The table to query
		    projection,                               // The columns to return
		    whereClause,                                // The columns for the WHERE clause
			clauseArguments,                            // The values for the WHERE clause
		    null,                                     // don't group the rows
		    null,                                     // don't filter by row groups
		    sortOrder                                 // The sort order
		    );

		ArrayList<DailyUsageEntry> wrapped_records = wrapRecordsFromCursor(cursor);
		db.close();
		return wrapped_records;
	}

	private ArrayList<DailyUsageEntry> wrapRecordsFromCursor(Cursor cursor) {
		ArrayList<DailyUsageEntry> records = new ArrayList<DailyUsageEntry>();
		if (cursor != null) {
			if (cursor.moveToFirst()) {
				do {
					records.add(new DailyUsageEntry(cursor.getString(cursor.getColumnIndex(RecordEntry.COLUMN_NAME_CRATED_DATE)),
													cursor.getString(cursor.getColumnIndex(RecordEntry.COLUMN_NAME_NUMBER_OF_UNLOCK)),
													cursor.getString(cursor.getColumnIndex(RecordEntry.COLUMN_NAME_TIME_IN_USE)),
                                                    cursor.getString(cursor.getColumnIndex(RecordEntry._ID))));
				} while (cursor.moveToNext());
			}
			cursor.close();
		} else {
			Log.d(LOG_TAG, "Cursor is null");
		}
		return records;
	}

    private void saveDailyUsageRecord(DailyUsageEntry dailyUsageEntry, SQLiteDatabase db) {
        ContentValues row = new ContentValues();
        row.put(RecordEntry.COLUMN_NAME_CRATED_DATE, dailyUsageEntry.getCreatedDate());
        row.put(RecordEntry.COLUMN_NAME_TIME_IN_USE, dailyUsageEntry.getTimeInUse());
        row.put(RecordEntry.COLUMN_NAME_NUMBER_OF_UNLOCK, dailyUsageEntry.getNumberOfUnlock());
        if(dailyUsageEntry.Id == null) {
            db.insert(RecordEntry.TABLE_NAME, null, row);
        } else {
            db.update(RecordEntry.TABLE_NAME, row, "_id=" + dailyUsageEntry.Id, null);
        }
    }

	private void initDataBaseIfNotExist() {
		//Clearing DB for test purpose
        //clearDailyUsageTable();

        SQLiteDatabase db = SQLDbHelper.getWritableDatabase();
		Cursor cursor = db.query(RecordEntry.TABLE_NAME, null, null, null, null, null, null);
		if (cursor.getCount() == 0) {
			saveDailyUsageRecord(DailyUsageEntry.getBlankRecord(), db);
		}
		cursor.close();
		db.close();
	}
}
