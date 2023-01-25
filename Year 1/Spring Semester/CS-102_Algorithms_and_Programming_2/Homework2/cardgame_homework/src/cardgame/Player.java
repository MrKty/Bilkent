package cardgame;

/**
 * Player - Simple card game player with name and hand of cards
 * @author Ömer Oktay Gültekin
 * @author Kutay Şenyiğit
 * @author Yunus Eren Türkeri
 * @version 1.0.0 22.02.2021
 */

public class Player
{
    // properties
    String name;
    Cards  hand;

    // constructors
    public Player( String name )
    {
        this.name = name;
        hand = new Cards( false );
    }
    
    // methods
    /**
     * The method to get the player's name
     * @return current player's name
     */
    public String getName()
    {
        return name;
    }

    /**
     * The method to add a card from the top.
     * @param c The specific card that to be added.
     */
    public void add( Card c )
    {
        hand.addTopCard(c);
    }

    /**
     * The method to removes and returns the top card from the players hand
     * @return The top card that is to be played.
     */
    public Card playCard()
    {
        return hand.getTopCard();
    }
    
} // end class Player
