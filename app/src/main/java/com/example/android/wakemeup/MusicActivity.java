package com.example.android.wakemeup;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Sofia on 3/12/2016.
 */
public class MusicActivity extends AppCompatActivity {

    private int choose_sound;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.music_list);

        FragmentManager fragmentManager = getFragmentManager();
        MusicListFragment musicListFragment = new MusicListFragment();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(android.R.id.content, musicListFragment, "musicListFragment");
        transaction.commit();

        }

    public void setMusicChoice(int choice){
        choose_sound = choice;
    }

    public void okButtonClick(View v){

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("whale_choice",choose_sound);
        setResult(RESULT_OK, intent);
        finish();

    }

    public void cancelButtonClick(View view) {

        Intent intent = new Intent(this, MainActivity.class);
        setResult(RESULT_OK, intent);
        finish();

    }
}
