import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

/**
 * This class tests the mail delivery system.
 * @author Omer Oktay Gultekin
 * @version 10.03.2021 3.0.0 final
 */
public class CompanyTester
{
    public static void main( String[] args ) throws InterruptedException
    {
        // Variables
        Scanner scanner;
        ArrayList<Company> companies;
        ArrayList<Customer> customers;
        Customer currentCustomer;
        boolean programLoop;
        int nextEmployeeNo;
        String choice;

        // Program Code
        programLoop = true;
        companies = new ArrayList<>();
        customers = new ArrayList<>();
        scanner = new Scanner( System.in ).useLocale( Locale.US );

        // Initialize the first company with one employee
        companies.add( new Company( 5, 1 ) );
        nextEmployeeNo = companies.get( 0 ).calculateTheNextEmployeeNo();
        companies.get( 0 ).addEmployee( new Employee( nextEmployeeNo, "Employee" + nextEmployeeNo ) );

        // Initialize the second company with zero employee
        companies.add( new Company( 2, 3) );

        System.out.println( "Welcome to the Mail Delivery System" );
        System.out.print( "Enter your name: " );
        currentCustomer = new Customer( scanner.nextLine() ); // it will be used as a sender

        while ( programLoop )
        {
            System.out.println( "Menu: \n" +
                    "1- Company List \n" +
                    "2- New Customer \n" +
                    "3- New Delivery \n" +
                    "4- Show the items that customers have \n" +
                    "5- Request delivery of packages \n" +
                    "0- Quit" );
            System.out.print( "Your choice: " );
            choice = scanner.nextLine();
            switch ( choice )
            {
                case "1":
                    showListOfCompanies( companies );
                    break;

                case "2":
                    addNewCustomer( scanner, customers );
                    break;

                case "3":
                    CreateNewDelivery( scanner, companies, customers, currentCustomer );
                    break;

                case "4":
                    showItemsDelivered( customers, currentCustomer );
                    break;

                case "5":
                    deliverPackages( companies );
                    break;

                case "0":
                    programLoop = false;
                    break;

                default:
                    System.out.println( "Invalid Operation" );
                    System.out.println();
                    break;
            }
        }
        System.out.println( "Bye!" );
    }

    /**
     * This method creates a new order for delivering items
     * @param scanner Scanner object to take user input
     * @param companies the list of all companies
     * @param customers the list of all customers
     * @param currentCustomer sender of the items
     */
    private static void CreateNewDelivery( Scanner scanner, ArrayList<Company> companies, ArrayList<Customer> customers,
                                           Customer currentCustomer ) throws InterruptedException
    {
        // Variables
        int companyIndex;
        int nextEmployeeNo;
        Item itemToSent;
        double itemWeight;
        String receiverName;
        String itemContent;
        String companyName;
        boolean isItemSent;

        //Method Implementation
        if ( customers.size() == 0 )
        {
            System.out.println( "You should first add customers in the main menu" );
        }
        else
        {
            System.out.print( "Enter the content of the new item: " );
            itemContent = scanner.nextLine();
            System.out.print( "Enter the weight of the new item: " );
            try
            {
                itemWeight = scanner.nextDouble();
            }
            catch ( Exception e )
            {
                itemWeight = 1;
                System.out.println( "Weight should be numeric. 1 gr assigned to the weight by default" );
            }
            scanner.nextLine(); // move the cursor to next line
            itemToSent = new Item( itemWeight, itemContent );

            System.out.print( "Enter the company name you want to use: " );
            companyName = scanner.nextLine();
            if ( companyName.equalsIgnoreCase( "Company1" ) ||
                    companyName.equalsIgnoreCase( "Company2" ) )
            {
                System.out.println( "The list of customers:" );
                for ( Customer customer : customers )
                {
                    System.out.println( customer );
                }
                System.out.println();
                System.out.print( "Enter the name of the customer you want to send the item: " );
                receiverName = scanner.nextLine().toLowerCase();

                for ( int i = 0; i < customers.size(); i++ )
                {
                    if ( customers.get( i ).getName().equals( receiverName ) )
                    {
                        companyIndex = companyName.equalsIgnoreCase( "Company1" ) ? 0 : 1;
                        isItemSent = currentCustomer.sendItem( companies.get( companyIndex ), itemToSent,
                                customers.get( i ) );
                        if ( isItemSent )
                        {
                            System.out.println( "The item to be sent was notified to the company successfully" );
                        }
                        else
                        {
                            System.out.println( "There is no available employee. The company trying to hire new " +
                                    "employee to take your order." );
                            nextEmployeeNo = companies.get( companyIndex ).calculateTheNextEmployeeNo();

                            // if the employee cannot be added because of maximum allowed number
                            if ( !companies.get( companyIndex ).addEmployee( new Employee( nextEmployeeNo,
                                    "Employee" + nextEmployeeNo ) ) )
                            {
                                System.out.println( "Companies are full. They make all the packages delivered. " +
                                        "Hold a second." );
                                Thread.sleep(100);
                            }
                            currentCustomer.sendItem( companies.get( companyIndex ), itemToSent, customers.get( i ) );
                            System.out.println( "The company took your order" );
                        }
                        break;

                    }
                    else if ( i == ( customers.size() - 1 ) )
                    {
                        System.out.println( "No customer found with this name!" );
                        System.out.println();
                    }
                }
            }
            else
            {
                System.out.println( "No such company found in the database!" );
                System.out.println();
            }
        }
    }

    /**
     * This method adds new customer to the all customers list.
     * @param scanner Scanner object to take an input
     * @param customers list of customers that new customer will be added
     */
    private static void addNewCustomer( Scanner scanner, ArrayList<Customer> customers )
    {
        // Variables
        String customerName;

        System.out.print( "Enter the name of the customer: " );
        customerName = scanner.nextLine().toLowerCase();
        customers.add( new Customer( customerName ) );
        System.out.println( "New customer was successfully added to the system!" );
        System.out.println();
    }

    /**
     * This method prints the available companies
     * @param companies available companies list
     */
    private static void showListOfCompanies( ArrayList<Company> companies )
    {
        for ( int i = 0; i < companies.size(); i++ )
        {
            System.out.println( "Company" + ( i + 1 ) + ":\n" + companies.get( i ) );
            System.out.println();
        }
    }

    /**
     * This method shows what customers has after the transactions.
     * @param customers all customers that have been created since the program ran
     * @param currentCustomer sender of the items
     */
    private static void showItemsDelivered( ArrayList<Customer> customers, Customer currentCustomer )
    {
        // Variables
        int counter;
        Item currentItem;

        // Method Implementation
        counter = 0;
        for ( Customer customer : customers )
        {
            currentItem = customer.getCurrentItem();
            if ( currentItem != null )
            {
                System.out.println( customer.getName() + ": " + currentItem );
                counter++;
            }
        }
        currentItem = currentCustomer.getCurrentItem();
        if ( currentItem != null )
        {
            System.out.println( currentItem );
            counter++;
        }
        if ( counter == 0 )
        {
            System.out.println( "The item list is empty" );
        }
        System.out.println();
    }

    /**
     * Deliver the packages of the customers of all companies and then terminate the contracts of the employees
     * @param companies a list of companies
     */
    private static void deliverPackages( ArrayList<Company> companies )
    {
        for ( int i = 0; i < companies.size(); i++ )
        {
            System.out.println( "Company" + ( i + 1 ) + ":" );
            companies.get( i ).deliverPackages();
            System.out.println();
        }

        // terminate all contracts of the employee, it is inefficient way to do this but purpose here is the show
        // the terminateContract() works. (one employee will be remain in each company)
        for ( Company company : companies )
        {
            for ( int j = 1; j < company.calculateTheNextEmployeeNo(); j++ )
            {
                company.terminateContract( j );
            }
        }
    }
}

