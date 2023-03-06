package ru.netology.classes;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class ReadWriteFileTest {

    private static File test;

    @BeforeAll
    static void CreateTestFile() throws IOException {
        System.out.println("Создание тестового файла");
        test = new File("test.json");
        test.createNewFile();
    }

    @Test
    void testReadString() {
        assertNull(ReadWriteFile.readString("abracadabra.test"));
        assertNotNull(ReadWriteFile.readString(test.getPath()));
    }

    @Test
    void testReadStringByHamcrest() {
        assertThat(ReadWriteFile.readString("abracadabra.test"), nullValue());
        assertThat(ReadWriteFile.readString(test.getPath()), notNullValue());
    }

    @AfterAll
    static void DeleteTestFile() {
        System.out.println("Удаление тестового файла");
        if (test.exists()) {
            test.delete();
        }
    }
}