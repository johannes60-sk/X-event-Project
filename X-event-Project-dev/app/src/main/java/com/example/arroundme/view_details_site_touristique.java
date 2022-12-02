package com.example.arroundme;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.arroundme.modele.ViewHolder;
import com.example.arroundme.modele.modele_lieux_touristique;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class view_details_site_touristique extends AppCompatActivity {

    private ImageView iv_view_detail_site_touristque;
    private TextView title, categorie_name, btnVoirPlus;
    private BottomSheetDialog bottomSheetDialog;
    private LinearLayout ly_view_detail;
    FirestoreRecyclerAdapter adapter;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details_site_touristique);

        iv_view_detail_site_touristque = findViewById(R.id.iv_view_detail_site_touristque);
        title = findViewById(R.id.title);
        iv_view_detail_site_touristque = findViewById(R.id.iv_view_detail_site_touristque);
        categorie_name = findViewById(R.id.categorie_name);
        btnVoirPlus = findViewById(R.id.btnVoirPlus);

        Bundle extras = getIntent().getExtras();

        if(extras.getString("name") != null){

            readDataFromFirebase(extras.getString("name"), "info_site_touristique_chateau");
        }

        btnVoirPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog = new BottomSheetDialog(view_details_site_touristique.this,R.style.BottomSheetTheme);
                View sheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.bottom_modal_description_site_touristique,null);

                bottomSheetDialog.setContentView(sheetView);
                bottomSheetDialog.show();
            }
        });



    }

    public void readDataFromFirebase(String name, String collection){

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection(collection)
                .whereEqualTo("name",name)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());

                                title.setText(document.get("name").toString());

                                Glide.with(view_details_site_touristique.this)
                                        .load(document.get("thumb_image"))
                                        .into(iv_view_detail_site_touristque);

                                categorie_name.setText(document.get("categorie_name").toString());

                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }

}