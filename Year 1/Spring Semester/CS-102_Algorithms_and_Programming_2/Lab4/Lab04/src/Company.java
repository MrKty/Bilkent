import java.util.ArrayList;

/**
 * This class represents a company
 * @author Omer Oktay Gultekin
 * @version 10.03.2021 3.0.0 final
 */
public class Company implements Locatable
{
    // Properties
    private final int EMPLOYEE_CAPACITY;
    private Employee[] employees;
    private ArrayList<Customer> customers;
    private int employeeNo; // the next available employee number
    private int packageNo; // the next package number of the delivery
    private int posX;
    private int posY;
    private int numOfEmployees;

    // Constructors
    /**
     * This constructor set the position and initialize any instance variables the object has.
     * @param x the x coordinate of the company
     * @param y the y coordinate of the company
     */
    public Company( int x, int y )
    {
        this.posX = x;
        this.posY = y;
        EMPLOYEE_CAPACITY = 15;
        numOfEmployees = 0;
        employees = new Employee[EMPLOYEE_CAPACITY];
        customers = new ArrayList<>();
        packageNo = 1;
        employeeNo = 0;
    }

    /**
     * This method adds new employee to the company
     * @param candidate employee that will be hired if there is an empty position for the candidate
     * @return true if the employee is successfully added, false otherwise.
     */
    public boolean addEmployee( Employee candidate )
    {
        // if there is an empty position for the new employee
        if ( numOfEmployees < EMPLOYEE_CAPACITY )
        {
                // Is the empty position at the end of the array?
                if ( employees[numOfEmployees] == null )
                {
                    employees[numOfEmployees] = candidate;
                }
                else
                {
                    // There is an empty position in the array (there is someone being fired earlier)
                    for ( int i = 0; i < numOfEmployees; i++ )
                    {
                        if ( employees[i] == null )
                        {
                            // Update the employee number of the employee and place the employee into an empty position
                            candidate.employeeNo = i + 1;
                            employees[i] = candidate;
                            break;
                        }
                    }
                }
                numOfEmployees++;
                return true;
        }
        return false;
    }

    /**
     * This method adds the given customer to the customer list of the company
     * @param customer the customer that will be added
     */
    public void addCustomer( Customer customer )
    {
        if ( !customers.contains( customer ) )
        {
            customers.add( customer );
        }
    }

    /**
     * This method fire the employee at the given index.
     * @param employeeIndex the position of the employee that will be fired.
     * @return true if the employee at the given index is deleted, false otherwise.
     */
    public boolean terminateContract( int employeeIndex )
    {
        if ( employees[employeeIndex] != null )
        {
            employees[employeeIndex] = null;
            numOfEmployees--;
            System.out.println( "Company fired the employee in the index " + employeeIndex );
            return true;
        }
        return false;
    }

    /**
     * This method creates a deliverable object from the item if
     * an employee is available and returns true, otherwise it returns false.
     * @param sendItem item to be sent
     * @param sender customer that send the item
     * @param receiver customer that will receive the item
     * @return true if the given order is successfully completed, false otherwise.
     */
    public boolean createDeliverable( Item sendItem, Customer sender, Customer receiver )
    {
            // if there is an employee in the company
            if ( employees[employeeNo] != null )
            {
                // if employee is not available and there is no other available employee, return false.
                if ( !employees[employeeNo].getAvailability() )
                {
                    employeeNo++;
                    if ( employees[employeeNo] == null )
                    {
                        return false;
                    }
                }

                employees[employeeNo].addJob( sendItem, sender, receiver, packageNo );
                packageNo++;
                return true;
            }
            return false;
    }

    /**
     * This method delivers all the packages via Employees and print
     * the delivery information.
     */
    public void deliverPackages()
    {
        if ( customers.size() == 0 )
        {
            System.out.println( "No packages to deliver" );
        }

        for ( Employee employee : employees )
        {
            // if there is no other employee, finish the process
            if ( employee == null )
            {
                break;
            }
            employee.deliverPackages();
        }
        // Clear the customers list since their orders were done
        customers.clear();
    }

    /**
     * This method determines the employee number of the next employee to be hired.
     * @return the employee number of the new employee
     */
    public int calculateTheNextEmployeeNo()
    {
        return numOfEmployees + 1;
    }

    /**
     * @return String representation of the company
     */
    @Override
    public String toString()
    {
        StringBuilder stringBuilder;

        stringBuilder = new StringBuilder( "Company{" +
                "EMPLOYEE_CAPACITY=" + EMPLOYEE_CAPACITY +
                ", customers=" + customers +
                ", numOfEmployees=" + numOfEmployees +
                ", posX=" + posX +
                ", posY=" + posY +
                ", employees=[" );
        for ( Employee employee : employees )
        {
            if ( employee != null )
            {
                stringBuilder.append( employee );
            }
        }
        stringBuilder.append( "]}" );
        return stringBuilder.toString();
    }

    /**
     * @return the position of the company in the x axis.
     */
    @Override
    public int getX()
    {
        return posX;
    }

    /**
     * @return the position of the company in the y axis.
     */
    @Override
    public int getY()
    {
        return posY;
    }

    /**
     * Set the position of the company in the coordinate system.
     * @param x the x position of the company
     * @param y the y position of the company
     */
    @Override
    public void setPos( int x, int y )
    {
        posX = x;
        posY = y;
    }
}
