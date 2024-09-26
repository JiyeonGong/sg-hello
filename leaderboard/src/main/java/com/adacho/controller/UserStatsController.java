package com.adacho.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adacho.domain.GameStats;
import com.adacho.service.GameService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stats")
public class UserStatsController {
	private final GameService gameService;
	
	@GetMapping
	public GameStats getStatsForUser(@RequestParam final Long userId) {
		return gameService.retrieveStatsForUser(userId);
	}
}