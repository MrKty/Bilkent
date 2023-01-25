package cardgame;

import java.util.ArrayList;

/**
 * ScoreCard - Maintains one integer score per player, for any number of players
 *             Caution: invalid player numbers result in run-time exception!
 * @author Ömer Oktay Gültekin
 * @author Kutay Şenyiğit
 * @author Yunus Eren Türkeri
 * @version 1.0.0 22.02.2021
 */
public class ScoreCard
{
    // properties
    int[] scores;
    
    // constructors
    public ScoreCard( int noOfPlayers)
    {
        scores = new int[noOfPlayers];
        
        // init all scores to zero
        for ( int i = 0; i < scores.length; i++) {
            scores[i] = 0;
        }
    }
    
    // methods

    /**
     * The method to return the score of a particular player.
     * @param playerNo The number of the player (this indicates
     * the position of the player)
     * @return The score of a particular player.
     */
    public int getScore( int playerNo)
    {
        return scores[playerNo];
    }

    /**
     * The method to return the updated score of a player.
     * @param playerNo The indicator which player's score will be incremented.
     * @param amount The amount of card face value in the score.
     */
    public void update( int playerNo, int amount)
    {
        scores[playerNo] += amount;
    }

    /**
     * The method to represent of the particular player and the indicated score of
     * that player
     * @return The player - score doublet.
     */
    public String toString()
    {
        String s;
        s = "\n"
            + "_____________\n"
            + "\nPlayer\tScore\n"
            + "_____________\n";
        
        for ( int playerNo = 0; playerNo < scores.length; playerNo++ )
        {
            int playerPrintNo = playerNo + 1;
            s = s + playerPrintNo + "\t" + scores[playerNo] + "\n";
        }
        
        s += "_____________\n";
        return s;
    }

    /**
     * The method to return all of the winners with the highest score.
     * @return The winners.
     */
    public int[] getWinners()
    {
        int max = scores[0];
        ArrayList<Integer> winnersIndex = new ArrayList<>();
        for ( int i = 0; i < scores.length; i++ ) {
            if( scores[i] > max ){
                max = scores[i];
                winnersIndex.clear();
                winnersIndex.add( i);
            }else if ( scores[i] == max ) {
                winnersIndex.add(i);
            }
        }

        int[] returnArray = new int[winnersIndex.size()];
        for ( int i = 0; i < returnArray.length ; i++ ) {
            returnArray[i] = winnersIndex.get(i);
        }
        return returnArray;
    }
    
} // end class ScoreCard
