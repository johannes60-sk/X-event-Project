package com.example.arroundme;

import static com.example.arroundme.R.id.list_view_musée;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

public class MainActivity extends AppCompatActivity {

    CardView list_chateau, list_musée, list_parc;
    TextView place_name;
    ImageView block_destination_fav_image;
    TextView textChateaux;
    Button btnLogin, btnGoogle, btnEvent;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list_chateau = (CardView)findViewById(R.id.list_View_Chateau);
        list_parc = (CardView)findViewById(R.id.list_view_parc);
        list_musée = (CardView)findViewById(R.id.list_view_musée);
        block_destination_fav_image = (ImageView)findViewById(R.id.block_destination_fav_image);
        btnLogin = findViewById(R.id.btn_login);
        btnGoogle = findViewById(R.id.btn_google);
        btnEvent = findViewById(R.id.btn_event);



        list_chateau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setIntent("id_chateaux","list_View_Chateau");
            }
        });

        list_parc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setIntent("id_parc","list_view_parc");
            }
        });

        list_musée.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setIntent("id_musée", "list_view_musée");
            }
        });

        block_destination_fav_image.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, view_details_site_touristique.class);
//                intent.putExtra(key, value);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ConnectionWithFacebook.class);
                startActivity(intent);
            }
        });

        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ConnectionWithGoogle.class);
                startActivity(intent);
            }
        });

        btnEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EventLandingPage.class );
                startActivity(intent);
                Animatoo.INSTANCE.animateSlideUp(MainActivity.this);
            }
        });


    }




    public void setIntent(String key, String value){
        Intent intent = new Intent(MainActivity.this, list_view_site_touriste.class);
        intent.putExtra(key, value);
        startActivity(intent);
    }
}