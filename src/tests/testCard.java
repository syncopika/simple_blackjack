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

public class testCard {

	@Test
	public void test() {
		// test card creation
		Card c1 = new Card(Suit.CLUBS, Value.ACE);
		Card c2 = new Card(Suit.CLUBS, Value.ACE);
		int value = c1.getValue().getValue();
		
		// check equals method
		assertEquals(c1.equals(c2), true);
		
		// check hash 
		assertEquals(c1.hashCode(), c2.hashCode());
		
		// check getters 
		assertEquals(c1.getSuit(), Suit.CLUBS);
		assertEquals(value, Value.ACE.getValue());
		
		Card c3 = new Card(Suit.HEARTS, Value.TWO);
		Card c4 = new Card(Suit.DIAMONDS, Value.TWO);
		int c3value = c3.getValue().getValue();
		int c4value = c4.getValue().getValue();
		
		// make sure their suits are not the same 
		assertFalse(c3.getSuit().equals(c4.getSuit()));
		
		// make sure their values are the same
		assertEquals(c3value, c4value);
		
		System.out.println("Tests for Card class passed!");
	}

}
