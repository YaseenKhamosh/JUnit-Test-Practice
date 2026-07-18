package org.example;

public class MathUtil {



    public int add(int a, int b){
        return a + b;
    }

    public double divide(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Division by zero is not allowed.");
        }
        return a / b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public double computeCircleArea(double radius) {
        return Math.PI * radius * radius;

    }
}
