package com.gabriel.slot.service.impl;

import com.gabriel.slot.domain.model.mathmodel.WinLine;
import com.gabriel.slot.service.LineService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Class that handles operations regarding Slot line processing
 */
@SuppressWarnings({"PMD.AvoidInstantiatingObjectsInLoops"})
@Service
public class LineServiceImpl implements LineService {

    /**
     * @param line
     * @return
     */
    @Override
    public int maxConsecutiveOccurrences(String line) {
        int power = 1;
        int currPower = 1;

        for(int i = 1 ; i < line.length(); i++) {
            if(line.charAt(i) == line.charAt(i - 1)) {
                currPower++;
            }
            else {
                power = Math.max(power, currPower);
                currPower = 1;
            }
        }
        return Math.max(power, currPower);
    }


    /**
     * Return repetitive symbol in a line
     * @param line
     * @return
     */
    @Override
    public  Map<String,List<Integer>> fetchRepeatedSymbol(String line) {
        Map<String, List<Integer>> repeatedSymbols = new ConcurrentHashMap<>();

        int index = 0;

        // Find the maximum repeating character
        // starting from str[i]

        for (int i=0; i<line.length(); i++) {
            int currentCount = 1;
            char currentChar = line.charAt(i);
            int j2 = 0;
            for (int j=i+1; j<line.length(); j++) {
                j2=j;
                if (line.charAt(i) != line.charAt(j)) {
                    break;
                }
                currentCount++;
            }

            if(currentCount > 1 && i >= index){

                List<Integer> occurrence = repeatedSymbols.get(String.valueOf(currentChar));
                if (occurrence == null){
                    occurrence = new ArrayList<>();
                    occurrence.add(currentCount);
                    repeatedSymbols.put(String.valueOf(currentChar), occurrence);
                }
                else{
                    occurrence.add(currentCount);
                    repeatedSymbols.put(String.valueOf(currentChar), occurrence);
                }

                index = j2;

            }
        }

        return repeatedSymbols;
    }


    /**
     * Calculate win in line
     *
     * @param symbol
     * @param occurrence
     * @param winLineSet
     * @param stake
     * @return
     */
    @Override
    public int calculateWin(String symbol, int occurrence, List<WinLine> winLineSet, Short stake) {
        int winAmount = 0;
        for (WinLine winLine: winLineSet) {
            if(winLine.getSymbol().equals(occurrence+symbol)){
                winAmount = winLine.getValue() * stake;
            }
        }
        return winAmount;

    }
}
