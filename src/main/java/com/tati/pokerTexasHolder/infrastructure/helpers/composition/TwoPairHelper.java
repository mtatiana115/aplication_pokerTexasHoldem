package com.tati.pokerTexasHolder.infrastructure.helpers.composition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tati.pokerTexasHolder.infrastructure.helpers.ValuesHelper;

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
}
