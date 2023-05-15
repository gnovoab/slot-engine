package com.gabriel.slot.service;

import com.gabriel.slot.domain.model.Spin;
import com.gabriel.slot.domain.model.SpinResult;
import com.gabriel.slot.domain.model.SpinSimulation;

@SuppressWarnings("PMD.CommentRequired")
public interface SpinService {
    SpinSimulation simulateSpin();
    SpinResult processSpin(SpinSimulation spinSimulation, Spin spin);


}
