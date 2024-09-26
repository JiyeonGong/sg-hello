package com.adacho.service;

import org.springframework.stereotype.Service;

import com.adacho.enums.RockPaperScissors;

@Service
public class RandomGeneratorService {
	//가위, 바위, 보 3개 중 하나 random으로 return 
	public RockPaperScissors getRockPaperScissors() {
		return RockPaperScissors.randomRps();
	}
}
