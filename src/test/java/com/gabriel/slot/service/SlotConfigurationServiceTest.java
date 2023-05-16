package com.gabriel.slot.service;

import com.gabriel.slot.domain.model.mathmodel.MathModel;
import com.gabriel.slot.domain.model.game.SlotGame;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Map;

/**
 * Unit test class
 */
@ActiveProfiles("unitTest")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SlotConfigurationServiceTest {

    //Fields
    @Autowired
    private transient SlotConfigurationService slotConfigurationService;

    @Test
    void loadMathModelsTest() {
        Map<Integer, MathModel> mathModels = slotConfigurationService.loadMathModels();
        Assertions.assertFalse(mathModels.isEmpty());
    }

    @Test
    void loadSlotGamesTest() {
        Map<Integer, SlotGame> gameCatalog = slotConfigurationService.loadSlotGames();
        Assertions.assertFalse(gameCatalog.isEmpty());
        Assertions.assertEquals("Simple 3 reel fruit slot", gameCatalog.get(1).getName());
    }
}