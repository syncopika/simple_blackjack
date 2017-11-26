package Game;

public abstract class AbstractPlayer {
	
	private int currentTotal = 0;
	private int currentState = 0;

	public int getScore(){
		return currentTotal;
	}
	
	public int getState(){
		return currentState;
	}
	
	public void setState(int state){
		currentState = state;
	}
	
	public void addToScore(int score){
		currentTotal += score;
	}
	
	// to be defined by subclass
	public abstract void hit(Card c);
	
	public void reset(){
		currentTotal = 0;
		currentState = 0;
	}
	
}
