import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CardTest {

    Card testCard;

    @Test
    void CardConstructor_Test() {
        testCard = new Card("Diamonds", 1);
        assertNotNull(testCard);
        assertEquals(testCard.getValue(), 1);
        assertEquals(testCard.getSuite(), "Diamonds");
    }

}