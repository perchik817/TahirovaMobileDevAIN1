package com.tahirova_ain1.registrationfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.tahirova_ain1.registrationfirebase.databinding.ActivityLoginBinding;

public class Login extends AppCompatActivity {

    ActivityLoginBinding binding;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }

        binding.registerNow.setOnClickListener(v1 -> {
            Intent intent = new Intent(getApplicationContext(), Registration.class);
            startActivity(intent);
            finish();
        });
        binding.btnLogin.setOnClickListener(v2 -> {
            binding.progressBar.setVisibility(View.GONE);
            mAuth.signInWithEmailAndPassword(binding.email.getText().toString(), binding.passwd.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                binding.progressBar.setVisibility(View.GONE);
                                Toast.makeText(Login.this, "Login s", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                                finish();
                            }else {
                                Toast.makeText(Login.this, "Failed to login :(", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        });
    }
}