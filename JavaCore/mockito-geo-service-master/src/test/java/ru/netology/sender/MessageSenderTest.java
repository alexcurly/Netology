package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class MessageSenderTest {

    @Mock
    GeoService geoService;

    @Mock
    LocalizationService localizationService;

    @Mock
    Location location;

    @ParameterizedTest
    @MethodSource("generateSource")
    void sendTest(String ip) {
        String expected = ip.startsWith("172.") ? "Добро пожаловать" : "Welcome";

        when(geoService.byIp(ip)).thenReturn(location);
        when(localizationService.locale(any())).thenReturn(expected);

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);
        String actual = messageSender.send(headers);
        ArgumentCaptor<String> ipArgumentCaptor = ArgumentCaptor.forClass(String.class);

        Assertions.assertEquals(expected, actual);
        Mockito.verify(geoService, only()).byIp(ip);
        Mockito.verify(localizationService, times(2)).locale(any());
        Mockito.verify(location, times(2)).getCountry();
        Mockito.verify(geoService).byIp(ipArgumentCaptor.capture());
        Assertions.assertEquals(ip, ipArgumentCaptor.getValue());
    }

    private static Stream<Arguments> generateSource() {
        return Stream.of(
                Arguments.of("172.123.12.19"),
                Arguments.of("96.1.2.3"),
                Arguments.of("172.1.2.3"),
                Arguments.of("96.100.100.100"));
    }
}