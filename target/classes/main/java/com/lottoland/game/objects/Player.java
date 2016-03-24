package com.lottoland.game.objects;

import com.lottoland.game.objects.enums.GameOptionEnum;

public class Player {

	private GameOptionEnum option = null;
	private boolean randomOption = true;

	public Player(){}
	/**
	 * Player constructor
	 * @param option - represents the option that the player is going to play
	 * @param randomOption - if true, every round this player will choose a random option to play.
	 */
	public Player(GameOptionEnum option, boolean randomOption){
		this.option = option;
		this.randomOption = randomOption;
	}
	public GameOptionEnum getOption() {
		return option;
	}

	public void setOption(GameOptionEnum option) {
		this.option = option;
	}

	public boolean isRandomOption() {
		return randomOption;
	}

	public void setRandomOption(boolean randomOption) {
		this.randomOption = randomOption;
	}

}
