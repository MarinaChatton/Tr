package com.chatton.marina.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
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

        initButtons();
    }

    public void initButtons() {
        int[] idList = {
                R.id.num0,
                R.id.num1,
                R.id.num2,
                R.id.num3,
                R.id.num4,
                R.id.num5,
                R.id.num6,
                R.id.num7,
                R.id.num8,
                R.id.num9,
                R.id.decimal_separator,
                R.id.clear,
                R.id.equal,
                R.id.operator_plus,
                R.id.operator_min,
                R.id.operator_mult,
                R.id.operator_div
        };

        for (int id : idList) {
            final Button button = (Button) findViewById(id);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String tag = button.getTag().toString();
                    switch (tag) {
                        case "num_key":
                            numKeyOnClick(button);
                            break;
                        case "operator":
                            operatorOnClick(button);
                            break;
                        case "decimal_separator":
                            decimalSeparatorOnClick();
                            break;
                        case "clear":
                            clearOnClick();
                            break;
                        default:
                            break;
                    }
                }
            });
        }
    }

    public void clearOnClick() {
        calculator.reset();
        display.setText("0");
        replace = true;
    }

    public void numKeyOnClick(Button button) {
        updateDisplay(button.getText().toString());
        replace = false;
    }

    public void decimalSeparatorOnClick() {
        appendElementDisplay(".");
        replace = false;
    }

    public void operatorOnClick(Button button) {
        int id = button.getId();
        Operator operator;
        switch (id) {
            case R.id.equal:
                operator = Operator.none;
                break;
            case R.id.operator_plus:
                operator = Operator.plus;
                break;
            case R.id.operator_min:
                operator = Operator.minus;
                break;
            case R.id.operator_mult:
                operator = Operator.multiply;
                break;
            case R.id.operator_div:
                operator = Operator.divide;
                break;
            default:
                operator = Operator.none;
                break;
        }
        Double result = calculator.getResult(operator, display.getText().toString());
        replaceDisplay(String.valueOf(result));//String.valueOf(null)=> "null"
        replace = true;
    }

    public void appendElementDisplay(String string) {
        String text = display.getText().toString();
        String newText = text + string;
        if (correctInput(newText)) {
            text = newText;
        }
        display.setText(text);
    }

    public void replaceDisplay(String string) {
        String newDisplay;
        if (string.equals("null")) {
            newDisplay = "Error";
        } else {
            //keep only the integer part if the decimal part is made of zeros
            StringTokenizer tokenizer = new StringTokenizer(string, ".");
            ArrayList<String> tokens = new ArrayList<>();
            while (tokenizer.hasMoreTokens()) {
                tokens.add(tokenizer.nextToken());
            }
            if (tokens.size() == 2 && tokens.get(1).matches("0+")) {
                newDisplay = tokens.get(0);
            } else {
                newDisplay = string;
            }
        }
        display.setText(newDisplay);
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