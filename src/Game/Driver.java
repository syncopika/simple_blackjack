/**
 * simple blackjack
 * 
 * @author Nicholas Hung
 * @date 09/2017
 * 
 */

package Game;
import java.util.*;

public class Driver {

	public static void main(String[] args){
		
		Player p1 = new Player();
		Dealer d1 = new Dealer();
		BlackjackGame game1 = new BlackjackGame(p1, d1);
		Scanner scan = new Scanner(System.in);
		
		// prompt user if they want to play blackjack
		System.out.println("Play a game of blackjack? Please enter Y or y for yes, and N or n for no.");
		System.out.println("----------------------------------------");
		
		// get a response 
		String response = scan.nextLine().toLowerCase();
		
		// input validation
		while(!response.equals("y") && !response.equals("n")){
			System.out.println("Sorry, that's not a valid response. Please enter Y or y to play blackjack, or N or n to leave.");
			System.out.println("----------------------------------------");
			response = scan.nextLine().toLowerCase();
		}
		
		// start the game!
		while(response.equals("y")){
			game1.initializeGame();
			game1.initialDeal();
			game1.runGame();
			if(game1.getState() == BlackjackGame.GAME_OVER){
				System.out.println("----------------------------------------");
				System.out.println("Play again? Please enter Y or y for yes, and N or n for no.");
				
				response = scan.nextLine().toLowerCase();
				while(!response.equals("y") && !response.equals("n")){
					System.out.println("Sorry, that's not a valid response. Please enter Y or y to play blackjack, or N or n to leave.");
					response = scan.nextLine().toLowerCase();
				}
			}
		}
		
		scan.close();
	}
	
}
