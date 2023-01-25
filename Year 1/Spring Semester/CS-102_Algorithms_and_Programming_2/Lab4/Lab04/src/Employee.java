/**
 * This class represents an employee who is responsible for packaging and delivery
 * @author Omer Oktay Gultekin
 * @version 10.03.2021 3.0.0 final
 */
public class Employee extends Person
{
    // Properties
    private final int MAX_JOBS;
    private int currentJobs;
    private Delivery[] deliveries; //The undelivered packages, mails are held here.
    private double salary;
    public int employeeNo;
    private boolean available;

    // Constructor
    /**
     * This constructor initialize the employee number and name of the employee.
     * @param employeeNo employee number of the employee
     * @param name name of the employee
     */
    public Employee( int employeeNo, String name )
    {
        super( name );
        this.employeeNo = employeeNo;
        MAX_JOBS = 5;
        deliveries = new Delivery[ MAX_JOBS ];
        currentJobs = 0;
        available = true;
        salary = 100;
    }

    // Methods
    /**
     * This method adjusts the employee’s salary by a given value.
     * @param value new salary
     */
    public void adjustSalary( double value )
    {
        this.salary = value;
    }

    /**
     * This method returns if the employee is available or not
     * @return true if the employee is available, false otherwise.
     */
    public boolean getAvailability()
    {
        return available;
    }


    /**
     * This method determines the type of item and converts it to either mail (weight <= 0.1) or
     * package (weight > 0.1) and adds it to the array of deliveries.
     * @param sendItem item object to be sent
     * @param sender customer that send the item
     * @param receiver customer that will get the item
     * @param packageNo package number of the delivery
     */
    public void addJob( Item sendItem, Customer sender, Customer receiver, int packageNo )
    {
        // Variables
        double weight;

        // Method implementation
        if ( available )
        {
            weight = sendItem.getWeight();

            if ( weight <= 0.1 )
            {
                Mail mail = new Mail( sendItem.getContent(), sender, receiver, packageNo );
                deliveries[currentJobs] = mail;
            }
            else
            {
                Package sendingPackage = new Package( sendItem, sender, receiver, packageNo );
                deliveries[currentJobs] = sendingPackage;
            }
            currentJobs++;

            if ( currentJobs == MAX_JOBS )
            {
                available = false;
            }
        }
    }

    /**
     * This method prints the information of the delivered items and of the sender and receiver customer.
     * This method also clears the array of deliveries.
     */
    public void deliverPackages()
    {
        for ( int i = 0; i < deliveries.length; i++ )
        {
            // Jobs is end
            if ( deliveries[i] == null )
            {
                break;
            }

            // Sent the delivery
            deliveries[i].sender.setCurrentItem( null );
            if ( deliveries[i] instanceof Package )
            {
                deliveries[i].getReceiver().setCurrentItem( ( (Package) deliveries[i] ).packedItem );
            }
            else
            {
                deliveries[i].getReceiver().setCurrentItem( new Item( deliveries[i].getWeight(),
                        ( (Mail) deliveries[i] ).content ) );
            }

            // Print the information of the delivery
            System.out.println( deliveries[i] );

            // Remove the delivery from delivery list
            deliveries[i] = null;
        }
        currentJobs = 0;
        available = true;
    }

    /**
     * @return String representation of the employee
     */
    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder("Employee{" +
                " name= " + this.getName() +
                ", MAX_JOBS=" + MAX_JOBS +
                ", currentJobs=" + currentJobs +
                ", salary=" + salary +
                ", employeeNo=" + employeeNo +
                ", available=" + available +
                ", posX=" + getX() +
                ", posY=" + getY() +
                ", deliveries=[");
        for ( Delivery delivery : deliveries )
        {
            if ( delivery != null )
            {
                stringBuilder.append( delivery ).append( " " );
            }
        }
        stringBuilder.append( "]}" );
        return stringBuilder.toString();
    }

}
