package com.example.manas.movieapp.Fragment_Handlers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.manas.movieapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Manas on 3/24/2015.
 */
public class NavigationDrawerFragmentHandler extends Fragment {
ListView sidelist;
    List<String> itemlist = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View v= inflater.inflate(R.layout.fragment_navigation_drawer,container,false);
        getListReady();
        arrayAdapter = new ArrayAdapter<String>(getActivity(),R.layout.simple_list_item,R.id.tv1,itemlist);
        sidelist = (ListView) v.findViewById(R.id.listviewsidemenu);
        sidelist.setAdapter(arrayAdapter);
        return  v;
    }

    public void getListReady(){
         String[] items = new String[]{"Top Rented", "Box Office Movies","Up Comming Movies","In Theater Movies"};
         for(int i =0;i<items.length;i++){
             itemlist.add(items[i]);
         }
     }


}
