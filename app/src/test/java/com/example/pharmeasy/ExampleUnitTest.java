package com.example.pharmeasy;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    private final_payment_Activity address,loyalty;


    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Before
    public void setUp(){
        address = new final_payment_Activity();
    }

    //IT19237114
    @Test
    public void province(){
        float result =address.calculating_address("Western");
        assertEquals(100,result,0.001);
    }





    //IT19092102
    @Test
    public void points(){
        float result = loyalty.calculating_loyality_discout(6, 1000.0f);
        assertEquals(100.0f,result,0.001);
    }

    @Test
    public void testCalc() {
        float result = loyalty.calculating_loyality_discout(6, (float) 1000);
        assertEquals(100,result,0.001);
    }


}