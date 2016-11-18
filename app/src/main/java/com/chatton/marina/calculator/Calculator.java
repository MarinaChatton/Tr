package com.chatton.marina.calculator;

import android.util.Log;
import android.widget.TextView;

/**
 * Created by Marina on 16/11/2016.
 */

public class Calculator {

    public enum Operator {
        plus,
        minus,
        multiply,
        divide,
        none
    }

    private double value = 0;
    private Operator operator = Operator.none;

    public Double incorrectValue() {
        reset();
        return null;
    }

    public void reset() {
        value = 0;
        operator = Operator.none;
    }

    public Double calculate(Operator operator, String stringValue) {
        if (stringValue.matches("-?[0-9]{1,}\\.?[0-9]*")) {
            Double value2 = Double.parseDouble(stringValue);
            switch (this.operator) {
                case plus:
                    value += value2;
                    break;
                case minus:
                    value -= value2;
                    break;
                case multiply:
                    value *= value2;
                    break;
                case divide:
                    if (value2 != 0) {
                        value /= value2;
                    } else {
                        return incorrectValue();
                    }
                    break;
                case none:
                    value = value2;
            }
            this.operator = operator;
            return value;
        } else {
            return incorrectValue();
        }
    }
}