/**
 * This abstract class provides a skeletal methods for the Mail and Package classes.
 * @author Omer Oktay Gultekin
 * @version 10.03.2021 3.0.0 final
 */
public abstract class Delivery
{
    // Properties
    int packageNo;
    Customer sender;
    Customer receiver;

    //Constructor
    /**
     * This constructor initializes the sender, receiver and package number of a delivery.
     * @param sender the sender of a delivery
     * @param receiver the receiver of a delivery
     * @param packageNo package no of a delivery
     */
    public Delivery( Customer sender, Customer receiver, int packageNo )
    {
        this.sender = sender;
        this.receiver = receiver;
        this.packageNo = packageNo;
    }

    // Methods
    /**
     * @return package No of the delivery
     */
    public int getPackageNo()
    {
        return packageNo;
    }

    /**
     * @return the sender of the delivery
     */
    public Customer getSender()
    {
        return sender;
    }

    /**
     * @return the receiver of the delivery
     */
    public Customer getReceiver()
    {
        return receiver;
    }

    /**
     * @return the weight of the delivery
     */
    abstract double getWeight();
}
