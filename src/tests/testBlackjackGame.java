/**
 * simple blackjack tests
 * 
 * @author Nicholas Hung
 * @date 09/2017
 * 
 */

package tests;

import static org.junit.Assert.*;

import org.junit.Test;
import Game.*;
import java.util.*;

public class testBlackjackGame {
	
	static final int NUM_CARDS_PER_SUIT = 13;
	static final int NUM_CARDS_PER_VALUE = 4;
	static final int NUM_SUITS = 4;
	static final int INITIAL_DEAL = 4;
	static final int DECK_SIZE = 52;

	@Test
	public void testBlackjack() {
		
		Player p = new Player();
		Dealer d = new Dealer();
		BlackjackGame blackjack = new BlackjackGame(p, d);
		
		assertEquals(blackjack.getState(), BlackjackGame.GAME_IN_PROGRESS);
		
		// initialize the game (i.e. get the deck ready)
		blackjack.initializeGame();
		ArrayList<Card> theDeck = blackjack.getDeck();
		assertEquals(theDeck.size(), DECK_SIZE);
		
		// confirm that there are 13 cards for each suit
		HashMap<Suit, Integer> suitCount = new HashMap<Suit, Integer>();
		HashMap<Value, Integer> valueCount = new HashMap<Value, Integer>();
		
		for(Card c : theDeck){
			Suit s = c.getSuit();
			Value v = c.getValue();
			
			// record suits 
			if(suitCount.containsKey(s)){
				int currValue = suitCount.get(s);
				suitCount.put(s, ++currValue);
			}else{
				suitCount.put(s, 1);
			}
			
			// record values
			if(valueCount.containsKey(v)){
				int currValue = valueCount.get(v);
				valueCount.put(v, ++currValue);
			}else{
				valueCount.put(v, 1);
			}
		}
		
		// make sure there are 4 suits in the deck
		assertEquals(suitCount.keySet().size(), NUM_SUITS);
		
		// check that there are 13 of each suit in the deck
		for(Suit suit : suitCount.keySet()){
			assertEquals((int)suitCount.get(suit), NUM_CARDS_PER_SUIT);
		}
		
		// check that there are 4 of each value in the deck
		for(Value value : valueCount.keySet()){
			assertEquals((int)valueCount.get(value), NUM_CARDS_PER_VALUE);
		}
		
		// make sure initialDeal distributes 4 cards
		blackjack.initialDeal();
		assertEquals(blackjack.getDeck().size(), DECK_SIZE - INITIAL_DEAL);
	
		System.out.println("Tests for BlackjackGame class passed!");
	}

}
