package com.tati.pokerTexasHolder.infrastructure.helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tati.pokerTexasHolder.utils.enums.HandType;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@AllArgsConstructor
@Slf4j
public class HandHelper {

    @Autowired
    private final ValuesHelper valuesHelper;
    @Autowired
    private final StrategyHelper strategyHelper;

    public HandType evaluateHand(String hand) {
        int[] values = valuesHelper.extractValues(hand);

        log.info("Values: {}", values);

        if (strategyHelper.isStraight(values)) {
            return HandType.STRAIGHT;
        } else if (strategyHelper.isThreeOfAKind(values)) {
            return HandType.THREE_OF_A_KIND;
        } else if (strategyHelper.isTwoPair(values)) {
            return HandType.TWO_PAIR;
        } else if (strategyHelper.isOnePair(values)) {
            return HandType.ONE_PAIR;
        } else {
            return HandType.HIGH_CARD;
        }
    }

}
