package com.gabriel.slot.config;


import com.gabriel.slot.domain.model.MathModel;
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

}
