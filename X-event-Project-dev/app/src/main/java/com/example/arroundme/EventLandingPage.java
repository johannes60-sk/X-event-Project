package com.example.arroundme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

public class EventLandingPage extends AppCompatActivity {

    private TextView btnLandingPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_landing_page);

        btnLandingPage = findViewById(R.id.btnLandingPage);

        btnLandingPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EventLandingPage.this, EventHomePage.class);
                startActivity(intent);
                Animatoo.INSTANCE.animateSlideLeft(EventLandingPage.this);
            }
        });
    }
}