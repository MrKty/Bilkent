package cardgame;

/**
 * Card - a single playing card
 * @author Ömer Oktay Gültekin
 * @author Kutay Şenyiğit
 * @author Yunus Eren Türkeri
 * @version 1.0.0 22.02.2021
 */
public class Card
{

    //Properties
    final String[] SUITS = { "Hearts", "Diamonds", "Spades", "Clubs"};
    final String[] FACES = { "A", "2", "3", "4", "5",
                             "6", "7", "8", "9", "10",
                             "J", "Q", "K"};
    
    final int NO_OF_CARDS_IN_SUIT = 13;

    int cardNo;

    //constructors
    public Card( int faceValue, int suit )
    {
        cardNo = faceValue + suit * NO_OF_CARDS_IN_SUIT;
    }
    
    public Card( int cardNumber)
    {
        cardNo = cardNumber;
    }

    //methods
    public int getFaceValue()
    {
        return cardNo % NO_OF_CARDS_IN_SUIT;
    }
    
    public int getSuit()
    {
        return cardNo / NO_OF_CARDS_IN_SUIT;
    }
    
    public String toString()
    {
        return FACES[ getFaceValue() ] + " of " + SUITS[ getSuit() ];
    }

    /**
     * The method to return equivalence of a card to card c.
     * @param c Card that is to be checked for equality.
     * @return The boolean result of whether the checked card is equal to
     * card c.
     */
    public boolean equals( Card c)
    {
        return this.cardNo == c.cardNo;
    }

    /**
     * This method compares two card object
     * @param c Card object that compare with
     * @return Returns an int depending on comparison result
     * 1 if the cardNo is greater than the other card's cardNo
     * 0 for Equals cardNo
     * -1 if the cardNo is less than the other card's cardNo
     */
    public int compareTo( Card c) {
        if ( this.cardNo > c.cardNo ){
            return 1;
        }
        else if ( this.cardNo == c.cardNo ){
            return 0;
        }
        else {
            return -1;
        }

    }


}