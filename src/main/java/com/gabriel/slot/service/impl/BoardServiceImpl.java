package com.gabriel.slot.service.impl;

import com.gabriel.slot.service.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Class that handles Board operations
 */
@Service
public class BoardServiceImpl implements BoardService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BoardService.class);

    /**
     * Fill the board. The selected Item should be in the middle
     *
     * @param board
     * @param reelNumber
     * @param symbols
     * @param rngPosition
     * @return
     */
    @Override
    public String[][] transposeReel(String[][] board, int reelNumber, List<String> symbols , int rngPosition) {

        LOGGER.info("Transposing reel [{}] into the board", reelNumber);

        //Check if the rnd generator selected the first item. I so, the last item from the reel will be in the first row in the board
        if(rngPosition ==0) {
            board[0][reelNumber - 1] = symbols.get(symbols.size()-1);
        }
        else {
            board[0][reelNumber - 1] = symbols.get(rngPosition - 1);
        }

        //Fill the middle row with the selected item
        board[1][reelNumber - 1] = symbols.get(rngPosition);

        //Check if the rnd generator selected the last item. I so, the first item from the reel will be in the last row in the board
        if (rngPosition == symbols.size()-1){
            board[2][reelNumber - 1] = symbols.get(0);
        }
        else{
            board[2][reelNumber - 1] = symbols.get(rngPosition + 1);
        }

        LOGGER.info("Reel [{}] transposed into the board successfully", reelNumber);
        return board;
    }
}
