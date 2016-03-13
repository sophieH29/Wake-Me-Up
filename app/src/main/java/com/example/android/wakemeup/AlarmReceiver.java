package com.example.android.wakemeup;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Sofia on 3/10/2016.
 */
public class AlarmReceiver  extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        // fetch extra strings from the intent
        // tells the app whether the user pressed the alarm on button or the alarm off button
        Boolean isAlarmOn = intent.getExtras().getBoolean("alarm_on");

        // fetch the extra longs from the intent
        // tells the app which value the user picked from the drop down menu/spinner
        Integer musicChoice = intent.getExtras().getInt("music_choice");

        Log.e("The whale choice is ", musicChoice.toString());

        // create an intent to the ringtone service
        Intent service_intent = new Intent(context, RingtoneService.class);

        // pass the extra string from Receiver to the Ringtone Playing Service
        service_intent.putExtra("alarm_on", isAlarmOn);
        // pass the extra integer from the Receiver to the Ringtone Playing Service
        service_intent.putExtra("music_choice", musicChoice);

        // start the ringtone service
        context.startService(service_intent);

    }
}
