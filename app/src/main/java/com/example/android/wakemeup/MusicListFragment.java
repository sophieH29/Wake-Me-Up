package com.example.android.wakemeup;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Sofia on 3/12/2016.
 */
public class MusicListFragment extends ListFragment {

    private MyArrayAdapter adapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.music_list, container, false);

        // Create an array of string to be data source of the ListFragment
        String[] datasource = getResources().getStringArray(R.array.music_array);

        // Create ArrayAdapter object to wrap the data source
        adapter = new MyArrayAdapter(getActivity(), datasource);

        // Bind adapter to the ListFragment
        setListAdapter(adapter);

        // Retain the ListFragment instance across Activity re-creation
        setRetainInstance(true);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {

        super.onListItemClick(listView, view, position, id);

        adapter.setSelectedIndex(position);
        adapter.notifyDataSetChanged();

        // send music choice to Music Activity
        MusicActivity musicActivity = (MusicActivity) getActivity();
        musicActivity.setMusicChoice(position);

        TextView textView = (TextView) view.findViewById(R.id.txtitem);
        Toast.makeText(getActivity(), textView.getText().toString(), Toast.LENGTH_SHORT).show();
    }
}
