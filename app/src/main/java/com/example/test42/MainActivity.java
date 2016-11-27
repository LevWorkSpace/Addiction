package com.example.test42;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

import dataBaseModule.RecordProviderFactory;
import global.classes.DailyUsageEntry;
import global.interfaces.IRecordProvider;
import global.utilities.DateUtility;

public class MainActivity extends AppCompatActivity {
    TextView testView;
    Button saveButton;
    Button clearButton;
    EditText numberOfUnlock;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testView = (TextView) findViewById(R.id.test_view);
        saveButton = (Button) findViewById(R.id.save_button);
        clearButton = (Button) findViewById(R.id.clear_button);
        numberOfUnlock = (EditText) findViewById(R.id.number_of_unlock);

        final IRecordProvider record_provider = RecordProviderFactory.getProvider(this);

        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                record_provider.saveDailyUsageRecords(new DailyUsageEntry(DateUtility.getTodayDate(), numberOfUnlock.getText().toString(), DateUtility.getTodayDate()));
                showAllRecords(record_provider);
                numberOfUnlock.setText("0");
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                record_provider.clearDailyUsageTable();
                showAllRecords(record_provider);
            }
        });

        record_provider.saveDailyUsageRecords(new DailyUsageEntry(DateUtility.getTodayDate(), "7", DateUtility.getTodayDate()));
        showAllRecords(record_provider);
    }

    private String DailyRecordsListtoString(List<DailyUsageEntry> dailyUsageEntries) {
        StringBuilder sb = new StringBuilder();
        for (DailyUsageEntry singleRecord : dailyUsageEntries) {
            sb.append(singleRecord.toString());
            sb.append("\n");
        }
        return sb.toString();
    }

    private void showAllRecords(IRecordProvider record_provider) {
        testView.setText(DailyRecordsListtoString(record_provider.getAllDailyUsageRecords()));
    }
}
