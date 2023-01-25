/**
 * This abstract class provides a skeletal methods for the 2D shape classes
 * @author Omer Oktay Gultekin
 * @version 03.03.2021 2.0.0 final
 */
public abstract class Shape2D
{
    // Properties
    // All the subclasses work with these properties in this class and after creating they are not changing
    // In the next labs, if the instructions demanded, these access modifiers and final keyword can be changed
    private final int X;
    private final int Y;

    // Constructor
    /**-
     * Constructor for the Shape2D object
     * @param x x value of the center of the Shape2D object
     * @param y y value of the center of the Shape2D object
     */
    public Shape2D( int x, int y )
    {
        this.X = x;
        this.Y = y;
    }

    // Methods
    /**
     * Calculate the perimeter of a 2D shape
     * @return perimeter of a 2D shape
     */
    public abstract double calculatePerimeter();

    /**
     * Calculate the area of a 2D shape
     * @return area of a 2D shape
     */
    public abstract double calculateArea();

    /**
     * Calculate the euclidean distance between the centers of two Shape2D objects.
     * @param anyShape Object class instance that if an instance of Shape2D class, the distance from the other shape will be calculated.
     * @return the euclidean distance between the centers of two Shape2D objects. -1 If parameter is not Shape2D object.
     */
    public double calculateDistance( Object anyShape )
    {
        if ( anyShape instanceof Shape2D )
        {
            return Math.sqrt( Math.abs( Math.pow( this.X - ( (Shape2D) anyShape ).X, 2 ) + Math.pow( this.Y - ( (Shape2D) anyShape).Y, 2 ) ) );
        }
        return -1;
    }

    /**
     * This method represents the class object as a String.
     * @return A string representation of the Shape 2D object with its center x and y coordinates.
     */
    @Override
    public String toString()
    {
        return "x = " + X + ", y = " + Y;
    }


    /**
     * This method compares one Shape2D object and one Object class instance and return whether or not they are equal.
     * @param o Object class instance that if an instance of a Shape2D class, will be compared with this Shape2D object.
     * @return true if given object is a Shape2D object that is equal to this one; false otherwise.
     */
    @Override
    public boolean equals( Object o )
    {
        if ( o instanceof Shape2D )
        {
            return this.X == ( (Shape2D) o ).X && this.Y == ( (Shape2D) o ).Y;
        }
        return false;
    }
}
