import java.util.Scanner;
import cardgame.*;

/**
 * This class test the Card game
 * @author Ömer Oktay Gültekin
 * @author Kutay Şenyiğit
 * @author Yunus Eren Türkeri
 * @version 1.0.0 22.02.2021
 */

public class MyCardGame
{
    public static void main( String[] args)
    {
        Scanner scan = new Scanner( System.in);

        System.out.println( "Start of MyCardGame\n");
        
        // CONSTANTS
        final int MENU_EXIT    = 0;
        final int MENU_PLAY_P1 = 1;
        final int MENU_PLAY_P2 = 2;
        final int MENU_PLAY_P3 = 3;
        final int MENU_PLAY_P4 = 4;
        final int MENU_SCORES  = 5;
        
        // VARIABLES
        Player     p1, p2, p3, p4;
        CardGame   game;
        int        selection;
        
        // PROGRAM CODE

        // create players...
        p1 = new Player( "Aynur");
        p2 = new Player( "Ömer");
        p3 = new Player( "Kutay");
        p4 = new Player( "Yunus");
        
        // create game with the 4 players...
        game = new CardGame( p1, p2, p3, p4);
        
        // display menu, get and process selection, until exit
        do 
        {
            // display menu
            System.out.println();
            System.out.println("MyCardGame   Round: " + game.getRoundNo()
                    + "\t TurnOfPlayer: " + ( game.getTurnOfPlayerNo() + 1));
            System.out.println();
            System.out.println( MENU_PLAY_P1 + " - Player " + MENU_PLAY_P1 + " plays");
            System.out.println( MENU_PLAY_P2 + " - Player " + MENU_PLAY_P2 + " plays");
            System.out.println( MENU_PLAY_P3 + " - Player " + MENU_PLAY_P3 + " plays");
            System.out.println( MENU_PLAY_P4 + " - Player " + MENU_PLAY_P4 + " plays");
            System.out.println( MENU_SCORES + " - Show scores");

            // ask for and get selection
            System.out.println();
            System.out.println("Selection (" + MENU_EXIT + " to exit): ");
            selection = scan.nextInt();

            // process selection
            if (selection == MENU_PLAY_P1)
                play(p1, game);

            else if (selection == MENU_PLAY_P2)
                play(p2, game);

            else if (selection == MENU_PLAY_P3)
                play(p3, game);

            else if (selection == MENU_PLAY_P4)
                play(p4, game);

            else if (selection == MENU_SCORES)
                System.out.println(game.showScoreCard());

            else if (selection != MENU_EXIT)
                System.out.println("Invalid selection! \n");

            // Finish the game after all cards in the decks of the players were finished
            if ( game.getRoundNo() == 14){
                break;
            }

        } while ( selection != MENU_EXIT );

        if( game.isGameOver() ){
            Player[] winners = game.getWinners();
            System.out.println( "Winners:");
            for ( int i=0; i < winners.length; i++ ){
                System.out.println( winners[i].getName());
            }
        }

        System.out.println( "\nEnd of MyCardGame\n" );   
    }

    // get the card, c, that player p wants to play
    // pass c to the game, see if it accepted c from p
    // if game didn't accept the card, give c back to the player! 
    // return accepted.
    private static boolean play( Player p, CardGame game )
    {
        Card       c;
        boolean    accepted;
        c = p.playCard();
        accepted = game.playTurn(p,c);
        return accepted;
    }
    
} // end class MyCardGame
