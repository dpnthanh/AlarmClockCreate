package com.dpn.thanh.alarmclockcreate;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TimePicker timePicker;
    Button btnStart, btnEnd;
    TextView txtInfo;
    Calendar calendar;
    AlarmManager alarmManager;
    PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initControls();
        initDisplay();
        initEvents();
    }

    private void initEvents() {
        btnStart.setOnClickListener(this);

    }

    private void initDisplay() {
        timePicker.setCurrentHour(calendar.get(Calendar.HOUR_OF_DAY));
        timePicker.setCurrentMinute(calendar.get(Calendar.MINUTE));

    }

    private void initControls() {
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        btnStart = (Button) findViewById(R.id.button_start_mainActivity);
        btnEnd = (Button) findViewById(R.id.button_end_mainActivity);
        txtInfo = (TextView) findViewById(R.id.textView_info_mainActivity);

        calendar = Calendar.getInstance();
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_start_mainActivity:

                setTimeAlarm();

                break;
            case R.id.button_end_mainActivity:
                break;
        }
    }

    private void setTimeAlarm() {
        int chooseHour = timePicker.getCurrentHour();
        int chooseMinute = timePicker.getCurrentMinute();
        calendar.set(Calendar.HOUR_OF_DAY, chooseHour);
        calendar.set(Calendar.MINUTE, chooseMinute);

        Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);

        pendingIntent = PendingIntent.getBroadcast(
                getApplicationContext()
                , 0
                , intent
                , PendingIntent.FLAG_UPDATE_CURRENT
        );
        alarmManager.set(AlarmManager.RTC_WAKEUP
                , calendar.getTimeInMillis()
                , pendingIntent);
        txtInfo.setText("set Time alarm: " + chooseHour + " : " + chooseMinute);


    }
}
