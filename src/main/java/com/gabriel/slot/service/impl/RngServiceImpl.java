package com.gabriel.slot.service.impl;

import com.gabriel.slot.service.RngService;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

/**
 * Class that handles operations regarding Random Number Generation
 */
@Service
public class RngServiceImpl implements RngService {

    private final transient SecureRandom random = new SecureRandom();
    /**
     * Returns a random number from a upperbound given
     * @param upperbound
     * @return
     */
    @Override
    public Integer retrieveRandomNumber(int upperbound) {
        return random.nextInt(upperbound);
    }
}
