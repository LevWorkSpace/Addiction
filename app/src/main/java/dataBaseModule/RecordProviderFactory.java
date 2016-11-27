package dataBaseModule;

import android.content.Context;

import global.interfaces.IRecordProvider;

public class RecordProviderFactory {

	public static IRecordProvider getProvider(Context context) {
		return RecordsProviderSQL.getInstance(context);
	}
}
