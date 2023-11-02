package com.example.caculatorgpt;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView display;
    private StringBuilder currentInput = new StringBuilder();
    private double operand1 = 0;
    private String operator = "";
    private boolean isOperatorClicked = false;

    private Button button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
    private Button buttonAdd, buttonSubtract, buttonMultiply, buttonDivide, buttonEquals, buttonClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = findViewById(R.id.display);

        // Initialize buttons and set click listeners
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);

        buttonAdd = findViewById(R.id.buttonAdd);
        buttonSubtract = findViewById(R.id.buttonSubtract);
        buttonMultiply = findViewById(R.id.buttonMultiply);
        buttonDivide = findViewById(R.id.buttonDivide);

        buttonEquals = findViewById(R.id.buttonEquals);
        buttonClear = findViewById(R.id.buttonClear);

        // Set click listeners for number buttons
        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNumberButtonClick(view);
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNumberButtonClick(view);
            }
        });
        // Repeat for buttons 2 to 9

        // Set click listeners for operator buttons
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onOperatorButtonClick(view);
            }
        });
        buttonSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onOperatorButtonClick(view);
            }
        });
        buttonMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onOperatorButtonClick(view);
            }
        });
        buttonDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onOperatorButtonClick(view);
            }
        });

        // Set click listeners for equals and clear buttons
        buttonEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onEqualsButtonClick(view);
            }
        });
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClearButtonClick(view);
            }
        });
    }

    public void onNumberButtonClick(View view) {
        Button button = (Button) view;
        String buttonText = button.getText().toString();
        currentInput.append(buttonText);
        updateDisplay();
    }

    public void onOperatorButtonClick(View view) {
        if (!currentInput.toString().isEmpty() && !isOperatorClicked) {
            operand1 = Double.parseDouble(currentInput.toString());
            operator = ((Button) view).getText().toString();
            currentInput.setLength(0);
            isOperatorClicked = true;
        }
    }

    public void onEqualsButtonClick(View view) {
        if (!currentInput.toString().isEmpty() && isOperatorClicked) {
            double operand2 = Double.parseDouble(currentInput.toString());
            double result = performOperation(operand1, operand2, operator);
            currentInput.setLength(0);
            currentInput.append(result);
            updateDisplay();
            isOperatorClicked = false;
        }
    }

    public void onClearButtonClick(View view) {
        currentInput.setLength(0);
        updateDisplay();
    }

    private double performOperation(double operand1, double operand2, String operator) {
        switch (operator) {
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "*":
                return operand1 * operand2;
            case "/":
                if (operand2 == 0) {
                    return Double.NaN; // Handle division by zero
                }
                return operand1 / operand2;
            default:
                return Double.NaN; // Handle unknown operator
        }
    }

    private void updateDisplay() {
        display.setText(currentInput.toString());
    }
}

