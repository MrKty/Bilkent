/**
 * This class represents a mail to be sent
 * @author Omer Oktay Gultekin
 * @version 10.03.2021 3.0.0 final
 */
public class Mail extends Delivery
{
    // Properties
    private final double WEIGHT_OF_MAIL;
    public String content;

    // Constructor
    /**
     * This constructor initializes the content, sender, receiver and package number of a mail.
     * @param content content of a mail
     * @param sender the sender of a mail
     * @param receiver the receiver of a mail
     * @param packageNo package number of a mail
     */
    public Mail( String content, Customer sender, Customer receiver, int packageNo )
    {
        super( sender, receiver, packageNo );
        this.content = content;
        WEIGHT_OF_MAIL = 0.1;
    }

    // Methods

    /**
     * @return weight of the mail determined by the constant value.
     */
    @Override
    double getWeight()
    {
        return WEIGHT_OF_MAIL;
    }

    /**
     * @return String representation of the mail.
     */
    @Override
    public String toString()
    {
        return "Mail{" +
                " packageNo=" + packageNo +
                ", sender=" + sender +
                ", receiver=" + receiver +
                ", content='" + content + '\'' +
                " }";
    }
}
