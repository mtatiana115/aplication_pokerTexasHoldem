package com.tati.pokerTexasHolder.infrastructure.interfaces;

import com.tati.pokerTexasHolder.api.dto.HandRequest;
import com.tati.pokerTexasHolder.api.dto.HandResponse;

public interface HandEvaluator {
  HandResponse evaluateHand(HandRequest request);

}
