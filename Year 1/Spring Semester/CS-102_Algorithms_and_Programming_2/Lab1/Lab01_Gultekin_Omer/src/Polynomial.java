/**
 * This class represents a polynomial equation
 * @author Omer Oktay Gultekin
 * @version 10.02.2020 2.0.0 Final
 */
public class Polynomial
{
    // Properties
    int numberOfElements;
    double[] coefficients;

    // Constructors
    /**
     * Constructor for polynomial in the form of cx^d
     * @param degree degree of the one element polynomial
     * @param coefficient coefficient of the element
     */
    public Polynomial( int degree, double coefficient )
    {
        numberOfElements = degree + 1;
        coefficients = new double[numberOfElements];
        coefficients[numberOfElements - 1] = coefficient;
    }

    /**
     * Constructor for zero polynomial
     */
    public Polynomial()
    {
        numberOfElements = 1;
        coefficients = new double[numberOfElements];
        coefficients[0] = 0;
    }

    /**
     * Constructor for polynomial whose terms are all explicitly defined
     * @param coefficients the list of double values that will be coefficients of the terms of polynomial
     */
    public Polynomial( double[] coefficients )
    {
        this.numberOfElements = coefficients.length;
        this.coefficients = coefficients;
    }


    // Methods
    /**
     * This method get coefficient of the term with specified degree.
     * @param degree which term will be considered
     * @return coefficient of the term with specified degree.
     */
    public double getCoefficients( int degree )
    {
        return coefficients[degree];
    }

    /**
     * This method get degree of the polynomial.
     * @return degree of the polynomial.
     */
    public int getDegree()
    {
        return coefficients.length - 1; // polynomials start with a constant value (ax^0)
    }

    /**
     * This method converts the class object to the String.
     * @return a well formatted representation of the polynomial.
     */
    @Override
    public String toString()
    {
        // Variables
        StringBuilder sb;

        // Method Implementation
        sb = new StringBuilder();
        for ( int i = 0; i < coefficients.length; i++ )
        {
            if ( coefficients[i] > 0 )
            {
                if ( sb.length() != 0 )
                {
                    sb.append( " + " );
                }
                sb.append( coefficients[i] );
                // do not print x^0
                if ( i != 0 )
                {
                        sb.append( "x^" ).append( i );
                }
            }
            else if ( coefficients[i] < 0 )
            {
                sb.append( " - " );
                sb.append( Math.abs( coefficients[i] ) );
                // do not print x^0
                if ( i != 0 )
                {
                    sb.append( "x^" ).append( i );
                }
            }
            // Print the 0 If and only if polynomial is zero polynomial
            else if ( coefficients.length == 1 && coefficients[i] == 0 )
            {
                sb.append( coefficients[i] );
            }

        }
        return sb.toString();
    }

    /**
     * This method evaluates the polynomial equation for given number using classic method.
     * @param x given number which will be inserted into the equation
     * @return the result of solving polynomial equation.
     */
    public double eval( double x )
    {
        // Variables
        double sum;

        // Method Implementation
        sum = 0;
        for ( int i = 0; i < coefficients.length; i++ )
        {
            sum += Math.pow( x, i ) * coefficients[i];
        }
        return sum;
    }

    /**
     * This method evaluates the polynomial equation for given number using Horner's method.
     * @param x given number which will be inserted into the equation
     * @return the result of solving polynomial equation.
     */
    public double eval2( double x )
    {
        // Variables
        double sum;

        // Method Implementation
        sum = 0;
        for ( int i = coefficients.length - 1; i > 0; i-- )
        {
            sum += coefficients[i];
            sum *= x;
        }
        sum += coefficients[0];
        return sum;
    }

}
