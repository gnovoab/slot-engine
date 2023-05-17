package com.gabriel.slot.service;

import com.gabriel.slot.domain.model.Spin;
import com.gabriel.slot.domain.model.mathmodel.WinLine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    private static final String LINE_AADAA = "aadaa";

    private static final String LINE_AADAAAB = "aadaaab";
    private static final String LINE_AADAAAA = "aadaaaa";


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

        Map<String, List<Integer>>  symbols = new HashMap<>();

        symbols = lineService.fetchRepeatedSymbol(LINE_ABCDE);
        Assertions.assertTrue(symbols.isEmpty());

        symbols = lineService.fetchRepeatedSymbol(LINE_ABBDE);
        Assertions.assertFalse(symbols.isEmpty());
        Assertions.assertEquals(1,symbols.size());
        Assertions.assertEquals(2,symbols.get("b").get(0));

        symbols = lineService.fetchRepeatedSymbol(LINE_AACEE);
        Assertions.assertFalse(symbols.isEmpty());
        Assertions.assertEquals(2,symbols.size());
        Assertions.assertEquals(2,symbols.get("a").get(0));
        Assertions.assertEquals(2,symbols.get("e").get(0));

        symbols = lineService.fetchRepeatedSymbol(LINE_AAAEE);
        Assertions.assertFalse(symbols.isEmpty());
        Assertions.assertEquals(2,symbols.size());
        Assertions.assertEquals(3,symbols.get("a").get(0));
        Assertions.assertEquals(2,symbols.get("e").get(0));

        symbols = lineService.fetchRepeatedSymbol(LINE_AAAAE);
        Assertions.assertFalse(symbols.isEmpty());
        Assertions.assertEquals(1,symbols.size());
        Assertions.assertEquals(4,symbols.get("a").get(0));

        symbols = lineService.fetchRepeatedSymbol(LINE_AAAAA);
        Assertions.assertFalse(symbols.isEmpty());
        Assertions.assertEquals(1,symbols.size());
        Assertions.assertEquals(5,symbols.get("a").get(0));

        symbols = lineService.fetchRepeatedSymbol(LINE_ABABA);
        Assertions.assertTrue(symbols.isEmpty());

        symbols = lineService.fetchRepeatedSymbol(LINE_ABAAB);
        Assertions.assertFalse(symbols.isEmpty());
        Assertions.assertEquals(1,symbols.size());
        Assertions.assertEquals(2,symbols.get("a").get(0));

        symbols = lineService.fetchRepeatedSymbol(LINE_BAAAB);
        Assertions.assertFalse(symbols.isEmpty());
        Assertions.assertEquals(1,symbols.size());
        Assertions.assertEquals(3,symbols.get("a").get(0));

        symbols = lineService.fetchRepeatedSymbol(LINE_AADAA);
        Assertions.assertFalse(symbols.isEmpty());
        Assertions.assertEquals(1,symbols.size());
        Assertions.assertEquals(2,symbols.get("a").size());
        Assertions.assertEquals(2,symbols.get("a").get(0));
        Assertions.assertEquals(2,symbols.get("a").get(1));

        symbols = lineService.fetchRepeatedSymbol(LINE_AADAAAB);
        Assertions.assertFalse(symbols.isEmpty());
        Assertions.assertEquals(1,symbols.size());
        Assertions.assertEquals(2,symbols.get("a").size());
        Assertions.assertEquals(2,symbols.get("a").get(0));
        Assertions.assertEquals(3,symbols.get("a").get(1));

        symbols = lineService.fetchRepeatedSymbol(LINE_AADAAAA);
        Assertions.assertFalse(symbols.isEmpty());
        Assertions.assertEquals(1,symbols.size());
        Assertions.assertEquals(2,symbols.get("a").size());
        Assertions.assertEquals(2,symbols.get("a").get(0));
        Assertions.assertEquals(4,symbols.get("a").get(1));
    }

    @Test
    void calculateWinTest() {


        List<WinLine> winLineSet = new ArrayList<>();
        WinLine winLine = new WinLine();
        winLine.setSymbol("4a");
        winLine.setValue(40);

        winLineSet.add(winLine);

        Spin spin = new Spin();
        spin.setStake((short) 100);

        int win = lineService.calculateWin("a", 4, winLineSet, spin.getStake());
        Assertions.assertEquals(4000, win);

        win= lineService.calculateWin("a", 2, winLineSet, spin.getStake());
        Assertions.assertEquals(0, win);

    }
}