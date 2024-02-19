//package com.example.myfirstapplication;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.google.android.material.textfield.TextInputEditText;
//
//public class CalcActivity extends AppCompatActivity {
//    TextInputEditText in1, in2;
//    Button plus, minus, devide, multiply, prozent,  sq, power,equal;
//    TextView answer,action;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_calc);
//        in1 = findViewById(R.id.in1);
//        in2 = findViewById(R.id.in2);
//
//        plus = findViewById(R.id.plus);
//        minus = findViewById(R.id.minus);
//        devide = findViewById(R.id.devide);
//        multiply = findViewById(R.id.multiply);
//        prozent = findViewById(R.id.prozent);
//        sq = findViewById(R.id.sq);
//        power = findViewById(R.id.power);
//        equal = findViewById(R.id.equal);
//        answer = findViewById(R.id.answer);
//        action = findViewById(R.id.action);
//
//
//
//        plus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//            action.setText("+");
//            }
//        });
//        minus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                action.setText("-");
//            }
//        });
//        devide.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                action.setText("/");
//            }
//        });
//        prozent.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                action.setText("%");
//            }
//        });
//        sq.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                action.setText("sq");
//            }
//        });
//        multiply.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                action.setText("*");
//            }
//        });
//        power.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                action.setText("^");
//            }
//        });
//
//
//
//
//        equal.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                try {
//                    String t_in1 = in1.getText().toString();
//                    String t_in2 = in2.getText().toString();
//                    double number1 = Double.parseDouble(t_in1);
//                    double number2 = Double.parseDouble(t_in2);
//                    double sum = 0;
//                    String ac = action.getText().toString();
//                    if (ac=="+"){
//                        sum = number1 + number2;
//                    } else if (ac=="-") {
//                        sum = number1 - number2;
//                    } else if (ac=="/") {
//                        sum = number1 / number2;
//                    } else if (ac=="*") {
//                        sum = number1 * number2;
//                    } else if (ac=="sq") {
//                        sum = Math.pow(number1, 1/number2);
//                    } else if (ac=="^") {
//                        sum = Math.pow(number1, number2);
//                    } else if (ac=="%") {
//                        sum = number1 % number2;
//                    } else {
//                        sum = 0;
//                    }
//
//
//                    answer.setText(String.valueOf(sum));
//
//                } catch (NumberFormatException e) {
//                    Toast.makeText(CalcActivity.this, "Please enter valid numbers", Toast.LENGTH_SHORT).show();
//                }
//
//
//
//
//            }
//        });
//
//
//
//
//
//
//    }
//}