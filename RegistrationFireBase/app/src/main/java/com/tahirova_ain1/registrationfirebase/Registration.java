package com.tahirova_ain1.registrationfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.tahirova_ain1.registrationfirebase.databinding.RegistrationBinding;

public class Registration extends AppCompatActivity {
    private Firebase firebase;
    RegistrationBinding binding;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.registration);

        mAuth = FirebaseAuth.getInstance();

        binding.tvRegistration.setOnClickListener(v1 -> {
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        });

        binding.btnReg.setOnClickListener(v2 -> {
            binding.progressBar.setVisibility(View.VISIBLE);
            mAuth.createUserWithEmailAndPassword(binding.email.getText().toString(), binding.passwd.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            binding.progressBar.setVisibility(View.GONE);
                            if(task.isSuccessful()){
                                Toast.makeText(Registration.this, "Account has been successfully created!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), Login.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(Registration.this, "Oops! Authentication failed :(", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        });
    }
}