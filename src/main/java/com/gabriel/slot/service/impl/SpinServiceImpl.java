package com.gabriel.slot.service.impl;

import com.gabriel.slot.domain.dto.object.ReelPosition;
import com.gabriel.slot.domain.dto.object.ReelSetPositions;
import com.gabriel.slot.domain.model.Spin;
import com.gabriel.slot.domain.model.SpinResult;
import com.gabriel.slot.domain.model.SpinSimulation;
import com.gabriel.slot.domain.model.mathmodel.Line;
import com.gabriel.slot.domain.model.mathmodel.Reel;
import com.gabriel.slot.domain.model.mathmodel.WinLine;
import com.gabriel.slot.service.BoardService;
import com.gabriel.slot.service.LineService;
import com.gabriel.slot.service.RngService;
import com.gabriel.slot.service.SpinService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

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

    @Autowired
    private transient LineService lineService;



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
     * @param board
     * @param lines
     * @param winLineSet
     * @param spin
     * @return
     */
    @Override
    public SpinResult processSpin(String[][] board, List<Line> lines, List<WinLine> winLineSet, Spin spin) {


        //Generate transaction ID
        String txId = UUID.randomUUID().toString();
        Map<Integer, Integer> winLines = new ConcurrentHashMap<>();
        int totalWin = 0;

        for (Line line : lines) {
            StringBuilder symbolsInLine = new StringBuilder();
            int lineWin = 0;
            if(line.getId() <= spin.getNumLines()){
                LOGGER.info("Evaluating line #{} for spin with txID[{}] ",line.getId(), txId);

                //Represent the line in a String
                int reelNo=0;
                for (short position : line.getPositions()){
                    symbolsInLine.append(board[position+1][reelNo]);
                    reelNo++;
                }

                //Get the maxConsecutiveOccurrences
                int maxConsecutiveOccurrences = lineService.maxConsecutiveOccurrences(symbolsInLine.toString());

                if(maxConsecutiveOccurrences > 1){
                    //Retrieve repeated symbols
                    Map<String, List<Integer>> symbolsOccurrences = lineService.fetchRepeatedSymbol(symbolsInLine.toString());
                    for (Map.Entry<String, List<Integer>> entry : symbolsOccurrences.entrySet()) {
                        for (int occurrence:entry.getValue()) {
                            lineWin += lineService.calculateWin(entry.getKey(), occurrence, winLineSet, spin.getStake());
                        }
                    }
                    winLines.put(line.getId(), lineWin);
                    totalWin += lineWin;
                }

                LOGGER.info("Line #{} for spin with txID[{}] has a win of [{}]",line.getId(), txId, lineWin);
            }
        }

        //Create the object
        SpinResult spinResult = new SpinResult();
        spinResult.setTransactionId(txId);
        spinResult.setLines(winLines);
        spinResult.setTotalWin(totalWin);

        return spinResult;
    }



}
