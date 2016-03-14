package com.example.android.wakemeup;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;


public class RingtoneService extends Service {

    MediaPlayer media_song;
    Boolean isAlarmOn;
    boolean isRunning;


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        // fetch the extra string from the alarm on/alarm off values
        isAlarmOn = intent.getExtras().getBoolean(getString(R.string.alarm_on_extra), false);

        // fetch the whale choice integer values
        int musicChoice = intent.getExtras().getInt(getString(R.string.music_choice_extra), 0);

        // notification
        // set up the notification service
        NotificationManager notify_manager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);
        // set up an intent that goes to the Main Activity
        Intent intent_main_activity = new Intent(this.getApplicationContext(), MainActivity.class);
        // set up a pending intent
        PendingIntent pending_intent_main_activity = PendingIntent.getActivity(this, 0,
                intent_main_activity, 0);

        // make the notification parameters
        Notification notification_popup = new Notification.Builder(this)
                .setContentTitle(getString(R.string.alarm_is_going_off))
                .setContentText(getString(R.string.click_me))
                .setSmallIcon(R.drawable.clock)
                .setContentIntent(pending_intent_main_activity)
                .setAutoCancel(true)
                .build();

         // if there is no music playing, and the user pressed "alarm on"
        // music should start playing
        if (!this.isRunning && isAlarmOn) {

            this.isRunning = true;
            this.isAlarmOn = false;

            // set up the start command for the notification
            notify_manager.notify(0, notification_popup);

            playMusic(musicChoice);
        }

        // if there is music playing, and the user pressed "alarm off"
        // music should stop playing
        else if (this.isRunning && !isAlarmOn) {

            // stop the ringtone
            media_song.stop();
            media_song.reset();

            this.isRunning = false;
            this.isAlarmOn = false;
       }

        return START_STICKY;
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
        this.isRunning = false;
        this.isAlarmOn = true;
    }

    // Play alarm music depending on user choice
    private void playMusic(int musicChoice){

        switch (musicChoice){
            case 0: media_song = MediaPlayer.create(this, R.raw.ac_dc_rock_or_bust);
                break;
            case 1: media_song = MediaPlayer.create(this, R.raw.ac_dc_the_jack);
                break;
            case 2 : media_song = MediaPlayer.create(this, R.raw.ac_dc_money_made);
                break;
            case 3: media_song = MediaPlayer.create(this, R.raw.airbourne_too_much);
                break;
            case 4: media_song = MediaPlayer.create(this, R.raw.alt_j_hunger_of_the_pine);
                break;
            case 5: media_song = MediaPlayer.create(this, R.raw.alt_j_fitzpleasure);
                break;
            case 6: media_song = MediaPlayer.create(this, R.raw.banks_waiting_game);
                break;
            case 7:  media_song = MediaPlayer.create(this, R.raw.ben_howard_keep_your_head_up);
                break;
            case 8: media_song = MediaPlayer.create(this, R.raw.beyonce_7_11);
                break;
            case 9: media_song = MediaPlayer.create(this, R.raw.billy_squire_the_stroke);
                break;
            case 10:  media_song = MediaPlayer.create(this, R.raw.black_sabbath_war_pigs);
                break;
            case 11: media_song = MediaPlayer.create(this, R.raw.black_sabbath_paranoid);
                break;
            case 12: media_song = MediaPlayer.create(this, R.raw.david_dallas_running);
                break;
            case 13:  media_song = MediaPlayer.create(this, R.raw.disclosure_you_and_me);
                break;
            case 14: media_song = MediaPlayer.create(this, R.raw.the_forest_rangers_john_the_revelator);
                break;
            case 15: media_song = MediaPlayer.create(this, R.raw.cellos_highway_to_hell);
                break;
            case 16: media_song = MediaPlayer.create(this, R.raw.cellos_wake_me_up);
                break;
            default: media_song = MediaPlayer.create(this, R.raw.black_sabbath_war_pigs);
                break;
        }

        media_song.start();
    }

}