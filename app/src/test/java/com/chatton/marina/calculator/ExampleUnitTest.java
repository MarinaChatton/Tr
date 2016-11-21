package com.chatton.marina.calculator;

import android.annotation.TargetApi;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    Calculator calculator;

    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    //will be called before each test
    @Before
    public void instanciateCalculator(){
        calculator = new Calculator();
        calculator.reset();//reset static attributes
    }

    @Test
    public void shouldDoAdditionWhenEqual() {
        calculator.getResult(Operator.PLUS, "5");
        Double result = calculator.getResult(Operator.NONE, "4");
        assert(result.equals(9.0));
    }

    @Test
    public void shouldDoSubstractionWhenPlus(){
        calculator.getResult(Operator.MINUS, "15");
        Double result = calculator.getResult(Operator.PLUS, "5");
        assert(result.equals(10.0));
    }

    @Test
    public void shouldDoMultiplicationWhenMinus(){
        calculator.getResult(Operator.MULTPILY, "10.2");
        Double result = calculator.getResult(Operator.MINUS, "2");
        assert(result.equals(20.4));
    }

    @Test
    public void shouldReturnNullWhenDivideByZero(){
        calculator.getResult(Operator.DIVIDE, "12");
        Double result = calculator.getResult(Operator.NONE, "0");
        assert(result==null);
    }

    @Test
    public void shouldReturnOperatorMultiply(){
        calculator.setOperator(Operator.MULTPILY);
        Operator operator = calculator.getOperator();
        assert(operator.equals(Operator.MULTPILY));
    }

    @Test
    public void shouldReturnValue(){
        calculator.setValue(15.5);
        double value = calculator.getValue();
        assert(value==15.5);
    }
}