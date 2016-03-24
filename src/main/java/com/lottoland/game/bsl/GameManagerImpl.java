package com.lottoland.game.bsl;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import com.lottoland.game.objects.GameResult;
import com.lottoland.game.objects.Game;
import com.lottoland.game.objects.Player;
import com.lottoland.game.objects.Round;
import com.lottoland.game.objects.RoundResult;
import com.lottoland.game.objects.enums.GameOptionEnum;
import com.lottoland.game.objects.enums.RoundResultEnum;
import com.lottoland.game.objects.exceptions.BadPlayerConfigurationException;

public class GameManagerImpl implements GameManager {

	@Autowired
	private Game game;

	@Override
	public void gameConfig(Integer numberOfRounds, Player A, Player B) {
		resetGameConfigs();
		game.setPlayerA(A);
		game.setPlayerB(B);
		game.setNumberOfRounds(numberOfRounds);

	}

	@Override
	public GameResult play() {
		if(game.getNumberOfRounds() <= 0 || game.getPlayerA() == null || game.getPlayerB() == null || 
				(game.getPlayerA().getOption() == null && !game.getPlayerA().isRandomOption()) ||
				(game.getPlayerB().getOption() == null && !game.getPlayerB().isRandomOption()) ){
			return new GameResult(false);
		}
		List<Round> rounds = new LinkedList<Round>();
		GameResult result = new GameResult(false);
		try {
			for(int i = 1; i <= game.getNumberOfRounds(); i++){
				if(game.getPlayerA().isRandomOption()){
					Random rand = new Random();
					int option = rand.nextInt(GameOptionEnum.values().length);
					game.getPlayerA().setOption(GameOptionEnum.values()[option]);
				}
				if(game.getPlayerB().isRandomOption()){
					Random rand = new Random();
					int option = rand.nextInt(GameOptionEnum.values().length);
					game.getPlayerB().setOption(GameOptionEnum.values()[option]);
				}
				Round round = new Round(game.getPlayerA().getOption(), game.getPlayerB().getOption());
				RoundResult play = startRound(round);
				if(play.getResult().equals(RoundResultEnum.WIN)){
					if(play.getPlayer().equals(game.getPlayerA().getOption())){
						game.setRoundWinPlayerA();
					} else if( play.getPlayer().equals(game.getPlayerB().getOption())){
						game.setRoundWinPlayerB();
					}
				} else if(play.getResult().equals(RoundResultEnum.DRAW)){
					game.setRoundDraw();
				}
				rounds.add(round);
			}
		} catch(BadPlayerConfigurationException e){
			System.out.println("Bad Player Configuration");
		}
		result.setStatus(true);
		result.setDraws(game.getDraws());
		result.setPlayerOneWins(game.getaWins());
		result.setPlayerTwoWins(game.getbWins());
		result.setNumberOfRounds(game.getNumberOfRounds());
		return result;
	}


	public RoundResult startRound(Round round) throws BadPlayerConfigurationException {
		if( round.getPlayerA() == null || round.getPlayerB() == null){
			throw new BadPlayerConfigurationException();
		}
		if(round.getPlayerA().equals(GameOptionEnum.ROCK)){
			if(round.getPlayerB().equals(GameOptionEnum.ROCK)){
				return new RoundResult(null, RoundResultEnum.DRAW);
			}else if(round.getPlayerB().equals(GameOptionEnum.SCISSORS)){
				return new RoundResult(round.getPlayerA(), RoundResultEnum.WIN);
			}else if(round.getPlayerB().equals(GameOptionEnum.PAPER)){
				return new RoundResult(round.getPlayerB(), RoundResultEnum.WIN);
			}
		} else if(round.getPlayerA().equals(GameOptionEnum.SCISSORS)){
			if(round.getPlayerB().equals(GameOptionEnum.ROCK)){
				return new RoundResult(round.getPlayerB(), RoundResultEnum.WIN);
			}else if(round.getPlayerB().equals(GameOptionEnum.SCISSORS)){
				return new RoundResult(null, RoundResultEnum.DRAW);
			}else if(round.getPlayerB().equals(GameOptionEnum.PAPER)){
				return new RoundResult(round.getPlayerA(), RoundResultEnum.WIN);
			}
		} else if(round.getPlayerA().equals(GameOptionEnum.PAPER)){
			if(round.getPlayerB().equals(GameOptionEnum.ROCK)){
				return new RoundResult(round.getPlayerA(), RoundResultEnum.WIN);
			}else if(round.getPlayerB().equals(GameOptionEnum.SCISSORS)){
				return new RoundResult(round.getPlayerB(), RoundResultEnum.WIN);
			}else if(round.getPlayerB().equals(GameOptionEnum.PAPER)){
				return new RoundResult(null, RoundResultEnum.DRAW);
			}
		}
		return null;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	@Override
	public void resetGameConfigs() {
		game.setaWins(0);
		game.setbWins(0);
		game.setDraws(0);
		game.setNumberOfRounds(0);
		game.setPlayerA(null);
		game.setPlayerB(null);
	}


}
