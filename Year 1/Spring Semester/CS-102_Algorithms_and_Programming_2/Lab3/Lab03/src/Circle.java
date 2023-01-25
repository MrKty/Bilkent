/**
 * This class represents the Circle shape
 * @author Omer Oktay Gultekin
 * @version 03.03.2021 2.0.0 final
 */
public class Circle extends Shape2D
{
    // Properties
    // private final can be changed depends on the instructions of the future labs
    private final double RADIUS;

    // Constructor
    /**
     * Constructor for the Circle object
     * @param x x value of the center of the Circle object
     * @param y y value of the center of the Circle object
     * @param radius radius of the Circle object
     */
    public Circle( int x, int y, double radius )
    {
        super( x, y );
        this.RADIUS = radius;
    }

    // Methods
    /**
     * Calculate the perimeter of the Circle object
     * @return perimeter of the Circle object
     */
    @Override
    public double calculatePerimeter()
    {
        return 2 * Math.PI * RADIUS;
    }

    /**
     * Calculate the area of the Circle object
     * @return area of the Circle object
     */
    @Override
    public double calculateArea()
    {
        return Math.PI * Math.pow( RADIUS, 2 );
    }

    /**
     * This method represents the Circle object as a String.
     * @return String representation of the Circle object with it's radius and center's x, y coordinates.
     */
    @Override
    public String toString()
    {
        return "[class Circle]" + super.toString() + " " + "and radius = " + RADIUS;
    }

    /**
     * This method compares one Circle object and one Object class instance and return whether or not they are equal.
     * @param o Object class instance that if an instance of Circle class, compared with this Circle object
     * @return true if given object is a Circle object that is equal to this one; false otherwise.
     */
    @Override
    public boolean equals( Object o )
    {
        if ( super.equals( o ) )
        {
            if ( o instanceof Circle )
            {
                return this.RADIUS == ( (Circle) o ).RADIUS;
            }
        }
        return false;
    }
}
