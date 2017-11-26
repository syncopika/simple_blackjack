package Game;

public enum Value {
	ACE(1),
	TWO(2),
	THREE(3),
	FOUR(4),
	FIVE(5),
	SIX(6),
	SEVEN(7),
	EIGHT(8),
	NINE(9),
	TEN(10),
	JACK(10),
	KING(10),
	QUEEN(10);
	
	// each enum value will be mapped to an int value
	private int value;
	
	Value(int val){
		value = val;
	}
	
	// get the value from the enum value
	public int getValue(){
		return value;
	}
}
