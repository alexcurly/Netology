package com.company;

import java.lang.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class StreamMain {



    public static void streamAPI() {
        int[] input = {1, 2, 5, 16, -1, -2, 0, 32, 3, 5, 8, 23, 4};
        List<Integer> result = Arrays.stream(input)
                .filter( x -> x > 0)
                .filter( x -> x % 2 == 0)
                .sorted()
                .boxed()
                .collect(Collectors.toList());
        System.out.println("С использованием StreamAPI " + result);


    }
}
