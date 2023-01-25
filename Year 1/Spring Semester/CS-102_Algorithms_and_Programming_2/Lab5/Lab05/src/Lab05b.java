import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.*;

/**
 * This program enable user to convert decimal-hex-binary number.
 * @author Omer Oktay Gultekin
 * @version 31.03.2021 2.0.0 final
 */
public class Lab05b {
    public static void main( String[] args ) {

        // Variables
        JFrame jFrame;
        JPanel jPanel;
        JPanel decimalPanel;
        JPanel hexPanel;
        JPanel binaryPanel;
        JLabel decimalLabel;
        JLabel hexLabel;
        JLabel binaryLabel;
        JTextField decimalTextField;
        JTextField hexTextField;
        JTextField binaryTextField;

        // Program Code
        jFrame = new JFrame("Convert Numbers" );

        // Panels Initialization
        jPanel = new JPanel();
        jPanel.setLayout( new GridLayout( 3, 1 ) );

        decimalPanel = new JPanel();
        decimalPanel.setLayout( new GridLayout( 1, 2 ) );

        hexPanel = new JPanel();
        hexPanel.setLayout( new GridLayout( 1, 2 ) );

        binaryPanel = new JPanel();
        binaryPanel.setLayout( new GridLayout( 1, 2 ) );


        // Labels Initialization
        decimalLabel = new JLabel( "Decimal" );
        decimalLabel.setPreferredSize( new Dimension( 40, 50 ) );

        hexLabel = new JLabel( "Hex" );
        hexLabel.setPreferredSize( new Dimension( 40, 50 ) );

        binaryLabel = new JLabel( "Binary" );
        binaryLabel.setPreferredSize( new Dimension( 40, 50 ) );

        // TextFields Initialization
        decimalTextField = new JTextField();
        decimalTextField.setPreferredSize( new Dimension( 250, 100 ) );
        decimalTextField.setHorizontalAlignment( JTextField.RIGHT );

        hexTextField = new JTextField();
        hexTextField.setPreferredSize( new Dimension( 250, 100 ) );
        hexTextField.setHorizontalAlignment( JTextField.RIGHT );

        binaryTextField = new JTextField();
        binaryTextField.setPreferredSize( new Dimension( 250, 100 ) );
        binaryTextField.setHorizontalAlignment( JTextField.RIGHT );

        // Adding Action Listeners to the TextFields
        hexTextField.addActionListener( new ActionListener() {

            // What will be done when the button pressed
            @Override
            public void actionPerformed( ActionEvent e ) {
                binaryTextField.setText( hexToBinary( hexTextField.getText() ) );
                decimalTextField.setText( hexToDecimal( hexTextField.getText() ) );
            }
        } );

        binaryTextField.addActionListener( new ActionListener() {

            // What will be done when the button pressed
            @Override
            public void actionPerformed( ActionEvent e ) {
                hexTextField.setText( binaryToHex( binaryTextField.getText() ) );
                decimalTextField.setText(binaryToDecimal( binaryTextField.getText() ) );
            }
        } );

        decimalTextField.addActionListener( new ActionListener() {

            // What will be done when the button pressed
            @Override
            public void actionPerformed( ActionEvent e ) {
                hexTextField.setText( decimalToHex( decimalTextField.getText() ) );
                binaryTextField.setText( decimalToBinary( decimalTextField.getText() ) );
            }
        } );

        // Adding components to the Containers
        decimalPanel.add( decimalLabel );
        hexPanel.add( hexLabel );
        binaryPanel.add( binaryLabel );
        hexPanel.add( hexTextField );
        binaryPanel.add( binaryTextField );
        decimalPanel.add( decimalTextField );

        jPanel.add( decimalPanel );
        jPanel.add( hexPanel );
        jPanel.add( binaryPanel );
        jFrame.add( jPanel );

        // Instead of giving size to the frame let it decide what the size should be based on the components it has
        jFrame.pack();

        // Dynamically change the size of the fonts by listening the resizing of jFrame
        jFrame.addComponentListener( new ComponentAdapter() {

            // Variables
            private int size;

            /**
             * This method make the text bigger when the frame resizing
             * @param e info about which event is done such as resizing, moving.
             */
            public void componentResized( ComponentEvent e ) {
                // Dynamically get one of the panels size (all three are identical )
                size = hexPanel.getHeight() / 16 + hexPanel.getWidth() / 16;

                // Assign a new font
                hexLabel.setFont( new Font( Font.SERIF, Font.PLAIN, size ) );
                binaryLabel.setFont( new Font( Font.SERIF, Font.PLAIN, size ) );
                decimalLabel.setFont( new Font( Font.SERIF, Font.PLAIN, size ) );
                hexTextField.setFont( new Font( Font.SERIF, Font.PLAIN, size / 2 ) );
                binaryTextField.setFont( new Font( Font.SERIF, Font.PLAIN, size / 2 ) );
                decimalTextField.setFont( new Font( Font.SERIF, Font.PLAIN, size / 2 ) );
            }
        } );

        jFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        jFrame.setVisible( true );
    }

    /**
     * Convert the given decimal number to the binary
     * @param number decimal number in String format
     * @return binary number that is in String format
     */
    public static String decimalToBinary( String number ) {

        // Variables
        int decimalNumber;
        StringBuilder binaryNumber;

        // Method Implementation
        decimalNumber = Integer.parseInt( number );
        binaryNumber = new StringBuilder();

        while ( decimalNumber > 0 ) {
            binaryNumber.append( decimalNumber % 2 );
            decimalNumber /= 2; // int division is intentional
        }
        // above algorithm gives the binary number in reverse order
        binaryNumber = binaryNumber.reverse();
        if ( binaryNumber.length() == 0 ) {
            binaryNumber.append( "0" );
        }

        return binaryNumber.toString();
    }

    /**
     * Convert the given decimal number to the hex number
     * @param number decimal number in String format
     * @return hex number that is in String format
     */
    public static String decimalToHex( String number ) {
        
        // Constants
        final String[] hexNumbers = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F" };

        // Variables
        int decimalNumber;
        StringBuilder hexNumber;

        // Method Implementation
        decimalNumber = Integer.parseInt( number );
        hexNumber = new StringBuilder();

        while ( decimalNumber > 0 ) {
            hexNumber.append( hexNumbers[decimalNumber % 16] );
            decimalNumber /= 16;
        }
        // above algorithm gives the hex number in reverse order
        hexNumber = hexNumber.reverse();
        if ( hexNumber.length() == 0 ) {
            hexNumber.append("0");
        }

        return hexNumber.toString();
    }

    /**
     * Convert the given hex number to the decimal number
     * @param number hex number in String format
     * @return decimal number that is in String format
     */
    public static String hexToDecimal( String number ) {
        // Constants
        final char[] hexNumbers = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

        // Variables
        int decimalNumber;
        int counter;

        // Method Implementation
        decimalNumber = 0;
        counter = 0;
        number = number.toUpperCase();

        // Search the place of every hex digit and add it to the decimal number by multiplying it with place value
        for ( int i = number.length() - 1; i >= 0; i-- ) {
            for ( int j = 0; j < hexNumbers.length; j++ ) {
                if ( hexNumbers[j] == number.charAt( i ) ) {
                    decimalNumber += j * Math.pow( 16, counter );
                    counter++;
                    break;
                }
            }
        }
        return Integer.toString( decimalNumber );
    }

    /**
     * Convert the given binary number to the decimal number
     * @param number hex number in String format
     * @return decimal number that is in String format
     */
    public static String binaryToDecimal( String number ) {

        // Variables
        int decimalNumber;
        int counter;
        int nextDigit;

        // Method Implementation
        decimalNumber = 0;
        counter = 0;

        for ( int i = number.length() - 1; i >= 0; i-- ) {
            nextDigit = Integer.parseInt( String.valueOf( number.charAt( i ) ) );
            if ( nextDigit == 1 || nextDigit == 0 ) {
                decimalNumber += nextDigit * Math.pow( 2, counter );
                counter++;
            }
            else {
                // Exception will not break the program while not showing meaningless result in the GUI
                throw new NumberFormatException();
            }
        }
        return Integer.toString( decimalNumber );
    }

    /**
     * Convert the given hex number to the binary number
     * @param number hex number in String format
     * @return binary number that is in String format
     */
    public static String hexToBinary( String number ) {
        return decimalToBinary( hexToDecimal( number ) );
    }

    /**
     * Convert the given binary number to the hex number
     * @param number binary number in String format
     * @return hex number that is in String format
     */
    public static String binaryToHex( String number ) {
        return decimalToHex( binaryToDecimal( number ) );
    }
}
