package fr.epita.tdd;

public class Factorial {

    public static int factorialIterative(int number){
        if (number < 0){
            throw new IllegalArgumentException("the number should be a positive integer");
        }else if (number == 0){
            return 1;
        }
        int result = 1;
        for (int i = number; i >= 1; i--){
            result = result * i;
        }
        return result;
    }
}
