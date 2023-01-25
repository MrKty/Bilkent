/**
 * This interface has a methods for location
 * @author Omer Oktay Gultekin
 * @version 10.03.2021 3.0.0 final
 */
interface Locatable
{
    //Methods
    /**
     * @return the position of the object in the x axis.
     */
    int getX();

    /**
     * @return the position of the object in the y axis.
     */
    int getY();

    /**
     * Set the position of the object in the coordinate system.
     * @param x the x position of the object
     * @param y the y position of the object
     */
    void setPos( int x, int y );
}
