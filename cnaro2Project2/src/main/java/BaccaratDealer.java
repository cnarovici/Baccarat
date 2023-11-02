import java.util.ArrayList;
import java.util.Collections;

public class BaccaratDealer {
    private ArrayList<Card> deck;
    private String[] suites = { "diamonds", "clubs", "hearts", "spades" };

    public BaccaratDealer() {
        deck = new ArrayList<>();
    }

    public void generateDeck() {
        for (int cardVal = 1; cardVal <= 13; cardVal++) {
            for (int suitesIndex = 0; suitesIndex < suites.length; suitesIndex++) {
                deck.add(new Card(suites[suitesIndex], cardVal));
            }
        }
    }

    public ArrayList<Card> dealHand() {
        ArrayList<Card> tempCards = new ArrayList<>();
        tempCards.add(deck.remove(0));
        tempCards.add(deck.remove(0));
        return tempCards;
    }

    public Card drawOne() {
        return deck.remove(0);
    }

    public void shuffleDeck() {
        deck.clear();
        generateDeck();
        Collections.shuffle(deck);
    }

    public int deckSize() {
        return deck.size();
    }

    // For Testing Purpose
    public ArrayList<Card> getDeck() {
        return deck;
    }
}