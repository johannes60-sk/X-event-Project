package com.example.arroundme.modele;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.arroundme.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ViewHolder extends RecyclerView.ViewHolder {

    public TextView place_name;
    public TextView distance;
    public TextView categorie_name;
    public ConstraintLayout categorieDrawer;
    public CircleImageView circleImageView;
    public ImageView iv_view_detail_site_touristque;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        place_name = itemView.findViewById(R.id.place_name);
        categorieDrawer = itemView.findViewById(R.id.drawerItem);
        categorie_name = itemView.findViewById(R.id.categorie_name);
        distance = itemView.findViewById(R.id.distance);
        circleImageView = itemView.findViewById(R.id.circleImageView);
        iv_view_detail_site_touristque = itemView.findViewById(R.id.iv_view_detail_site_touristque);
    }
}
