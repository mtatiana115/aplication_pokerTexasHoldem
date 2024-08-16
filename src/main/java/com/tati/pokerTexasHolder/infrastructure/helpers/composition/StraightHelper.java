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
public class StraightHelper {

  @Autowired
  private final ValuesHelper valuesHelper;

  public List<String> compositionStraight(String winnerHand) {
    List<String> compositionWinnerHand = new ArrayList<>();
    int[] values = valuesHelper.extractValues(winnerHand);

    // Añadir todas las cartas ya que son parte del Straight
    for (int value : values) {
      compositionWinnerHand.add(valuesHelper.convertValueToString(value));
    }
    return compositionWinnerHand;
  }

}
