package com.tati.pokerTexasHolder.infrastructure.helpers.composition;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tati.pokerTexasHolder.infrastructure.helpers.ValuesHelper;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@AllArgsConstructor
public class HighCardHelper {

  @Autowired
  private final ValuesHelper valuesHelper;

  public List<String> compositionHighCard(String winnerHand) {
    List<String> compositionWinnerHand;
    int[] values = valuesHelper.extractValues(winnerHand);
    int mayor = values[0];
    for (int i = 0; i < 5; i++) {
      if (values[i] > mayor) {
        mayor = values[i];
      }
    }
    compositionWinnerHand = Arrays.asList(valuesHelper.convertValueToString(mayor));
    return compositionWinnerHand;
  }

}
