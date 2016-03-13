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
        Integer whale_sound_choice = intent.getExtras().getInt("whale_choice");

        Log.e("Ringtone extra is ", state);
        Log.e("Whale choice is ", whale_sound_choice.toString());

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

            playMusic(whale_sound_choice);

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
//
//        // these are if the user presses random buttons
//        // just to bug-proof the app
//        // if there is no music playing, and the user pressed "alarm off"
//        // do nothing
//        else if (!this.isRunning && startId == 0) {
//            Log.e("there is no music, ", "and you want end");
//
//            this.isRunning = false;
//            this.startId = 0;
//
//        }
//
//        // if there is music playing and the user pressed "alarm on"
//        // do nothing
//        else if (this.isRunning && startId == 1) {
//            Log.e("there is music, ", "and you want start");
//
//            this.isRunning = true;
//            this.startId = 1;
//
//        }
//
//        // can't think of anything else, just to catch the odd event
//        else {
//            Log.e("else ", "somehow you reached this");
//
//        }



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
            case 0: media_song = MediaPlayer.create(this, R.raw.humpback_bubblenet_and_vocals);
                break;
            case 1: media_song = MediaPlayer.create(this, R.raw.humpback_contact_call_moo);
                break;
            case 2 : media_song = MediaPlayer.create(this, R.raw.humpback_contact_call_whup);
                break;
            case 3: media_song = MediaPlayer.create(this, R.raw.humpback_feeding_call);
                break;
            case 4: media_song = MediaPlayer.create(this, R.raw.humpback_flipper_splash);
                break;
            case 5: media_song = MediaPlayer.create(this, R.raw.humpback_tail_slaps_with_propeller_whine);
                break;
            case 6: media_song = MediaPlayer.create(this, R.raw.humpback_whale_song);
                break;
            case 7:  media_song = MediaPlayer.create(this, R.raw.humpback_whale_song_with_outboard_engine_noise);
                break;
            case 8: media_song = MediaPlayer.create(this, R.raw.humpback_wheeze_blows);
                break;
            case 9: media_song = MediaPlayer.create(this, R.raw.killerwhale_echolocation_clicks);
                break;
            case 10:  media_song = MediaPlayer.create(this, R.raw.killerwhale_offshore);
                break;
            case 11: media_song = MediaPlayer.create(this, R.raw.killerwhale_resident);
                break;
            case 12: media_song = MediaPlayer.create(this, R.raw.killerwhale_transient);
                break;
            default: media_song = MediaPlayer.create(this, R.raw.killerwhale_resident);
                break;
        }

        media_song.start();
    }

}