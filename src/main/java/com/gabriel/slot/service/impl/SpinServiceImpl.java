package com.gabriel.slot.service.impl;

import com.gabriel.slot.domain.mathmodel.Reel;
import com.gabriel.slot.domain.model.Spin;
import com.gabriel.slot.domain.model.SpinResult;
import com.gabriel.slot.domain.model.SpinSimulation;
import com.gabriel.slot.domain.object.ReelPosition;
import com.gabriel.slot.domain.object.ReelSetPositions;
import com.gabriel.slot.service.BoardService;
import com.gabriel.slot.service.RngService;
import com.gabriel.slot.service.SpinService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Class that handles spin operations
 */
@SuppressWarnings({"PMD.AvoidInstantiatingObjectsInLoops", "PMD.LawOfDemeter"})
@Service
public class SpinServiceImpl implements SpinService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpinService.class);

    @Autowired
    private transient RngService rngService;

    @Autowired
    private transient BoardService boardService;



    /**
     * Calculates the line from a spin in the reelset
     *
     * @return
     */
    @Override
    public SpinSimulation simulateSpin(Map<Integer, Reel> reels) {
        LOGGER.info("Simulating Spin...");

        //Variables
        ReelSetPositions reelSetPositions = new ReelSetPositions();
        String[][] board = new String[3][reels.size()];

        //Iterate ove each reel
        for (Map.Entry<Integer, Reel> entry : reels.entrySet()) {

            if(LOGGER.isInfoEnabled()) {
                LOGGER.info("Simulating spin on Reel #{}...", entry.getKey());
            }

            //Variables
            int reelNumber = entry.getKey();
            Reel currentReel = entry.getValue();

            //RNG Value
            int rngPosition = rngService.retrieveRandomNumber(currentReel.getSymbols().size());

            //Fill the board
            board = boardService.transposeReel(board, reelNumber, currentReel.getSymbols(), rngPosition);

            //Create POJO
            ReelPosition reelPosition = new ReelPosition();
            reelPosition.setReelNumber(reelNumber);
            reelPosition.setPosition(rngPosition);
            reelPosition.setSymbol(currentReel.getSymbols().get(reelPosition.getPosition()));

            //Store POJO
            reelSetPositions.getReelsPositions().add(reelPosition);

            LOGGER.info("Reel #{} spin simulated position[{}] symbol[{}]", reelPosition.getReelNumber(), reelPosition.getPosition(), reelPosition.getSymbol());

        }

        //Create the object to be return
        SpinSimulation spinSimulation = new SpinSimulation();
        spinSimulation.setReelSetPositions(reelSetPositions);
        spinSimulation.setBoard(board);

        LOGGER.info("Retrieving spin simulation details...");
        return spinSimulation;
    }


    /**
     * Process and evaluate a spin
     *
     * @param spinSimulation
     * @param spin
     * @return result
     */
    @Override
    public SpinResult processSpin(SpinSimulation spinSimulation, Spin spin) {


        return null;
    }


}
