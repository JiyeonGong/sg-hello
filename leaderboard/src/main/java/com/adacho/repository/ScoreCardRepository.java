package com.adacho.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.adacho.domain.LeaderBoardRow;
import com.adacho.domain.ScoreCard;

public interface ScoreCardRepository extends JpaRepository<ScoreCard, Long> {
	@Query("SELECT SUM(s.score) FROM com.adacho.domain.ScoreCard s WHERE s.userId = :userId GROUP BY s.userId")
	int getTotalScoreForUser(@Param("userId") final Long userId);

	@Query("SELECT NEW com.adacho.domain.LeaderBoardRow(s.userId, s.alias, SUM(s.score)) "
			+ "FROM com.adacho.domain.ScoreCard s "
			+ "GROUP BY s.userId, s.alias ORDER BY SUM(s.score) DESC")
	List<LeaderBoardRow> findFirst10();
	
	List<ScoreCard> findByUserIdOrderByScoreTimestampDesc(final Long userId);
}