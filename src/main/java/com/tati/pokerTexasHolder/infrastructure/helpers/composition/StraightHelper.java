package com.tati.pokerTexasHolder.infrastructure.helpers.composition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tati.pokerTexasHolder.api.dto.HandResponse;
import com.tati.pokerTexasHolder.infrastructure.helpers.ValuesHelper;
import com.tati.pokerTexasHolder.utils.enums.HandType;

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

  public HandResponse resolveStraightTie(String hand1, String hand2) {
    List<String> values1 = compositionStraight(hand1);
    List<String> values2 = compositionStraight(hand2);

    int highCard1 = valuesHelper.getValue(values1.get(4));
    int highCard2 = valuesHelper.getValue(values2.get(4));

    if (highCard1 > highCard2) {
      return new HandResponse("hand1", HandType.STRAIGHT.getHandType(), values1);
    } else if (highCard1 < highCard2) {
      return new HandResponse("hand2", HandType.STRAIGHT.getHandType(), values2);
    } else {
      return new HandResponse("Tie", HandType.STRAIGHT.getHandType(), Arrays.asList("tie"));
    }
  }

}
