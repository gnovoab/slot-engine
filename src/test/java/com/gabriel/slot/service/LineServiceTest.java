package com.gabriel.slot.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashMap;
import java.util.Map;

/**
 * Unit test class
 */
@ActiveProfiles("unitTest")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LineServiceTest {

    //Fields
    @Autowired
    private transient LineService lineService;
    
    private static final String LINE_ABCDE = "abcde";
    private static final String LINE_ABBDE = "abbde";

    private static final String LINE_AACEE = "aacee";
    private static final String LINE_AAAEE = "aaaee";
    private static final String LINE_AAAAE = "aaaae";
    private static final String LINE_AAAAA = "aaaaa";
    private static final String LINE_ABABA = "ababa";
    private static final String LINE_ABAAB = "abaab";
    private static final String LINE_BAAAB = "baaab";

    @Test
    void maxConsecutiveOccurrencesTest() {

        Assertions.assertEquals(1, lineService.maxConsecutiveOccurrences(LINE_ABCDE));
        Assertions.assertEquals(2, lineService.maxConsecutiveOccurrences(LINE_ABBDE));
        Assertions.assertEquals(2, lineService.maxConsecutiveOccurrences(LINE_AACEE));
        Assertions.assertEquals(3, lineService.maxConsecutiveOccurrences(LINE_AAAEE));
        Assertions.assertEquals(4, lineService.maxConsecutiveOccurrences(LINE_AAAAE));
        Assertions.assertEquals(5, lineService.maxConsecutiveOccurrences(LINE_AAAAA));

        Assertions.assertEquals(1, lineService.maxConsecutiveOccurrences(LINE_ABABA));
        Assertions.assertEquals(2, lineService.maxConsecutiveOccurrences(LINE_ABAAB));
        Assertions.assertEquals(3, lineService.maxConsecutiveOccurrences(LINE_BAAAB));
    }

    @Test
    void fetchRepeatedSymbolTest() {

        Map<String, Integer> symbols = new HashMap<>();

        symbols = lineService.fetchRepeatedSymbol(LINE_ABCDE);
        Assertions.assertTrue(symbols.isEmpty());

        symbols = lineService.fetchRepeatedSymbol(LINE_ABBDE);
        Assertions.assertFalse(symbols.isEmpty());
        Assertions.assertEquals(1,symbols.size());
        Assertions.assertEquals(2,symbols.get("b"));


        symbols = lineService.fetchRepeatedSymbol(LINE_AACEE);
        Assertions.assertFalse(symbols.isEmpty());
        Assertions.assertEquals(2,symbols.size());
        Assertions.assertEquals(2,symbols.get("a"));
        Assertions.assertEquals(2,symbols.get("e"));

        symbols = lineService.fetchRepeatedSymbol(LINE_AAAEE);
        Assertions.assertFalse(symbols.isEmpty());
        Assertions.assertEquals(2,symbols.size());
        Assertions.assertEquals(3,symbols.get("a"));
        Assertions.assertEquals(2,symbols.get("e"));

        symbols = lineService.fetchRepeatedSymbol(LINE_AAAAE);
        Assertions.assertFalse(symbols.isEmpty());
        Assertions.assertEquals(1,symbols.size());
        Assertions.assertEquals(4,symbols.get("a"));

        symbols = lineService.fetchRepeatedSymbol(LINE_AAAAA);
        Assertions.assertFalse(symbols.isEmpty());
        Assertions.assertEquals(1,symbols.size());
        Assertions.assertEquals(5,symbols.get("a"));

        symbols = lineService.fetchRepeatedSymbol(LINE_ABABA);
        Assertions.assertTrue(symbols.isEmpty());

        symbols = lineService.fetchRepeatedSymbol(LINE_ABAAB);
        Assertions.assertFalse(symbols.isEmpty());
        Assertions.assertEquals(1,symbols.size());
        Assertions.assertEquals(2,symbols.get("a"));

        symbols = lineService.fetchRepeatedSymbol(LINE_BAAAB);
        Assertions.assertFalse(symbols.isEmpty());
        Assertions.assertEquals(1,symbols.size());
        Assertions.assertEquals(3,symbols.get("a"));
    }
}