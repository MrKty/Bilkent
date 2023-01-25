/**
 * This class represents a package to be sent
 * @author Omer Oktay Gultekin
 * @version 10.03.2021 3.0.0 final
 */
public class Package extends Delivery
{
    // Properties
    public Item packedItem;

    // Constructor
    /**
     * This constructor initializes the packed item, sender, receiver and package number of a mail.
     * @param packedItem item inside the package
     * @param sender the sender of a package
     * @param receiver the receiver of a package
     * @param packageNo package number of a package
     */
    public Package( Item packedItem, Customer sender, Customer receiver, int packageNo )
    {
        super( sender, receiver, packageNo );
        this.packedItem = packedItem;
    }

    // Methods
    /**
     * @return weight of the package
     */
    @Override
    double getWeight()
    {
        return packedItem.getWeight();
    }

    /**
     * @return String representation of the package.
     */
    @Override
    public String toString()
    {
        return "Package{" +
                " packageNo=" + packageNo +
                ", sender=" + sender +
                ", receiver=" + receiver +
                ", packedItem=" + packedItem +
                " }";
    }
}
