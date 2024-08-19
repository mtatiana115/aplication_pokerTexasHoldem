package com.tati.pokerTexasHolder.infrastructure.helpers.composition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tati.pokerTexasHolder.api.dto.HandResponse;
import com.tati.pokerTexasHolder.infrastructure.helpers.KickerHelper;
import com.tati.pokerTexasHolder.infrastructure.helpers.ValuesHelper;
import com.tati.pokerTexasHolder.utils.enums.HandType;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@AllArgsConstructor
public class TwoPairHelper {

  @Autowired
  private final ValuesHelper valuesHelper;

  public List<String> compositionTwoPair(String winnerHand) {
    List<String> compositionWinnerHand = new ArrayList<>();
    int[] values = valuesHelper.extractValues(winnerHand);
    int pairsFound = 0;

    // Buscar los dos pares
    for (int i = 0; i < 4; i++) {
      if (values[i] == values[i + 1]) {
        compositionWinnerHand.add(valuesHelper.convertValueToString(values[i]));
        pairsFound++;
        i++; // Saltar al siguiente par
      }
      if (pairsFound == 2) {
        break;
      }
    }
    Collections.reverse(compositionWinnerHand);
    return compositionWinnerHand;
  }

  public HandResponse resolveTwoPairTie(String hand1, String hand2, KickerHelper kickerHelper) {
    List<String> pairs1 = compositionTwoPair(hand1);
    List<String> pairs2 = compositionTwoPair(hand2);

    int compareHighPair = valuesHelper.getValue(pairs1.get(0)) - valuesHelper.getValue(pairs2.get(0));
    if (compareHighPair > 0) {
      return new HandResponse("hand1", HandType.TWO_PAIR.getHandType(), pairs1);
    } else if (compareHighPair < 0) {
      return new HandResponse("hand2", HandType.TWO_PAIR.getHandType(), pairs2);
    }

    int compareLowPair = valuesHelper.getValue(pairs1.get(1)) - valuesHelper.getValue(pairs2.get(1));
    if (compareLowPair > 0) {
      return new HandResponse("hand1", HandType.TWO_PAIR.getHandType(), pairs1);
    } else if (compareLowPair < 0) {
      return new HandResponse("hand2", HandType.TWO_PAIR.getHandType(), pairs2);
    }

    int[] valuesHand1 = valuesHelper.extractValues(hand1);
    int[] valuesHand2 = valuesHelper.extractValues(hand2);

    List<Integer> kickers1 = kickerHelper.findKickers(valuesHand1, pairs1.get(0));
    List<Integer> kickers2 = kickerHelper.findKickers(valuesHand2, pairs2.get(0));

    int compareKicker = kickerHelper.compareKickers(kickers1, kickers2);
    if (compareKicker > 0) {
      return new HandResponse("hand1", HandType.TWO_PAIR.getHandType(), pairs1);
    } else if (compareKicker < 0) {
      return new HandResponse("hand2", HandType.TWO_PAIR.getHandType(), pairs2);
    } else {
      return new HandResponse("Tie", HandType.TWO_PAIR.getHandType(), Arrays.asList("tie"));
    }
  }

}
