import java.util.*;

/**
 * Simple console based Hangman game
 * @author CS102-01 Group 3
 * @author Kardelen Ceren 22003017
 * @author Ömer Oktay Gültekin 21901413
 * @version 15.02.2021 2.0.0
 */
public class Game
{
    // Constants
    final static String RESET_COLOR = "\u001b[0m";
    final static String PURPLE = "\u001b[35m"; // For table
    final static String RED = "\u001b[31m"; // For hangman

    public static void main ( String[] args )
    {
        // Variables
        Scanner scan;
        Hangman game;
        int remainingTries;
        int occurrences;
        char letter;
        StringBuilder incorrectLetters; // it can be stored here or directly in hangman class

        // Program code
        scan = new Scanner( System.in );
        game = new Hangman(); // New game object created
        incorrectLetters = new StringBuilder();

        // Ask for guessed letters and try them until the game is over
        while ( !game.isGameOver() )
        {
            // calculate the remaining tries (only to print it afterwards)
            // Following line assumes numberOfIncorrectTries updated in the tryThis method
            remainingTries = game.getMaxAllowedIncorrectTries() - game.getNumOfIncorrectTries();

            // print out the "user interface" (MVC can't be followed as we don't know GUI yet)
            System.out.println( "#####################################################################" ); // separator
            System.out.println( "Currently" );
            printTableOfLetters( game , incorrectLetters );
            System.out.printf( "Remaining Tries: %d%n%s%n" , remainingTries , game.getKnownSoFar() );

            // ask for and get the guessed letter
            System.out.print( "Please enter the character you would like to try: " );
            letter = scan.nextLine().charAt( 0 );

            System.out.println( "#####################################################################" ); // separator
            occurrences = game.tryThis( letter ); // this assumes knownSoFar is updated in the tryThis method
            if ( occurrences == -1 )
            {
                System.out.println( "Entered character is not valid" );
            }
            else if ( occurrences == -2 )
            {
                System.out.println( "You have already tried this letter!" );
            }
            else if ( occurrences == -3 )
            {
                System.out.println( "Game is over" );
            }
            else
            {
                // print a message saying if the guess was correct or not
                if ( occurrences > 0 )
                {
                    System.out.println( "This letter appears " + occurrences + " time(s) in the secret word." );
                }
                else
                {
                    System.out.println( "Incorrect!" );
                    incorrectLetters.append( Character.toLowerCase(letter) );
                }
            }

            // print the name-sake hangman (static method defined below)
            printHangman( game.getNumOfIncorrectTries() );

        }

        // print out a goodbye message depending on game's outcome
        if ( game.hasLost() )
        {
            System.out.println( "Better luck next time!" );
        }
        else
        {
            System.out.println("Congratulations!");
        }

        // show the word at the end no matter the outcome
        System.out.println( "The word was " + game.getKnownSoFar() );
        scan.close();
        
        // End of the main method
    }

    /**
     * Method to print out a person drawn with "*"
     * @param incorrectTries (must not be over 6!) determines which body parts will be drawn
     */
    public static void printHangman ( int incorrectTries )
    {
        switch ( incorrectTries )
        {
            case 1:
                printRope();
                printHead();
                break;
            case 2:
                printRope();
                printHead();
                printBody();
                break;
            case 3:
                printRope();
                printHead();
                printBodyWithArm();
                break;
            case 4:
                printRope();
                printHead();
                printBodyWithArms();
                break;
            case 5:
                printRope();
                printHead();
                printBodyWithArms();
                printOneLeg();
                break;
            case 6:
                printRope();
                printHead();
                printBodyWithArms();
                printTwoLegs();
                break;
        }
    }

    // Following method "draw" the rope (the spaces are deliberately put)
    public static void printRope()
    {
        System.out.printf( RED + "_____________%n      |     %n      |     %n      |     %n" + RESET_COLOR );
    }

    // Following methods all "draw" the specified parts of a person and (the spaces are deliberately put)
    public static void printHead()
    {
        System.out.printf( RED + "     ***%n    *   *%n    *   *%n     ***%n" + RESET_COLOR );
    }
    public static void printBody()
    {
        System.out.printf( RED + "      *%n      *%n      *%n      *%n" + RESET_COLOR );
    }
    public static void printBodyWithArm()
    {
        System.out.printf( RED + "      *%n     **%n    * *%n   *  *%n" + RESET_COLOR );
    }
    public static void printBodyWithArms()
    {
        System.out.printf( RED + "      *%n     ***%n    * * *%n   *  *  *%n" + RESET_COLOR );
    }
    public static void printOneLeg()
    {
        System.out.printf( RED + "      *%n     *%n    *%n   *%n" + RESET_COLOR );
    }
    public static void printTwoLegs()
    {
        System.out.printf( RED + "      *%n     * *%n    *   *%n   *     *%n" + RESET_COLOR );
    }

    /**
     * Method to print out possible and used letters in a table
     * @param game hangman object to access it's list of possible letters
     * @param incorrectLetters user's prediction of letters which is not in the secret word
     */
    public static void printTableOfLetters ( Hangman game , StringBuilder incorrectLetters )
    {
        // Constants
        final int FIRST_ROW_LENGTH = 16;
        final int ROW_LENGTH = 4;

        // Variables
        char[] possibleLetters;
        StringBuilder tableBuilder;


        // Method Implementation
        possibleLetters = game.getAllLetters().toCharArray();
        tableBuilder = new StringBuilder();

        // Top of the Table
        tableBuilder.append( "_".repeat( FIRST_ROW_LENGTH + possibleLetters.length * ROW_LENGTH ) );
        tableBuilder.append( "\n" );

        // First Row
        tableBuilder.append( "| All Letters  |" );
        for ( char possibleLetter : possibleLetters )
        {
            tableBuilder.append( " " ).append( possibleLetter ).append( " " ).append( "|" );
        }
        tableBuilder.append( "\n" );
        tableBuilder.append( "-".repeat( FIRST_ROW_LENGTH + possibleLetters.length * ROW_LENGTH ) );
        tableBuilder.append( "\n" );

        // Second Row
        tableBuilder.append( "| Used Letters |" );
        printSecondRow( game.getAllLetters() , game.getUsedLetters() , tableBuilder , incorrectLetters );
        tableBuilder.append( "\n" );
        tableBuilder.append( "¯".repeat( FIRST_ROW_LENGTH + possibleLetters.length * ROW_LENGTH ) );
        tableBuilder.append( "\n" );
        System.out.print( PURPLE + tableBuilder.toString() + RESET_COLOR );
    }

    /**
     * Method to print out Second Row
     * @param possibleLetters The list of possible letters user can use in the game
     * @param usedLetters The list of possible letters user used in the game
     * @param tableBuilder table to be printed
     * @param incorrectLetters User's prediction of letters which is not in the secret word
     */
    private static void printSecondRow( String possibleLetters , String usedLetters , StringBuilder tableBuilder , StringBuilder incorrectLetters )
    {
        // Constants
        final String CORRECT_PREDICTION = "✓";
        final String WRONG_PREDICTION = "x";

        // Variables
        char[] guesses;
        char[] incorrectGuesses;
        Set<Integer> positionOfUsedLetters;
        Set<Integer> positionOfIncorrectLetters;

        // Method Implementation
        guesses = usedLetters.toCharArray();
        incorrectGuesses = incorrectLetters.toString().toCharArray();

        // Using Tree structure is recommended here because in for loop we need to have an ordered list
        positionOfUsedLetters = new TreeSet<>();
        positionOfIncorrectLetters = new TreeSet<>();

        // Find the position of the usedLetters in the table
        for ( char guess : guesses )
        {
            positionOfUsedLetters.add( possibleLetters.indexOf( guess ) );
        }

        // Find the position of incorrectLetters in the table
        for ( char incorrectGuess : incorrectGuesses )
        {
            positionOfIncorrectLetters.add( possibleLetters.indexOf( incorrectGuess ) );
        }

        // Caution: all incorrectLetters are in the usedLetters therefore first insert them
        for ( int i = 0; i < possibleLetters.length(); i++ )
        {

            if ( positionOfIncorrectLetters.contains( i ) )
            {
                tableBuilder.append( " " ).append( WRONG_PREDICTION ).append( " " );
            }
            else if ( positionOfUsedLetters.contains( i ) )
            {
                tableBuilder.append( " " ).append( CORRECT_PREDICTION ).append( " " );
            }
            else
            {
                tableBuilder.append( "   " );
            }
            tableBuilder.append( "|" );
        }
    }
}
