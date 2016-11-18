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
     double value = 0;
     Operator operator = Operator.none;

    public void reset(){
        value = 0;
        operator = Operator.none;
    }

    public double calculate(Operator operator, String stringValue){
        double value2 = Double.parseDouble(stringValue);
        switch (this.operator){
            case plus:
                value+=value2;
                break;
            case minus:
                value-=value2;
                break;
            case multiply:
                value*=value2;
                break;
            case divide:
                value/=value2;
                break;
            case none:
                value=value2;
        }
        this.operator = operator;
        return value;
    }
}