package ru.netology.classes;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.classes.Parsers.jsonToList;
import static ru.netology.classes.Parsers.listToJson;

class ParsersTest {

    @Test
    void testJsonToList() {
        String json = "[{\"id\":1,\"firstName\":\"John\",\"lastName\":\"Smith\",\"country\":\"USA\",\"age\":25}]";
        List<Employee> expected = new ArrayList<>();
        expected.add(new Employee(1, "John", "Smith", "USA", 25));
        List<Employee> result = jsonToList(json);
        assertEquals(expected, result);
    }

    @Test
    void testJsonToListByHamcrest() {
        String json = "[{\"id\":1,\"firstName\":\"John\",\"lastName\":\"Smith\",\"country\":\"USA\",\"age\":25}]";
        List<Employee> expected = new ArrayList<>();
        expected.add(new Employee(1, "John", "Smith", "USA", 25));
        List<Employee> result = jsonToList(json);
        assertThat(expected, equalTo(result));
    }

    @ParameterizedTest
    @MethodSource("getArgumentsForTest")
    void testListToJson(List<Employee> listEmployee, String jsonExpected) {
        assertEquals(jsonExpected, listToJson(listEmployee));
    }

    @ParameterizedTest
    @MethodSource("getArgumentsForTest")
    void testListToJsonByHamcrest(List<Employee> listEmployee, String jsonExpected) {
        assertThat(jsonExpected, equalTo(listToJson(listEmployee)));
    }

    static Stream<Arguments> getArgumentsForTest() {
        List<Employee> listEmployee1 = new ArrayList<>();
        listEmployee1.add(new Employee(1, "John", "Smith", "USA", 25));
        String json1 = "[\n" +
                "  {\n" +
                "    \"id\": 1,\n" +
                "    \"firstName\": \"John\",\n" +
                "    \"lastName\": \"Smith\",\n" +
                "    \"country\": \"USA\",\n" +
                "    \"age\": 25\n" +
                "  }\n" +
                "]";

        List<Employee> listEmployee2 = new ArrayList<>();
        listEmployee2.add(new Employee(2, "Ivan", "Petrov", "RU", 23));
        String json2 = "[\n" +
                "  {\n" +
                "    \"id\": 2,\n" +
                "    \"firstName\": \"Ivan\",\n" +
                "    \"lastName\": \"Petrov\",\n" +
                "    \"country\": \"RU\",\n" +
                "    \"age\": 23\n" +
                "  }\n" +
                "]";

        return Stream.of(
                Arguments.of(listEmployee1, json1),
                Arguments.of(listEmployee2, json2)
        );
    }

}

