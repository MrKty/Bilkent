/**
 * This class represents the Square shape
 * @author Omer Oktay Gultekin
 * @version 03.03.2021 2.0.0 final
 */
public class Square extends Shape2D
{
    // Properties
    // private final can be changed depends on the instructions of the future labs
    private final int SIDE_LENGTH;

    // Constructor
    /**
     * Constructor for the Square object
     * @param x x value of the center of the Square object
     * @param y y value of the center of the Square object
     * @param sideLength the side of the Square object
     */
    public Square( int x, int y, int sideLength )
    {
        super( x, y );
        this.SIDE_LENGTH = sideLength;
    }

    // Methods
    /**
     * Calculate the perimeter of the Square object
     * @return perimeter of the Square object
     */
    @Override
    public double calculatePerimeter()
    {
        return 4 * SIDE_LENGTH;
    }

    /**
     * Calculate the area of the Square object
     * @return area of the Square object
     */
    @Override
    public double calculateArea()
    {
        return SIDE_LENGTH * SIDE_LENGTH;
    }


    /**
     * This method represents the Square object as a String.
     * @return String representation of the Square object with it's side length and center's x, y coordinates.
     */
    @Override
    public String toString() {
        return "[class Square]" + super.toString() + " " + "and side = " + SIDE_LENGTH;
    }

    /**
     * This method compares one Square object and one Object class instance and return whether or not they are equal.
     * @param o Object class instance that if an instance of Square class, compared with this Square object
     * @return true if given object is a Square object that is equal to this one; false otherwise.
     */
    @Override
    public boolean equals( Object o )
    {
        if ( super.equals( o ) )
        {
            if ( o instanceof Square )
            {
                return this.SIDE_LENGTH == ( (Square) o ).SIDE_LENGTH;
            }
        }
        return false;
    }
}
