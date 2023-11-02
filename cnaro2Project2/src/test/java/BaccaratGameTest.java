import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class BaccaratGameTest {

    BaccaratGame testGame;
    ArrayList<Card> playerHand;
    ArrayList<Card> bankerHand;

    @Test
    public void evaluateWinnings_PlayerWin() {
        playerHand = new ArrayList<>();
        playerHand.add(new Card("Diamonds", 3));
        playerHand.add(new Card("Hearts", 3));

        bankerHand = new ArrayList<>();
        bankerHand.add(new Card("Clubs", 1));
        bankerHand.add(new Card("Spades", 1));

        testGame = new BaccaratGame();
        testGame.setPlayerHand(playerHand);
        testGame.setBankerHand(bankerHand);
        testGame.setBetSelection("Player");
        testGame.setCurrentBet(100);
        assertEquals(testGame.evaluateWinnings(), 200);
    }

    @Test
    public void evaluateWinnings_BankerWin() {
        playerHand = new ArrayList<>();
        playerHand.add(new Card("Diamonds", 1));
        playerHand.add(new Card("Hearts", 2));

        bankerHand = new ArrayList<>();
        bankerHand.add(new Card("Clubs", 3));
        bankerHand.add(new Card("Spades", 3));

        testGame = new BaccaratGame();
        testGame.setPlayerHand(playerHand);
        testGame.setBankerHand(bankerHand);
        testGame.setBetSelection("Banker");
        testGame.setCurrentBet(100);
        assertEquals(testGame.evaluateWinnings(), 195);
    }

    @Test
    public void evaluateWinnings_Draw() {
        playerHand = new ArrayList<>();
        playerHand.add(new Card("Diamonds", 3));
        playerHand.add(new Card("Hearts", 3));

        bankerHand = new ArrayList<>();
        bankerHand.add(new Card("Clubs", 3));
        bankerHand.add(new Card("Spades", 3));

        testGame = new BaccaratGame();
        testGame.setPlayerHand(playerHand);
        testGame.setBankerHand(bankerHand);
        testGame.setBetSelection("Draw");
        testGame.setCurrentBet(100);
        assertEquals(testGame.evaluateWinnings(), 900);
    }
}