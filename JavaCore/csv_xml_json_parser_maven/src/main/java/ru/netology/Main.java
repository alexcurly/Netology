package ru.netology;

import ru.netology.classes.Employee;
import ru.netology.classes.Parsers;
import ru.netology.classes.ReadWriteFile;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        //  Задача 1
        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        List<Employee> list = Parsers.parseCSV(columnMapping, "data.csv");
        String json = Parsers.listToJson(list);
        ReadWriteFile.writeString(json, "data.json");
        //  Задача 2
        list = Parsers.parseXML("data.xml");
        json = Parsers.listToJson(list);
        ReadWriteFile.writeString(json, "data2.json");
        //  Задача 3
        json = ReadWriteFile.readString("data.json");
        list = Parsers.jsonToList(json);
        if (list != null) {
            list.forEach(System.out::println);
        }
    }
}
