import java.util.ArrayList;

/**
 * This class represents a polynomial equation
 * @author Omer Oktay Gultekin
 * @version 17.02.2020 4.0.0 final
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
        numberOfElements = degree + 1; // remember ax^0
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
                // Do not add sign of the first element
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
            // Print the 0 if and only if polynomial is zero polynomial
            else if ( coefficients.length <= 1 && coefficients[i] == 0 )
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
        sum += coefficients[0]; // explicitly add ax^0 because it does not have an x value
        return sum;
    }

    /**
     * This method sums this polynomial (polynomial for which the method
     * is called) and polynomial p2, and returns the result as a new polynomial.
     * @param p2 polynomial object which will be added
     * @return the result of addition.
     */
    public Polynomial add( Polynomial p2 )
    {
        return internalCalculationForAddAndSub( p2, "+" );
    }

    /**
     * This method subtracts polynomial p2 from this polynomial for which
     * the method is called on, and returns the result as a new polynomial.
     * @param p2 polynomial object which will be subtracted
     * @return the result of subtraction.
     */
    public Polynomial sub( Polynomial p2 )
    {
        return internalCalculationForAddAndSub( p2, "-" );
    }

    /**
     * This method multiplies this polynomial (polynomial for which the method
     * is called), and polynomial p2 and returns the result as a new polynomial.
     * @param p2 polynomial object which will be multiplied
     * @return the result of multiplication.
     */
    public Polynomial mul( Polynomial p2 )
    {
        // Variables
        double[] resultCoefficients;
        int length;
        Polynomial resultPolynomial;

        // Method Implementation
        length = this.getDegree() + p2.getDegree() + 1;
        resultCoefficients = new double[length];

        // Multiply all elements one by one
        for ( int i = 0; i <= this.getDegree(); i++ )
        {
            for ( int j = 0; j <= p2.getDegree(); j++ )
            {
                resultCoefficients[i + j] += this.getCoefficients( i ) * p2.getCoefficients( j );
            }
        }
        resultCoefficients = removeLeadingZerosOfCoefficients( resultCoefficients );
        resultPolynomial = new Polynomial( resultCoefficients );
        return resultPolynomial;
    }

    /**
     * This method compose this polynomial (polynomial for which the method
     * is called) with the polynomial p2 and returns the result as a new polynomial.
     * @param p2 polynomial object which will be composed of
     * @return the result of composition.
     */
    public Polynomial compose( Polynomial p2 )
    {
        // Variables
        Polynomial resultPolynomial;
        Polynomial tempPolynomial;

        // Method Implementation
        resultPolynomial = new Polynomial();

        // Take coefficients of this polynomial one by one and multiply them with p2 to the power of x times
        for ( int i = 0; i <= this.getDegree(); i++ )
        {
            tempPolynomial = new Polynomial( 0, this.getCoefficients( i ) );
            for ( int j = 0; j < i; j++ )
            {
                tempPolynomial = tempPolynomial.mul( p2 );
            }
            resultPolynomial = resultPolynomial.add( tempPolynomial );
        }
        return resultPolynomial;
    }

    /**
     * This method divides this polynomial (polynomial for which the method
     * is called) with the polynomial p2 and returns the result as a new polynomial.
     * @param p2 polynomial object which will be divider
     * @return the result of division.
     */
    public Polynomial div( Polynomial p2 )
    {
        // Variables
        Polynomial copyOfThisPolynomial;
        Polynomial temp;
        Polynomial resultPolynomial;
        double coefficient;

        // Method Implementation
        resultPolynomial = new Polynomial();
        if ( p2.getDegree() != 0 && p2.getCoefficients( 0 ) != 0 )
        {
            // Make a copy of this polynomial
            copyOfThisPolynomial = new Polynomial( this.coefficients );

            while ( copyOfThisPolynomial.getDegree() >= p2.getDegree() )
            {
                // Find the leading terms and divide
                // Check zero division!
                if ( p2.getCoefficients( p2.getDegree() ) == 0 )
                {
                    return null;
                }
                // Use round method to get double value with 2 decimal place
                coefficient = Math.round( copyOfThisPolynomial.getCoefficients( copyOfThisPolynomial.getDegree() ) /
                        p2.getCoefficients( p2.getDegree() ) * 100 ) / 100.0;

                temp = new Polynomial( copyOfThisPolynomial.getDegree() - p2.getDegree(), coefficient );
                // P(x) = P(x) - T(x) * Q(x)
                copyOfThisPolynomial = copyOfThisPolynomial.sub( temp.mul( p2 ) );
                resultPolynomial = resultPolynomial.add( temp );
            }
            return resultPolynomial;
        }
        else
        {
            return null;
        }
    }

    /**
     * This method do the calculations for given operator.
     * @param p2 polynomial object which will be operated with this polynomial
     * @param mode whether the calculation will be addition or subtraction
     * @return the result of calculation.
     */
    private Polynomial internalCalculationForAddAndSub( Polynomial p2, String mode )
    {
        // Instead of ArrayList, array can be used with removeLeadingZerosOfCoefficients method
        // but I want to show another method

        // Variables
        // Use ArrayList as the size of result polynomial is not certain (absolute value of leading terms can be equal)
        ArrayList<Double> resultCoefficients;
        int length;
        Polynomial resultPolynomial;
        double[] resultCoefficientsArray;

        // Method Implementation
        length = Math.max( this.numberOfElements, p2.numberOfElements );
        resultCoefficients = new ArrayList<>();

        // Start with the biggest power of the x
        for ( int i = length - 1; i >= 0; i-- )
        {
            double coefficient = 0;
            // Operation
            if ( i <= this.getDegree() )
            {
                coefficient += this.getCoefficients(i);
            }
            if ( i <= p2.getDegree() )
            {
                if ( mode.equals( "+" ) )
                {
                    coefficient += p2.getCoefficients( i );
                }
                else
                {
                    coefficient -= p2.getCoefficients( i );
                }
            }
            // Do not add leading zeros
            if ( coefficient != 0 )
            {
                resultCoefficients.add( coefficient );
            }
            else
            {
                if ( resultCoefficients.size() > 0 )
                {
                    resultCoefficients.add( coefficient );
                }
            }
        }
        // if there is no element after the addition operation, put 0
        if ( resultCoefficients.size() == 0 )
        {
            resultCoefficients.add( 0.0 );
        }

        resultCoefficientsArray = new double[resultCoefficients.size()];
        for ( int i = 0; i < resultCoefficients.size(); i++ )
        {
            resultCoefficientsArray[i] = resultCoefficients.get( resultCoefficients.size() - i - 1 );
        }
        resultPolynomial = new Polynomial( resultCoefficientsArray );
        return resultPolynomial;
    }

    /**
     * This method removes the leading zeros from given array of coefficients
     * @param resultCoefficients result coefficients of the calculation
     * @return shrunk array of coefficients
     */
    private double[] removeLeadingZerosOfCoefficients( double[] resultCoefficients )
    {
        // Variables
        double[] nonZeroCoefficients;
        int leadingZeroTermCounter;

        // Implementation
        leadingZeroTermCounter = 0;
        for ( int i = resultCoefficients.length - 1; i >= 1; i-- )
        {
            if ( resultCoefficients[i] == 0 )
            {
                leadingZeroTermCounter++;
            }
            else
            {
                break;
            }
        }
        nonZeroCoefficients = new double[resultCoefficients.length - leadingZeroTermCounter];
        for ( int i = 0; i < nonZeroCoefficients.length; i++ )
        {
            nonZeroCoefficients[i] = resultCoefficients[i];
        }
        return nonZeroCoefficients;
    }
}