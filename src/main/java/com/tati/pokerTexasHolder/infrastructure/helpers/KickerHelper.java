package com.tati.pokerTexasHolder.infrastructure.helpers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@AllArgsConstructor
@Slf4j
public class KickerHelper {
  private final ValuesHelper valuesHelper;

  public List<Integer> findKickers(int[] values, String keyCardValue) {
    List<Integer> kickers = new ArrayList<>();
    int keyCardInt = valuesHelper.getValue(keyCardValue);

    for (int value : values) {
      if (value != keyCardInt) {
        kickers.add(value);
      }
    }
    Collections.sort(kickers, Collections.reverseOrder());
    return kickers;
  }

  public int compareKickers(List<Integer> kickers1, List<Integer> kickers2) {
    for (int i = 0; i < Math.min(kickers1.size(), kickers2.size()); i++) {
      int compare = kickers1.get(i) - kickers2.get(i);
      if (compare != 0) {
        return compare;
      }
    }
    return 0; // Son iguales
  }
}
