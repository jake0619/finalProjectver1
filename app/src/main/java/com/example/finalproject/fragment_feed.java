package com.example.finalproject;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

import static androidx.core.content.ContextCompat.getSystemService;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_feed#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_feed extends Fragment implements View.OnClickListener {

    private static final String LOG_TAG =
            fragment_feed.class.getSimpleName();

    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    ArrayList<Product> pData;
    ImageButton searchButton;
    EditText searchInput;

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
        searchButton = (ImageButton) view.findViewById(R.id.searchButton);
        searchInput = view.findViewById(R.id.searchText);
        searchButton.setOnClickListener(this);
        recyclerView = view.findViewById(R.id.rView);
        recyclerView.setHasFixedSize(true);
        pData = new ArrayList<>();
        pData.add(new Product("Loading", "", "", false, ""));
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerAdapter = new RecyclerAdapter(pData, view.getContext());
        recyclerView.setAdapter(recyclerAdapter);
        initializeData();
        return view;
    }

    public void initializeData(){
        /* TODO: fetch data from database and populate recycler view with initial data from database */
        FetchBook fetchBook = new FetchBook(this);
        fetchBook.execute("best graphic cards");

    }

    public void updateData(ArrayList<Product> products) {
        this.pData = products;
        Log.d(LOG_TAG, "Size: "+pData.size());
        recyclerAdapter.setData(pData);
        recyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        try {
            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            // TODO: handle exception
        }

        if(searchInput.getText().toString().trim().length() == 0){
            Toast.makeText(this.getActivity(), "Please enter a search query", Toast.LENGTH_SHORT).show();
        }
        else{

            String searchQuery = searchInput.getText().toString();
            FetchBook fetchBook = new FetchBook(this);
            fetchBook.execute(searchQuery);
            ArrayList<Product> temp = new ArrayList<Product>();
            temp.add(new Product("Loading", "", "", false, ""));
            recyclerAdapter.setData(temp);
            recyclerAdapter.notifyDataSetChanged();

        }

    }
}