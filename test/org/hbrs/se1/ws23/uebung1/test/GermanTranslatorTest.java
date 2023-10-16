package org.hbrs.se1.ws23.uebung1.test;

import org.hbrs.se1.ws23.uebung1.control.GermanTranslator;
import org.hbrs.se1.ws23.uebung1.control.Translator;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class GermanTranslatorTest {

    GermanTranslator translator;

    @BeforeEach
    void setUp() {
        translator = new GermanTranslator();
    }

    @Test
    void aPositiveTest() {
       // Es folgt die Äquivalenzklasse mit den korrekten Werten
       String value = translator.translateNumber(1);
       assertEquals("eins", value);
       value = translator.translateNumber(2);
       assertEquals("zwei", value);
       value = translator.translateNumber(3);
       assertEquals("drei", value);
       value = translator.translateNumber(4);
       assertEquals("vier", value);
       value = translator.translateNumber(5);
       assertEquals("fünf", value);
       value = translator.translateNumber(6);
       assertEquals("sechs", value);
       value = translator.translateNumber(7);
       assertEquals("sieben", value);
       value = translator.translateNumber(8);
       assertEquals("acht", value);
       value = translator.translateNumber(9);
       assertEquals("neun", value);
       value = translator.translateNumber(10);
       assertEquals("zehn", value);

       // Nun folgt die Äquivalenzklasse mit nicht korrekten Werten
        value = translator.translateNumber(-3);
        assertEquals("Übersetzung der Zahl" + -3 + "nicht möglich" + Translator.version, value);
        value = translator.translateNumber(22);
        assertEquals("Übersetzung der Zahl" + 22 + "nicht möglich" + Translator.version, value);
        value = translator.translateNumber(0);
        assertEquals("Übersetzung der Zahl" + 0 + "nicht möglich" + Translator.version, value);
        value = translator.translateNumber(11);
        assertEquals("Übersetzung der Zahl" + 11 + "nicht möglich" + Translator.version, value);
    }
}