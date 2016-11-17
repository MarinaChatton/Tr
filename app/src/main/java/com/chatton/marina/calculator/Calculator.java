package com.chatton.marina.calculator;

import android.widget.TextView;

/**
 * Created by Marina on 16/11/2016.
 */

public class Calculator {

    public enum Operator{
        plus,
        minus,
        multiply,
        divide,
        none
    }
     double value1 = 0;
     double value2 = 0;
     Operator operator = Operator.none;

    public void reset(){
        value1 = 0;
        value2 = 0;
        operator = Operator.none;
    }

    public void setValue(String stringValue){
        double value = Double.parseDouble(stringValue);
        if(operator == Operator.none){
            value1 = value;
        }else{
            value2 = value;
        }

    }

    public double calculate(Operator operator){
        double result = value1;
        switch (this.operator){
            case plus:
                result = value1+value2;
                break;
            case minus:
                result = value1-value2;
                break;
            case multiply:
                result = value1*value2;
                break;
            case divide:
                result = value1/value2;
                break;
            case none:
                result = value1;
        }
        this.operator = operator;
        value1 = result;
        value2 = 0;
        return result;
    }
}
