package com.tahirova_ain1.simplecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView resultTv, solutionTv;
    MaterialButton buttonC, buttonBracketClose, buttonBracketOpen, buttonAc, buttonDote,
            buttonPlus, buttonDivide, buttonMinus, buttonMultiply, buttonEquals,
            button0, button1, button2, button3, button4, button5, button6, button7,
            button8, button9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTv = findViewById(R.id.result_tv);
        solutionTv = findViewById(R.id.solution_tv);
        buttonC = findViewById(R.id.button_c);
        buttonAc = findViewById(R.id.button_ac);
        buttonDote = findViewById(R.id.button_dote);
        buttonBracketClose = findViewById(R.id.button_close_bracket);
        buttonBracketOpen = findViewById(R.id.button_open_bracket);
        buttonPlus = findViewById(R.id.button_plus);
        buttonMinus = findViewById(R.id.button_minus);
        buttonMultiply = findViewById(R.id.button_multiply);
        buttonDivide = findViewById(R.id.button_divide);
        buttonEquals = findViewById(R.id.button_equals);
        button0 = findViewById(R.id.button_0);
        button1 = findViewById(R.id.button_1);
        button2 = findViewById(R.id.button_2);
        button3 = findViewById(R.id.button_3);
        button4 = findViewById(R.id.button_4);
        button5 = findViewById(R.id.button_5);
        button6 = findViewById(R.id.button_6);
        button7 = findViewById(R.id.button_7);
        button8 = findViewById(R.id.button_8);
        button9 = findViewById(R.id.button_9);

        // Установка слушателей для кнопок
        setClickListeners();
    }

    private void setClickListeners() {
        MaterialButton[] buttons = {
                buttonC, buttonAc, buttonDote, buttonBracketClose, buttonBracketOpen,
                buttonPlus, buttonDivide, buttonMinus, buttonMultiply, buttonEquals,
                button0, button1, button2, button3, button4, button5, button6, button7,
                button8, button9
        };

        for (MaterialButton button : buttons) {
            button.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        MaterialButton button = (MaterialButton) v;
        String buttonText = button.getText().toString();

        if (buttonText.equals("=")) {
            calculateAndDisplayResult();
        } else if (buttonText.equals("AC")) {
            clearAll();
        } else if (buttonText.equals("C")) {
            clearLastEntry();
        } else {
            appendToExpression(buttonText);
        }
    }

    private void calculateAndDisplayResult() {
        String expression = solutionTv.getText().toString();

        try {
            double result = calculateExpression(expression);
            // Проверяем, является ли результат целым числом
            if (result == (int) result) {
                resultTv.setText(String.valueOf((int) result)); // Выводим без десятичной части
            } else {
                resultTv.setText(String.valueOf(result));
            }
        } catch (Exception e) {
            resultTv.setText("Error");
        }
    }

    private double calculateExpression(String expression) {
        // Используем Rhino для вычисления выражения
        Context context = Context.enter();
        context.setOptimizationLevel(-1);
        Scriptable scriptable = context.initStandardObjects();
        String process = ""; // Создаем строку для записи процесса подсчета
        try {
            // Вычисляем результат и сохраняем процесс подсчета
            Object result = context.evaluateString(scriptable, expression, "JavaScript", 1, null);
            process = expression + " = " + Context.toString(result);
            return Double.parseDouble(Context.toString(result));
        } catch (Exception e) {
            // В случае ошибки записываем "Error" в процесс подсчета
            process = "Error";
            e.printStackTrace();
            return Double.NaN;
        } finally {
            // Освобождаем ресурсы
            Context.exit();
        }
    }

    private void clearAll() {
        solutionTv.setText("");
        resultTv.setText("0");
    }

    private void clearLastEntry() {
        String expression = solutionTv.getText().toString();
        if (!expression.isEmpty()) {
            if (expression.length() == 1 && expression.equals("0")) {
                clearAll();
            } else {
                expression = expression.substring(0, expression.length() - 1);
                solutionTv.setText(expression);
            }
        }
    }

    private void appendToExpression(String text) {
        String expression = solutionTv.getText().toString();
        if (expression.equals("0")) {
            expression = text; // Заменяем "0" на введенное число
        } else {
            expression += text;
        }
        solutionTv.setText(expression);
    }
}
