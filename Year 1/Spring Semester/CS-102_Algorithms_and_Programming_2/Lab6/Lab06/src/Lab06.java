import java.util.ArrayList;

/**
 * This program has some recursive method practices.
 * @author Omer Oktay Gultekin
 * @version 14.04.2021 2.0.0 final
 */
public class Lab06
{
    public static void main( String[] args )
    {
        // Variables
        String wordRToBeDeleted;
        int decimal;
        ArrayList<String> strings;
        String toBeReversed;

        // Part A
        wordRToBeDeleted = "ororororororooradsafafsgfRrrrrererwwr";
        System.out.println( "Part A - actual string = " + wordRToBeDeleted + " after remove r's = " + removeRFromString( wordRToBeDeleted ) );

        // Part B
        decimal = 1249;
        System.out.println( "Part B - decimal = " + decimal + " hex = " + decimalToHex( decimal ) );

        // Part c
        strings = new ArrayList<>();
        strings.add( "Ada" );
        strings.add( "Ahmet" );
        strings.add( "Arda" );
        strings.add( "Berk" );
        strings.add( "Berkay" );
        strings.add( "Ege" );
        strings.add( "Kutay" );
        strings.add( "Melih" );
        strings.add( "Mert" );
        strings.add( "Mert" );
        strings.add( "Omer" );
        strings.add( "Yunus" );
        System.out.println( "Part C isLexicographic method = " + isLexicographic( strings ) );

        // Part D
        toBeReversed = "OmerOktay";
        System.out.println( "Part D - given string = " + toBeReversed + " reversed String = " + reverseString( toBeReversed ) );

        // Part E
        System.out.println( "Part E results:" );
        printNEvenNumber( 3, "" );
    }

    /**
     * Recursive Method to remove 'r' from given String
     * @param string String in which 'r's will be deleted
     * @return new String which do not have Rs
     */
    public static String removeRFromString( String string )
    {
        // Base Case
        if ( string.length() == 0 )
        {
            return "";
        }

        // Recursive Part
        if ( string.charAt( string.length() - 1 ) == 'r' || string.charAt( string.length() - 1 ) == 'R' )
        {
            return removeRFromString( string.substring( 0, string.length() - 1 ) );
        }
        else
        {
            return removeRFromString( string.substring( 0, string.length() - 1 ) ) + string.charAt( string.length() - 1 );
        }
    }

    /**
     * Recursive Method that takes a decimal value as an int parameter and returns the hexadecimal equivalent of it as a String
     * @param decimal int value that should be decimal value
     * @return hexadecimal number that is in String format
     */
    public static String decimalToHex( int decimal )
    {
        // Variables
        String[] hexDigits;

        // Base Case
        if ( decimal <= 0 )
        {
            return "";
        }

        hexDigits = new String[]{ "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F" };

        // Recursive Part
        return decimalToHex( decimal / 16 ) + hexDigits[ decimal % 16 ];
    }

    /**
     * Recursive Method that determine whether given list in lexicographic order
     * @param strings List of string that the method determine whether they are in lexicographic order
     * @return true if the strings in the array are in lexicographic order, and false otherwise.
     */
    public static boolean isLexicographic( ArrayList<String> strings )
    {
        // Variables
        String string1;
        String string2;

        // Base case
        // All the values in the list was compared
        if ( strings.size() <= 1 )
        {
            return true;
        }

        // Get next 2 elements of the list, notice that first one will be removed from list.
        string1 = strings.remove(0 ).toLowerCase();
        string2 = strings.get( 0 ).toLowerCase();

        // if two string in order, then continue to iterate, otherwise return false
        if ( compareTwoString( string1, string2, 0 ) )
        {
            // Recursive Part
            return isLexicographic( strings );
        }
        else
        {
            return false;
        }
    }

    /**
     * Recursive helper method that determine whether given two string in lexicographic order
     * @param string1 first String
     * @param string2 second String
     * @param index   which index of the Strings will be compared
     * @return true if string1 come before string2 in lexicographic order, false otherwise.
     */
    private static boolean compareTwoString( String string1, String string2, int index )
    {
        // Variables
        int minLength;

        minLength = Math.min( string1.length(), string2.length() );

        // Base case - if all the chars are the same so far, then if string1 is shorter, then Strings are in order.
        if ( index >= minLength )
        {
            return string1.length() <= string2.length();
        }

        if ( string1.charAt( index ) < string2.charAt( index ) )
        {
            return true;
        }
        else if ( string1.charAt( index ) > string2.charAt( index ) )
        {
            return false;
        }
        else
        {
            // Recursive Part
            return compareTwoString( string1, string2, index + 1 );
        }
    }

    /**
     * Recursive Method that reverse the given String
     * @param string String to be reversed
     * @return reversed String
     */
    public static String reverseString( String string )
    {
        // Base Case
        if ( string.length() <= 0 )
        {
            return "";
        }

        // Recursive Part
        return string.charAt( string.length() - 1 ) + reverseString( string.substring( 0, string.length() - 1 ) );
    }

    /**
     * Recursive method that enumerates and prints all n-digit even numbers in which each digit of the number,
     * from its most-significant to least-significant digits, is greater than the previous one.
     * @param n int value determining how many digits the numbers will be
     * @param number String that will be printed
     */
    public static void printNEvenNumber( int n, String number )
    {
        // Base Case
        if ( n == 0 )
        {
            if ( Integer.parseInt( number ) % 2 == 0 )
            {
                if ( checkCriteria( number ) )
                {
                    System.out.println( number );
                }
            }
        }
        else
        {
            // it is possible because for loop increase chars ascii values
            for ( char digit = '1'; digit <= '9'; digit++ )
            {
                printNEvenNumber( n - 1,number + digit );
            }
        }
    }

    /**
     * Recursive helper method to check whether each digit of the number, from its most-significant to least-significant digit,
     * is greater than the previous one
     * @param number number to be checked in String type
     * @return true if the number meets the criteria, false otherwise.
     */
    private static boolean checkCriteria( String number )
    {
        // Variables
        char temp;

        // Base Case
        if ( number.length() < 2 )
        {
            return true;
        }

        temp = number.charAt( 0 );

        // ascii values of the chars are used in comparison
        if ( temp < number.charAt( 1 ) )
        {
            return checkCriteria( number.substring( 1 ) );
        }
        else
        {
            return false;
        }
    }
}