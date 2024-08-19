package com.tati.pokerTexasHolder.infrastructure.helpers.composition;

import java.util.ArrayList;
import java.util.Arrays;
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

  public HandResponse resolveThreeOfAKindTie(String hand1, String hand2, KickerHelper kickerHelper) {
    List<String> values1 = compositionThreeOfAKind(hand1);
    List<String> values2 = compositionThreeOfAKind(hand2);

    int compareTrio = valuesHelper.getValue(values1.get(0)) - valuesHelper.getValue(values2.get(0));
    if (compareTrio > 0) {
      return new HandResponse("hand1", HandType.THREE_OF_A_KIND.getHandType(), values1);
    } else if (compareTrio < 0) {
      return new HandResponse("hand2", HandType.THREE_OF_A_KIND.getHandType(), values2);
    } else {
      int[] valuesHand1 = valuesHelper.extractValues(hand1);
      int[] valuesHand2 = valuesHelper.extractValues(hand2);

      List<Integer> kickers1 = kickerHelper.findKickers(valuesHand1, values1.get(0));
      List<Integer> kickers2 = kickerHelper.findKickers(valuesHand2, values2.get(0));

      int compareKickers = kickerHelper.compareKickers(kickers1, kickers2);
      if (compareKickers > 0) {
        return new HandResponse("hand1", HandType.THREE_OF_A_KIND.getHandType(), values1);
      } else if (compareKickers < 0) {
        return new HandResponse("hand2", HandType.THREE_OF_A_KIND.getHandType(), values2);
      } else {
        return new HandResponse("Tie", HandType.THREE_OF_A_KIND.getHandType(), Arrays.asList("tie"));
      }
    }
  }
}
