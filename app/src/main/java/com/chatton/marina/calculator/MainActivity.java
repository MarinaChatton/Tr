package com.chatton.marina.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Calculator calculator = new Calculator();
    DisplayManager displayManager = new DisplayManager();
    boolean replace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView display = (TextView) findViewById(R.id.display);
        Button clear = (Button) findViewById(R.id.clear);
        Button operatorPlus = (Button) findViewById(R.id.operator_plus);
        Button operatorMin = (Button) findViewById(R.id.operator_min);
        Button operatorDiv = (Button) findViewById(R.id.operator_div);
        Button operatorMult = (Button) findViewById(R.id.operator_mult);
        Button equal = (Button) findViewById(R.id.equal);
        Button decimalSeparator = (Button) findViewById(R.id.decimal_separator);
        Button num0 = (Button) findViewById(R.id.num0);
        Button num1 = (Button) findViewById(R.id.num1);
        Button num2 = (Button) findViewById(R.id.num2);
        Button num3 = (Button) findViewById(R.id.num3);
        Button num4 = (Button) findViewById(R.id.num4);
        Button num5 = (Button) findViewById(R.id.num5);
        Button num6 = (Button) findViewById(R.id.num6);
        Button num7 = (Button) findViewById(R.id.num7);
        Button num8 = (Button) findViewById(R.id.num8);
        Button num9 = (Button) findViewById(R.id.num9);

        replace = true;

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.reset();
                resetDisplay(display);
                replace = true;
            }
        });

        operatorPlus.setOnClickListener(new View.OnClickListener() {
            Calculator.Operator operator = Calculator.Operator.plus;
            @Override
            public void onClick(View v) {
                calculator.setValue(display.getText().toString());
                double result = calculator.calculate(operator);
                replaceDisplay(display, result);
                replace = true;
            }
        });

        operatorMin.setOnClickListener(new View.OnClickListener() {
            String symbol = "-";
            Calculator.Operator operator = Calculator.Operator.minus;
            @Override
            public void onClick(View v) {
                calculator.setValue(display.getText().toString());
                double result = calculator.calculate(operator);
                replaceDisplay(display, result);
                replace = true;
            }
        });

        operatorDiv.setOnClickListener(new View.OnClickListener() {
            Calculator.Operator operator = Calculator.Operator.divide;
            @Override
            public void onClick(View v) {
                calculator.setValue(display.getText().toString());
                double result = calculator.calculate(operator);
                replaceDisplay(display, result);
                replace = true;
            }
        });

        operatorMult.setOnClickListener(new View.OnClickListener() {
            Calculator.Operator operator = Calculator.Operator.multiply;
            @Override
            public void onClick(View v) {
                calculator.setValue(display.getText().toString());
                double result = calculator.calculate(operator);
                replaceDisplay(display, result);
                replace = true;
            }
        });

        equal.setOnClickListener(new View.OnClickListener() {
            Calculator.Operator operator = Calculator.Operator.none;
            @Override
            public void onClick(View v) {
                calculator.setValue(display.getText().toString());
                double result = calculator.calculate(operator);
                replaceDisplay(display, result);
                replace = true;
            }
        });

        decimalSeparator.setOnClickListener(new View.OnClickListener() {
            String symbol = ".";
            @Override
            public void onClick(View v) {
                appendElementDisplay(display, symbol);
                replace = false;
            }
        });

        num0.setOnClickListener(new View.OnClickListener() {
            int value = 0;
            @Override
            public void onClick(View v) {
                updateDisplay(display, value);
                replace = false;
            }
        });

        num1.setOnClickListener(new View.OnClickListener() {
            int value = 1;
            @Override
            public void onClick(View v) {
                updateDisplay(display, value);
                replace = false;
            }
        });

        num2.setOnClickListener(new View.OnClickListener() {
            int value = 2;
            @Override
            public void onClick(View v) {
                updateDisplay(display, value);
                replace = false;
            }
        });

        num3.setOnClickListener(new View.OnClickListener() {
            int value = 3;
            @Override
            public void onClick(View v) {
                updateDisplay(display, value);
                replace = false;
            }
        });

        num4.setOnClickListener(new View.OnClickListener() {
            int value = 4;
            @Override
            public void onClick(View v) {
                updateDisplay(display, value);
                replace = false;
            }
        });

        num5.setOnClickListener(new View.OnClickListener() {
            int value = 5;
            @Override
            public void onClick(View v) {
                updateDisplay(display, value);
                replace = false;
            }
        });

        num6.setOnClickListener(new View.OnClickListener() {
            int value = 6;
            @Override
            public void onClick(View v) {
                updateDisplay(display, value);
                replace = false;
            }
        });

        num7.setOnClickListener(new View.OnClickListener() {
            int value = 7;
            @Override
            public void onClick(View v) {
                updateDisplay(display, value);
                replace = false;
            }
        });

        num8.setOnClickListener(new View.OnClickListener() {
            int value = 8;
            @Override
            public void onClick(View v) {
                updateDisplay(display, value);
                replace = false;
            }
        });

        num9.setOnClickListener(new View.OnClickListener() {
            int value = 9;
            @Override
            public void onClick(View v) {
                updateDisplay(display, value);
                replace = false;
            }
        });
    }

    public void resetDisplay(TextView textView) {
        textView.setText("0");
        replace = true;
    }

    public void appendElementDisplay(TextView textView, String string) {
        String text = textView.getText().toString();
        String newText = text+string;
        if(correctInput(newText)){
            text = newText;
        }
        textView.setText(text);
    }

    public void appendElementDisplay(TextView textView, int integer) {
        String text = textView.getText().toString();
        String newText = text+String.valueOf(integer);
        if(correctInput(newText)){
            text = newText;
        }
        textView.setText(text);
    }

    public void replaceDisplay(TextView textView, int integer) {
        textView.setText(String.valueOf(integer));
    }

    public void replaceDisplay(TextView textView, String symbol){
        textView.setText(symbol);
    }

    public void replaceDisplay(TextView textView, double num) {
        int numInt = (int)num;
        if(num-numInt==0){
            textView.setText(String.valueOf(numInt));
        }else{
            textView.setText(String.valueOf(num));
        }
    }

    public void updateDisplay(TextView textView, int integer) {
        if (replace) {
            replaceDisplay(textView, integer);
        } else {
            appendElementDisplay(textView, integer);
        }
    }

    public boolean correctInput(String input){
        return input.matches("-?[0-9]{1,}\\.?[0-9]*");
    }
}
