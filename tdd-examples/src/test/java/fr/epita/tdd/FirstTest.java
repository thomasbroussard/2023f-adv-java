package fr.epita.tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FirstTest {


    @Test
    @DisplayName("WHEN Junit Is Configured THEN TestAnnotation IsRunning This As TestCase")
    public void firstTestCase(){
        System.out.println("test");
    }


    @Test
    @DisplayName("WHEN I call 5! (factorial(5)) THEN I should have a resulting value of 120")
    public void testPassingFactorial(){
        //given
        int number = 5;

        //when
        int result = Factorial.factorialIterative(number);

        //then
        Assertions.assertEquals(120, result, "expected 120 but got" + result);


    }


    @Test
    @DisplayName("WHEN I call 0! THEN I should have a resulting value of 1")
    public void testLimitCase0(){
        //given
        int number = 0;

        //when
        int result = Factorial.factorialIterative(number);

        //then
        Assertions.assertEquals(1, result);

    }

 //   @Test
    @DisplayName("WHEN I call Factorial with negative integer THEN I should have an exception ")
    public void testLimitCaseNegativeInput(){
        //given
        int number = -2;

        Exception e = null;
        //when
        try {
            Factorial.factorialIterative(number);
        }catch (Exception caught){
            e = caught;
        }

        //then
        Assertions.assertNotNull(e);
        Assertions.assertTrue(e instanceof IllegalArgumentException);

    }

}
