/**
 * simple blackjack tests
 * 
 * @author Nicholas Hung
 * @date 09/2017
 * 
 */

package tests;

import static org.junit.Assert.*;
import Game.*;

import org.junit.Test;

public class testPlayer {

	@Test
	public void test() {
		
		Player p = new Player();
		
		// check that score is initially 0 
		assertEquals(p.getScore(), 0);
		
		// check that there is no state initially
	    assertEquals(p.getState(), 0);
	    
	    // test giving the player some cards
	    Card c1 = new Card(Suit.DIAMONDS, Value.QUEEN);
	    Card c2 = new Card(Suit.CLUBS, Value.JACK);
	    p.hit(c1);
	    p.hit(c2);
	    assertEquals(p.getScore(), 20);
	    
	    // test high ace (ace = 11)
	    Card c3 = new Card(Suit.HEARTS, Value.ACE);
	    p.reset();
	    p.hit(c1);
	    p.hit(c3);
	    assertEquals(p.getScore(), 21);
	    
	    // test low ace (ace = 1)
	    p.reset();
	    assertEquals(p.getScore(), 0);
	    p.hit(c1);
	    p.hit(c2);
	    p.hit(c3);
	    assertEquals(p.getScore(), 21);
	    
	    // test player busts at appropriate amount 
	    Card c4 = new Card(Suit.SPADES, Value.THREE);
	    p.hit(c4);
	    assertEquals(p.getScore(), 24);
	    assertEquals(p.getState(), BlackjackGame.PLAYER_BUST);
	    
	    Card c5 = new Card(Suit.DIAMONDS, Value.ACE);
	    p.reset();
	    p.hit(c3);
	    p.hit(c5);
	    // for two Aces, should be 11 + 1 so as to not go over 21
	    assertEquals(p.getScore(), 12);

	    // now test if 22 leads to bust (it should)
	    Card c6 = new Card(Suit.HEARTS, Value.KING);
	    Card c7 = new Card(Suit.SPADES, Value.TWO);
	    p.reset();
	    p.hit(c6);
	    p.hit(c1);
	    p.hit(c7);
	    assertEquals(p.getScore(), 22);
	    assertEquals(p.getState(), BlackjackGame.PLAYER_BUST);
	    
	    System.out.println("Tests for the Player class passed!");
	}

}
