package com.chatton.marina.calculator;

/**
 * Created by Marina on 16/11/2016.
 */

public class Calculator {

    //attributes set as static to handle rotation-triggered new call to onCreate() and "save" the values
    private static Double value = 0.0;
    private static Operator operator = Operator.none;

    public void reset() {
        value = 0.0;
        operator = Operator.none;
    }

    public double add(double value2) {
        return value + value2;
    }

    public double substract(double value2) {
        return value - value2;
    }

    public double multiply(double value2) {
        return value * value2;
    }

    public Double divide(double value2) {
        if (value2 == 0) {
            return null;
        } else {
            return value / value2;
        }
    }

    public Double calculate(String stringValue) {
        Double result = null;
        if (stringValue.matches("-?[0-9]{1,}\\.?[0-9]*")) {
            double value2 = Double.parseDouble(stringValue);
            switch (this.operator) {
                case plus:
                    result = add(value2);
                    break;
                case minus:
                    result = substract(value2);
                    break;
                case multiply:
                    result = multiply(value2);
                    break;
                case divide:
                    result = divide(value2);
                    break;
                case none:
                    result = value2;
                    break;
                default:
                    result = null;
                    break;
            }
        }
        return result;
    }

    public Double getResult(Operator operator, String stringValue) {
        Double result = calculate(stringValue);
        if (result == null) {
            reset();
        } else {
            value = result;
            this.operator = operator;
        }
        return result;
    }
}