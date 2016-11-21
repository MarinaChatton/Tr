package com.chatton.marina.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.StringTokenizer;

import hugo.weaving.DebugLog;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Calculator calculator = new Calculator();
    private boolean replace = true;
    private TextView display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = (TextView) findViewById(R.id.display);

        initButtons();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putBoolean("replace", replace);
        savedInstanceState.putString("display", display.getText().toString());
        savedInstanceState.putDouble("value", calculator.getValue());
        savedInstanceState.putSerializable("operator", calculator.getOperator());
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        replace = savedInstanceState.getBoolean("replace");
        display.setText(savedInstanceState.getString("display"));
        calculator.setValue(savedInstanceState.getDouble("value"));
        calculator.setOperator((Operator) savedInstanceState.getSerializable("operator"));
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
            button.setOnClickListener(this);
        }
    }


    @Override
    public void onClick(View v) {
        String tag = v.getTag().toString();
        switch (tag) {
            case "num_key":
                numKeyOnClick((Button)v);
                break;
            case "operator":
                operatorOnClick((Button)v);
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
                operator = Operator.NONE;
                break;
            case R.id.operator_plus:
                operator = Operator.PLUS;
                break;
            case R.id.operator_min:
                operator = Operator.MINUS;
                break;
            case R.id.operator_mult:
                operator = Operator.MULTPILY;
                break;
            case R.id.operator_div:
                operator = Operator.DIVIDE;
                break;
            default:
                operator = Operator.NONE;
                break;
        }
        Double result = calculator.getResult(operator, display.getText().toString());
        replaceDisplay(String.valueOf(result));//String.valueOf(null)=> "null"
        replace = true;
    }

    @DebugLog
    public void appendElementDisplay(String string) {
        String text = display.getText().toString();
        String newText = text + string;
        if (correctInput(newText)) {
            text = newText;
        }
        display.setText(text);
    }

    @DebugLog
    public void replaceDisplay(String string) {
        String newDisplay;
        if ("null".equals(string)) {
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

    @DebugLog
    public void updateDisplay(String string) {
        if (replace) {
            replaceDisplay(string);
        } else {
            appendElementDisplay(string);
        }
    }

    public boolean correctInput(String input) {
        return input.matches("-?[0-9]{1,}\\.?[0-9]*");
    }
}