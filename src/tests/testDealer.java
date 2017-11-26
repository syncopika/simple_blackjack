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

public class testDealer {

	@Test
	public void test() {
		
		Dealer d = new Dealer();
		
		// test initial state 
		assertEquals(d.getScore(), 0);
		assertEquals(d.getState(), BlackjackGame.HIT);
		
		// test low Ace (Ace = 1)
		Card c1 = new Card(Suit.SPADES, Value.TEN);
		Card c2 = new Card(Suit.HEARTS, Value.TEN);
		Card c3 = new Card(Suit.DIAMONDS, Value.ACE);
		
		d.hit(c1);
		d.hit(c2);
		d.hit(c3);
		assertEquals(d.getScore(), BlackjackGame.TWENTY_ONE);
		
		// test high Ace (Ace = 11)
		d.reset();
		assertEquals(d.getScore(), 0);
		d.hit(c1);
		d.hit(c3);
		assertEquals(d.getScore(), BlackjackGame.TWENTY_ONE);
		
		// make sure dealer stands at >= 17
		Card c4 = new Card(Suit.CLUBS, Value.EIGHT);
		d.reset();
		d.hit(c4);
		assertFalse(d.getState() == BlackjackGame.STAND); // make sure not standing yet
		d.hit(c2);
		assertEquals(d.getState(), BlackjackGame.STAND);
		
		Card c5 = new Card(Suit.HEARTS, Value.SEVEN);
		d.reset();
		d.hit(c5);
		d.hit(c1);
		assertEquals(d.getState(), BlackjackGame.STAND);
		
		// test bust
		Card c6 = new Card(Suit.HEARTS, Value.SIX);
		d.reset();
		d.hit(c1);
		d.hit(c6);
		d.hit(c5);
		assertEquals(d.getState(), BlackjackGame.DEALER_BUST);
		assertEquals(d.getScore(), 23);
		
		System.out.println("Tests for the Dealer class passed!");
	}

}
