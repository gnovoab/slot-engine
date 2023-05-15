package com.gabriel.slot.service;

import com.gabriel.slot.domain.model.MathModel;

import java.util.Map;

@SuppressWarnings("PMD.CommentRequired")
public interface SlotConfigurationService {
    Map<Integer, MathModel> loadMathModels();
}
