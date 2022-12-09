package com.example.arroundme;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class modalDescriptionSIteTouristique extends AppCompatActivity {

    private ImageView iv_modal_view_detail_site_touristique;
    private BottomSheetDialog bottomSheetDialog;
    private TextView source;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modal_description_site_touristique);

        iv_modal_view_detail_site_touristique = findViewById(R.id.iv_modal_view_detail_site_touristique);
        source = findViewById(R.id.source);

        Bundle extras = getIntent().getExtras();

        source.setText(extras.getString("title"));
        Glide.with(modalDescriptionSIteTouristique.this)
                .load(extras.getString("thumb_image"))
                .into(iv_modal_view_detail_site_touristique);


        bottomSheetDialog = new BottomSheetDialog(modalDescriptionSIteTouristique.this,R.style.BottomSheetTheme);
        View sheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_modal_description_site_touristique,null);

        bottomSheetDialog.setContentView(sheetView);
        bottomSheetDialog.show();

    }
}