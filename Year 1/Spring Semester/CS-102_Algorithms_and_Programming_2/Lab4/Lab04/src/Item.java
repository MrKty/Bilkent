/**
 * This class represents an item to be sent via Delivery object
 * @author Omer Oktay Gultekin
 * @version 10.03.2021 3.0.0 final
 */
public class Item
{
    //Properties
    private double weight;
    private String content;

    //Constructor
    /**
     * This constructor initializes the weight and the content of an item.
     * @param weight weight of the item
     * @param content content of the item
     */
    public Item( double weight, String content )
    {
        this.weight = weight;
        this.content = content;
    }

    // Methods
    /**
     * @return weight of the item
     */
    public double getWeight()
    {
        return weight;
    }

    /**
     * @return content of the item
     */
    public String getContent()
    {
        return content;
    }

    /**
     * @return String representation of the item
     */
    @Override
    public String toString()
    {
        return "Item{" +
                " weight=" + weight +
                ", content='" + content + '\'' +
                " }";
    }
}
