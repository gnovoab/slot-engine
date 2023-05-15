package com.gabriel.slot.service;

import com.gabriel.slot.domain.model.Symbol;

import java.util.List;

@SuppressWarnings("PMD.CommentRequired")
public interface BoardService {
    Symbol[][] transposeReel(Symbol[][] board, int reelNumber, List<Symbol> symbols, int rngPosition);
}
