package com.adacho.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.adacho.domain.LeaderBoardRow;
import com.adacho.repository.ScoreCardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LeaderBoardService {
	private final ScoreCardRepository scoreCardRepository;
	
	public List<LeaderBoardRow> getCurrentLeaderBoard() {
		return scoreCardRepository.findFirst10();
	}
}