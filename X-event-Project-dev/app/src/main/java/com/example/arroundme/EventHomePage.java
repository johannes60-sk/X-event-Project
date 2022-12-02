package com.example.arroundme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.arroundme.modele.ChildModelClass;
import com.example.arroundme.modele.ParentAdapter;
import com.example.arroundme.modele.ParentModelClass;

import java.util.ArrayList;

public class EventHomePage extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<ParentModelClass> parentModelClassArrayList;
    ArrayList<ChildModelClass> childModelClassArrayList;
    ArrayList<ChildModelClass> favoriteList;
    ArrayList<ChildModelClass> recentlyWatchedList;
    ArrayList<ChildModelClass> latestList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_home_page);

//        getSupportActionBar().hide();

        recyclerView = findViewById(R.id.rv_parent);
        childModelClassArrayList = new ArrayList<>();
        favoriteList = new ArrayList<>();
        recentlyWatchedList = new ArrayList<>();
        latestList = new ArrayList<>();
        parentModelClassArrayList = new ArrayList<>();
        ParentAdapter parentAdapter;

        latestList.add(new ChildModelClass(R.drawable.festival_musical));
        latestList.add(new ChildModelClass(R.drawable.arc_noir_deon));
        latestList.add(new ChildModelClass(R.drawable.arc_noir_deon));
        latestList.add(new ChildModelClass(R.drawable.arc_noir_deon));
        latestList.add(new ChildModelClass(R.drawable.festival_musical));

        parentModelClassArrayList.add(new ParentModelClass("Evenement a la une", latestList));

        recentlyWatchedList.add(new ChildModelClass(R.drawable.arc_noir_deon));
        recentlyWatchedList.add(new ChildModelClass(R.drawable.arc_noir_deon));
        recentlyWatchedList.add(new ChildModelClass(R.drawable.arc_noir_deon));
        recentlyWatchedList.add(new ChildModelClass(R.drawable.festival_musical));
        recentlyWatchedList.add(new ChildModelClass(R.drawable.festival_musical));

        parentModelClassArrayList.add(new ParentModelClass("A proximite", recentlyWatchedList));

        favoriteList.add(new ChildModelClass(R.drawable.festival_musical));
        favoriteList.add(new ChildModelClass(R.drawable.arc_noir_deon));
        favoriteList.add(new ChildModelClass(R.drawable.arc_noir_deon));
        favoriteList.add(new ChildModelClass(R.drawable.arc_noir_deon));
        favoriteList.add(new ChildModelClass(R.drawable.festival_musical));

        parentModelClassArrayList.add(new ParentModelClass("Populaire aupres de tes amies", favoriteList));

        childModelClassArrayList.clear();

//        childModelClassArrayList.add(new ChildModelClass(R.drawable.localisation));
//        childModelClassArrayList.add(new ChildModelClass(R.drawable.arc_noir_deon));
//        childModelClassArrayList.add(new ChildModelClass(R.drawable.concert));
//        childModelClassArrayList.add(new ChildModelClass(R.drawable.concert2));
//        childModelClassArrayList.add(new ChildModelClass(R.drawable.festival_musical));
//
//        parentModelClassArrayList.add(new ParentModelClass("Recently", childModelClassArrayList));
//        parentModelClassArrayList.add(new ParentModelClass("Recently", childModelClassArrayList));
//        parentModelClassArrayList.add(new ParentModelClass("Great", childModelClassArrayList));
//        parentModelClassArrayList.add(new ParentModelClass("Fine", childModelClassArrayList));

        parentAdapter = new ParentAdapter(parentModelClassArrayList, EventHomePage.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(parentAdapter);
        parentAdapter.notifyDataSetChanged();
    }
}