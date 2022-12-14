package com.example.arroundme.otp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.arroundme.MainActivity;
import com.example.arroundme.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class otpConnexion extends AppCompatActivity {

    private boolean otpSent = false;
    private String countryCode = "+33";
    private String id = "";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        final EditText mobileET = findViewById(R.id.mobileET);
        final EditText otpET = findViewById(R.id.otpET);
        final Button actionBtn = findViewById(R.id.otpButton);

        FirebaseApp.initializeApp(this);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        actionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(otpSent){
                    final String getOTP = otpET.getText().toString();

                    if(id.isEmpty()){

                        Toast.makeText(otpConnexion.this, "Unable to verify otpConnexion", Toast.LENGTH_SHORT).show();
                    }
                    else{

                        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(id, getOTP);

                        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if(task.isSuccessful()){

                                    FirebaseUser userDetails = task.getResult().getUser();

                                    Toast.makeText(otpConnexion.this, "Verified", Toast.LENGTH_SHORT).show();

                                    Intent acceuilIntent = new Intent(otpConnexion.this, MainActivity.class);
                                    startActivity(acceuilIntent);
                                    Animatoo.INSTANCE.animateZoom(otpConnexion.this);
                                }
                                else{
                                    Toast.makeText(otpConnexion.this, "Something went wrong!!!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }
                else{
                    final String getMobile = mobileET.getText().toString();

                    PhoneAuthOptions options = PhoneAuthOptions.newBuilder(firebaseAuth)
                            .setPhoneNumber(countryCode+""+getMobile)
                            .setTimeout(60L, TimeUnit.SECONDS)
                            .setActivity(otpConnexion.this)
                            .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                @Override
                                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                    Toast.makeText(otpConnexion.this, "otpConnexion sent successfully", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onVerificationFailed(@NonNull FirebaseException e) {

                                    Toast.makeText(otpConnexion.this, "Something went wrong "+e.getMessage(), Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                    super.onCodeSent(s, forceResendingToken);
                                    otpET.setVisibility(View.VISIBLE);
                                    actionBtn.setText("Verify otpConnexion");
                                    id = s;
                                    otpSent = true;
                                }
                            }).build();

                    PhoneAuthProvider.verifyPhoneNumber(options);
                }



            }
        });
    }
}