package com.adacho.event;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class RpsSolvedEvent implements Serializable {
	private static final long serialVersionUID = 7450937185178593640L;

	private final Long rpsChallengeId;
    private final Long userId;
    private final String alias;
    private final String outcome;
    
    RpsSolvedEvent() {
    	this.rpsChallengeId = 0L;
    	this.userId = 0L;
    	this.alias = null;
    	this.outcome = null;
    }
}