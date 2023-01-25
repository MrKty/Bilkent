/**
 * This class represents a customer
 * @author Omer Oktay Gultekin
 * @version 10.03.2021 3.0.0 final
 */
public class Customer extends Person
{
    // Properties
    private Item currentItem;

    // Constructor
    /**
     * This constructor initialize the name of the customer and his/her position to 0 (internally).
     * @param name name of the customer
     */
    public Customer( String name )
    {
        super( name );
    }

    // Methods
    /**
     * @return current item that customer has
     */
    public Item getCurrentItem()
    {
        return currentItem;
    }

    /**
     * @param currentItem item to be set to the current item of the customer
     */
    public void setCurrentItem( Item currentItem )
    {
        this.currentItem = currentItem;
    }

    /**
     * This method sends an item via given company to the receiver if an employee is available(returns false otherwise).
     * Item object is ignored if the customer already has an item to be sent.
     * @param company the company that will deliver the item to the receiver
     * @param item item to be sent if the customer don't have an item
     * @param receiver the customer to whom the item was sent
     * @return true if the company's employees were available and took the item, false otherwise.
     */
    public boolean sendItem( Company company, Item item, Customer receiver )
    {
        // Variables
        boolean isSuccessful;

        // Method Implementation
        if ( currentItem != null )
        {
            isSuccessful = company.createDeliverable( currentItem, this, receiver );
            currentItem = null;
        }
        else
        {
            isSuccessful = company.createDeliverable( item, this, receiver );
        }
        // Now, sender and receiver are customers of the company
        company.addCustomer( this );
        company.addCustomer( receiver );

        return isSuccessful;
    }

    /**
     * @return String representation of the customer.
     */
    @Override
    public String toString()
    {
        return "Customer{" +
                " name=" + getName() +
                ", posX=" + getX() +
                ", posY=" + getY() +
                " }";
    }
}
