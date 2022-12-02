package com.example.arroundme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.arroundme.modele.ViewHolder;
import com.example.arroundme.modele.modele_lieux_touristique;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class list_view_site_touriste extends AppCompatActivity {

    FirebaseFirestore db;
    FirestoreRecyclerAdapter adapter;
    RecyclerView recyclerLieuTouristique;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_site_touriste);

        Bundle extras = getIntent().getExtras();

        if(extras.getString("id_chateaux") != null){

            show_site_touriste("info_site_touristique_chateau");

        }else if(extras.getString("id_parc") != null){

            show_site_touriste("info_site_touristique_parc");

        }else if(extras.getString("id_musée") != null){
            show_site_touriste("info_site_touristique_musée ");
        }

    }

    public void show_site_touriste( String collection){

        // *********************** connexion a firebase et recuperation de la collection "info_site_touristique_chateau" **************

//        db = FirebaseFirestore.getInstance();
//        CollectionReference collRef = db.collection("info_site_touristique_chateau");
//        Query query = collRef.orderBy("name");
        Query query = FirebaseFirestore.getInstance().collection(collection);

        // *************************** Creation d'un adaptateur ***********************

        FirestoreRecyclerOptions<modele_lieux_touristique> options = new FirestoreRecyclerOptions.Builder<modele_lieux_touristique>()
                .setQuery(query, modele_lieux_touristique.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<modele_lieux_touristique, ViewHolder>(options) {
            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                // Create a new instance of the ViewHolder, in this case we are using a custom
                // layout called R.layout.message for each item
                View layout = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_view_site_touriste, parent, false);
                return new ViewHolder(layout);
            }

            @Override
            protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull modele_lieux_touristique model) {

                holder.place_name.setText(model.getName());
                holder.distance.setText(model.getDistance());
                Glide.with(list_view_site_touriste.this)
                        .load(model.getThumb_image())
                        .into(holder.circleImageView);
                holder.categorie_name.setText(model.getCategorie_name());

                holder.categorieDrawer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(list_view_site_touriste.this, view_details_site_touristique.class);
                        intent.putExtra("name", model.getName());
                        intent.putExtra("image", model.getThumb_image());
                        intent.putExtra("categorie_name", model.getCategorie_name());
                        startActivity(intent);
                    }
                });

            }
        };

        // *************************** On passe l'adaptateur creer au recyclerView *******************

        //************************ Rcuperation du recyclerView creer dans activity_list_view_site_touristique ************************

        recyclerLieuTouristique = findViewById(R.id.recyclerLieuTouristique);
        recyclerLieuTouristique.setLayoutManager(new LinearLayoutManager(this));
        recyclerLieuTouristique.setHasFixedSize(true);
        recyclerLieuTouristique.setAdapter(adapter);

        super.onStart();
        adapter.startListening();

//      adapter.stopListening();
    }












    //    @Override
//    protected void onStart() {
//        super.onStart();
//        adapter.startListening();
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        adapter.stopListening();
//    }
}