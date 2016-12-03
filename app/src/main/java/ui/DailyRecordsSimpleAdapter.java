package ui;

import android.content.Context;
import android.widget.SimpleAdapter;

import com.example.test42.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import global.classes.DailyUsageEntry;


public class DailyRecordsSimpleAdapter extends SimpleAdapter {

    private final static String CREATED_DATE_TEXT="date";
    private final static String TIME_IN_USE_TEXT="time";
    private final static String NUMBER_OF_UNLOCKS_TEXT="unlocks";

     public DailyRecordsSimpleAdapter(Context context, ArrayList<DailyUsageEntry> myrecords,int resource) {
        super(context, translate(myrecords), resource, from, to);
    }
    private static String[] from = { CREATED_DATE_TEXT, TIME_IN_USE_TEXT,NUMBER_OF_UNLOCKS_TEXT };
    private static int[] to = { R.id.tvDate, R.id.tvTime, R.id.tvUnlocks };

    private static ArrayList<Map<String, Object>> translate(ArrayList<DailyUsageEntry> myrecords){
        ArrayList<Map<String,Object>> mylist = new ArrayList<Map<String,Object>>();
        Map<String, Object> m;
        for (DailyUsageEntry myrec:myrecords) {
            m = new HashMap<String, Object>();
            m.put(CREATED_DATE_TEXT, myrec.getCreatedDate());
            m.put(TIME_IN_USE_TEXT, myrec.getTimeInUse());
            m.put(NUMBER_OF_UNLOCKS_TEXT, myrec.getNumberOfUnlock());
            mylist.add(m);
        }
        return mylist;
    }
}
