package cardgame;

import java.util.ArrayList;

/**
 * CardGame - a single playing card
 * @author Ömer Oktay Gültekin
 * @author Kutay Şenyiğit
 * @author Yunus Eren Türkeri
 * @version 1.0.0 22.02.2021
 */
public class CardGame
{
    // properties
    Cards             fullPack;
    ArrayList<Player> players;
    ScoreCard         scoreCard;
    Cards[]           cardsOnTable;
    int               roundNo;
    int               turnOfPlayer;
    
    // constructors
    public CardGame( Player p1, Player p2, Player p3, Player p4)
    {
        players = new ArrayList<>();
        players.add(p1);
        players.add(p2);
        players.add(p3);
        players.add(p4);
        scoreCard = new ScoreCard( players.size() );
        roundNo = 1;
        turnOfPlayer = 0;
        fullPack = new Cards ( true );
        fullPack.shuffle();

        // Deal all the pack between the players
        for ( int i = 0; i < 4; i++ ) {
            for ( int j = 0; j < 13; j++ ) {
                players.get(i).add( fullPack.getTopCard() );
            }
        }

        // Initialize all piles of cards on table
        cardsOnTable = new Cards[4];
        for ( int i = 0; i < cardsOnTable.length; i++ ) {
            cardsOnTable[i] = new Cards( false );
        }
    }

    //methods
    /**
     * The method to play a turn.
     * @param p The player that has the current turn.
     * @param c The card that is to be played.
     * @return The boolean of whether the turn is of p's turn.
     */
    public boolean playTurn( Player p, Card c)
    {
        if ( isTurnOf(p) ) {
            cardsOnTable[players.indexOf(p)].addTopCard(c);

            turnOfPlayer++;

            if( turnOfPlayer == 4 ) {
                ArrayList<Integer> lastCards = new ArrayList<Integer>();
                for ( int i = 0; i <= 3; i++ ) {
                    Card currentCard = cardsOnTable[i].getTopCard();
                    int faceValue =  currentCard.getFaceValue();
                    int suit = currentCard.getSuit();
                    cardsOnTable[i].addTopCard(currentCard);
                    int cardNo = faceValue + suit * 13;
                    lastCards.add(cardNo);
                }
                int max = 0;
                int index = -1;
                for (int i = 0; i < lastCards.size(); i++) {
                    if ( lastCards.get(i) > max ){
                        max = lastCards.get(i);
                        index = i;
                    }
                }
                scoreCard.update( index , 1);
                roundNo++;
                turnOfPlayer = 0;
            }
            return true;
        }
        else {
            // If wrong player puts the card to the table, game returns that card back to the player
            p.add(c);
            System.out.println( "Wrong player played! Card has returned to the player!");
            return false;
        }
    }

    /**
     * The method to check the turn of a player.
     * @param p The player that is to be checked.
     * @return The boolean of the turn of player with the particular player.
     */
    public boolean isTurnOf( Player p )
    {
        return players.indexOf(p) == turnOfPlayer;
    }

    /**
     * The method to check whether the game is over or not.
     * @return The boolean of the maximum possible round number is reached
     * or not.
     */
    public boolean isGameOver()
    {
        return roundNo == 14;
    }

    /**
     * The method to get the score of a player.
     * @param playerNumber The number of a player.
     * @return The score of a specified player number/player
     */
    public int getScore( int playerNumber )
    {
        return scoreCard.getScore( playerNumber);
    }

    /**
     * The method to get the name of a player.
     * @param playerNumber The player number that is related with the requested player name.
     * @return The player name according to the specific player number.
     */
    public String getName( int playerNumber )
    {
        return players.get(playerNumber).getName();
    }

    /**
     * The method to get the round number.
     * @return The current round number.
     */
    public int getRoundNo()
    {
        return roundNo;
    }

    /**
     * The method for returning which of the player's turn the current turn is.
     * @return The turn of a particular player.
     */
    public int getTurnOfPlayerNo()
    {
        return turnOfPlayer;
    }

    /**
     * The method to get all the winners.
     * @return The winners in the game.
     */
    public Player[] getWinners()
    {
        int[] winnersIndex = scoreCard.getWinners();
        Player[] winners = new Player[winnersIndex.length];
        for ( int i = 0; i < winnersIndex.length; i++ ){
            winners[i] = players.get(winnersIndex[i]);
        }
        return winners;
    }

    /**
     * The method to show the score card
     * @return The score card.
     */
    public String showScoreCard() {
        return scoreCard.toString();
    }
}