package com.gabriel.slot.service;

import com.gabriel.slot.domain.model.game.SlotGame;
import com.gabriel.slot.domain.model.mathmodel.MathModel;

import java.util.Map;

@SuppressWarnings("PMD.CommentRequired")
public interface SlotConfigurationService {
    Map<Integer, SlotGame> loadSlotGames();
    Map<Integer, MathModel> loadMathModels();


}
