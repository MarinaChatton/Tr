package com.chatton.marina.calculator;

/**
 * Created by Marina on 16/11/2016.
 */

public class Calculator {

    private Double value = 0.0;
    private Operator operator = Operator.NONE;

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public void reset() {
        value = 0.0;
        operator = Operator.NONE;
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
        if (value2 != 0) {
            return value / value2;
        } else {
            return null;
        }
    }

    public Double calculate(String stringValue) {
        Double result = null;
        if (stringValue.matches("-?[0-9]{1,}\\.?[0-9]*")) {
            double value2 = Double.parseDouble(stringValue);
            switch (this.operator) {
                case PLUS:
                    result = add(value2);
                    break;
                case MINUS:
                    result = substract(value2);
                    break;
                case MULTPILY:
                    result = multiply(value2);
                    break;
                case DIVIDE:
                    result = divide(value2);
                    break;
                case NONE:
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