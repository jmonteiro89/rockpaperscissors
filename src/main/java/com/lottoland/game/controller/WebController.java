package com.lottoland.game.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.lottoland.game.bsl.GameManager;
import com.lottoland.game.objects.GameResult;
import com.lottoland.game.objects.Player;
import com.lottoland.game.objects.enums.GameOptionEnum;


@Controller
public class WebController {

	@Autowired
	private GameManager gameManager;

	@RequestMapping(value = "/game", method = RequestMethod.GET)
	public ModelAndView game( HttpServletRequest request, HttpServletResponse response) {
		ModelAndView output = new ModelAndView("index");
		Player playerA = new Player();
		playerA.setOption(GameOptionEnum.ROCK);
		playerA.setRandomOption(false);
		GameResult result = null;
		gameManager.gameConfig(100, playerA, new Player());
		result = gameManager.play();
		output.addObject("result", result);
		return output;
	}	
}
