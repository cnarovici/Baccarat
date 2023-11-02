import java.util.ArrayList;

public class BaccaratGameLogic {
    public String whoWon(ArrayList<Card> hand1, ArrayList<Card> hand2) {
        int playerVal = handTotal(hand1);
        int bankerVal = handTotal(hand2);

        if (playerVal == 8 || playerVal == 9) {
            return "Player";
        }
        if (bankerVal == 8 || bankerVal == 9) {
            return "Banker";
        }
        if (playerVal == bankerVal) {
            return "Draw";
        }

        int val1 = 9 - playerVal;
        int val2 = 9 - bankerVal;
        if (val1 == val2) {
            return "Draw";
        }
        if (val1 < val2) {
            return "Player";
        } else {
            return "Banker";
        }
    }

    public int handTotal(ArrayList<Card> hand) {
        int sum = 0;
        for (int i = 0; i < hand.size(); i++) {
            int currVal = hand.get(i).getValue();
            if (currVal < 10) {
                sum += currVal;
            }
        }
        return sum % 10;
    }

    public boolean evaluateBankerDraw(ArrayList<Card> hand, Card playerCard) {
        int val1 = 0, val2 = 0;
        if (hand.get(0).getValue() < 10) {
            val1 = hand.get(0).getValue();
        }
        if (hand.get(1).getValue() < 10) {
            val2 = hand.get(1).getValue();
        }
        int totVal = (val1 + val2) % 10;
        if (totVal < 3) {
            return true;
        }
        switch (totVal) {
            case 3:
                if (playerCard != null && playerCard.getValue() == 8) {
                    return false;
                }
                return true;
            case 4:
                if (playerCard == null) {
                    return true;
                }
                if (playerCard.getValue() > 1 && playerCard.getValue() < 8) {
                    return true;
                }
                return false;
            case 5:
                if (playerCard == null) {
                    return true;
                }
                if (playerCard.getValue() > 3 && playerCard.getValue() < 8) {
                    return true;
                }
                return false;
            case 6:
                if (playerCard == null) {
                    return false;
                }
                if (playerCard.getValue() == 6 || playerCard.getValue() == 7) {
                    return true;
                }
                return false;
            default:
                return false;
        }
    }

    public boolean evaluatePlayerDraw(ArrayList<Card> hand) {
        int val1 = 0, val2 = 0;
        if (hand.get(0).getValue() < 10) {
            val1 = hand.get(0).getValue();
        }
        if (hand.get(1).getValue() < 10) {
            val2 = hand.get(1).getValue();
        }
        int totVal = (val1 + val2) % 10;
        if (totVal > 5) {
            return false;
        }
        return true;
    }
}