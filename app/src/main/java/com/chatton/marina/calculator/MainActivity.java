package com.chatton.marina.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Calculator calculator = new Calculator();
    private boolean replace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        replace = true;

        final TextView display = (TextView) findViewById(R.id.display);

        Button clear = (Button) findViewById(R.id.clear);
        Button operatorPlus = (Button) findViewById(R.id.operator_plus);
        Button operatorMin = (Button) findViewById(R.id.operator_min);
        Button operatorDiv = (Button) findViewById(R.id.operator_div);
        Button operatorMult = (Button) findViewById(R.id.operator_mult);
        Button equal = (Button) findViewById(R.id.equal);
        Button decimalSeparator = (Button) findViewById(R.id.decimal_separator);
        //digit buttons:
        int[] idList = {R.id.num0,R.id.num1,R.id.num2,R.id.num3,R.id.num4,R.id.num5,R.id.num6,R.id.num7,R.id.num8,R.id.num9};
        for(int i = 0; i<idList.length; i++){
            final Button button = (Button)findViewById(idList[i]);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateDisplay(display, button.getText().toString());
                    replace = false;
                }
            });
        }

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
                Double result = calculator.calculate(operator, display.getText().toString());
                replaceDisplay(display, result);
                replace = true;
            }
        });

        operatorMin.setOnClickListener(new View.OnClickListener() {
            Calculator.Operator operator = Calculator.Operator.minus;

            @Override
            public void onClick(View v) {
                Double result = calculator.calculate(operator, display.getText().toString());
                replaceDisplay(display, result);
                replace = true;
            }
        });

        operatorDiv.setOnClickListener(new View.OnClickListener() {
            Calculator.Operator operator = Calculator.Operator.divide;

            @Override
            public void onClick(View v) {
                Double result = calculator.calculate(operator, display.getText().toString());
                replaceDisplay(display, result);
                replace = true;
            }
        });

        operatorMult.setOnClickListener(new View.OnClickListener() {
            Calculator.Operator operator = Calculator.Operator.multiply;

            @Override
            public void onClick(View v) {
                Double result = calculator.calculate(operator, display.getText().toString());
                replaceDisplay(display, result);
                replace = true;
            }
        });

        equal.setOnClickListener(new View.OnClickListener() {
            Calculator.Operator operator = Calculator.Operator.none;

            @Override
            public void onClick(View v) {
                Double result = calculator.calculate(operator, display.getText().toString());
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

    }

    public void resetDisplay(TextView textView) {
        textView.setText("0");
    }

    public void appendElementDisplay(TextView textView, String string) {
        String text = textView.getText().toString();
        String newText = text + string;
        if (correctInput(newText)) {
            text = newText;
        }
        textView.setText(text);
    }

    public void replaceDisplay(TextView textView, Double num) {
        if(num==null) {
            textView.setText("Error");
        }else{
            int numInt = (int) num.doubleValue();
            if (num - numInt == 0) {
                textView.setText(String.valueOf(numInt));
            } else {
                textView.setText(String.valueOf(num));
            }
        }
    }

    public void replaceDisplay(TextView textView, String string) {
        textView.setText(string);
    }

    public void updateDisplay(TextView textView, String string) {
        if (replace) {
            replaceDisplay(textView, string);
        } else {
            appendElementDisplay(textView, string);
        }
    }

    public boolean correctInput(String input) {
        return input.matches("[0-9]{1,}\\.?[0-9]*");
    }
}