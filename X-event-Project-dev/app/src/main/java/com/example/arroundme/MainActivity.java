package com.example.arroundme;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;
import static com.example.arroundme.R.id.homeLayout;
import static com.example.arroundme.R.id.list_view_musée;
import static com.example.arroundme.R.id.list_view_parc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.arroundme.maps.MapsActivity;

public class MainActivity extends AppCompatActivity implements HomeFragment.OnButtonClickedListener {

    CardView list_chateau, list_musée, list_parc;
    TextView place_name;
    ImageView block_destination_fav_image;
    TextView textChateaux;
    Button btnLogin, btnGoogle, maps;

    // number of selected  tab | we have 4 tabs so value must lie between 1-4 | default value is 1 because first tab is selected by default
    private int selectedTab = 1;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        block_destination_fav_image = findViewById(R.id.block_destination_fav_image);
//        btnLogin = findViewById(R.id.btn_login);
//        btnGoogle = findViewById(R.id.btn_google);
          maps = findViewById(R.id.maps);

        final LinearLayout homeLayout;
        final LinearLayout profileLayout ;
        final LinearLayout notificationLayout;
        final LinearLayout eventLayout;

        final ImageView homeImage;
        final ImageView eventImage;
        final ImageView notificationImage;
        final ImageView profileImage;

        final TextView homeTxt;
        final TextView eventTxt;
        final TextView notificationTxt;
        final TextView profileTxt;

        homeLayout = findViewById(R.id.homeLayout);
        profileLayout = findViewById(R.id.profileLayout);
        notificationLayout = findViewById(R.id.notificationLayout);
        eventLayout = findViewById(R.id.eventLayout);

        homeImage = findViewById(R.id.homeImage);
        eventImage = findViewById(R.id.eventImage);
        notificationImage = findViewById(R.id.notifcationImage);
        profileImage = findViewById(R.id.profileImage);

        homeTxt = findViewById(R.id.homeTxt);
        eventTxt = findViewById(R.id.eventTxt);
        notificationTxt = findViewById(R.id.notificationTxt);
        profileTxt = findViewById(R.id.notificationTxt);

//            block_destination_fav_image.setOnClickListener(new View.OnClickListener(){
//                @Override
//                public void onClick(View view) {
//
//                    Intent intent = new Intent(MainActivity.this, view_details_site_touristique.class);
////                intent.putExtra(key, value);
//                    startActivity(intent);
//                }
//            });

//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, ConnectionWithFacebook.class);
//                startActivity(intent);
//            }
//        });
//
//        btnGoogle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, ConnectionWithGoogle.class);
//                startActivity(intent);
//            }
//        });
//
            maps.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, MapsActivity.class );
                    startActivity(intent);
                    Animatoo.INSTANCE.animateSlideUp(MainActivity.this);
                }
            });



        // **************************     Navigation Bottom TabBar    **************************


        homeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // check if home is already selected or not
                if(selectedTab != 1){

                    // set home fragment by default
                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.fragmentContainer,HomeFragment.class,null)
                            .commit();

                    // unselect other tabs expect home tab
                    eventTxt.setVisibility(View.GONE);
                    notificationTxt.setVisibility(View.GONE);
                    profileTxt.setVisibility(View.GONE);

                    eventImage.setImageResource(R.drawable.baseline_event_18);
                    notificationImage.setImageResource(R.drawable.baseline_notifications_24);
                    profileImage.setImageResource(R.drawable.baseline_person_24);

                    eventLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    notificationLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    profileLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    // select home tab
                    homeTxt.setVisibility(View.VISIBLE);
                    homeImage.setImageResource(R.drawable.baseline_home_20_selected);
                    homeLayout.setBackgroundResource(R.drawable.round_back_home);

                    // create animation
                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f, 1.0f, 1f, 1f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    homeLayout.startAnimation(scaleAnimation);

                    // set first tab as selected tab
                    selectedTab = 1;
                }
            }
        });

        eventLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // check if like is already selected or not
                if(selectedTab != 2){

                    Intent intent = new Intent(MainActivity.this,EventLandingPage.class);
                    startActivity(intent);
                    Animatoo.INSTANCE.animateSlideUp(MainActivity.this);

                    // set Event fragment by default
//                    getSupportFragmentManager().beginTransaction()
//                            .setReorderingAllowed(true)
//                            .replace(R.id.fragmentContainerLandingPage,EventLandingPageFragment.class,null)
//                            .commit();

                    // unselect other tabs expect like tab
                    homeTxt.setVisibility(View.GONE);
                    notificationTxt.setVisibility(View.GONE);
                    profileTxt.setVisibility(View.GONE);

                    homeImage.setImageResource(R.drawable.baseline_home_20);
                    notificationImage.setImageResource(R.drawable.baseline_notifications_24);
                    profileImage.setImageResource(R.drawable.baseline_person_24);

                    homeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    notificationLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    profileLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    // select event tab
                    eventTxt.setVisibility(View.VISIBLE);
                    eventImage.setImageResource(R.drawable.baseline_event_18_selected);
                    eventLayout.setBackgroundResource(R.drawable.round_back_like);

                    // create animation
                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f, 1.0f, 1f, 1f, Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    eventLayout.startAnimation(scaleAnimation);

                    // set 2nd tab as selected tab
                    selectedTab = 2;
                }

            }
        });

        notificationLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // check if notification is already selected or not
                if(selectedTab != 3){

                    // set notification fragment by default
                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.fragmentContainer,NotificationFragment.class,null)
                            .commit();

                    // unselect other tabs expect notification tab
                    homeTxt.setVisibility(View.GONE);
                    eventTxt.setVisibility(View.GONE);
                    profileTxt.setVisibility(View.GONE);

                    homeImage.setImageResource(R.drawable.baseline_home_20);
                    eventImage.setImageResource(R.drawable.baseline_event_18);
                    profileImage.setImageResource(R.drawable.baseline_person_24);

                    homeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    eventLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    profileLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    // select notification tab
                    notificationTxt.setVisibility(View.VISIBLE);
                    notificationImage.setImageResource(R.drawable.baseline_notifications_selected_24);
                    notificationLayout.setBackgroundResource(R.drawable.round_back_notification);

                    // create animation
                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f, 1.0f, 1f, 1f, Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    notificationLayout.startAnimation(scaleAnimation);

                    // set 3rd tab as selected tab
                    selectedTab = 3;
                }
            }
        });

        profileLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // check if profile is already selected or not
                if(selectedTab != 4){

                    // set profile fragment by default
                    getSupportFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.fragmentContainer,profileFragment.class,null)
                            .commit();

                    // unselect other tabs expect profile tab
                    homeTxt.setVisibility(View.GONE);
                    eventTxt.setVisibility(View.GONE);
                    notificationTxt.setVisibility(View.GONE);

                    homeImage.setImageResource(R.drawable.baseline_home_20);
                    eventImage.setImageResource(R.drawable.baseline_event_18);
                    notificationImage.setImageResource(R.drawable.baseline_notifications_24);

                    homeLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    eventLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    notificationLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                    // select profile tab
                    profileTxt.setVisibility(View.VISIBLE);
                    profileImage.setImageResource(R.drawable.baseline_person_selected_24);
                    profileLayout.setBackgroundResource(R.drawable.round_back_profile);

                    // create animation
                    ScaleAnimation scaleAnimation = new ScaleAnimation(0.8f, 1.0f, 1f, 1f, Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
                    scaleAnimation.setDuration(200);
                    scaleAnimation.setFillAfter(true);
                    profileLayout.startAnimation(scaleAnimation);

                    // set 4th tab as selected tab
                    selectedTab = 4;
                }
            }
        });

    }

    public void setIntent(String key, String value){
        Intent intent = new Intent(MainActivity.this, list_view_site_touriste.class);
        intent.putExtra(key, value);
        startActivity(intent);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onButtonClicked(View view) {

        if(view.getId() == R.id.list_View_Chateau){

            setIntent("id_chateaux","list_View_Chateau");

        }else if(view.getId() == list_view_musée){

            setIntent("id_musée", "list_view_musée");

        }else if(view.getId() == list_view_parc){

            setIntent("id_parc","list_view_parc");

        }

    }

}