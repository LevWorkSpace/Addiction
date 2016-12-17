package dataBaseModule;

import android.provider.BaseColumns;

public final class RecordContract {
    // To prevent someone from accidentally instantiating the contract class
    private RecordContract() {}

    /* Inner class that defines the table contents */
    public static abstract class RecordEntry implements BaseColumns {
        public static final String TABLE_NAME = "Device_Usage";
        public static final String COLUMN_NAME_RECORD_ID = "record_id";
        public static final String COLUMN_NAME_CRATED_DATE = "created_date";
        public static final String COLUMN_NAME_TIME_IN_USE = "time_in_use";
        public static final String COLUMN_NAME_NUMBER_OF_UNLOCK = "number_of_unlock";
        public static final String COLUMN_NAME_UNLOCKED_DATE = "unlocked_date";
    }
}