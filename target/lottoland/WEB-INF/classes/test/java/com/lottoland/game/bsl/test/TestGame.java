package com.lottoland.game.bsl.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.lottoland.game.bsl.GameManagerImpl;
import com.lottoland.game.objects.Game;
import com.lottoland.game.objects.GameResult;
import com.lottoland.game.objects.Player;
import com.lottoland.game.objects.Round;
import com.lottoland.game.objects.RoundResult;
import com.lottoland.game.objects.enums.GameOptionEnum;
import com.lottoland.game.objects.enums.RoundResultEnum;
import com.lottoland.game.objects.exceptions.BadPlayerConfigurationException;

public class TestGame {
	@Rule
	public final ExpectedException exception = ExpectedException.none();

	private GameManagerImpl gameManager;

	private Player playerA;
	private Player playerB;

	@Before
	public void setup() {
		gameManager = new GameManagerImpl();
		gameManager.setGame(new Game());
		gameManager.resetGameConfigs();
	}

	@Test
	public void GameConfigs(){
		playerA = new Player(GameOptionEnum.ROCK, false);
		playerB = new Player(GameOptionEnum.PAPER, false);
		Integer numberOfRounds = 1;
		Integer zero = 0;
		gameManager.gameConfig(numberOfRounds, playerA, playerB);
		assertEquals(gameManager.getGame().getPlayerA(), playerA);
		assertEquals(gameManager.getGame().getPlayerB(), playerB);
		assertEquals(gameManager.getGame().getNumberOfRounds(), numberOfRounds);
		assertEquals(gameManager.getGame().getaWins(), zero);
		assertEquals(gameManager.getGame().getbWins(), zero);
		assertEquals(gameManager.getGame().getDraws(), zero);
		GameResult result = gameManager.play();
		assertEquals((Integer)(gameManager.getGame().getaWins()+gameManager.getGame().getbWins()+gameManager.getGame().getDraws()), numberOfRounds);
		assertEquals(result.getDraws(), gameManager.getGame().getDraws());
		assertEquals(result.getNumberOfRounds(), gameManager.getGame().getNumberOfRounds());
		assertEquals(result.getPlayerOneWins(), gameManager.getGame().getaWins());
		assertEquals(result.getPlayerTwoWins(), gameManager.getGame().getbWins());
	}
	@Test
	public void GameConfigZeroRounds(){
		playerA = new Player(GameOptionEnum.ROCK, false);
		playerB = new Player(GameOptionEnum.ROCK, false);
		gameManager.gameConfig(0, playerA, playerB);
		GameResult result = gameManager.play();

		assertFalse(result.isStatus());
	}
	@Test
	public void GameConfigNullPlayerA(){
		playerB = new Player(GameOptionEnum.ROCK, false);
		gameManager.gameConfig(1, null, playerB);
		GameResult result = gameManager.play();

		assertFalse(result.isStatus());
	}
	@Test
	public void GameConfigNullPlayerB(){
		playerA = new Player(GameOptionEnum.ROCK, false);
		gameManager.gameConfig(1, playerA, null);
		GameResult result = gameManager.play();

		assertFalse(result.isStatus());
	}
	@Test
	public void GameConfigPlayerARandomOption(){
		playerA = new Player(null, true);
		playerB = new Player(GameOptionEnum.ROCK, false);
		gameManager.gameConfig(1, playerA, playerB);
		GameResult result = gameManager.play();

		assertTrue(result.isStatus());
	}
	@Test
	public void GameConfigPlayerBRandomOption(){
		playerA = new Player(GameOptionEnum.ROCK, false);
		playerB = new Player(null, true);
		gameManager.gameConfig(1, playerA, playerB);
		GameResult result = gameManager.play();

		assertTrue(result.isStatus());
	}
	@Test
	public void GameConfigPlayerABadConfiguration(){
		playerA = new Player(null, false);
		playerB = new Player(GameOptionEnum.ROCK, false);
		gameManager.gameConfig(1, playerA, playerB);
		GameResult result = gameManager.play();

		assertFalse(result.isStatus());
	}
	@Test
	public void GameConfigPlayerBBadConfiguration(){
		playerA = new Player(GameOptionEnum.ROCK, false);
		playerB = new Player(null, false);
		gameManager.gameConfig(1, playerA, playerB);
		GameResult result = gameManager.play();

		assertFalse(result.isStatus());
	}

	//Test Game Logic
	@Test
	public void StartRoundDraw(){
		try {
			Round round = new Round(GameOptionEnum.ROCK, GameOptionEnum.ROCK);
			RoundResult result = gameManager.startRound(round);
			assertEquals(result.getResult(), RoundResultEnum.DRAW);

			round = new Round(GameOptionEnum.SCISSORS, GameOptionEnum.SCISSORS);
			result = gameManager.startRound(round);
			assertEquals(result.getResult(), RoundResultEnum.DRAW);

			round = new Round(GameOptionEnum.PAPER, GameOptionEnum.PAPER);
			result = gameManager.startRound(round);
			assertEquals(result.getResult(), RoundResultEnum.DRAW);

		} catch (BadPlayerConfigurationException e) {
			assertNull(e);
		}	
	}
	@Test
	public void StartRoundRockWins(){
		try {
			Round round = new Round(GameOptionEnum.ROCK, GameOptionEnum.SCISSORS);
			RoundResult result = gameManager.startRound(round);
			assertEquals(result.getResult(), RoundResultEnum.WIN);
			assertEquals(result.getPlayer(), GameOptionEnum.ROCK);

			round = new Round(GameOptionEnum.SCISSORS, GameOptionEnum.ROCK);
			result = gameManager.startRound(round);
			assertEquals(result.getResult(), RoundResultEnum.WIN);
			assertEquals(result.getPlayer(), GameOptionEnum.ROCK);

		} catch (BadPlayerConfigurationException e) {
			assertNull(e);
		}	
	}
	@Test
	public void StartRoundPaperWins(){
		try {
			Round round = new Round(GameOptionEnum.ROCK, GameOptionEnum.PAPER);
			RoundResult result = gameManager.startRound(round);
			assertEquals(result.getResult(), RoundResultEnum.WIN);
			assertEquals(result.getPlayer(), GameOptionEnum.PAPER);

			round = new Round(GameOptionEnum.PAPER, GameOptionEnum.ROCK);
			result = gameManager.startRound(round);
			assertEquals(result.getResult(), RoundResultEnum.WIN);
			assertEquals(result.getPlayer(), GameOptionEnum.PAPER);

		} catch (BadPlayerConfigurationException e) {
			assertNull(e);
		}	
	}
	@Test
	public void StartRoundScissorsWins(){
		try {
			Round round = new Round(GameOptionEnum.SCISSORS, GameOptionEnum.PAPER);
			RoundResult result = gameManager.startRound(round);
			assertEquals(result.getResult(), RoundResultEnum.WIN);
			assertEquals(result.getPlayer(), GameOptionEnum.SCISSORS);

			round = new Round(GameOptionEnum.PAPER, GameOptionEnum.SCISSORS);
			result = gameManager.startRound(round);
			assertEquals(result.getResult(), RoundResultEnum.WIN);
			assertEquals(result.getPlayer(), GameOptionEnum.SCISSORS);

		} catch (BadPlayerConfigurationException e) {
			assertNull(e);
		}	
	}
	@Test
	public void StartRoundBadConfiguration(){

		Round round;
		RoundResult result;

		try {				
			round = new Round(GameOptionEnum.ROCK, null);
			result = gameManager.startRound(round);
			assertTrue(false);
		} catch (BadPlayerConfigurationException e) {
			assertNotNull(e);
		}	
		try {				
			round = new Round(null, GameOptionEnum.ROCK);
			result = gameManager.startRound(round);
			assertTrue(false);
		} catch (BadPlayerConfigurationException e) {
			assertNotNull(e);
		}	
	}
	@Test
	public void ResetGameConfigs(){
		playerA = new Player(GameOptionEnum.ROCK, false);
		playerB = new Player(GameOptionEnum.PAPER, false);
		Integer numberOfRounds = 1;
		gameManager.gameConfig(numberOfRounds, playerA, playerB);
		assertEquals(gameManager.getGame().getPlayerA(), playerA);
		assertEquals(gameManager.getGame().getPlayerB(), playerB);
		assertEquals(gameManager.getGame().getNumberOfRounds(), numberOfRounds);
		gameManager.resetGameConfigs();
		assertEquals(gameManager.getGame().getPlayerA(), null);
		assertEquals(gameManager.getGame().getPlayerB(), null);
		assertEquals(gameManager.getGame().getNumberOfRounds(), new Integer(0));	
	}
	@Test
	public void ResetGameResults_aWins(){
		playerA = new Player(GameOptionEnum.ROCK, false);
		playerB = new Player(GameOptionEnum.SCISSORS, false);
		Integer numberOfRounds = 1;
		gameManager.gameConfig(numberOfRounds, playerA, playerB);
		gameManager.play();
		assertTrue(gameManager.getGame().getaWins() > 0);
		gameManager.resetGameConfigs();
		assertTrue(gameManager.getGame().getaWins() == 0);	
	}
	@Test
	public void ResetGameResults_bWins(){
		playerA = new Player(GameOptionEnum.SCISSORS, false);
		playerB = new Player(GameOptionEnum.ROCK, false);
		Integer numberOfRounds = 1;
		gameManager.gameConfig(numberOfRounds, playerA, playerB);
		gameManager.play();
		assertTrue(gameManager.getGame().getbWins() > 0);
		gameManager.resetGameConfigs();
		assertTrue(gameManager.getGame().getbWins() == 0);	
	}
	@Test
	public void ResetGameResults_draws(){
		playerA = new Player(GameOptionEnum.SCISSORS, false);
		playerB = new Player(GameOptionEnum.SCISSORS, false);
		Integer numberOfRounds = 1;
		gameManager.gameConfig(numberOfRounds, playerA, playerB);
		gameManager.play();
		assertTrue(gameManager.getGame().getDraws() > 0);
		gameManager.resetGameConfigs();
		assertTrue(gameManager.getGame().getDraws() == 0);	
	}




















}
