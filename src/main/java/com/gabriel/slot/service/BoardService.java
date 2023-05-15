package com.gabriel.slot.service;

import java.util.List;

@SuppressWarnings("PMD.CommentRequired")
public interface BoardService {
    String[][] transposeReel(String[][] board, int reelNumber, List<String> symbols, int rngPosition);
}
