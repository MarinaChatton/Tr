package com.chatton.marina.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

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

        //todo void initButtons(View view)
        Button clear = (Button) findViewById(R.id.clear);
        Button operatorPlus = (Button) findViewById(R.id.operator_plus);
        Button operatorMin = (Button) findViewById(R.id.operator_min);
        Button operatorDiv = (Button) findViewById(R.id.operator_div);
        Button operatorMult = (Button) findViewById(R.id.operator_mult);
        Button equal = (Button) findViewById(R.id.equal);
        Button decimalSeparator = (Button) findViewById(R.id.decimal_separator);
        //digit buttons:
        int[] idList = {R.id.num0, R.id.num1, R.id.num2, R.id.num3, R.id.num4, R.id.num5, R.id.num6, R.id.num7, R.id.num8, R.id.num9};
        for (int id : idList) {
            final Button button = (Button) findViewById(id);
            //button.setOnClickListener(this); //si initButtons()
            //view.setOnClickListener(this); //si initButtons(view)
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
            Operator operator = Operator.plus;

            @Override
            public void onClick(View v) {
                Double result = calculator.getResult(operator, display.getText().toString());
                replaceDisplay(result);
                replace = true;
            }
        });

        operatorMin.setOnClickListener(new View.OnClickListener() {
            Operator operator = Operator.minus;

            @Override
            public void onClick(View v) {
                Double result = calculator.getResult(operator, display.getText().toString());
                replaceDisplay(result);
                replace = true;
            }
        });

        operatorDiv.setOnClickListener(new View.OnClickListener() {
            Operator operator = Operator.divide;

            @Override
            public void onClick(View v) {
                Double result = calculator.getResult(operator, display.getText().toString());
                replaceDisplay(result);
                replace = true;
            }
        });

        operatorMult.setOnClickListener(new View.OnClickListener() {
            Operator operator = Operator.multiply;

            @Override
            public void onClick(View v) {
                Double result = calculator.getResult(operator, display.getText().toString());
                replaceDisplay(result);
                replace = true;
            }
        });

        equal.setOnClickListener(new View.OnClickListener() {
            Operator operator = Operator.none;

            @Override
            public void onClick(View v) {
                Double result = calculator.getResult(operator, display.getText().toString());
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
//            //todo : replace with tokenizer
//            ////todo pass num as string
//            StringTokenizer tokenizer = new StringTokenizer(String.valueOf(num), ".");
//            String firstToken = tokenizer.nextToken();
//            String secondToken = tokenizer.nextToken();
//            if(secondToken.matches("0+")){
//                display.setText(firstToken);
//            }else{
//                display.setText(String.valueOf(num));
//            }

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