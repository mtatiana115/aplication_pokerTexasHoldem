package com.tati.pokerTexasHolder.infrastructure.helpers;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tati.pokerTexasHolder.api.dto.HandResponse;
import com.tati.pokerTexasHolder.infrastructure.helpers.composition.HighCardHelper;
import com.tati.pokerTexasHolder.infrastructure.helpers.composition.OnePairHelper;
import com.tati.pokerTexasHolder.infrastructure.helpers.composition.StraightHelper;
import com.tati.pokerTexasHolder.infrastructure.helpers.composition.ThreeOfAKindHelper;
import com.tati.pokerTexasHolder.infrastructure.helpers.composition.TwoPairHelper;
import com.tati.pokerTexasHolder.utils.enums.HandType;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@AllArgsConstructor
@Slf4j
public class TieHelper {

    @Autowired
    private final HighCardHelper highCardHelper;
    @Autowired
    private final OnePairHelper onePairHelper;
    @Autowired
    private final TwoPairHelper twoPairHelper;
    @Autowired
    private final ThreeOfAKindHelper threeOfAKindHelper;
    @Autowired
    private final StraightHelper straightHelper;
    @Autowired
    private final KickerHelper kickerHelper;

    public HandResponse resolveTie(String hand1, String hand2, HandType handType) {
        log.info("Resolving tie for handType: {}", handType);

        switch (handType) {
            case HIGH_CARD:
                return highCardHelper.resolveHighCardTie(hand1, hand2);
            case ONE_PAIR:
                return onePairHelper.resolveOnePairTie(hand1, hand2, kickerHelper);
            case TWO_PAIR:
                return twoPairHelper.resolveTwoPairTie(hand1, hand2, kickerHelper);
            case THREE_OF_A_KIND:
                return threeOfAKindHelper.resolveThreeOfAKindTie(hand1, hand2, kickerHelper);
            case STRAIGHT:
                return straightHelper.resolveStraightTie(hand1, hand2);
            default:
                log.warn("Unhandled hand type in tie resolution: {}", handType);
                return new HandResponse("Tie", handType.toString(), Arrays.asList("tie"));
        }
    }
}
