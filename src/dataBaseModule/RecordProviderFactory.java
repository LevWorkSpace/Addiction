package dataBaseModule;

import global.interfaces.IRecordProvider;

public class RecordProviderFactory {

	public static IRecordProvider getProvider() {
		return RecordsProviderSQL.getInstance();
	}
}
