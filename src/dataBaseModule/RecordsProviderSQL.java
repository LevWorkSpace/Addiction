package dataBaseModule;

import java.util.ArrayList;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import dataBaseModule.RecordContract.RecordEntry;
import global.classes.DailyUsageEntry;
import global.interfaces.IRecordProvider;

public class RecordsProviderSQL implements IRecordProvider {
	private static final String WHERE_CLAUSE = "";
	private static final String[] DAILY_CLAUSE_ARGUMENTS = {"rt", "rt"};
	private static final String[] MONTHLY_CLAUSE_ARGUMENTS = {"rt", "rt"};

	private SQLDataBaseHelper SQLDbHelper;
	
	private static RecordsProviderSQL Self;
	
	public static RecordsProviderSQL getInstance() {
		if (Self == null) {
			Self = new RecordsProviderSQL();
		}
		return Self;
	}

	private RecordsProviderSQL() {
		SQLDbHelper = new SQLDataBaseHelper(getContext())
	}

	@Override
	public DailyUsageEntry getTodayRecord() {
		return queryRecords(WHERE_CLAUSE, DAILY_CLAUSE_ARGUMENTS).get(0);
	}

	@Override
	public ArrayList<DailyUsageEntry> getRecordsForThisMonth() {
		return queryRecords(WHERE_CLAUSE, MONTHLY_CLAUSE_ARGUMENTS);
	}
	
	private ArrayList<DailyUsageEntry> queryRecords(String where_clause, String[] clause_arguments) {
		SQLiteDatabase db = SQLDbHelper.getReadableDatabase();

		// Define a projection that specifies which columns from the database
		// you will actually use after this query.
		String[] projection = {
			RecordEntry._ID,
			RecordEntry.COLUMN_NAME_CRATED_DATE,
			RecordEntry.COLUMN_NAME_TIME_IN_USE,
			RecordEntry.COLUMN_NAME_NUMBER_OF_UNLOCK,
		    };

		// How you want the results sorted in the resulting Cursor
		String sortOrder =
				RecordEntry.COLUMN_NAME_CRATED_DATE + " DESC";

		Cursor c = db.query(
				RecordEntry.TABLE_NAME,  // The table to query
		    projection,                               // The columns to return
		    where_clause,                                // The columns for the WHERE clause
		    clause_arguments,                            // The values for the WHERE clause
		    null,                                     // don't group the rows
		    null,                                     // don't filter by row groups
		    sortOrder                                 // The sort order
		    );
		return null;
	}
}
