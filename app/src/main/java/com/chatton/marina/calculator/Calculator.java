package com.chatton.marina.calculator;

/**
 * Created by Marina on 16/11/2016.
 */

public class Calculator {
    private OnErrorListener onErrorListener;

    private Double value1 = 0.0;
    private Double value2 = 0.0;
    private Operator operator = Operator.NONE;
    private Object[] storedOperation = {0.0, Operator.NONE};

    public void setOnErrorListener(OnErrorListener onErrorListener) {
        this.onErrorListener = onErrorListener;
    }

    public Double getValue1() {
        return value1;
    }

    public void setValue1(Double value1) {
        this.value1 = value1;
    }

    public Double getValue2() {
        return value2;
    }

    public void setValue2(Double value2) {
        this.value2 = value2;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public Object[] getStoredOperation() {
        return storedOperation;
    }

    public void setStoredOperation(Object[] storedOperation) {
        this.storedOperation = storedOperation;
    }

    public void reset() {
        value1 = 0.0;
        value2 = 0.0;
        operator = Operator.NONE;
        storedOperation[0] = 0.0;
        storedOperation[1] = Operator.NONE;
    }

    public double add(double value1, double value2) {
        return value1 + value2;
    }

    public double substract(double value1, double value2) {
        return value1 - value2;
    }

    public double multiply(double value1, double value2) {
        return value1 * value2;
    }

    public Double divide(double value1, double value2) {
        if (value2 != 0) {
            return value1 / value2;
        } else {
            onErrorListener.displayError(Error.DIVIDE_BY_ZERO);
            return null;
        }
    }

    public double sin(double value2) {
        return Math.sin(value2);
    }

    public double cos(double value2) {
        return Math.cos(value2);
    }

    public double tan(double value2) {
        return Math.tan(value2);
    }

    public Double asin(double value2) {
        if (Math.abs(value2) > 1) {
            onErrorListener.displayError(Error.GREATER_THAN_ABS_ONE);
            return null;
        }
        return Math.asin(value2);
    }

    public Double acos(double value2) {
        if (Math.abs(value2) > 1) {
            onErrorListener.displayError(Error.GREATER_THAN_ABS_ONE);
            return null;
        }
        return Math.acos(value2);
    }

    public double atan(double value2) {
        return Math.atan(value2);
    }

    public double exp(double value2) {
        return Math.exp(value2);
    }

    public Double ln(double value2) {
        if (value2 <= 0) {
            onErrorListener.displayError(Error.NEGATIVE_OR_NULL);
            return null;
        }
        return Math.log(value2);
    }

    public Double log(double value2) {
        if (value2 <= 0) {
            onErrorListener.displayError(Error.NEGATIVE_OR_NULL);
            return null;
        }
        return Math.log10(value2);
    }

    public Double sqrt(double value2) {
        if (value2 < 0) {
            onErrorListener.displayError(Error.NEGATIVE);
            return null;
        }
        return Math.sqrt(value2);
    }

    public double pow2(double value2) {
        return Math.pow(value2, 2);
    }

    public double pow3(double value2) {
        return Math.pow(value2, 3);
    }

    public Double inv(double value2) {
        if (value2 == 0) {
            onErrorListener.displayError(Error.DIVIDE_BY_ZERO);
            return null;
        }
        return 1 / value2;
    }

    public double tenPow(double value2) {
        return Math.pow(10, value2);
    }

    public double pow(double value1, double value2) {
        return Math.pow(value1, value2);
    }

    public double sign(double value2) {
        if (value2 <= 0) {
            return Math.abs(value2);
        } else {
            return 0 - value2;
        }
    }

    public Double calculate(Double value1, Operator operator, Double value2) {
        Double result = null;
        if (value1 != null && value2 != null) {
            switch (operator) {
                case PLUS:
                    result = add(value1, value2);
                    break;
                case MINUS:
                    result = substract(value1, value2);
                    break;
                case MULTPILY:
                    result = multiply(value1, value2);
                    break;
                case DIVIDE:
                    result = divide(value1, value2);
                    break;
                case POW:
                    result = pow(value1, value2);
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

    public Double getResult(Operator operator) {
        Double result;
        //calculation
        if (operator == Operator.POW && this.operator != Operator.NONE && this.operator != Operator.POW) {
            storedOperation[0] = value1;
            storedOperation[1] = this.operator;

            result = value2;
        } else {
            value2 = calculate(value1, this.operator, value2);
            result = calculate((double) storedOperation[0], (Operator) storedOperation[1], value2);

            storedOperation[0] = 0.0;
            storedOperation[1] = Operator.NONE;
        }
        //prepare next operation
        if (result == null) {
            reset();
        } else {
            value1 = result;
            this.operator = operator;
        }
        return result;
    }

    public Double func(Function function) {
        Double result = null;
        if (value2 != null) {
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
        if (result == null) {
            reset();
        } else {
            value2 = result;
        }
        return result;
    }
}