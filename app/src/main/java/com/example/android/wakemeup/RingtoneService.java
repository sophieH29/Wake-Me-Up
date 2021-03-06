package com.example.android.wakemeup;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;


public class RingtoneService extends IntentService {

    private MediaPlayer media_song;
    private boolean isAlarmOn;
    private boolean isRunning;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     */
    public RingtoneService() {
        super("");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    protected void onHandleIntent(Intent intent) {

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

            Intent mainActivityIntent = new Intent(this, MainActivity.class);
            mainActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            this.startActivity(mainActivityIntent);
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

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        super.onStartCommand(intent, flags, startId);
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
        this.isRunning = false;
        this.isAlarmOn = true;
    }

    // Play alarm music depending on user choice
    private void playMusic(int musicChoice){

        int[] musicIdsList = new int[]{
                R.raw.ac_dc_rock_or_bust,
                R.raw.ac_dc_the_jack,
                R.raw.ac_dc_money_made,
                R.raw.airbourne_too_much,
                R.raw.alt_j_hunger_of_the_pine,
                R.raw.alt_j_fitzpleasure,
                R.raw.banks_waiting_game,
                R.raw.ben_howard_keep_your_head_up,
                R.raw.beyonce_7_11,
                R.raw.billy_squire_the_stroke,
                R.raw.black_sabbath_war_pigs,
                R.raw.black_sabbath_paranoid,
                R.raw.david_dallas_running,
                R.raw.disclosure_you_and_me,
                R.raw.the_forest_rangers_john_the_revelator,
                R.raw.cellos_highway_to_hell,
                R.raw.cellos_wake_me_up
        };

        //default value
        media_song = MediaPlayer.create(this, R.raw.ac_dc_rock_or_bust);

        if (musicIdsList.length > musicChoice){

            media_song = MediaPlayer.create(this, musicIdsList[musicChoice]);
        }

        media_song.start();
    }
}