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

    public double sin(double value2){
        return Math.sin(value2);
    }

    public double cos(double value2){
        return Math.cos(value2);
    }

    public double tan(double value2){
        return Math.tan(value2);
    }

    public Double asin(double value2){
        if(Math.abs(value2)>1){
            return null;
        }
        return Math.asin(value2);
    }

    public Double acos(double value2){
        if(Math.abs(value2)>1){
            return null;
        }
        return Math.acos(value2);
    }

    public double atan(double value2){
        return Math.atan(value2);
    }

    public double exp(double value2){
        return Math.exp(value2);
    }

    public Double ln(double value2){
        if(value2<=0){
            return null;
        }
        return Math.log(value2);
    }

    public Double log(double value2){
        if(value2<=0){
            return null;
        }
        return Math.log10(value2);
    }

    public Double sqrt(double value2){
        if(value2<0){
            return null;
        }
        return Math.sqrt(value2);
    }

    public double pow2(double value2){
        return Math.pow(value2, 2);
    }

    public double pow3(double value2){
        return Math.pow(value2, 3);
    }

    public Double inv(double value2){
        if(value2==0){
            return null;
        }
        return 1/value2;
    }

    public double tenPow(double value2){
        return Math.pow(10, value2);
    }

    public double pow(double value1, double value2){
        return Math.pow(value1, value2);
    }

    public double sign(double value2){
        if(value2<=0){
            return Math.abs(value2);
        }else{
            return 0-value2;
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

    public Double func(Function function, String stringValue) {
        Double result = null;
        if (stringValue.matches("-?[0-9]{1,}\\.?[0-9]*")) {
            double value2 = Double.parseDouble(stringValue);
            switch (function) {
                case SIN:
                    result = sin(value2);
                    break;
                case COS:
                    result = cos(value2);
                    break;
                case TAN:
                    result = tan(value2);
                    break;
                case ASIN:
                    result = asin(value2);
                    break;
                case ACOS:
                    result = acos(value2);
                    break;
                case ATAN:
                    result = atan(value2);
                    break;
                case EXP:
                    result = exp(value2);
                    break;
                case TENPOW:
                    result = tenPow(value2);
                    break;
                case INV:
                    result = inv(value2);
                    break;
                case LN:
                    result = ln(value2);
                    break;
                case LOG:
                    result = log(value2);
                    break;
                case SQRT:
                    result = sqrt(value2);
                    break;
                case SIGN:
                    result = sign(value2);
                    break;
                case POW2:
                    result = pow2(value2);
                    break;
                case POW3:
                    result = pow3(value2);
                    break;
                default:
                    result = null;
                    break;
            }
        }
        return result;
    }
}