import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class BaccaratDealerTest {

    BaccaratDealer testDealer;

    @Test
    public void generateDeckTest_CorrectSize() {
        testDealer = new BaccaratDealer();
        testDealer.generateDeck();
        assertEquals(testDealer.deckSize(), 52);
    }

    @Test
    public void generateDeckTest_CorrectValues() {
        testDealer = new BaccaratDealer();
        testDealer.generateDeck();
        ArrayList<Card> actual = testDealer.getDeck();
        ArrayList<Card> expected = new ArrayList<>();
        String[] suites = { "diamonds", "clubs", "hearts", "spades" };
        for (int i = 1; i <= 13; i++) {
            for (int k = 0; k < suites.length; k++) {
                expected.add(new Card(suites[k], i));
            }
        }
        for (int i = 0; i < 52; i++) {
            assertEquals(actual.get(i).getValue(), expected.get(i).getValue());
            assertEquals(actual.get(i).getSuite(), expected.get(i).getSuite());
        }
    }

    @Test
    public void dealHand_CorrectSize() {
        testDealer = new BaccaratDealer();
        testDealer.generateDeck();
        testDealer.dealHand();
        assertEquals(testDealer.deckSize(), 50);
    }

    @Test
    public void dealHand_CorrectValues() {
        testDealer = new BaccaratDealer();
        testDealer.generateDeck();
        ArrayList<Card> actual = testDealer.dealHand();
        ArrayList<Card> expected = new ArrayList<>();
        expected.add(new Card("diamonds", 1));
        expected.add(new Card("clubs", 1));
        for (int i = 0; i < 2; i++) {
            assertEquals(actual.get(i).getValue(), expected.get(i).getValue());
            assertEquals(actual.get(i).getSuite(), expected.get(i).getSuite());
        }
    }

    @Test
    public void drawOne_CorrectSize() {
        testDealer = new BaccaratDealer();
        testDealer.generateDeck();
        testDealer.drawOne();
        assertEquals(testDealer.deckSize(), 51);
    }

    @Test
    public void drawOne_CorrectValues() {
        testDealer = new BaccaratDealer();
        testDealer.generateDeck();
        Card actual = testDealer.drawOne();
        Card expected = new Card("diamonds", 1);
        assertEquals(actual.getValue(), expected.getValue());
        assertEquals(actual.getSuite(), expected.getSuite());
    }

    @Test
    public void shuffleDeck_CorrectSize() {
        testDealer = new BaccaratDealer();
        testDealer.shuffleDeck();
        assertEquals(testDealer.deckSize(), 52);
    }

    @Test
    public void shuffleDeck_ShuffledValues() {
        testDealer = new BaccaratDealer();
        testDealer.shuffleDeck();
        ArrayList<Card> actual = testDealer.getDeck();
        ArrayList<Card> expected = new ArrayList<>();
        String[] suites = { "diamonds", "clubs", "hearts", "spades" };
        for (int i = 1; i <= 13; i++) {
            for (int k = 0; k < suites.length; k++) {
                expected.add(new Card(suites[k], i));
            }
        }
        assertNotEquals(actual.get(7).getValue(), expected.get(7).getValue());
    }

}