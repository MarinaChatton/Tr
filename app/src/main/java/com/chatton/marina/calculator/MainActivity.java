package com.chatton.marina.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Calculator calculator = new Calculator();
    private boolean replace;
    private TextView display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        replace = true;

        display = (TextView) findViewById(R.id.display);

        Button clear = (Button) findViewById(R.id.clear);
        Button operatorPlus = (Button) findViewById(R.id.operator_plus);
        Button operatorMin = (Button) findViewById(R.id.operator_min);
        Button operatorDiv = (Button) findViewById(R.id.operator_div);
        Button operatorMult = (Button) findViewById(R.id.operator_mult);
        Button equal = (Button) findViewById(R.id.equal);
        Button decimalSeparator = (Button) findViewById(R.id.decimal_separator);
        //digit buttons:
        int[] idList = {R.id.num0, R.id.num1, R.id.num2, R.id.num3, R.id.num4, R.id.num5, R.id.num6, R.id.num7, R.id.num8, R.id.num9};
        for (int i = 0; i < idList.length; i++) {
            final Button button = (Button) findViewById(idList[i]);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateDisplay(button.getText().toString());
                    replace = false;
                }
            });
        }

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.reset();
                resetDisplay();
                replace = true;
            }
        });

        operatorPlus.setOnClickListener(new View.OnClickListener() {
            Calculator.Operator operator = Calculator.Operator.plus;

            @Override
            public void onClick(View v) {
                Double result = calculator.calculate(operator, display.getText().toString());
                replaceDisplay(result);
                replace = true;
            }
        });

        operatorMin.setOnClickListener(new View.OnClickListener() {
            Calculator.Operator operator = Calculator.Operator.minus;

            @Override
            public void onClick(View v) {
                Double result = calculator.calculate(operator, display.getText().toString());
                replaceDisplay(result);
                replace = true;
            }
        });

        operatorDiv.setOnClickListener(new View.OnClickListener() {
            Calculator.Operator operator = Calculator.Operator.divide;

            @Override
            public void onClick(View v) {
                Double result = calculator.calculate(operator, display.getText().toString());
                replaceDisplay(result);
                replace = true;
            }
        });

        operatorMult.setOnClickListener(new View.OnClickListener() {
            Calculator.Operator operator = Calculator.Operator.multiply;

            @Override
            public void onClick(View v) {
                Double result = calculator.calculate(operator, display.getText().toString());
                replaceDisplay(result);
                replace = true;
            }
        });

        equal.setOnClickListener(new View.OnClickListener() {
            Calculator.Operator operator = Calculator.Operator.none;

            @Override
            public void onClick(View v) {
                Double result = calculator.calculate(operator, display.getText().toString());
                replaceDisplay(result);
                replace = true;
            }
        });

        decimalSeparator.setOnClickListener(new View.OnClickListener() {
            String symbol = ".";

            @Override
            public void onClick(View v) {
                appendElementDisplay(symbol);
                replace = false;
            }
        });

    }

    public void resetDisplay() {
        display.setText("0");
    }

    public void appendElementDisplay(String string) {
        String text = display.getText().toString();
        String newText = text + string;
        if (correctInput(newText)) {
            text = newText;
        }
        display.setText(text);
    }

    public void replaceDisplay(Double num) {
        if (num == null) {
            display.setText("Error");
        } else {
            int numInt = (int) num.doubleValue();
            if (num - numInt == 0) {
                display.setText(String.valueOf(numInt));
            } else {
                display.setText(String.valueOf(num));
            }
        }
    }

    public void replaceDisplay(String string) {
        display.setText(string);
    }

    public void updateDisplay(String string) {
        if (replace) {
            replaceDisplay(string);
        } else {
            appendElementDisplay(string);
        }
    }

    public boolean correctInput(String input) {
        return input.matches("[0-9]{1,}\\.?[0-9]*");
    }
}