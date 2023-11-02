public class Card {
    private String suite;
    private int value;

    public Card(String theSuite, int theValue) {
        suite = theSuite;
        value = theValue;
    }

    public String getSuite() {
        return suite;
    }

    public int getValue() {
        return value;
    }
}