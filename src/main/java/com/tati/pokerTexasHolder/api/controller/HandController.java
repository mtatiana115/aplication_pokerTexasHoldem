package com.tati.pokerTexasHolder.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tati.pokerTexasHolder.api.dto.HandRequest;
import com.tati.pokerTexasHolder.api.dto.HandResponse;
import com.tati.pokerTexasHolder.infrastructure.services.HandService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/poker/validation")
@Slf4j
@AllArgsConstructor
public class HandController {

  @Autowired
  private final HandService handService;

  @PostMapping
  public HandResponse evaluatePokerHands(@RequestBody HandRequest request) {
    log.info("Request received: {}", request);
    return handService.analyzeHands(request);
  }
}
