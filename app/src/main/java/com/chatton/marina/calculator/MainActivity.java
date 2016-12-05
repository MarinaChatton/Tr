package com.chatton.marina.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, OnStandardButtonClickListener, OnScientificButtonClickListener, OnErrorListener {
    private Calculator calculator = new Calculator();
    private boolean replace = true;
    private TextView display;
    private StandardFragment standardFragment;
    private ScientificFragment scientificFragment;

    private final static String WRONG_RESULT = "null";//String.valueOf(null)
    private final static String DEFAULT_DISPLAY = "0";
    private final static String ERROR_DISPLAY = "Error";
    private final static String DECIMAL_SEPARATOR = ".";

    //bundle keys
    private final static String VALUE1 = "value1";
    private final static String VALUE2 = "value2";
    private final static String OPERATOR = "operator";
    private final static String SAVED_OPERATOR = "savedOperator";
    private final static String SAVED_VALUE = "savedValue";
    private final static String DISPLAY = "display";
    private final static String REPLACE = "replace";

    private String getDisplay() {
        return display.getText().toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculator.setOnErrorListener(MainActivity.this);

        display = (TextView) findViewById(R.id.display);

        standardFragment = (StandardFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_standard);
        standardFragment.setOnStandardButtonClickListener(MainActivity.this);

        scientificFragment = (ScientificFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_scientific);
        if (scientificFragment != null) {
            scientificFragment.setOnScientificButtonClickListener(MainActivity.this);
        }

        initButtons();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putBoolean(REPLACE, replace);
        savedInstanceState.putString(DISPLAY, display.getText().toString());
        savedInstanceState.putDouble(VALUE1, calculator.getValue1());
        savedInstanceState.putDouble(VALUE2, calculator.getValue2());
        savedInstanceState.putSerializable(OPERATOR, calculator.getOperator());
        if(calculator.getStoredOperation()!=null) {
            savedInstanceState.putSerializable(SAVED_OPERATOR, (Serializable) calculator.getStoredOperation()[1]);
            savedInstanceState.putSerializable(SAVED_VALUE, (Serializable) calculator.getStoredOperation()[0]);
        }else{
            savedInstanceState.putSerializable(SAVED_OPERATOR, null);
            savedInstanceState.putSerializable(SAVED_VALUE, null);
        }
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        replace = savedInstanceState.getBoolean(REPLACE);
        display.setText(savedInstanceState.getString(DISPLAY));
        calculator.setValue1(savedInstanceState.getDouble(VALUE1));
        calculator.setValue2(savedInstanceState.getDouble(VALUE2));
        calculator.setOperator((Operator) savedInstanceState.getSerializable(OPERATOR));
        if(savedInstanceState.getSerializable(SAVED_VALUE)!=null && savedInstanceState.getSerializable(SAVED_OPERATOR)!=null) {
            calculator.setStoredOperation(new Object[]{savedInstanceState.getSerializable(SAVED_VALUE), savedInstanceState.getSerializable(SAVED_OPERATOR)});
        }
    }

    public void initButtons() {
        int[] idList = {
                R.id.clear,
                R.id.equal
        };

        for (int id : idList) {
            final Button button = (Button) findViewById(id);
            button.setOnClickListener(this);
        }
    }


    @Override
    public void onClick(View v) {
        String tag = v.getTag().toString();
        if (tag.equals(getResources().getString(R.string.tag_num_key))) {
            numKeyOnClick(((Button) v).getText().toString());
        } else if (tag.equals(getResources().getString(R.string.tag_operator))) {
            operatorOnClick(v.getId());
        } else if (tag.equals(getResources().getString(R.string.tag_decimal_separator))) {
            decimalSeparatorOnClick();
        } else if (tag.equals(getResources().getString(R.string.tag_clear))) {
            clearOnClick();
        } else if (tag.equals(getResources().getString(R.string.tag_func))) {
            funcOnClick(v.getId());
        }
    }

    public void clearOnClick() {
        calculator.reset();
        display.setText(DEFAULT_DISPLAY);
        replace = true;
    }

    public void numKeyOnClick(String keyValue) {
        updateDisplay(keyValue);
        replace = false;
    }

    public void decimalSeparatorOnClick() {
        appendElementDisplay(DECIMAL_SEPARATOR);
        replace = false;
    }

    public void operatorOnClick(int id) {
        if (correctInput(getDisplay())) {
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
                case R.id.operator_pow:
                    operator = Operator.POW;
                    break;
                default:
                    operator = Operator.NONE;
                    break;
            }
            setValue2(getDisplay());
            Double result = calculator.getResult(operator);
            replaceDisplay(String.valueOf(result));
            replace = true;
        }
    }

    public void funcOnClick(int id) {
        if (correctInput(getDisplay())) {
            Function function;
            switch (id) {
                case R.id.func_sin:
                    function = Function.SIN;
                    break;
                case R.id.func_cos:
                    function = Function.COS;
                    break;
                case R.id.func_tan:
                    function = Function.TAN;
                    break;
                case R.id.func_asin:
                    function = Function.ASIN;
                    break;
                case R.id.func_acos:
                    function = Function.ACOS;
                    break;
                case R.id.func_atan:
                    function = Function.ATAN;
                    break;
                case R.id.func_exp:
                    function = Function.EXP;
                    break;
                case R.id.func_ten_pow:
                    function = Function.TENPOW;
                    break;
                case R.id.func_inv:
                    function = Function.INV;
                    break;
                case R.id.func_ln:
                    function = Function.LN;
                    break;
                case R.id.func_log:
                    function = Function.LOG;
                    break;
                case R.id.func_sqrt:
                    function = Function.SQRT;
                    break;
                case R.id.func_sign:
                    function = Function.SIGN;
                    break;
                case R.id.func_pow2:
                    function = Function.POW2;
                    break;
                case R.id.func_pow3:
                    function = Function.POW3;
                    break;
                default:
                    function = null;
                    break;
            }
            setValue2(getDisplay());
            Double newValue2 = calculator.func(function);
            replaceDisplay(String.valueOf(newValue2));
            replace = true;
        }
    }

    public void appendElementDisplay(String string) {
        String text = getDisplay();
        String newText = text + string;
        if (correctInput(newText)) {
            text = newText;
        }
        display.setText(text);
    }

    public void replaceDisplay(String string) {
        String newDisplay;
        if (string.equals(WRONG_RESULT)) {
            newDisplay = ERROR_DISPLAY;
        } else {
            //keep only the integer part if the decimal part is made of zeros
            StringTokenizer tokenizer = new StringTokenizer(string, DECIMAL_SEPARATOR);
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
        return input.matches("-?[0-9]{1,}\\.?[0-9]*");
    }

    public void setValue2(String displayString) {
        if (correctInput(displayString)) {
            calculator.setValue2(Double.parseDouble(displayString));
        }
    }

    @Override
    public void onStandardButtonClick(View v) {
        onClick(v);
    }

    @Override
    public void onScientificButtonClick(View v) {
        onClick(v);
    }

    @Override
    public void displayError(Error error) {
        String errorMessage;
        switch (error){
            case DIVIDE_BY_ZERO:
                errorMessage = "You cannot divide by zero";
                break;
            case GREATER_THAN_ABS_ONE:
                errorMessage = "The absolute value must be less than one";
                break;
            case NEGATIVE:
                errorMessage = "The value must be positive or null";
                break;
            case NEGATIVE_OR_NULL:
                errorMessage = "The value must be greater than zero";
                break;
            default:
                errorMessage = "Unknown error";
                break;
        }
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }
}