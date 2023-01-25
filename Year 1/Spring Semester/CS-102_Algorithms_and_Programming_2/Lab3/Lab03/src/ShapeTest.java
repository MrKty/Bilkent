/**
 * This class tests the Shape classes methods
 * @author Omer Oktay Gultekin
 * @version 03.03.2021 2.0.0 final
 */
public class ShapeTest
{
    public static void main( String[] args )
    {
        // Variables
        Circle    circle;
        Square    sq;
        Square    sq2;
        Square    sq3;
        Square    sq4;
        Rectangle rectangle;
        Shape2D[] shapeArray;

        // Program Code
        //Initializations
        rectangle = new Rectangle( 2, 3, 8, 15 );
        circle = new Circle( 13, 15, 3.0 );
        sq= new Square( -2, -5, 5 );
        sq2 = new Square( -2, -5, 5 );
        sq3 = new Square( -1, -5, 5 );
        sq4 = null;
        shapeArray = new Shape2D[3];

        //Testing toString and equals methods
        System.out.println( rectangle );
        System.out.println( circle );
        System.out.println( "sq: " + sq );
        System.out.println( "sq2: " + sq2 );
        System.out.println( "sq3: " + sq3 );
        System.out.println( "sq4: " + sq4 );
        System.out.println( "sq.equals( sq2 ) is " +  sq.equals( sq2 ) );
        System.out.println( "sq.equals( sq3 ) is " + sq.equals( sq3 ) );
        System.out.println( "sq.equals( sq4 ) is " + sq.equals( sq4 ) );
        System.out.println( "sq.equals( circle ) is " + sq.equals( circle ) );


        // Filling, Printing and Doing some operations with shapeArray
        shapeArray[0] = rectangle;
        shapeArray[1] = circle;
        shapeArray[2] = sq;
        System.out.print( "The shape array: [" );
        for ( int i = 0; i < shapeArray.length; i++ )
        {
            System.out.print( shapeArray[i] );
            if ( i != shapeArray.length - 1 )
            {
                System.out.println( ", " );
            }
        }
        System.out.println( "]" );
        System.out.println( findLargestArea( shapeArray ).toString().split( "]" )[0].split( " " )[1] + " has largest area" );
        System.out.println( findLongestPerimeter( shapeArray ).toString().split( "]" )[0].split( " " )[1] + " has longest perimeter" );
        System.out.println( "Distance between shape 1 shape 2 is " + shapeArray[0].calculateDistance( shapeArray[1] ) );
        System.out.println( "Distance between shape 1 shape 3 is " + shapeArray[0].calculateDistance( shapeArray[2] ) );
        System.out.println( "Distance between shape 2 shape 3 is " + shapeArray[1].calculateDistance( shapeArray[2] ) );
    }

    /**
     * This method find and return the shape that has a largest area in the given Shape2D Array
     * @param shapeArray Shape2D array in which the shape with the largest area is searched
     * @return Shape2D object that has a largest area
     */
    public static Shape2D findLargestArea( Shape2D[] shapeArray )
    {
        // Variables
        double largestArea;
        double currentShapeArea;
        Shape2D shape;

        // Method Implementation
        largestArea = 0;
        shape = null;
        for ( Shape2D shape2D: shapeArray )
        {
            currentShapeArea = shape2D.calculateArea();
            if ( currentShapeArea > largestArea )
            {
                largestArea = currentShapeArea;
                shape = shape2D;
            }
        }
        return shape;
    }

    /**
     * This method find and return the shape that has a longest perimeter in the given Shape2D Array
     * @param shapeArray Shape2D array in which the shape with the longest perimeter is searched
     * @return Shape2D object that has a longest perimeter
     */
    public static Shape2D findLongestPerimeter( Shape2D[] shapeArray )
    {
        // Variables
        double longestPerimeter;
        double currentShapePerimeter;
        Shape2D shape;

        // Method Implementation
        longestPerimeter = 0;
        shape = null;
        for ( Shape2D shape2D: shapeArray )
        {
            currentShapePerimeter = shape2D.calculatePerimeter();
            if ( currentShapePerimeter > longestPerimeter )
            {
                longestPerimeter = currentShapePerimeter;
                shape = shape2D;
            }
        }
        return shape;
    }
}
