package com.lottoland.game.objects;

import com.lottoland.game.objects.enums.GameOptionEnum;
import com.lottoland.game.objects.enums.RoundResultEnum;

public class RoundResult {

	private GameOptionEnum player;
	private RoundResultEnum result;

	public RoundResult(GameOptionEnum player, RoundResultEnum result){
		this.player = player;
		this.result = result;
	}
	public GameOptionEnum getPlayer() {
		return player;
	}
	public void setPlayer(GameOptionEnum player) {
		this.player = player;
	}
	public RoundResultEnum getResult() {
		return result;
	}
	public void setResult(RoundResultEnum result) {
		this.result = result;
	}
}
