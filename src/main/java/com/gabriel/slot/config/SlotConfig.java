package com.gabriel.slot.config;


import com.gabriel.slot.domain.model.mathmodel.MathModel;
import com.gabriel.slot.domain.model.game.SlotGame;
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
     * The Math models
     */
    @Bean
    public Map<Integer, MathModel> mathModels(){
        return new HashMap<>();
    }

    /**
     * The Slot Game Catalog
     */
    @Bean
    public Map<Integer, SlotGame> gamesCatalog() {
        return new HashMap<>();
    }

}
