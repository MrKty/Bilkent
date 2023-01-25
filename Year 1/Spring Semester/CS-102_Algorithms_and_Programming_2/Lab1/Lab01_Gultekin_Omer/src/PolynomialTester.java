import java.util.Scanner;

/**
 * This program creates polynomial equation and does some operations with this equation.
 * @author Omer Oktay Gultekin
 * @version 10.02.2020 2.0.0 Final
 */
public class PolynomialTester {

    public static void main( String[] args )
    {
        // Constants
        final String RESET_COLOR = "\u001b[0m";
        final String PURPLE = "\u001b[35m"; // For the outputs about the Polynomial class
        final String RED = "\u001b[31m"; // For error messages

        // Variables
        Scanner scanner;
        Polynomial polynomial;
        String userChoice;
        double[] coefficients;
        boolean isProgramFinished;
        int degree;
        int number;
        double coefficient;

        // Program Code
        scanner = new Scanner( System.in );

        do {
            // Show the menu and get the user choice
            System.out.println();
            showMainMenu();
            System.out.print( "Your choice: " );
            userChoice = scanner.nextLine();

            // Determine whether the program should finished or not
            isProgramFinished = userChoice.equals( "0" );

            // Initialize polynomial considering user choice
            if ( userChoice.equals( "1" ) )
            {
                polynomial = new Polynomial();
                System.out.println( PURPLE + "Zero polynomial successfully created!" + RESET_COLOR );
            }
            else if ( userChoice.equals( "2" ) )
            {
                System.out.print( "Enter the degree of the term: " );
                degree = scanner.nextInt();
                scanner.nextLine(); // use it to move the cursor to the next line
                System.out.print( "Enter the coefficient of the term: " );
                coefficient = scanner.nextDouble();
                scanner.nextLine(); // use it to move the cursor to the next line
                if ( coefficient == 0 )
                {
                    System.out.println( RED + "Coefficient could not be 0" + RESET_COLOR );
                    polynomial = null;
                }
                else
                {
                    polynomial = new Polynomial( degree, coefficient );
                    System.out.println( PURPLE + "Polynomial with the specified term successfully created!" + RESET_COLOR );
                }

            }
            else if ( userChoice.equals( "3" ) )
            {
                System.out.print( "Enter the degree of the polynomial: " );
                degree = scanner.nextInt();
                scanner.nextLine(); // use it to move the cursor to the next line
                coefficients = new double[degree + 1];
                for ( int i = 0; i <= degree; i++ )
                {
                    System.out.print( "Enter coefficient of the degree " + i + ": " );
                    coefficients[i] = scanner.nextDouble();
                    scanner.nextLine(); // use it to move the cursor to the next line
                }
                if( coefficients[coefficients.length - 1] != 0 )
                {
                    polynomial = new Polynomial( coefficients );
                    System.out.println( PURPLE + "Polynomial with the given coefficients successfully created!" + RESET_COLOR );
                }
                else
                {
                    System.out.println( RED + "Coefficient of the last term could not be zero!" + RESET_COLOR );
                    polynomial = null;
                }
            }
            else
            {
                // For any other input
                polynomial = null;
            }

            // If user enter one of the valid option for initialize polynomial
            if ( polynomial != null )
            {
                do {
                    // Show the menu and get the user choice
                    System.out.println();
                    showPolynomialMenu();
                    System.out.print( "Your choice: " );
                    userChoice = scanner.nextLine();

                    // Call appropriate method considering user choice
                    if ( userChoice.equals( "1" ) )
                    {
                        System.out.print( "Enter the degree: " );
                        degree = scanner.nextInt();
                        scanner.nextLine(); // use it to move the cursor to the next line
                        if ( degree > polynomial.getDegree() )
                        {
                            System.out.println( RED + "No term found with this degree!" + RESET_COLOR );
                        }
                        else
                        {
                            System.out.println( PURPLE + "The coefficient of the term is: " + polynomial.getCoefficients( degree ) + RESET_COLOR );
                        }
                    }
                    else if ( userChoice.equals( "2" ) )
                    {
                        System.out.println( PURPLE + "The degree of the polynomial is: " + polynomial.getDegree() + RESET_COLOR );
                    }
                    else if ( userChoice.equals( "3" ) )
                    {
                        System.out.print( "Enter the number to calculate the result of polynomial: " );
                        number = scanner.nextInt();
                        scanner.nextLine(); // use it to move the cursor to the next line
                        System.out.println( PURPLE +  "The result is " + polynomial.eval( number ) + RESET_COLOR );
                    }
                    else if ( userChoice.equals( "4" ) )
                    {
                        System.out.print( "Enter the number to calculate the result of polynomial: " );
                        number = scanner.nextInt();
                        scanner.nextLine(); // use it to move the cursor to the next line
                        System.out.println( PURPLE +  "The result is " + polynomial.eval2(number) + RESET_COLOR );
                    }
                    else if ( userChoice.equals( "5" ) )
                    {
                        System.out.println( PURPLE + "P(x) = " + polynomial.toString() + RESET_COLOR );
                    }
                } while ( !userChoice.equals( "0" ) );
            }

        } while ( !isProgramFinished );

        scanner.close();
        System.out.println( "Bye!" );
    }

    /**
     * This method print an appropriate menu for initializing polynomial object
     */
    public static void showMainMenu()
    {
        System.out.println( "Main Menu Options:\n" +
                "1) Create Zero Polynomial\n" +
                "2) Create one-term Polynomial with a given degree\n" +
                "3) Create polynomial with a given list of coefficients\n" +
                "0) Quit" );
    }

    /**
     * This method print an appropriate menu to enable user to use methods of polynomial object
     */
    public static void showPolynomialMenu()
    {
        System.out.println( "Polynomial Menu Options:\n" +
                "1) Get coefficient of the specified term with a given degree\n" +
                "2) Get degree of the polynomial\n" +
                "3) Evaluate polynomial for a given number using classic method\n" +
                "4) Evaluate polynomial for a given number using Horner's method\n" +
                "5) Print the polynomial\n" +
                "0) Return to the main menu" );
    }


}
