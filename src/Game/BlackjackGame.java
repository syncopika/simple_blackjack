/**
 * simple blackjack
 * 
 * @author Nicholas Hung
 * @date 09/2017
 * 
 */

package Game;
import java.util.*;

public class BlackjackGame {

	private ArrayList<Card> deck;
	
	// keep track of status of the game
	private int currentStatus;
	
	// keep track of players
	private Player player;
	private Dealer dealer;
	
	// important constant values
	public final static int TWENTY_ONE = 21;
	public final static int HIT = 0;
	public final static int STAND = 1;
	
	private final static int DEALER_WINS = 1;
	private final static int PLAYER_WINS = 2;
	private final static int PLAYER_TIE = 3;
	
	public final static int PLAYER_BUST = 4;
	public final static int DEALER_BUST = 5;
	public final static int GAME_IN_PROGRESS = 6;
	public final static int GAME_OVER = 7;
	
	// constructor
	public BlackjackGame(Player p, Dealer d){
		dealer = d;
		player = p;
		deck = new ArrayList<Card>();
		currentStatus = GAME_IN_PROGRESS;
	}
	
	/**
	 *  initialize the game
	 *  
	 *  empties the deck, 
	 *  resets player and dealer's totals to 0,
	 *  creates a new deck,
	 *  shuffles the deck,
	 *  and deals 2 cards to each player
	 *  
	 */
	public void initializeGame(){
		deck.clear();
		player.reset();
		dealer.reset();
		createDeck();
		shuffleDeck();
	}
	
	/**
	 *  run a blackjack game 
	 */
	public void runGame(){
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("(after inital deal) The player's total is now: " + player.getScore());
		
		// check to see if anyone wins just based on the initial draw
		evaluateTurn();
		
		// otherwise, keep playing
		if(currentStatus != GAME_OVER){
			
			promptPlayer(scan);
			
			// player gets to hit first as many times as they want
			while(player.getState() == HIT && player.getState() != PLAYER_BUST && currentStatus != GAME_OVER){
				player.hit(drawCard());
				System.out.println("The player's total is now: " + player.getScore());
				evaluateTurn();
				if(currentStatus != GAME_OVER){
					promptPlayer(scan);
				}
			}
			
			// now the dealer's turn
			while(dealer.getState() != STAND && dealer.getState() != DEALER_BUST && currentStatus != GAME_OVER){
				dealer.hit(drawCard());
				evaluateTurn();
			}
			
			if(currentStatus != GAME_OVER){
				evaluateTurn();
			}
		}
	}
	
	/**
	 * 	see the deck
	 */
	public ArrayList<Card> getDeck(){
		return deck;
	}
	
	/**
	 * 	get current state of game
	 */
	public int getState(){
		return currentStatus;
	}
	
	/**
	 * 	deal each player 2 cards initially
	 */
	public void initialDeal(){
		// 2 cards each 
		for(int i = 0; i < 2; i++){
			player.hit(drawCard());
			dealer.hit(drawCard());
		}
	}
	
	/**
	 *  draw a card from the deck
	 *  this removes 1 Card object from the deck
	 */
	private Card drawCard(){
		Card card = deck.get(deck.size() - 1);
		deck.remove(deck.size() - 1);
		return card;
	}
	
	/**
	 *  create the deck
	 *  fills the deck with 52 Card objects
	 */
	private void createDeck(){
		for(Suit s : Suit.values()){
			for(Value v : Value.values()){
				deck.add(new Card(s, v));
			}
		}
	}
	
	/**
	 *  shuffle the deck 
	 */
	private void shuffleDeck(){
		Collections.shuffle(deck);
	}
	
	/**
	 *  check if bust
	 *  returns True if the passed-in value is greater or equal to 22
	 *  returns False if the passed-in value is less than 22 
	
	private boolean isBust(int val){
		return val >= BUST;
	} */
	
	/** 
	 * 	check players' scores
	 * 	returns an int based on current status 
	*/
	private int checkScores(){
		
		int playerScore = player.getScore();
		int dealerScore = dealer.getScore();
		int playerState = player.getState();
		int dealerState = dealer.getState();
		
		if(playerScore == dealerScore && dealerState != DEALER_BUST && playerState == STAND){
			/*
			 *	player and dealer tie
			 */
			printCurrentScores(playerScore, dealerScore);
			System.out.println("The dealer and player tied.");
			currentStatus = PLAYER_TIE;
			return GAME_OVER;
		}else if(playerState == PLAYER_BUST && dealerState == DEALER_BUST){
			/*
			 *	both the player and dealer bust
			 */
			printCurrentScores(playerScore, dealerScore);
			System.out.println("Both players bust. Dealer wins.");
			currentStatus = DEALER_WINS;
			return GAME_OVER;
		}else if(playerScore == TWENTY_ONE){
			/*
			 *  player gets 21
			 */
			printCurrentScores(playerScore, dealerScore);
			System.out.println("Player has 21! Player wins! :D");
			currentStatus = PLAYER_WINS;
			return GAME_OVER;
		}else if(dealerScore == TWENTY_ONE){
			/*
			 *  dealer gets 21
			 */
			printCurrentScores(playerScore, dealerScore);
			System.out.println("The dealer has 21. Player loses. :(");
			currentStatus = DEALER_WINS;
			return GAME_OVER;
		}else if(dealerState == STAND && playerState == PLAYER_BUST){
			/*
			 *  if the dealer is standing but the player busts
			 */
			printCurrentScores(playerScore, dealerScore);
			System.out.println("Player busts.");
			System.out.println("Dealer wins!");
			currentStatus = DEALER_WINS;
			return GAME_OVER;
		}else if(dealerState == DEALER_BUST && playerState == STAND){
			/*
			 *  if the player is standing but the dealer busts
			 */
			printCurrentScores(playerScore, dealerScore);
			System.out.println("Dealer busts.");
			System.out.println("Player wins! :D");
			currentStatus = PLAYER_WINS;
			return GAME_OVER;
		}else if(playerState == STAND && dealerState == STAND){
			/*
			 * when player decides to stand and dealer is also standing
			 */
			printCurrentScores(playerScore, dealerScore);
			if(TWENTY_ONE - playerScore < TWENTY_ONE - dealerScore){
				System.out.println("Player wins!");
			}else{
				System.out.println("Dealer wins.");
			}
			return GAME_OVER;
		}else{
			currentStatus = GAME_IN_PROGRESS;
			return GAME_IN_PROGRESS;
		}
	}
	
	
	/**
	 *  evaluate a turn
	 *  checks to see if a hit resulted in a change in the currentStatus field
	 *  if the currentStatus field was changed to GAME_OVER,
	 *  getResult() is called.
	 */
	private void evaluateTurn(){
		if(checkScores() == GAME_OVER){
			currentStatus = GAME_OVER;
		}
	}
	
	/**
	 * 	validate user input
	 */
	private boolean validInput(String s){
		return s.equals("hit") || s.equals("stand");
	}
	
	/**
	 * 	print player and dealer scores
	 */
	private void printCurrentScores(int playerScore, int dealerScore){
		System.out.println("Player score: " + playerScore);
		System.out.println("Dealer score: " + dealerScore);
	}
	
	/**
	 * 	prompt the player if they want to hit or stand,
	 *  so long as they haven't busted yet
	 *  
	 */
	private void promptPlayer(Scanner scan){
		
		String userInput;
		
		// as long as player did not bust, see if they want to hit 
		if(player.getState() != PLAYER_BUST){
			System.out.println("Would you like to hit or stand? (please type in \"hit\" or \"stand\")");
		
			userInput = scan.nextLine().toLowerCase();
	
			while(!validInput(userInput)){
				System.out.println("Sorry, that is not a valid choice. Please type in either \"hit\" or \"stand\"");
				userInput = scan.nextLine().toLowerCase();
			}
			
			if(userInput.equals("hit")){
				player.setState(HIT);
			}else if(userInput.equals("stand")){
				player.setState(STAND);
				System.out.println("Player decided to stand...");
			}	
		}
	}
	
}
