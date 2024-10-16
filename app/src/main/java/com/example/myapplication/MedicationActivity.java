package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class MedicationActivity extends AppCompatActivity {

    private EditText medicationName;
    private TextView medicationTime;
    private Button setDailyReminder, setWeeklyReminder;
    private ListView medicationListView;
    private ArrayList<String> medicationList;
    private ArrayAdapter<String> adapter;
    private int hour, minute;

    private SharedPreferences sharedPreferences;
    private static final String MEDICATION_PREFS = "MedicationPrefs";
    private static final String MEDICATION_SET = "MedicationSet";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medication);

        medicationName = findViewById(R.id.medicationName);
        medicationTime = findViewById(R.id.medicationTime);
        setDailyReminder = findViewById(R.id.setDailyReminder);
        setWeeklyReminder = findViewById(R.id.setWeeklyReminder);
        medicationListView = findViewById(R.id.medicationListView);

        sharedPreferences = getSharedPreferences(MEDICATION_PREFS, MODE_PRIVATE);

        loadMedicationList();

        medicationTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePickerDialog();
            }
        });

        setDailyReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = medicationName.getText().toString();
                String time = medicationTime.getText().toString();
                if (!name.isEmpty() && !time.isEmpty()) {
                    setReminder(name, time, true);
                    updateMedicationList(name, time);
                } else {
                    Toast.makeText(MedicationActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        setWeeklyReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = medicationName.getText().toString();
                String time = medicationTime.getText().toString();
                if (!name.isEmpty() && !time.isEmpty()) {
                    setReminder(name, time, false);
                    updateMedicationList(name, time);
                } else {
                    Toast.makeText(MedicationActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        medicationListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                showEditDeleteDialog(position);
                return true;
            }
        });
    }

    private void showTimePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                hour = selectedHour;
                minute = selectedMinute;
                String formattedTime = formatTime(hour, minute);
                medicationTime.setText(formattedTime);
            }
        }, hour, minute, false);
        timePickerDialog.show();
    }

    private String formatTime(int hour, int minute) {
        String amPm = (hour >= 12) ? "PM" : "AM";
        hour = (hour > 12) ? hour - 12 : (hour == 0) ? 12 : hour;
        return String.format(Locale.getDefault(), "%02d:%02d %s", hour, minute, amPm);
    }

    private void setReminder(String medicationName, String time, boolean isDaily) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(MedicationActivity.this, Reminder.class);
        intent.putExtra("medicationName", medicationName);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        String[] timeParts = time.split("[: ]");
        int hour = Integer.parseInt(timeParts[0]);
        int minute = Integer.parseInt(timeParts[1]);
        String amPm = timeParts[2];

        if (amPm.equals("PM") && hour != 12) {
            hour += 12;
        } else if (amPm.equals("AM") && hour == 12) {
            hour = 0;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);

        if (isDaily) {
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
            Toast.makeText(this, "Daily reminder set for " + medicationName, Toast.LENGTH_SHORT).show();
        } else {
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY * 7, pendingIntent);
            Toast.makeText(this, "Weekly reminder set for " + medicationName, Toast.LENGTH_SHORT).show();
        }
    }

    private void updateMedicationList(String name, String time) {
        medicationList.add(name + " - " + time);
        adapter.notifyDataSetChanged();
        saveMedicationList();
    }

    private void saveMedicationList() {
        Set<String> medicationSet = new HashSet<>(medicationList);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet(MEDICATION_SET, medicationSet);
        editor.apply();
    }

    private void loadMedicationList() {
        Set<String> medicationSet = sharedPreferences.getStringSet(MEDICATION_SET, new HashSet<>());
        medicationList = new ArrayList<>(medicationSet);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, medicationList);
        medicationListView.setAdapter(adapter);
    }

    private void showEditDeleteDialog(final int position) {
        new AlertDialog.Builder(this)
                .setTitle("Delete Reminder")
                .setMessage("Are you sure you want to delete this reminder?")
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Remove from list and update
                        medicationList.remove(position);
                        adapter.notifyDataSetChanged();
                        saveMedicationList(); // Save updated list
                        Toast.makeText(MedicationActivity.this, "Reminder deleted", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}
