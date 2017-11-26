/**
 * simple blackjack
 * 
 * @author Nicholas Hung
 * @date 09/2017
 * 
 */

package Game;

public class Dealer extends AbstractPlayer{
	
	public void hit(Card c){
		int value = c.getValue().getValue();
		if(value == Value.ACE.getValue()){
			whichAceValue();
		}else{
			this.addToScore(value);
		}
		
		if(this.getScore() >= 17){
			this.setState(BlackjackGame.STAND);
		}
		
		if(this.getScore() >= BlackjackGame.TWENTY_ONE){
			this.setState(BlackjackGame.DEALER_BUST);
		}
	}

	
	private void whichAceValue(){
		int aceValue = Value.ACE.getValue();
		if(this.getScore() + (aceValue + 10) <= BlackjackGame.TWENTY_ONE){
			this.addToScore((aceValue + 10));
		}else{
			this.addToScore(aceValue);
		}
	}
	
}
