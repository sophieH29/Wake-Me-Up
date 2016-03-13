package com.example.android.wakemeup;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Sofia on 3/12/2016.
 */
public class MusicActivity extends AppCompatActivity {

    MusicListFragment musicListFragment;
    FragmentManager fragmentManager;

    private RadioButton listRadioButton = null;
    int listIndex = -1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music_list);

        fragmentManager = getFragmentManager();
        musicListFragment=new MusicListFragment();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(android.R.id.content,musicListFragment,"musicListFragment");
        transaction.commit();

        }

    public void onClickRadioButton(View v) {
        View vMain = ((View) v.getParent());
        // getParent() must be added 'n' times,
        // where 'n' is the number of RadioButtons' nested parents
        // in your case is one.

        // uncheck previous checked button.
        if (listRadioButton != null) listRadioButton.setChecked(false);
        // assign to the variable the new one
        listRadioButton = (RadioButton) v;
        // find if the new one is checked or not, and set "listIndex"
        if (listRadioButton.isChecked()) {
            listIndex = ((ViewGroup) vMain.getParent()).indexOfChild(vMain);
        } else {
            listRadioButton = null;
            listIndex = -1;
        }

       // ViewGroup viewg=(ViewGroup)view;
        TextView tv=(TextView)vMain.findViewById(R.id.txtitem);
        Toast.makeText(this, tv.getText().toString(), Toast.LENGTH_LONG).show();
    }
    }
