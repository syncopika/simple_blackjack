package Game;

public class Card {
	
	private Suit suit;
	private Value value;
	
	// constructor
	public Card(Suit s, Value v){
		suit = s;
		value = v;
	}
	
	// getters
	public Suit getSuit(){
		return suit;
	}
	
	public Value getValue(){
		return value;
	}
	
	// equals method
	@Override
	public boolean equals(Object o){
		if(this == o){
			// check for aliasing
			return true;
		}
		
		if(!(o instanceof Card)){
			// check object class
			return false;
		}
		
		// cast and compare
		Card c = (Card)o;
		return c.suit.equals(suit) && c.value.equals(value);
	}
	
	// hash method 
	@Override
	public int hashCode(){
		return suit.hashCode() + value.hashCode();
	}
	
	// toString method 
	@Override 
	public String toString(){
		return "Suit: " + suit + ", Value: " + value;
	}
	
}
