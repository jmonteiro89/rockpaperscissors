package com.lottoland.game.objects;


import java.util.LinkedList;
import java.util.List;

import com.lottoland.game.objects.Player;

public class Game {

	private Integer numberOfRounds;
	private Player playerA;
	private Player playerB;
	private Integer aWins;
	private Integer bWins;
	private Integer draws;
	private List<Round> rounds;


	public Integer getNumberOfRounds() {
		return numberOfRounds;
	}
	public void setNumberOfRounds(Integer numberOfRounds) {
		this.numberOfRounds = numberOfRounds;
		rounds = new LinkedList<Round>();
	}
	public Player getPlayerA() {
		return playerA;
	}
	public void setPlayerA(Player playerA) {
		this.playerA = playerA;
	}
	public Player getPlayerB() {
		return playerB;
	}
	public void setPlayerB(Player playerB) {
		this.playerB = playerB;
	}
	public Integer getaWins() {
		return aWins;
	}
	public void setaWins(Integer aWins) {
		this.aWins = aWins;
	}
	public Integer getbWins() {
		return bWins;
	}
	public void setbWins(Integer bWins) {
		this.bWins = bWins;
	}
	public Integer getDraws() {
		return draws;
	}
	public void setDraws(Integer draws) {
		this.draws = draws;
	}
	public void setRoundDraw(){
		draws++;
	}
	public void setRoundWinPlayerA(){
		aWins++;
	}
	public void setRoundWinPlayerB(){
		bWins++;
	}
	public List<Round> getRounds() {
		return rounds;
	}
	public void setRounds(List<Round> rounds) {
		this.rounds = rounds;
	}
	
	
}
