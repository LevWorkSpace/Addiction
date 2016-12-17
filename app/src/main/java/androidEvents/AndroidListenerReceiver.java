package androidEvents;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Date;

import dataBaseModule.RecordProviderFactory;
import global.classes.DailyUsageEntry;
import global.interfaces.IRecordProvider;

/**
 * Created by Дум on 17.12.2016.
 */
public class AndroidListenerReceiver extends BroadcastReceiver {
    private IRecordProvider recordProvider;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_USER_PRESENT)) {
            Log.i("[BroadcastReceiver]", "Unlocked");
            phoneUnlocked(context);
        } else if(intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            Log.i("[BroadcastReceiver]", "Locked");
            phoneLocked(context);
        }  else if(intent.getAction().equals(Intent.ACTION_SHUTDOWN)) {
            Log.i("[BroadcastReceiver]", "Locked");
            phoneLocked(context);
        }
    }

    private void phoneUnlocked(Context context) {
        recordProvider = RecordProviderFactory.getProvider(context);
        DailyUsageEntry today = recordProvider.getTodayDailyUsageRecord();
        Log.d("Test", today.toString());
        today.phoneUnlocked();
        Log.d("Test", today.toString());
        recordProvider.saveDailyUsageRecords(today);
    }

    private void phoneLocked(Context context) {
        recordProvider = RecordProviderFactory.getProvider(context);
        DailyUsageEntry today = recordProvider.getTodayDailyUsageRecord();
        Log.d("Test", today.toString());
        today.phoneLocked();
        Log.d("Test", today.toString());
        recordProvider.saveDailyUsageRecords(today);
    }
}
