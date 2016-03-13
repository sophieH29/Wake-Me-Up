package com.example.android.wakemeup;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Sofia on 3/12/2016.
 */
public class MusicActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.music_list);

        FragmentManager fragmentManager = getFragmentManager();
        MusicListFragment musicListFragment = new MusicListFragment();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(android.R.id.content, musicListFragment, "musicListFragment");
        transaction.commit();

        }
}
