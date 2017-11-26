package tests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
	testCard.class,
	testBlackjackGame.class,
	testPlayer.class,
	testDealer.class
})

public class TestSuite {
}
