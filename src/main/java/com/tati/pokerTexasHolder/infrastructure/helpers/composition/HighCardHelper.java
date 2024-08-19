package com.tati.pokerTexasHolder.infrastructure.helpers.composition;

import java.util.Arrays;
import java.util.ArrayList;
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

  public HandResponse resolveHighCardTie(String hand1, String hand2) {
    int[] valuesHand1 = valuesHelper.extractValues(hand1);
    int[] valuesHand2 = valuesHelper.extractValues(hand2);

    // Comparar cada carta desde la más alta hasta la más baja
    for (int i = valuesHand1.length - 1; i >= 0; i--) {
      int compare = valuesHand1[i] - valuesHand2[i];
      if (compare > 0) {
        return new HandResponse("hand1", HandType.HIGH_CARD.getHandType(), compositionHighCard(hand1));
      } else if (compare < 0) {
        return new HandResponse("hand2", HandType.HIGH_CARD.getHandType(), compositionHighCard(hand2));
      }
    }

    // Si todas las cartas son iguales, es un empate
    return new HandResponse("Tie", HandType.HIGH_CARD.getHandType(), List.of("tie"));
  }
}
