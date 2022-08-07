package com.company;
import org.w3c.dom.ls.LSOutput;

import java.util.function.*;
public class Calculator  {

    static Supplier instance = Calculator::new;

    BinaryOperator<Integer> plus = (x, y) -> x + y;
    BinaryOperator<Integer> minus = (x, y) -> x - y;
    BinaryOperator<Integer> multiply = (x, y) -> x * y;
    BinaryOperator<Integer> devide = (x, y) -> y != 0 ? x / y : 0;
    UnaryOperator<Integer> pow = x -> x * x;
    UnaryOperator<Integer> abs = x -> x > 0 ? x : x * -1;
    Predicate<Integer> isPositive = x -> x > 0;
    Consumer<Integer> println = System.out::println;



//    public Integer plus (Integer a, Integer b) {
//        return a + b;
//    }
//
//    public Integer minus (Integer a, Integer b) {
//        return a - b;
//    }
//
//    public Integer multiply (Integer a, Integer b) {
//        return a * b;
//    }
//
//    public Integer divide (Integer a, Integer b) {
//        return a / b;
//    }

}
