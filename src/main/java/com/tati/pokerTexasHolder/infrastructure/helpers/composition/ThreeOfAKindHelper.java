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
public class ThreeOfAKindHelper {

  @Autowired
  private final ValuesHelper valuesHelper;

  public List<String> compositionThreeOfAKind(String winnerHand) {
    List<String> compositionWinnerHand = new ArrayList<>();
    int[] values = valuesHelper.extractValues(winnerHand);

    // Buscar el valor que aparece tres veces
    for (int i = 0; i < 3; i++) {
      if (values[i] == values[i + 1] && values[i + 1] == values[i + 2]) {
        compositionWinnerHand.add(valuesHelper.convertValueToString(values[i]));
        break;
      }
    }
    return compositionWinnerHand;
  }
}
