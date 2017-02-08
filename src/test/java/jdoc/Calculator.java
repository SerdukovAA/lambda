package jdoc;

import org.junit.Test;

/**
 * Created by serdukovaa on 08.02.2017.
 */
public class Calculator {


    interface IntegerMath {
        int operation(int a, int b);
    }

    public int operateBinary(int a, int b, IntegerMath op) {
        return op.operation(a, b);
    }

    @Test
    public void calculateTest() {

        IntegerMath addition = (a, b) -> a + b;
        IntegerMath subtraction = (a, b) -> a - b;
        IntegerMath multy = (a, b) -> a * b;



        System.out.println("40 + 2 = " + operateBinary(40, 2, addition));

        System.out.println("20 - 10 = " + operateBinary(20, 10, subtraction));

        System.out.println("20 * 10 = " + operateBinary(20, 10, multy));
    }


}
