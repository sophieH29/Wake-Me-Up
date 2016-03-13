package com.example.android.wakemeup;

import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Sofia on 3/12/2016.
 */
public class MusicListFragment extends ListFragment {

    protected ArrayAdapter arrayAdapter;

    @Override
//    public View onCreateView(LayoutInflater inflater,
//                              ViewGroup container,  Bundle savedInstanceState) {


//        Context context = getActivity();
//        ArrayAdapter<CharSequence> fromResource = arrayAdapter.createFromResource(context, R.array.whale_array, R.layout.music_list);
//        ListView musicList = (ListView)getActivity().findViewById(R.id.listview_fragment);
//        musicList.setAdapter(fromResource);


//

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.music_list, container, false);
        // Create an array of string to be data source of the ListFragment
        String[] datasource={"English","French","Khmer","Japanese","Russian","Chinese"};
        // Create ArrayAdapter object to wrap the data source
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity(),R.layout.music_item,R.id.txtitem,datasource);
        // Bind adapter to the ListFragment
        setListAdapter(adapter);
        //  Retain the ListFragment instance across Activity re-creation
        setRetainInstance(true);

        //listRadioButton = (RadioButton)getActivity().findViewById(R.id.radio_button);
        return rootView;
    }

//    public void onListItemClick(ListView l, View view, int position, long id){
//        ViewGroup viewg=(ViewGroup)view;
//        TextView tv=(TextView)viewg.findViewById(R.id.txtitem);
//        Toast.makeText(getActivity(), tv.getText().toString(), Toast.LENGTH_LONG).show();
//
//    }




    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
