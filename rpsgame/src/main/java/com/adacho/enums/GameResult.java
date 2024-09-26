package com.adacho.enums;

public enum GameResult {
	WON("승"), LOST("패"), TIE("비김");
	
	private String commentary;
	
	private GameResult(String commentary) {
		this.commentary = commentary;
	}

	public String getCommentary() {
		return commentary;
	}	
}
