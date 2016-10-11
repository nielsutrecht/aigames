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

import com.nibado.project.aigames.texas.stevebrecher.HandEval;
import com.nibado.project.aigames.texas.poker.Card;
import com.nibado.project.aigames.texas.poker.HandHoldem;
import com.nibado.project.aigames.texas.poker.PokerMove;

/**
 * This class is the brains of your com.nibado.project.aigames.texas.bot_starter. Make your calculations here and return the best move with GetMove
 */
public class BotStarter implements Bot {

	/**
	 * Implement this method to return the best move you can. Currently it will return a raise the ordinal value
	 * of one of our cards is higher than 9, a call when one of the cards has a higher ordinal value than 5 and
	 * a check otherwise.
	 * @param state : The current state of your com.nibado.project.aigames.texas.bot_starter, with all the (parsed) information given by the engine
	 * @param timeOut : The time you have to return a move
	 * @return PokerMove : The move you will be doing
	 */
	@Override
	public PokerMove getMove(BotState state, Long timeOut) {
		HandHoldem hand = state.getHand();
		String handCategory = getHandCategory(hand, state.getTable()).toString();
		System.err.printf("my hand is %s, opponent action is %s, pot: %d\n", handCategory, state.getOpponentAction(), state.getPot());
		
		// Get the ordinal values of the cards in your hand
		int height1 = hand.getCard(0).getHeight().ordinal();
		int height2 = hand.getCard(1).getHeight().ordinal();
		
		// Return the appropriate move according to our amazing strategy
		if( height1 > 9 || height2 > 9 ) {
			return new PokerMove(state.getMyName(), "raise", 2*state.getBigBlind());
		} else if( height1 > 5 && height2 > 5 ) {
			return new PokerMove(state.getMyName(), "call", state.getAmountToCall());
		} else {
			return new PokerMove(state.getMyName(), "check", 0);
		}
	}
	
	/**
	 * Calculates the com.nibado.project.aigames.texas.bot_starter's hand strength, with 0, 3, 4 or 5 cards on the table.
	 * This uses the com.nibado.project.aigames.texas.stevebrecher package to get hand strength.
	 * @param hand : cards in hand
	 * @param table : cards on table
	 * @return HandCategory with what the com.nibado.project.aigames.texas.bot_starter has got, given the table and hand
	 */
	public HandEval.HandCategory getHandCategory(HandHoldem hand, Card[] table) {
		if( table == null || table.length == 0 ) { // there are no cards on the table
			return hand.getCard(0).getHeight() == hand.getCard(1).getHeight() // return a pair if our hand cards are the same
					? HandEval.HandCategory.PAIR
					: HandEval.HandCategory.NO_PAIR;
		}
		long handCode = hand.getCard(0).getNumber() + hand.getCard(1).getNumber();
		
		for( Card card : table ) { handCode += card.getNumber(); }
		
		if( table.length == 3 ) { // three cards on the table
			return rankToCategory(HandEval.hand5Eval(handCode));
		}
		if( table.length == 4 ) { // four cards on the table
			return rankToCategory(HandEval.hand6Eval(handCode));
		}
		return rankToCategory(HandEval.hand7Eval(handCode)); // five cards on the table
	}
	
	/**
	 * small method to convert the int 'rank' to a readable enum called HandCategory
	 */
	public HandEval.HandCategory rankToCategory(int rank) {
		return HandEval.HandCategory.values()[rank >> HandEval.VALUE_SHIFT];
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BotParser parser = new BotParser(new BotStarter());
		parser.run();
	}

}
