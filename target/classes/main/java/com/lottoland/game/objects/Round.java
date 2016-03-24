package com.lottoland.game.objects;

import com.lottoland.game.objects.enums.GameOptionEnum;

public class Round {

	private GameOptionEnum playerA;
	private GameOptionEnum playerB;

	public Round(GameOptionEnum A, GameOptionEnum B){
		playerA = A;
		playerB = B;
	}
	
	public GameOptionEnum getPlayerA() {
		return playerA;
	}

	public void setPlayerA(GameOptionEnum playerA) {
		this.playerA = playerA;
	}

	public GameOptionEnum getPlayerB() {
		return playerB;
	}

	public void setPlayerB(GameOptionEnum playerB) {
		this.playerB = playerB;
	}


}
