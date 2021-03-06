package dataBaseModule;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import dataBaseModule.RecordContract.RecordEntry;

public class SQLDataBaseHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "DeviceUsage.db";
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
        "CREATE TABLE " + RecordEntry.TABLE_NAME + " (" +
		RecordEntry._ID + " INTEGER PRIMARY KEY," +
		RecordEntry.COLUMN_NAME_RECORD_ID + TEXT_TYPE + COMMA_SEP +
        RecordEntry.COLUMN_NAME_CRATED_DATE + TEXT_TYPE + COMMA_SEP +
        RecordEntry.COLUMN_NAME_TIME_IN_USE + TEXT_TYPE + COMMA_SEP +
        RecordEntry.COLUMN_NAME_NUMBER_OF_UNLOCK + TEXT_TYPE + COMMA_SEP +
        RecordEntry.COLUMN_NAME_UNLOCKED_DATE + TEXT_TYPE +
        " )";

    private static final String SQL_DELETE_ENTRIES =
        "DROP TABLE IF EXISTS " + RecordEntry.TABLE_NAME;

    public SQLDataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}