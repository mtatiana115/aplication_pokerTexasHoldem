package com.tati.pokerTexasHolder.infrastructure.helpers;

import java.util.Arrays;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@AllArgsConstructor
public class ValuesHelper {

  public int[] extractValues(String hand) {
    String[] cards = hand.split(" ");
    int[] values = new int[5];

    for (int i = 0; i < 5; i++) {
      // Extraer el valor de la carta
      String value = cards[i].substring(0, cards[i].length() - 1);
      // Convertir el valor de la carta en un número entero
      values[i] = getValue(value);
    }

    Arrays.sort(values);
    return values;
  }

  public char[] extractSuits(String hand) {
    char[] suits = new char[5];
    String[] cards = hand.split(" ");
    for (int i = 0; i < 5; i++) {
      suits[i] = cards[i].charAt(cards[i].length() - 1);
    }
    return suits;
  }

  public int getValue(String card) {
    log.info("Card: {}", card);
    try {
      switch (card) {
        case "A":
          return 14;
        case "K":
          return 13;
        case "Q":
          return 12;
        case "J":
          return 11;
        case "10":
          return 10;
        default:
          return Integer.parseInt(card);
      }
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Invalid card value: " + card);
    }

  }

  public String convertValueToString(int value) {
    switch (value) {
      case 14:
        return "As";
      case 13:
        return "King";
      case 12:
        return "Queen";
      case 11:
        return "J";
      default:
        return String.valueOf(value);
    }
  }

  public String convertSuitToString(char suit) {
    switch (suit) {
      case 'S':
        return "Spade";
      case 'H':
        return "Heart";
      case 'D':
        return "Diamond";
      case 'C':
        return "Club";
      default:
        return String.valueOf(suit);
    }
  }

}
