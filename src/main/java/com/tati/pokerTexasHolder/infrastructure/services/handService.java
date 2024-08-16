package com.tati.pokerTexasHolder.infrastructure.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tati.pokerTexasHolder.api.dto.HandRequest;
import com.tati.pokerTexasHolder.api.dto.HandResponse;
import com.tati.pokerTexasHolder.infrastructure.helpers.HandHelper;
import com.tati.pokerTexasHolder.infrastructure.helpers.ValuesHelper;
import com.tati.pokerTexasHolder.infrastructure.helpers.composition.CompositionHelper;
import com.tati.pokerTexasHolder.infrastructure.helpers.composition.HighCardHelper;
import com.tati.pokerTexasHolder.infrastructure.interfaces.HandEvaluator;
import com.tati.pokerTexasHolder.utils.enums.HandType;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class handService implements HandEvaluator {

  @Autowired
  private final HandHelper handHelper;

  @Autowired
  private final CompositionHelper compositionHelper;

  @Autowired
  private final ValuesHelper valuesHelper;

  @Autowired
  private final HighCardHelper highCardHelper;

  @Override
  public HandResponse analyzeHands(HandRequest request) {
    String hand1 = request.getHand1();
    String hand2 = request.getHand2();

    HandType hand1Type = handHelper.evaluateHand(hand1);
    HandType hand2Type = handHelper.evaluateHand(hand2);
    log.info("Hand1: {}", hand1);
    log.info("Hand1Type: {}", hand1Type);
    log.info("Hand2: {}", hand2);
    log.info("Hand2Type: {}", hand2Type);

    int comparison = hand1Type.compareTo(hand2Type);
    String winnerHand;
    HandType winnerHandType;
    List<String> compositionWinnerHand = Arrays.asList("tie");

    if (comparison > 0) {
      winnerHand = "hand1";
      winnerHandType = hand1Type;
      compositionWinnerHand = compositionHelper.validComposition(hand1, winnerHandType);
    } else if (comparison < 0) {
      winnerHand = "hand2";
      winnerHandType = hand2Type;
      compositionWinnerHand = compositionHelper.validComposition(hand2, winnerHandType);
    } else {
      if (HandType.HIGH_CARD.equals(hand1Type)) {
        log.info("Comparing hands");
        int compare = valuesHelper.compareHandsValues(hand1, hand2);
        if (compare > 0) {
          winnerHand = "hand1";
          winnerHandType = hand1Type;
          compositionWinnerHand = highCardHelper.compositionHighCard(hand1);
        } else if (compare < 0) {
          winnerHand = "hand2";
          winnerHandType = hand2Type;
          compositionWinnerHand = highCardHelper.compositionHighCard(hand2);
        } else {
          winnerHand = "Tie";
          winnerHandType = hand1Type;
          compositionWinnerHand = Arrays.asList("tie");
        }
      } else {

        winnerHand = "Tie";
        winnerHandType = hand1Type;
        compositionWinnerHand = Arrays.asList("tie");

      }
    }

    return new HandResponse(winnerHand, winnerHandType.getHandType(), compositionWinnerHand);

  }
}
