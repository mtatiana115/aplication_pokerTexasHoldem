package com.tati.pokerTexasHolder.infrastructure.helpers.composition;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tati.pokerTexasHolder.infrastructure.helpers.ValuesHelper;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@AllArgsConstructor
public class OnePairHelper {

  @Autowired
  private final ValuesHelper valuesHelper;

  public List<String> compositionOnePair(String winnerHand) {
    List<String> compositionWinnerHand = new ArrayList<>();

    int[] values = valuesHelper.extractValues(winnerHand);

    // Buscar el par
    for (int i = 0; i < 4; i++) {
      if (values[i] == values[i + 1]) {
        compositionWinnerHand.add(valuesHelper.convertValueToString(values[i]));
        break;
      }
    }
    return compositionWinnerHand;
  }

}
