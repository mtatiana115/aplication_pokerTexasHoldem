package com.tati.pokerTexasHolder.infrastructure.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tati.pokerTexasHolder.api.dto.HandRequest;
import com.tati.pokerTexasHolder.api.dto.HandResponse;
import com.tati.pokerTexasHolder.infrastructure.helpers.HandHelper;
import com.tati.pokerTexasHolder.infrastructure.helpers.TieHelper;
import com.tati.pokerTexasHolder.infrastructure.helpers.composition.CompositionHelper;
import com.tati.pokerTexasHolder.infrastructure.interfaces.HandEvaluator;
import com.tati.pokerTexasHolder.utils.enums.HandType;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class HandService implements HandEvaluator {

  @Autowired
  private final HandHelper handHelper;

  @Autowired
  private final CompositionHelper compositionHelper;

  @Autowired
  private final TieHelper tieHelper;

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
    if (comparison > 0) {
      return buildHandResponse("hand1", hand1Type, compositionHelper.validComposition(hand1, hand1Type));
    } else if (comparison < 0) {
      return buildHandResponse("hand2", hand2Type, compositionHelper.validComposition(hand2, hand2Type));
    } else {
      return tieHelper.resolveTie(hand1, hand2, hand1Type);
    }
  }

  private HandResponse buildHandResponse(String winnerHand, HandType winnerHandType,
      List<String> compositionWinnerHand) {
    return new HandResponse(winnerHand, winnerHandType.getHandType(), compositionWinnerHand);
  }
}
