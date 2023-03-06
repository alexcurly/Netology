package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;

import java.util.stream.Stream;

class GeoServiceTest {

    @ParameterizedTest
    @MethodSource("generateSource")
    void byIpTest(String ip, Country expected) {
        GeoService geoService = new GeoServiceImpl();
        Country actual = geoService.byIp(ip).getCountry();

        Assertions.assertEquals(expected, actual);
    }

    private static Stream<Arguments> generateSource() {
        return Stream.of(
                Arguments.of("172.1.2.3", Country.RUSSIA),
                Arguments.of("96.3.2.1", Country.USA));
    }
}