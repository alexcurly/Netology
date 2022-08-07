package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        StreamMain.streamAPI();

        List<Integer> numbers = Arrays.asList(1, 2, 5, 16, -1, -2, 0, 32, 3, 5, 8, 23, 4);

        //сортировка положительных чисел
        numbers = positiveNumbers(numbers);

        //сортировка по четности
        numbers = evenNumbers(numbers);

        //сортировка по возрастанию
        numbers = ascendingOrderOfNumbers(numbers);

        System.out.println("Без использования StreamAPI " + numbers);
    }

    public static ArrayList<Integer> positiveNumbers(List<Integer> numbers) {
        ArrayList<Integer> supportNumbers = new ArrayList<>();
        for (Integer i : numbers) {
            if(i > 0) {
                supportNumbers.add(i);
            }
        }
        return supportNumbers;
    }

    public static ArrayList<Integer> evenNumbers(List<Integer> numbers){
        ArrayList<Integer> supportNumbers = new ArrayList<>();
        for(Integer i : numbers){
            if(i % 2 == 0){
                supportNumbers.add(i);
            }
        }
        return supportNumbers;
    }

    public static ArrayList<Integer> ascendingOrderOfNumbers(List<Integer> numbers) {
        ArrayList<Integer> supportNumbers = new ArrayList<>();
        supportNumbers.addAll(0, numbers);

        boolean isSorted = false;
        while(!isSorted) {
            isSorted = true;
            for(int i = 0; i < supportNumbers.size() - 1; i++){
                if(supportNumbers.get(i) > supportNumbers.get(i+1)) {
                    isSorted = false;

                    Integer buf1 = supportNumbers.get(i);
                    Integer buf2 = supportNumbers.get(i+1);
                    supportNumbers.set(i, buf2);
                    supportNumbers.set(i+1, buf1);
                }
            }
        }
        return supportNumbers;
    }
}