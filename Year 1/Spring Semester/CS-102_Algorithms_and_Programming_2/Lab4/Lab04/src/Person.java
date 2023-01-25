/**
 * This abstract class provides a skeletal methods for the Employee and Customer classes.
 * @author Omer Oktay Gultekin
 * @version 10.03.2021 3.0.0 final
 */
public abstract class Person implements Locatable
{
    // Properties
    private String name;
    private int posX;
    private int posY;

    // Constructors
    /**
     * This constructor initialize the name of the person and his/her position
     * @param name name of the person
     * @param posX x position of the person
     * @param posY y position of the person
     */
    public Person( String name, int posX, int posY )
    {
        this.name = name;
        this.posX = posX;
        this.posY = posY;
    }

    /**
     * This constructor initialize the name of the person and his/her position to 0
     * @param name name of the person
     */
    public Person( String name )
    {
        this.name = name;
        this.posX = 0;
        this.posY = 0;
    }

    // Methods
    /**
     * @return the name of the person
     */
    public String getName()
    {
        return name;
    }

    /**
     * set the name of the person
     * @param name name to be set
     */
    public void setName( String name )
    {
        this.name = name;
    }


    /**
     * @return the position of the person in the x axis.
     */
    @Override
    public int getX()
    {
        return posX;
    }

    /**
     * @return the position of the person in the y axis.
     */
    @Override
    public int getY()
    {
        return posY;
    }

    /**
     * Set the position of the person in the coordinate system.
     * @param x the x position of the person
     * @param y the y position of the person
     */
    @Override
    public void setPos( int x, int y )
    {
        posX = x;
        posY = y;
    }

}
