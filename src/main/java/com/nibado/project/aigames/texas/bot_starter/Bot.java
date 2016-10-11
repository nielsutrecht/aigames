/**
 * www.TheAIGames.com 
 * Heads Up Omaha pokerbot
 *
 * Last update: May 07, 2014
 *
 * @author Jim van Eeden, Starapple
 * @version 1.0
 * @License MIT License (http://opensource.org/Licenses/MIT)
 */


package com.nibado.project.aigames.texas.bot_starter;

import com.nibado.project.aigames.texas.poker.PokerMove;

public interface Bot {

	public PokerMove getMove(BotState state, Long timeOut);

}
