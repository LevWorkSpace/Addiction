package com.example.test42;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.test42.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import dataBaseModule.RecordProviderFactory;
import global.classes.DailyUsageEntry;
import global.interfaces.IRecordProvider;
import ui.DailyRecordsSimpleAdapter;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final IRecordProvider record_provider = RecordProviderFactory.getProvider(this);
        ArrayList<DailyUsageEntry> myrecords=record_provider.getAllDailyUsageRecords();
        ListView list1=(ListView) findViewById(R.id.lvMain);
        SimpleAdapter sAdapter = new DailyRecordsSimpleAdapter(this, myrecords, R.layout.item);
        list1.setAdapter(sAdapter);
    }

}
