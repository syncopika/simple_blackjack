/**
 * simple blackjack
 * 
 * @author Nicholas Hung
 * @date 09/2017
 * 
 */

package Game;

public class Player extends AbstractPlayer{
	
	// can modify this to imitate various AI? i.e. if currentTotal is 
	// at or above a certain number, you can change state here to STAND to prevent 
	// getting anymore cards
	public void hit(Card c){
		int value = c.getValue().getValue();
		System.out.println("You received: " + c.toString());
		if(value == Value.ACE.getValue()){
			whichAceValue();
		}else{
			this.addToScore(value);
		}
		
		if(this.getScore() > BlackjackGame.TWENTY_ONE){
			this.setState(BlackjackGame.PLAYER_BUST);
		}
	}
	
	
	private void whichAceValue(){
		int aceValue = Value.ACE.getValue();
		// if the current hand's total <= 21, then use 11 (1 + 10)
		if(this.getScore() + (aceValue + 10) <= BlackjackGame.TWENTY_ONE){
			this.addToScore((aceValue + 10));
		}else{
			this.addToScore(aceValue);
		}
	}
}
