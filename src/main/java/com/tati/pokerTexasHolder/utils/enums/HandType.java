package com.tati.pokerTexasHolder.utils.enums;

public enum HandType {
  HIGH_CARD("HighCard"), // ORDINAL 0
  ONE_PAIR("OnePair"), // ORDINAL 1
  TWO_PAIR("TwoPair"), // ORDINAL 2
  THREE_OF_A_KIND("ThreeOfAKind"), // ORDINAL 3
  STRAIGHT("Straight"), // ORDINAL 4
  FLUSH("Flush"), // ORDINAL 5
  STRAIGHT_FLUSH("StraightFlush"); // ORDINAL 6

  private String handType;

  HandType(String handType) {
    this.handType = handType;
  }

  public String getHandType() {
    return handType;
  }

}
