package com.adacho.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adacho.domain.LeaderBoardRow;
import com.adacho.service.LeaderBoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/leaders")
public class LeaderBoardController {
	private final LeaderBoardService leaderBoardService;
	
	@GetMapping
	public List<LeaderBoardRow> getLeaderBoard() {
		log.info("Retrieving leaderboard!!");
		return leaderBoardService.getCurrentLeaderBoard();
	}
}