package com.gabriel.slot.service.impl;

import com.gabriel.slot.domain.model.Reel;
import com.gabriel.slot.domain.model.Symbol;
import com.gabriel.slot.service.SlotConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;

/**
 * Class that handles all related slot configuration
 */
@Service
public class SlotConfigServiceImpl implements SlotConfigService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SlotConfigService.class);

    @Autowired
    private transient Map<Integer, Reel> reelSet;

    @Override
    public void setupReels() {

        LOGGER.info("Setting up Reels");

        Reel reel1 = new Reel();
        Reel reel2 = new Reel();
        Reel reel3 = new Reel();
        Symbol[] symbolArray = { Symbol.BAR, Symbol.BELL, Symbol.CHERRY, Symbol.ORANGE, Symbol.LEMON, Symbol.PLUM };

        //Setup reels
        reel1.setId(1);
        reel1.setSymbols(Arrays.asList(symbolArray));

        reel2.setId(2);
        reel2.setSymbols(Arrays.asList(symbolArray));

        reel3.setId(3);
        reel3.setSymbols(Arrays.asList(symbolArray));

        //Load reels in memory
        reelSet.put(reel1.getId(),reel1);
        reelSet.put(reel2.getId(),reel2);
        reelSet.put(reel3.getId(),reel3);

        LOGGER.info("Reels being loaded");
    }


}
