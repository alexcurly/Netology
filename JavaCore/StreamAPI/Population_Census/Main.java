package com.company;
import java.util.*;
import java.util.stream.*;


public class Main {

    public static void main(String[] args) {
	// write your code here
    /*  Из коллеции объектов Person необходимо:

        Найти количество несовершеннолетних (т.е. людей младше 18 лет).
        Получить список фамилий призывников (т.е. мужчин от 18 и до 27 лет).
        Получить отсортированный по фамилии список потенциально работоспособных людей
    с высшим образованием в выборке (т.е. людей с высшим образованием от 18 до 60 лет для
    женщин и до 65 лет для мужчин).
     */


        List<String> names =
                Arrays.asList("Ivan", "Andrey", "Vladimir", "Sergey", "Alexandr", "Fedor");
        List<String> families =
                Arrays.asList("Ivanov", "Smirnov", "Bogolub", "Sokolov", "Marinov", "Petrov");

        Collection<Person> persons = new ArrayList<>();
        for(int i = 0; i < 1_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        //Найти количество несовершеннолетних (т.е. людей младше 18 лет).
        int underage = (int) persons.stream()
                .filter( value -> value.getAge() >= 18 )
                .count();
        System.out.println("Количество несовершеннолетних = " + underage);

        //Получить список фамилий призывников (т.е. мужчин от 18 и до 27 лет).
        List<String> allFamilies = new ArrayList<>();

        allFamilies = persons.stream()
                .filter(value -> value.getAge() >= 18)
                .filter(value -> value.getAge() < 27)
                .filter(value -> value.getSex().equals(Sex.MAN))
                .map(Person::getFamily)
                .collect(Collectors.toList());
        for(String x : allFamilies) System.out.println(x);

        //Получить вселенский список самых трудоспособных рабов
        List<String> allSlaves = new ArrayList<>();

        allSlaves = persons.stream()
                .filter(value -> value.getAge() >= 18
                        & value.getAge() < 65
                        & value.getSex().equals(Sex.MAN)
                        & value.getEducation().equals(Education.HIGHER) )
                .filter(value -> value.getAge() >= 18
                        & value.getAge() < 60
                        & value.getSex().equals(Sex.WOMAN)
                        & value.getEducation().equals(Education.HIGHER))
                .map(Person::getFamily)
                .collect(Collectors.toList());
        for(String x : allSlaves) System.out.println(x);










    }
}
