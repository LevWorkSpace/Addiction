package com.example.test42;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;

import dataBaseModule.RecordProviderFactory;
import global.classes.DailyUsageEntry;
import global.interfaces.IRecordProvider;
import ui.DailyRecordsSimpleAdapter;


public class MainActivity extends AppCompatActivity {
	
	ListView list1;

    private IRecordProvider recordProvider;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

		list1=(ListView) findViewById(R.id.lvMain);

        recordProvider = RecordProviderFactory.getProvider(this);
        showAllRecords();
    }

    public boolean goToTestEnvironment(MenuItem item) {
        Intent nextScreen = new Intent(getApplicationContext(), TestEnvironmentActivity.class);
        //Sending data to another Activity
        //nextScreen.putExtra("record_provider", recordProvider);
        startActivity(nextScreen);
        return true;
    }
	
	@Override
    protected void onResume() {
        //Executes on closing secondary activity.
        super.onResume();
        showAllRecords();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.testing_activity:
                return goToTestEnvironment(item);
            case R.id.help:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
	
	private void showAllRecords() {
        ArrayList<DailyUsageEntry> myrecords=recordProvider.getAllDailyUsageRecords();
		SimpleAdapter sAdapter = new DailyRecordsSimpleAdapter(this, myrecords, R.layout.item);
        list1.setAdapter(sAdapter);
    }
}
