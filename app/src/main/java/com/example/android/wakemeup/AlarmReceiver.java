package com.example.android.wakemeup;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Sofia on 3/10/2016.
 */
public class AlarmReceiver  extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        // fetch extra strings from the intent
        // tells the app whether the user pressed the alarm on button or the alarm off button
        Boolean isAlarmOn = intent.getExtras().getBoolean(context.getString(R.string.alarm_on_extra));

        // fetch the extra longs from the intent
        // tells the app which value the user picked from the drop down menu/spinner
        Integer musicChoice = intent.getExtras().getInt(context.getString(R.string.music_choice_extra));

        // create an intent to the ringtone service
        Intent service_intent = new Intent(context, RingtoneService.class);

        // pass the extra string from Receiver to the Ringtone Playing Service
        service_intent.putExtra(context.getString(R.string.alarm_on_extra), isAlarmOn);
        // pass the extra integer from the Receiver to the Ringtone Playing Service
        service_intent.putExtra(context.getString(R.string.music_choice_extra), musicChoice);

        // start the ringtone service
        context.startService(service_intent);

    }
}
