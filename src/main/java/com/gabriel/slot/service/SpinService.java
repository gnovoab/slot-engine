package com.gabriel.slot.service;

import com.gabriel.slot.domain.model.Spin;
import com.gabriel.slot.domain.model.SpinResult;
import com.gabriel.slot.domain.model.SpinSimulation;
import com.gabriel.slot.domain.model.mathmodel.Reel;

import java.util.Map;

@SuppressWarnings("PMD.CommentRequired")
public interface SpinService {
    SpinSimulation simulateSpin(Map<Integer, Reel> reels);
    SpinResult processSpin(SpinSimulation spinSimulation, Spin spin);


}
