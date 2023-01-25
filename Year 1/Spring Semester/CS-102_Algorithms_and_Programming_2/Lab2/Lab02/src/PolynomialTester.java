import java.util.Scanner;

/**
 * This program creates polynomial equation and does some operations with this equation.
 * @author Omer Oktay Gultekin
 * @version 17.02.2020 4.0.0 final
 */
public class PolynomialTester
{
    // Constants
    final static String RESET_COLOR = "\u001b[0m";
    final static String PURPLE = "\u001b[35m"; // For the outputs of the operations
    final static String RED = "\u001b[31m"; // For error messages
    final static String YELLOW =  "\u001b[33m"; // For result of calculations

    public static void main( String[] args )
    {
        // Variables
        Scanner scanner;
        Polynomial polynomial;
        String userChoice;

        // Program Code
        scanner = new Scanner( System.in );

        do {
            // Show the menu and get the user choice
            System.out.println();
            System.out.println( "Main Menu Options:" );
            showPolynomialInitializingMenu();
            System.out.print( "Your choice: " );
            userChoice = scanner.nextLine();
            
            // Create polynomial based on user choice
            polynomial = createPolynomial( scanner, userChoice );

            // If user enter one of the valid option for initialize polynomial
            if ( polynomial != null )
            {
                OperateWithPolynomial( scanner, polynomial );
            }
        } while ( !userChoice.equalsIgnoreCase( "q" ) ); // q is key for quit
        scanner.close();
        System.out.println( "Bye!" );
    }

    /**
     * This method print an appropriate menu for initializing polynomial object
     */
    public static void showPolynomialInitializingMenu()
    {
        System.out.println( "1) Create Zero Polynomial\n" +
                "2) Create one-term Polynomial with a given degree\n" +
                "3) Create polynomial with a given list of coefficients\n" +
                "Q) Quit" );
    }

    /**
     * This method print an appropriate menu to enable user to use methods of polynomial object
     */
    public static void showPolynomialMenu()
    {
        System.out.println( "Polynomial Menu Options:\n" +
                "0) Get coefficient of the specified term with a given degree\n" +
                "1) Get degree of the polynomial\n" +
                "2) Evaluate polynomial for a given number using classic method\n" +
                "3) Evaluate polynomial for a given number using Horner's method\n" +
                "4) Print the polynomial\n" +
                "5) Do operation with this polynomial\n" +
                "Q) Return to the main menu" );
    }

    /**
     * This method creates polynomial with the given choices.
     * @param scanner Scanner object to get the user inputs
     * @param userChoice user choice for creating polynomial
     * @return new polynomial initializing by given choices.
     */
    private static Polynomial createPolynomial( Scanner scanner, String userChoice )
    {
        // Variables
        double[] coefficients;
        Polynomial polynomial;
        int degree;
        double coefficient;

        // Method Implementation
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
        return polynomial;
    }

    /**
     * This method enable user to make operations with given polynomial.
     * @param scanner Scanner object to get the user inputs
     * @param polynomial Polynomial object to work with
     */
    private static void OperateWithPolynomial( Scanner scanner, Polynomial polynomial )
    {
        // Variables
        int degree;
        int number;
        String userChoice;

        // Method Implementation
        do {
            // Show the menu and get the user choice
            System.out.println();
            showPolynomialMenu();
            System.out.print( "Your choice: " );
            userChoice = scanner.nextLine();

            // Call appropriate method considering user choice
            if ( userChoice.equals( "0" ) )
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
            else if ( userChoice.equals( "1" ) )
            {
                System.out.println( PURPLE + "The degree of the polynomial is: " + polynomial.getDegree() + RESET_COLOR );
            }
            else if ( userChoice.equals( "2" ) )
            {
                System.out.print( "Enter the number to calculate the result of polynomial: " );
                number = scanner.nextInt();
                scanner.nextLine(); // use it to move the cursor to the next line
                System.out.println( PURPLE +  "The result is " + polynomial.eval( number ) + RESET_COLOR );
            }
            else if ( userChoice.equals( "3" ) )
            {
                System.out.print( "Enter the number to calculate the result of polynomial: " );
                number = scanner.nextInt();
                scanner.nextLine(); // use it to move the cursor to the next line
                System.out.println( PURPLE +  "The result is " + polynomial.eval2(number) + RESET_COLOR );
            }
            else if ( userChoice.equals( "4" ) )
            {
                System.out.println( PURPLE + "P(x) = " + polynomial.toString() + RESET_COLOR );
            }
            else if ( userChoice.equals( "5" ) )
            {
                doCalculation( scanner, polynomial );
            }

        } while ( !userChoice.equalsIgnoreCase( "q" ) );

    }

    /**
     * This method does calculations with given polynomial.
     * @param scanner Scanner object to get the user inputs
     * @param polynomial Polynomial object to work with
     */
    private static void doCalculation( Scanner scanner, Polynomial polynomial)
    {
        // Variables
        String userChoice;
        Polynomial p2;
        Polynomial result;

        // Method Implementation
        // Show the menu and get the user choice
        System.out.println();
        System.out.println( "How do you want to create the second polynomial?" );
        showPolynomialInitializingMenu();
        System.out.print( "Your choice: " );
        userChoice = scanner.nextLine();
        p2 = createPolynomial( scanner, userChoice );

        // if p2 is successfully created in the method
        if ( p2 != null )
        {
            while ( !userChoice.equalsIgnoreCase( "q" ) )
            {
                System.out.println();
                System.out.println( "Polynomial Calculation Menu\n" +
                        "1) Add polynomial to this polynomial\n" +
                        "2) Subtract new polynomial from this polynomial\n" +
                        "3) Multiply this polynomial with new polynomial\n" +
                        "4) Compose this polynomial with new polynomial\n" +
                        "5) Divide this polynomial by new polynomial\n" +
                        "Q) Quit operation mode" );

                System.out.print( "Your choice: " );
                userChoice = scanner.nextLine();

                // Made calculations
                if ( userChoice.equals( "1" ) )
                {
                    result = polynomial.add( p2 );
                }
                else if ( userChoice.equals( "2" ) )
                {
                    result = polynomial.sub( p2 );
                }
                else if ( userChoice.equals( "3" ) )
                {
                    result = polynomial.mul( p2 );
                }
                else if ( userChoice.equals( "4" ) )
                {
                    result = polynomial.compose( p2 );
                }
                else if ( userChoice.equals( "5" ) )
                {
                    result = polynomial.div( p2 );
                }
                else
                {
                    result = null;
                }

                // Print the result
                if ( result != null )
                {
                    System.out.println( YELLOW + "P(x) = " + result + RESET_COLOR );
                }
                else if ( !userChoice.equals( "q" ) )
                {
                    System.out.println( RED + "Something went wrong." + RESET_COLOR );
                }
            }
        }
    }
}
