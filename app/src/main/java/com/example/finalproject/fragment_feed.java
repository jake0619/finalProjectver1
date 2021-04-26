package com.example.finalproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_feed#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_feed extends Fragment {

    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    ArrayList<Product> pData;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fragment_feed() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_feed.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_feed newInstance(String param1, String param2) {
        fragment_feed fragment = new fragment_feed();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_feed, container, false);
        recyclerView = view.findViewById(R.id.rView);
        recyclerView.setHasFixedSize(true);
        pData = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerAdapter = new RecyclerAdapter(pData, view.getContext());
        recyclerView.setAdapter(recyclerAdapter);
        initializeData();
        recyclerAdapter.notifyDataSetChanged();
        return view;
    }

    public void initializeData(){
        /* TODO: fetch data from database and populate recycler view with initial data from database */
        String[] tempNames = {"GeForce RTX 3080", "Radeon 6800 XT", "GeForce RTX 3060"};
        for(int i = 0; i<3; i++){
            pData.add(new Product(tempNames[i], 300+i, "Some GPU", true));
        }
    }
}