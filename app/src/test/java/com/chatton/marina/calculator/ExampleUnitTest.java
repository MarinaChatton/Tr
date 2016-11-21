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
        calculator.getResult(Operator.plus, "5");
        Double result = calculator.getResult(Operator.none, "4");
        assert(result.equals(9.0));
    }

    @Test
    public void shouldDoSubstractionWhenPlus(){
        calculator.getResult(Operator.minus, "15");
        Double result = calculator.getResult(Operator.plus, "5");
        assert(result.equals(10.0));
    }
}