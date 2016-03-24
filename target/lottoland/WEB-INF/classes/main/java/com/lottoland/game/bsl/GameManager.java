package com.lottoland.game.bsl;

import com.lottoland.game.objects.GameResult;
import com.lottoland.game.objects.Player;

public interface GameManager {

	/**
	 * Sets the configurations for the game
	 * @param numberOfRounds
	 * @param A is Player A
	 * @param B is Player B
	 */
	public void gameConfig(Integer numberOfRounds, Player A, Player B);
	
	/**
	 * The game is played with the configurations stored.
	 * GameResult.status == false represents bad configurations
	 * @return
	 */
	public GameResult play();
	
	/**
	 * Reset all game configurations
	 */
	public void resetGameConfigs();
}
