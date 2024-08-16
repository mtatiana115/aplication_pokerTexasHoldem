package com.tati.pokerTexasHolder.infrastructure.helpers;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@AllArgsConstructor
@Slf4j
public class StrategyHelper {

  // ------------------ Valid Hand types --------------------------------

  public boolean isStraight(int[] values) {
    for (int i = 0; i < 4; i++) {
      if (values[i] + 1 != values[i + 1]) {
        return false;
      }
    }
    return true;
  }

  public boolean isThreeOfAKind(int[] values) {
    if (values[0] == values[1] && values[1] == values[2]) {
      return values[3] != values[4];
    } else if (values[1] == values[2] && values[2] == values[3]) {
      return values[0] != values[4];
    } else if (values[2] == values[3] && values[3] == values[4]) {
      return values[0] != values[1];
    }
    return false;
  }

  public boolean isTwoPair(int[] values) {
    if (values[0] == values[1] && values[2] == values[3]) {
      return values[0] != values[2] && values[4] != values[0] && values[4] != values[2];
    } else if (values[0] == values[1] && values[3] == values[4]) {
      return values[0] != values[3] && values[2] != values[0] && values[2] != values[3];
    } else if (values[1] == values[2] && values[3] == values[4]) {
      return values[1] != values[3] && values[0] != values[1] && values[0] != values[3];
    }
    return false;
  }

  public boolean isOnePair(int[] values) {
    for (int i = 0; i < 4; i++) {
      if (values[i] == values[i + 1]) {
        return true;
      }
    }
    return false;
  }
}
