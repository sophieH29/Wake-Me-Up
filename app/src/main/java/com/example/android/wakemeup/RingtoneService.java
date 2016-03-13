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
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.security.Provider;
import java.util.Random;


public class RingtoneService extends Service {

    MediaPlayer media_song;
    int startId;
    boolean isRunning;


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("LocalService", "Received start id " + startId + ": " + intent);

        // fetch the extra string from the alarm on/alarm off values
        String state = intent.getExtras().getString("extra");
        // fetch the whale choice integer values
        Integer music_choice = intent.getExtras().getInt("music_choice");

        Log.e("Ringtone extra is ", state);
        Log.e("Whale choice is ", music_choice.toString());

        // put the notification here, test it out

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
                .setContentTitle("An alarm is going off!")
                .setContentText("Click me!")
                .setSmallIcon(R.drawable.ic_action_call)
                .setContentIntent(pending_intent_main_activity)
                .setAutoCancel(true)
                .build();


        // this converts the extra strings from the intent
        // to start IDs, values 0 or 1
        assert state != null;
        switch (state) {
            case "alarm on":
                startId = 1;
                break;
            case "alarm off":
                startId = 0;
                Log.e("Start ID is ", state);
                break;
            default:
                startId = 0;
                break;
        }


        // if else statements

        // if there is no music playing, and the user pressed "alarm on"
        // music should start playing
        if (!this.isRunning && startId == 1) {

            this.isRunning = true;
            this.startId = 0;

            // set up the start command for the notification
            notify_manager.notify(0, notification_popup);

            playMusic(music_choice);

        }

        // if there is music playing, and the user pressed "alarm off"
        // music should stop playing
        else if (this.isRunning && startId == 0) {

            // stop the ringtone
            media_song.stop();
            media_song.reset();

            this.isRunning = false;
            this.startId = 0;
       }

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        // Tell the user we stopped.
        Log.e("on Destroy called", "ta da");

        super.onDestroy();
        this.isRunning = false;
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