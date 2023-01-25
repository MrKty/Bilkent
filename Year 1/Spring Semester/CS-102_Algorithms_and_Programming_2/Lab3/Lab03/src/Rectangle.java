/**
 * This class represents the Rectangle shape
 * @author Omer Oktay Gultekin
 * @version 03.03.2021 2.0.0 final
 */
public class Rectangle extends Shape2D
{
    // Properties
    // private final can be changed depends on the instructions of the future labs
    private final int HEIGHT;
    private final int WIDTH;

    // Constructor
    /**
     * Constructor for the Rectangle object
     * @param x x value of the center of the Rectangle object
     * @param y y value of the center of the Rectangle object
     * @param height height of the Rectangle object
     * @param width width of the Rectangle object
     */
    public Rectangle( int x, int y, int height, int width )
    {
        super( x, y );
        this.HEIGHT = height;
        this.WIDTH = width;
    }

    // Methods
    /**
     * Calculate the perimeter of the Rectangle object
     * @return perimeter of the Rectangle object
     */
    @Override
    public double calculatePerimeter()
    {
        return 2 * ( HEIGHT + WIDTH );
    }

    /**
     * Calculate the area of the Rectangle object
     * @return area of the Rectangle object
     */
    @Override
    public double calculateArea()
    {
        return HEIGHT * WIDTH;
    }

    /**
     * This method represents the Rectangle object as a String.
     * @return String representation of the Rectangle object with it's width and center's x, y coordinates.
     */
    @Override
    public String toString()
    {
        return "[class Rectangle]" + super.toString() + " " + "height = " + HEIGHT + " and width = " + WIDTH;
    }

    /**
     * This method compares one Rectangle object and one Object class instance and return whether or not they are equal.
     * @param o Object class instance that if an instance of Rectangle class, compared with this Rectangle object
     * @return true if given object is a Rectangle object that is equal to this one; false otherwise.
     */
    @Override
    public boolean equals( Object o )
    {
        if ( super.equals( o ) )
        {
            if ( o instanceof Rectangle )
            {
                return this.HEIGHT == ( (Rectangle) o ).HEIGHT && this.WIDTH == ( (Rectangle) o ).WIDTH;
            }
        }
        return false;
    }
}
