package com.adacho.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.adacho.domain.RpsChallenge;
import com.adacho.domain.User;
import com.adacho.enums.GameResult;
import com.adacho.enums.RockPaperScissors;
import com.adacho.event.EventDispatcher;
import com.adacho.event.RpsSolvedEvent;
import com.adacho.repository.RpsChallengeRepository;
import com.adacho.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RpsService {
	private final RandomGeneratorService randomGeneratorService;
	private final RpsChallengeRepository rpsChallengeRepository;
	private final UserRepository userRepository;
	private final EventDispatcher eventDispatcher;
	
	private RockPaperScissors createRandomRps() {
		return randomGeneratorService.getRockPaperScissors();
	}
	
	//user와 computer의 가위바위보 결과 확인
	private GameResult checkScore(RockPaperScissors userRps, RockPaperScissors computerRps) {
		GameResult result = GameResult.LOST;
		if (userRps == computerRps) {
			result = GameResult.TIE;
		} else if (userRps == RockPaperScissors.SCISSORS) {
			if (computerRps == RockPaperScissors.ROCK) {
				result = GameResult.LOST;
			} else {
				result = GameResult.WON;
			}
		} else if (userRps == RockPaperScissors.ROCK) {
			if (computerRps == RockPaperScissors.SCISSORS) {
				result = GameResult.WON;
			} else {
				result = GameResult.LOST;
			}
		} else if (userRps == RockPaperScissors.PAPER) {
			if (computerRps == RockPaperScissors.SCISSORS) {
				result = GameResult.LOST;
			} else {
				result = GameResult.WON;
			}
		}
		return result;
	}
	
	@Transactional
	public Map<String, String> checkChallenge(RpsChallenge rpsChallenge) {
		Map<String, String> map = new HashMap<String, String>();
		Optional<User> user = userRepository.findByAlias(rpsChallenge.getUser().getAlias());
		
		Assert.isNull(rpsChallenge.getGameResult(), "완료된 상태를 보낼 수 없습니다.");
		RockPaperScissors computerChoice = createRandomRps();
		GameResult gameResult = checkScore(rpsChallenge.getRps().getChallenge(), computerChoice);
		
		//기존 유저가 아닌 새로운 유저이면 rpsChallenge에서 get
		RpsChallenge checkedChallenge = new RpsChallenge(
				user.orElse(rpsChallenge.getUser()),
				rpsChallenge.getRps(),
				computerChoice,
				gameResult
			);
		rpsChallengeRepository.save(checkedChallenge);
		
		// 이벤트로 결과를 전송
        eventDispatcher.send(new RpsSolvedEvent(checkedChallenge.getId(), checkedChallenge.getUser().getId(),
                checkedChallenge.getUser().getAlias(), checkedChallenge.getGameResult().getCommentary()));
		
		map.put("opponent", computerChoice.getCommentary());
		map.put("outcome", checkedChallenge.getGameResult().getCommentary());
		map.put("userId", "" + checkedChallenge.getUser().getId());
		
		return map;
	}
	
	public List<RpsChallenge> getStateForUser(String userAlias) {
		return rpsChallengeRepository.findTop5ByUserAliasOrderByIdDesc(userAlias);
	}
}