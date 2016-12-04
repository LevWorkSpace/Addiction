package com.example.test42;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

import dataBaseModule.RecordProviderFactory;
import global.classes.DailyUsageEntry;
import global.interfaces.IRecordProvider;
import global.utilities.DateUtility;

public class TestEnvironmentActivity extends AppCompatActivity {

    TextView testView;
    Button saveButton, clearButton, goMainButton;
    EditText numberOfUnlockView, createdDateView;

    private IRecordProvider recordProvider;
    private DatePicker datePicker;
    private Calendar calendar;
    private int year, month, day;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_environment);

        testView = (TextView) findViewById(R.id.test_view);
        saveButton = (Button) findViewById(R.id.save_button);
        clearButton = (Button) findViewById(R.id.clear_button);
        goMainButton = (Button) findViewById(R.id.go_main_button);
        numberOfUnlockView = (EditText) findViewById(R.id.number_of_unlock_view);
        createdDateView = (EditText) findViewById(R.id.created_date_view);
        initDatePicker();

        recordProvider = RecordProviderFactory.getProvider(this);
        showAllRecords();

        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                recordProvider.saveDailyUsageRecords(new DailyUsageEntry(createdDateView.getText().toString(),
                                                                         numberOfUnlockView.getText().toString(),
                                                                         DateUtility.getTodayDate()));
                showAllRecords();
                clearInputViews();
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                recordProvider.clearDailyUsageTable();
                showAllRecords();
                clearInputViews();
            }
        });

        goMainButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                finish();
            }
        });
    }

    private void showAllRecords() {
        testView.setText(DailyRecordsListToString(recordProvider.getAllDailyUsageRecords()));
    }

    private void clearInputViews() {
        numberOfUnlockView.setText("");
        createdDateView.setText("");
    }

    private String DailyRecordsListToString(List<DailyUsageEntry> dailyUsageEntries) {
        StringBuilder sb = new StringBuilder();
        for (DailyUsageEntry singleRecord : dailyUsageEntries) {
            sb.append(singleRecord.toString());
            sb.append("\n");
        }
        return sb.toString();
    }

    private void initDatePicker() {
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
    }

    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
        Toast.makeText(getApplicationContext(), "ca",
                Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    calendar.set(arg1, arg2 - 1, arg3, 0, 0);
                    createdDateView.setText(DateUtility.toString(calendar.getTime()));
                }
            };
}
