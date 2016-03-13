package com.example.android.wakemeup;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    //to make our alarm manager
    AlarmManager alarmManager;
    TimePicker alarmTimepicker;
    Context context;
    PendingIntent pending_intent;
    int choose_sound = 0 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.context = this;

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MusicActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        // initialize our alarm manager
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        //initialize our timepicker
        alarmTimepicker = (TimePicker) findViewById(R.id.timePicker);

        // create an instance of a calendar
        final Calendar calendar = Calendar.getInstance();

        // create an intent to the Alarm Receiver class
        final Intent my_intent = new Intent(this.context, AlarmReceiver.class);


        // initialize start button
        final Button alarm_on = (Button) findViewById(R.id.alarm_on);
        // initialize the stop button
        final Button alarm_off = (Button) findViewById(R.id.alarm_off);

        // create an onClick listener to start the alarm
        alarm_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alarm_on.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                alarm_on.setTextColor(Color.WHITE);

                alarm_off.setBackgroundColor(getResources().getColor(R.color.lightGray));
                alarm_off.setTextColor(Color.BLACK);

                // setting calendar instance with the hour and minute that we picked
                // on the time picker
                calendar.set(Calendar.HOUR_OF_DAY, alarmTimepicker.getCurrentHour());
                calendar.set(Calendar.MINUTE, alarmTimepicker.getCurrentMinute());

                // get the int values of the hour and minute
                int hour = alarmTimepicker.getCurrentHour();
                int minute = alarmTimepicker.getCurrentMinute();

                // convert the int values to strings
                String hour_string = String.valueOf(hour);
                String minute_string = String.valueOf(minute);
                String timeSymbol = "AM";

                // convert 24-hour time to 12-hour time
                if (hour > 12) {
                    hour_string = String.valueOf(hour - 12);
                    timeSymbol = "PM";
                }

                if (minute < 10) {
                    //10:7 --> 10:07
                    minute_string = "0" + String.valueOf(minute);
                }

                // method that changes the update text Textbox
                showToast("Alarm set to: " + hour_string + ":" + minute_string + " " + timeSymbol);

                // put in extra string into my_intent
                // tells the clock that you pressed the "alarm on" button
                my_intent.putExtra("extra", "alarm on");

                // put in an extra int into my_intent
                // tells the clock that you want a certain value from the drop-down menu/spinner
                my_intent.putExtra("music_choice", choose_sound);
                Log.e("The whale id is" , String.valueOf(choose_sound));

                // create a pending intent that delays the intent
                // until the specified calendar time
                pending_intent = PendingIntent.getBroadcast(MainActivity.this, 0,
                        my_intent, PendingIntent.FLAG_UPDATE_CURRENT);

                // set the alarm manager
                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                        pending_intent);

            }



        });

       // create an onClick listener to stop the alarm or undo an alarm set

        alarm_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alarm_off.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                alarm_off.setTextColor(Color.WHITE);

                alarm_on.setBackgroundColor(getResources().getColor(R.color.lightGray));
                alarm_on.setTextColor(Color.BLACK);

                // method that changes the update text Textbox
                showToast("Alarm off!");

                // cancel the alarm
                alarmManager.cancel(pending_intent);

                // put extra string into my_intent
                // tells the clock that you pressed the "alarm off" button
                my_intent.putExtra("extra", "alarm off");
                // also put an extra int into the alarm off section
                // to prevent crashes in a Null Pointer Exception
                my_intent.putExtra("music_choice", choose_sound);

                // stop the ringtone
                sendBroadcast(my_intent);
            }
        });
    }

    private void showToast(String output) {
        Toast.makeText(context, output, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK){
                choose_sound = data.getIntExtra("music_choice", 0);
            }
        }
    }

}
