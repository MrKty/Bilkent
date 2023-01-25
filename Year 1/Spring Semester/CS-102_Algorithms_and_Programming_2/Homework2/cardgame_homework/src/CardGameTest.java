import java.util.Scanner;
import cardgame.*;

/**
 * CardGameTest
 * @author Ömer Oktay Gültekin
 * @author Kutay Şenyiğit
 * @author Yunus Eren Türkeri
 * @version 1.0.0 22.02.2021
 */

public class CardGameTest
{
    public static void main( String[] args)
    {
        Scanner scan = new Scanner( System.in);
        
        System.out.println( "Start of CardGameTest\n");
        
        // CONSTANTS
        
        // VARIABLES
        Card       c;
        Cards      cards;
        ScoreCard  scores;

        // PROGRAM CODE
        
        // test Card class
        c = new Card( 0);
        System.out.println( c);
        System.out.println();
        
        // test Cards class
        cards = new Cards( true);
        cards.shuffle();
        cards.addTopCard(c);
        
        // test ScoreCard class
        scores = new ScoreCard( 4);
        scores.update( 3, 1);
        scores.update( 1, 2);
        System.out.println( "\n\t" + scores );
        
        // test Player class

        Player p1;
        Player p2;
        Player p3;
        Player p4;
        p1 = new Player("Aynur");
        p2 = new Player("Omer");
        p3 = new Player("Yunus");
        p4 = new Player("Kutay");
        System.out.println( p1.getName());
        System.out.println( p2.playCard());
        p1.add( c);
        System.out.println( p1.playCard());

        // test CardGame class too?

        CardGame cardGame = new CardGame(p1, p2, p3, p4);
        Card c1 = p1.playCard();
        System.out.println(cardGame.playTurn( p1, c1));
        System.out.println(cardGame.isGameOver());
        System.out.println(cardGame.showScoreCard());
        System.out.println(cardGame.getTurnOfPlayerNo());
        System.out.println(cardGame.getRoundNo());
        System.out.println(cardGame.getName(2));
        System.out.println(cardGame.getScore(2));



        // Once you have all the bits working, complete the MyCardGame program
        // that provides a menu allowing any of the players to play their card,
        // an option to see the score card, and one to quit the game at any time.
        // When the game is over it should print out the winners.
        
        System.out.println( "\nEnd of CardGameTest\n" );
    }
    
} // end of class CardGameTest
