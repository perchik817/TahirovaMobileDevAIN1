package com.example.myfirstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button btnGen, calc;
    TextView response, answer;
    int answerInt, getAnswerInt;
    LottieAnimationView lottySun, lottyOne, lottyTwo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Random random = new Random();
        btnGen = findViewById(R.id.generate_btn);
        response = findViewById(R.id.response);
        answer = findViewById(R.id.main_answer);
        lottySun = findViewById(R.id.lotty_sun);
        lottyOne = findViewById(R.id.lotty_one);
        lottyTwo = findViewById(R.id.lotty_two);
        lottySun.setAnimation(R.raw.sunshine);
        lottyOne.setAnimation(R.raw.animation_fire);
        lottyTwo.setAnimation(R.raw.fun_time);

        lottyOne.setVisibility(View.INVISIBLE);
        lottyTwo.setVisibility(View.INVISIBLE);


        answerInt = random.nextInt(100);
        answerInt = getAnswerInt;
//        calc = findViewById(R.id.calculator);

        btnGen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                answerInt = random.nextInt(101);
                if(answerInt > 0){
                    printAnswer();
                    response.setText(String.valueOf(answerInt) + "%");
                    btnGen.setVisibility(View.INVISIBLE);
                }else {
                    Toast.makeText(MainActivity.this, "Press again", Toast.LENGTH_LONG).show();
                    btnGen.setVisibility(View.VISIBLE);
                }

            }


        });

//        calc.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, CalcActivity.class);
//                startActivity(intent);
//            }
//        });
    }

    private void printAnswer(){

        if(answerInt >= 1 && answerInt <= 48){
            answer.setText("Excellent. You buy the beer!");
            lottyTwo.setVisibility(View.VISIBLE);
        }
        else if(answerInt > 48 && answerInt <= 65){
            answer.setText("You are almost an optimist, dude)");
            lottyTwo.setVisibility(View.VISIBLE);
        } else if (answerInt > 65 && answerInt <= 100){
            answer.setText("This is cool)");
            lottyTwo.setVisibility(View.VISIBLE);
        }

    }
}