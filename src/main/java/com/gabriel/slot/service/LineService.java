package com.gabriel.slot.service;

import com.gabriel.slot.domain.model.mathmodel.WinLine;

import java.util.List;
import java.util.Map;

@SuppressWarnings("PMD.CommentRequired")
public interface LineService {
    int maxConsecutiveOccurrences(String line);
    Map<String,Integer> fetchRepeatedSymbol(String line);

    int calculateWin(String symbol, int occurrence, List<WinLine> winLineSet);
}
