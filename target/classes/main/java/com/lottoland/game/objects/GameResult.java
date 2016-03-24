package com.lottoland.game.objects;

import java.io.Serializable;

public class GameResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6714673421458424287L;
	private boolean status = false;
	private Integer playerOneWins;
	private Integer playerTwoWins;
	private Integer draws;
	private Integer numberOfRounds;

	/**
	 * Object represents game results.
	 * @param playerOneWins
	 * @param playerTwoWins
	 * @param draws
	 * @param numberOfRounds
	 */
	public GameResult(Integer playerOneWins, Integer playerTwoWins, Integer draws, Integer numberOfRounds) {
		super();
		this.playerOneWins = playerOneWins;
		this.playerTwoWins = playerTwoWins;
		this.draws = draws;
		this.numberOfRounds = numberOfRounds;
		status = true;
	}
	
	/**
	 * Used to throw expected bad GameResult
	 * @param status
	 */
	public GameResult(boolean status){
		this.status = status;
	}
	
	public Integer getPlayerOneWins() {
		return playerOneWins;
	}
	public void setPlayerOneWins(Integer playerOneWins) {
		this.playerOneWins = playerOneWins;
	}
	public Integer getPlayerTwoWins() {
		return playerTwoWins;
	}
	public void setPlayerTwoWins(Integer playerTwoWins) {
		this.playerTwoWins = playerTwoWins;
	}
	public Integer getDraws() {
		return draws;
	}
	public void setDraws(Integer draws) {
		this.draws = draws;
	}
	public Integer getNumberOfRounds() {
		return numberOfRounds;
	}
	public void setNumberOfRounds(Integer numberOfRounds) {
		this.numberOfRounds = numberOfRounds;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString(){
		return "Game Over - " + numberOfRounds + " rounds played.\r\nPlayer One won " + playerOneWins
				+" times. \r\nPlayer Two won " + playerTwoWins + " times. \r\n"
				+ draws + " draws.";
	}
}
