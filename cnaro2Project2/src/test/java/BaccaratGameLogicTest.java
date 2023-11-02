import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class BaccaratGameLogicTest {
    BaccaratGameLogic testLogic;
    ArrayList<Card> playerHand;
    ArrayList<Card> bankerHand;

    @Test
    void BaccaratGameLogicTest_ConstructorNotNull() {
        testLogic = new BaccaratGameLogic();
        assertNotNull(testLogic);
    }

    @Test
    void whoWonTest_BankerWin() {
        testLogic = new BaccaratGameLogic();
        playerHand = new ArrayList<>();
        bankerHand = new ArrayList<>();

        playerHand.add(new Card("Spades", 2));
        playerHand.add(new Card("Diamonds", 1));

        bankerHand.add(new Card("Clubs", 3));
        bankerHand.add(new Card("Hearts", 3));

        assertEquals(testLogic.whoWon(playerHand, bankerHand), "Banker");
    }

    @Test
    void whoWonTest_PlayerWin() {
        testLogic = new BaccaratGameLogic();
        playerHand = new ArrayList<>();
        bankerHand = new ArrayList<>();

        playerHand.add(new Card("Spades", 4));
        playerHand.add(new Card("Diamonds", 1));

        bankerHand.add(new Card("Clubs", 1));
        bankerHand.add(new Card("Hearts", 3));

        assertEquals(testLogic.whoWon(playerHand, bankerHand), "Player");
    }

    @Test
    void whoWonTest_Draw() {
        testLogic = new BaccaratGameLogic();
        playerHand = new ArrayList<>();
        bankerHand = new ArrayList<>();

        playerHand.add(new Card("Spades", 1));
        playerHand.add(new Card("Diamonds", 1));

        bankerHand.add(new Card("Clubs", 1));
        bankerHand.add(new Card("Hearts", 1));

        assertEquals(testLogic.whoWon(playerHand, bankerHand), "Draw");
    }

    @Test
    void handTotalTest_DropFirstDigitWhenDoubleDigit() {
        testLogic = new BaccaratGameLogic();
        playerHand = new ArrayList<>();

        playerHand.add(new Card("Spades", 9));
        playerHand.add(new Card("Diamonds", 9));
        assertEquals(testLogic.handTotal(playerHand), 8);
    }

    @Test
    void handTotalTest_ValueGreaterThanNineAreZeroValue() {
        testLogic = new BaccaratGameLogic();
        playerHand = new ArrayList<>();

        playerHand.add(new Card("Spades", 10));
        playerHand.add(new Card("Diamonds", 12));
        playerHand.add(new Card("Clubs", 11));
        playerHand.add(new Card("Hearts", 13));
        assertEquals(testLogic.handTotal(playerHand), 0);
    }

    @Test
    void evaluatePlayerDraw_GreaterThanFiveNoDraw() {
        testLogic = new BaccaratGameLogic();
        playerHand = new ArrayList<>();

        playerHand.add(new Card("Spades", 8));
        playerHand.add(new Card("Diamonds", 9));

        assertFalse(testLogic.evaluatePlayerDraw(playerHand));
    }

    @Test
    void evaluatePlayerDraw_LessThanSixDraw() {
        testLogic = new BaccaratGameLogic();
        playerHand = new ArrayList<>();

        playerHand.add(new Card("Spades", 1));
        playerHand.add(new Card("Diamonds", 3));

        assertTrue(testLogic.evaluatePlayerDraw(playerHand));
    }

    @Test
    void evaluateBankerDraw_LessThanThreeDraw() {
        testLogic = new BaccaratGameLogic();
        bankerHand = new ArrayList<>();
        Card playerCard = new Card("Diamonds", 7);

        bankerHand.add(new Card("Clubs", 1));
        bankerHand.add(new Card("Hearts", 1));
        assertTrue(testLogic.evaluateBankerDraw(bankerHand, playerCard));
    }

    @Test
    void evaluateBankerDraw_NoDrawWhenBankerIsThreeAndPlayerIsEight() {
        testLogic = new BaccaratGameLogic();
        bankerHand = new ArrayList<>();
        Card playerCard = new Card("Diamonds", 8);

        bankerHand.add(new Card("Clubs", 2));
        bankerHand.add(new Card("Hearts", 1));
        assertFalse(testLogic.evaluateBankerDraw(bankerHand, playerCard));
    }

    @Test
    void evaluateBankerDraw_DrawWhenBankerIsFourAndPlayerNoDraw() {
        testLogic = new BaccaratGameLogic();
        bankerHand = new ArrayList<>();
        Card playerCard = null;

        bankerHand.add(new Card("Clubs", 2));
        bankerHand.add(new Card("Hearts", 2));
        assertTrue(testLogic.evaluateBankerDraw(bankerHand, playerCard));
    }

    @Test
    void evaluateBankerDraw_NoDrawWhenBankerIsFiveAndPlayerBetweenZeroAndThree() {
        testLogic = new BaccaratGameLogic();
        bankerHand = new ArrayList<>();
        Card playerCard = new Card("Diamonds", 2);

        bankerHand.add(new Card("Clubs", 2));
        bankerHand.add(new Card("Hearts", 2));
    }
}