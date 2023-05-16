package com.gabriel.slot.service;

import com.gabriel.slot.domain.model.MathModel;
import com.gabriel.slot.domain.model.SlotGame;

import java.util.Map;

@SuppressWarnings("PMD.CommentRequired")
public interface SlotConfigurationService {
    Map<Integer, SlotGame> loadSlotGames();
    Map<Integer, MathModel> loadMathModels();


}
