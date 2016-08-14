package com.urjapawar.jagrukbharat;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;



public class FragFarmerInfo extends android.support.v4.app.Fragment {
    private ArrayList<FarmerInfo> fi;
    private OnFragmentInteractionListener mListener;
    public static final String[] titles = new String[] { "H C Verma",
            "P Bahadur", "I E Irodov", "M L Khanna",
            "O P Tondon", "Morrison & Boyd", "Halliday Resnick",
            "R D Sharma", "S L Loney", "Hall and Knight", "Krotov", "I A Maron" };
    CustomAdapter adapter;
    public static final String[] descriptions = new String[] {

            "Physics",
            "Chemistry",
            "Physics",
            "Mathematics",
            "Chemistry",
            "Chemistry",
            "Physics",
            "Mathematics",
            "Mathematics",
            "Chemistry",
            "Physics",
            "Mathematics"

    };


    public static final String[] pub = new String[]{"Bharti Bhavan", "G R Bathla Publications", "Arihant",
            "Jai Prakash Nath", "G R Bathla Publications", "Pearson Publications",
            "Wiley Publications", "Dhanpat Rai Publications","Classic Texts series","Classic Texts series","G K Publications","G K Publications"};

    ListView listView;
    ArrayList<FarmerInfo> rowItems;
    public FragFarmerInfo() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview=inflater.inflate(R.layout.fragment_frag_farmer_info, container, false);
        Toast.makeText(getActivity(),"inflated",Toast.LENGTH_SHORT).show();
        rowItems = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            FarmerInfo item = new FarmerInfo(titles[i], descriptions[i]);
            rowItems.add(item);
        }
        listView = (ListView)rootview.findViewById(R.id.list);
      //  adapter = new CustomAdapter(getActivity(), );
        listView.setAdapter(adapter);
        return rootview;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


}
