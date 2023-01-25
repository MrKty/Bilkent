import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Locale;
import java.util.Random;

/**
 * Forms a part of hangman game (CS102 - Section 01 - Hangman Game)
 * @author Our Group(Group 13): Mahmut Furkan Gön - Mustafa Yetgin - Ömer Kağan Danacı
 * @author (Modified by) Omer Oktay Gultekin
 * @version 15.02.2021 2.0.0
*/
public class Hangman
{
    // Properties
    private final String[] WORDS = { "Turkey", "America", "England" };
    private StringBuffer allLetters;
    private StringBuffer usedLetters;
    private StringBuffer secretWord;
    private StringBuffer knownSoFar; // secretWord but with chars not yet found blanked out
    private int maxAllowedIncorrectTries;
    private int numberOfIncorrectTries;


    // Constructors
    /**
     * Constructor for initialize hangman game
     */
    public Hangman()
    {
        // Always convert given set to the lower case and work with lower case in other methods
        allLetters = new StringBuffer( "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toLowerCase( Locale.US ) );
        numberOfIncorrectTries = 0;
        maxAllowedIncorrectTries = 6;
        usedLetters = new StringBuffer();

        // trying to get a word from an Internet. If it is failed, then use locale sources.
        try
        {
            chooseSecretWordFromInternet();
        }
        catch ( Exception e )
        {
            try
            {
                System.out.println( "\u001b[31mCouldn't connect to the server! Game mode will be offline.\u001b[0m" );
                chooseSecretWordFromFile();
            }
            catch ( IOException io )
            {
                chooseSecretWord();
            }
        }
        String unknownCharacter = "*";
        knownSoFar = new StringBuffer( unknownCharacter.repeat( secretWord.length() ) );
    }

    // Methods
    /**
     * Gets all letters as a String
     * @return a String that includes all letters
     */
    public String getAllLetters()
    {
        return allLetters.toString();
    }
	
    /**
     * Gets used letters as a String 
     * @return a String that includes used letters
    */
	public String getUsedLetters()
    {
        return usedLetters.toString();	
    }
    
    /**
     * Gets the number of incorrect tries
     * @return the number of incorrect tries
    */
	public int getNumOfIncorrectTries()
    {
		return numberOfIncorrectTries;
	}
    
    /**
     * Gets how many tries are allowed
     * @return the value of maximum allowed incorrect tries
    */
	public int getMaxAllowedIncorrectTries()
    {
		return maxAllowedIncorrectTries;
	}

    /**
     * Gets the word by showing letters known so far or whole word if the user lost
     * @return a String representation of word with letters known so far or whole word if the user lost
    */
    public String getKnownSoFar()
    {
        if ( isGameOver() )
        {
            return secretWord.toString();
        }
        else
        {
            return knownSoFar.toString();
        }
	}

    /**
     * Search the given letter in the word and if there is, update knownSoFar
     * @return -1 if the letter is not valid, -2 if the letter is already used, -3 if the game is over, otherwise how many times the letter found in the word
     */
    public int tryThis( char letter)
    {
        // Constants
        final int NOT_VALID_ERROR_CODE = -1;
        final int ALREADY_USED_LETTER_CODE = -2;
        final int GAME_OVER_CODE = -3;

        // Variables
        int timesLetterFound;

        // Method Implementation
        // Caution! Hangman class works with lower case letters.
        letter = Character.toLowerCase( letter );
        timesLetterFound = 0;
        if ( usedLetters.toString().contains( Character.toString( letter ) ) )
        {
            return ALREADY_USED_LETTER_CODE;
        }
        if ( !allLetters.toString().contains( Character.toString( letter ) ) )
        {
            return NOT_VALID_ERROR_CODE;
        }

        for ( int i = 0; i < secretWord.length(); i++ )
        {
            if ( secretWord.charAt( i ) == letter )
            {
                knownSoFar.setCharAt( i , letter );
                timesLetterFound++;
            }
        }
        if ( timesLetterFound == 0 )
        {
            numberOfIncorrectTries++;
        }
        usedLetters.append( letter );
        if ( isGameOver() )
        {
            return  GAME_OVER_CODE;
        }
        return timesLetterFound;
    }

    /**
     * Initialize secret word with a random word from a fixed list
     */
    private void chooseSecretWord()
    {
        Random random;
        random = new Random();
        secretWord = new StringBuffer( WORDS[ random.nextInt( WORDS.length ) ].toLowerCase( Locale.US ) );
    }

    /**
     * Initialize secret word with a random word from a file
     * The file should include one word long words, all in a separate line.
     */
    private void chooseSecretWordFromFile() throws IOException
    {
        // Variables
        RandomAccessFile file;
        long fileLength;
        long pos;

        // Method Implementation
        file = new RandomAccessFile( "src/words.txt" , "r" );
        fileLength = file.length();
        pos  = ( int ) ( Math.random() * fileLength );
        file.seek( pos ); // Move the cursor of the file to the pos line
        file.readLine(); // Move the cursor to the beginning of the line
        secretWord = new StringBuffer( file.readLine().toLowerCase( Locale.US ) );
        file.close();
    }

    /**
     * Initialize secret word with a random word from an Internet
     * This method will return a word between 4 and 9 letters long inclusive.
     * throw Exception after about 5 seconds.
     */
    private void chooseSecretWordFromInternet() throws Exception {
        // Constants
        final String RED = "\u001b[31m"; // For reports
        final String RESET_COLOR = "\u001b[0m";
        
        // Variables
        String word;
        HttpResponse<String> response;
        HttpRequest request;
        long beginTime;
        long endTime;

        // Method Implementation
        System.out.println( RED + "Trying to get a word from the server, please wait..." + RESET_COLOR );

        // Request to the server
        request = HttpRequest.newBuilder()
                .uri( URI.create( "https://wordsapiv1.p.rapidapi.com/words/?random=true" ) )
                .header( "x-rapidapi-key" , "00354269abmshb50fc522613037cp10fa10jsn97b96add2ba9" )
                .header( "x-rapidapi-host" , "wordsapiv1.p.rapidapi.com" )
                .method( "GET", HttpRequest.BodyPublishers.noBody() )
                .build();

        // Get Response until the word is in specified length long
        response = HttpClient.newHttpClient().send( request , HttpResponse.BodyHandlers.ofString() );
        // Retrieve word from response JSON
        word = response.body().split( "\"word\":" )[1].split( "\"," )[0].substring( 1 );

        // Start the time
        beginTime = System.currentTimeMillis();
        while ( !word.matches( "[A-Za-z]{4,9}" ) )
        {
            response = HttpClient.newHttpClient().send( request , HttpResponse.BodyHandlers.ofString() );
            word = response.body().split( "\"word\":" )[1].split( "\"," )[0].substring( 1 );
            endTime = System.currentTimeMillis();
            if ( endTime - beginTime > 5000 ) // 5 second
            {
                System.out.println( RED + "Connection time out!" + RESET_COLOR );
                throw new Exception();
            }
        }
        secretWord = new StringBuffer( word );
        System.out.println( RED + "Done!" + RESET_COLOR );
    }

    /**
     * Checks whether the game is over or not
     * @return a boolean expression of whether the game is over or not
    */
    public boolean isGameOver()
    {
        return knownSoFar.toString().equals( secretWord.toString() ) || hasLost();
    }

    /**
     * Checks whether the player has lost or not
     * @return a boolean expression of whether the player has lost or not
    */
    public boolean hasLost()
    {
        return numberOfIncorrectTries == maxAllowedIncorrectTries;
    }
}