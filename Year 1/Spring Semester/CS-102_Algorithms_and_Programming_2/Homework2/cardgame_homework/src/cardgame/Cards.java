package cardgame;

import java.util.*;

/**
 * Cards - Maintains a collection of zero or more playing cards.
 *        Provides facilities to create a full pack of 52 cards
 *        and to shuffle the cards.
 * @author Ömer Oktay Gültekin
 * @author Kutay Şenyiğit
 * @author Yunus Eren Türkeri
 * @version 1.0.0 22.02.2021
 */

public class Cards {
    final int NO_OF_CARDS_IN_FULL_PACK = 52;

    // properties
    Card[] cards;
    int    valid;  // number of cards currently in collection

    // constructors
    public Cards( boolean fullPack )
    {
        cards = new Card[NO_OF_CARDS_IN_FULL_PACK];
        valid = 0;

        if ( fullPack )
            createFullPackOfCards();
    }

    // methods
    /**
     * The method to get the top card.
     * @return The top card.
     */
    public Card getTopCard()
    {
        Card tmp;

        if ( valid <= 0 )
            return null;
        else
        {
            valid--;
            tmp = cards[valid];
            cards[valid] = null;
            return tmp;
        }
    }

    /**
     * The method to add a card to the top.
     * @param c The card that is to be added.
     * @return The result of adding a card to if there is enough space.
     */
    public boolean addTopCard( Card c )
    {
        if ( valid < cards.length )
        {
            cards[valid] = c;
            valid++;
            return true;
        }
        return false;
    }

    /**
     * The method to create a full pack of cards
     */
    private void createFullPackOfCards() {
        for ( int i = 0; i <= 51; i++ ) {
            addTopCard( new Card(i) );
        }
    }

    /**
     * This method shuffles the full pack of cards
     */
    public void shuffle() {
        List<Card> temp = Arrays.asList( cards );
        Collections.shuffle(temp);
        cards = temp.toArray( cards );
    }
}
