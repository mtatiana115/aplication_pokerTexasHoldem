package com.tati.pokerTexasHolder.infrastructure.helpers.composition;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.tati.pokerTexasHolder.utils.enums.HandType;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@AllArgsConstructor
@Slf4j
public class CompositionHelper {

    private final ThreeOfAKindHelper threeOfAKindHelper;
    private final TwoPairHelper twoPairHelper;
    private final OnePairHelper onePairHelper;
    private final StraightHelper straightHelper;

    public List<String> validComposition(String winnerHand, HandType winnerHandType) {
        List<String> compositionWinnerHand = new ArrayList<>();
        switch (winnerHandType) {
            case THREE_OF_A_KIND:
                compositionWinnerHand = threeOfAKindHelper.compositionThreeOfAKind(winnerHand);
                break;
            case TWO_PAIR:
                compositionWinnerHand = twoPairHelper.compositionTwoPair(winnerHand);
                break;
            case ONE_PAIR:
                compositionWinnerHand = onePairHelper.compositionOnePair(winnerHand);
                break;
            case STRAIGHT:
                compositionWinnerHand = straightHelper.compositionStraight(winnerHand);
                break;
            case HIGH_CARD:
            default:

                break;
        }
        return compositionWinnerHand;
    }
}
