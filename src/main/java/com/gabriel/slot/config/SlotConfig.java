package com.gabriel.slot.config;


import com.gabriel.slot.domain.model.Reel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Slot configuration class
 */
@Configuration
public class SlotConfig {

    /**
     * The reel set
     */
    @Bean
    public Map<Integer, Reel> reelSet() {
        return new HashMap<>();
    }
}
