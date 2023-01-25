import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.Comparator;
import javax.swing.*;

/**
 * This program make a simple guess game
 * @author Omer Oktay Gultekin
 * @version 31.03.2021 2.0.0 final
 */
public class Lab05a {

    // Properties
    private static JButton[] jButtons;
    private static JFrame jFrame;
    private static JPanel gamePanel;
    public static JLabel statusLabel;
    private static int responsiveFontSize;

    public static void main( String[] args ) {

        // Variables
        JPanel statusPanel;

        // Program Code
        jFrame = new JFrame( "PotLuck" );
        jFrame.setSize( 400, 400 );
        gamePanel = new JPanel();
        gamePanel.setLayout( new GridLayout( 5, 5 ) );
        jFrame.add( gamePanel, BorderLayout.CENTER );
        statusLabel = new JLabel( "" );

        // create the status bar panel and show it down the bottom of the jFrame
        statusPanel = new JPanel();
        jFrame.add( statusPanel, BorderLayout.SOUTH );
        statusPanel.setPreferredSize( new Dimension( jFrame.getWidth(), 20 ) );

        // In order to make the alignment left, use BoxLayout
        statusPanel.setLayout( new BoxLayout( statusPanel, BoxLayout.X_AXIS ) );
        statusLabel.setHorizontalAlignment( JLabel.LEFT );
        statusPanel.add( statusLabel );

        startGame();

        // Dynamically change the size of the fonts by listening the resizing of jFrame
        jFrame.addComponentListener( new ComponentAdapter() {

            // Variables
            private int previousHeight;
            private int previousWidth;

            /**
             * This method make the text bigger when the frame resizing
             * @param e info about which event is done such as resizing, moving.
             */
            public void componentResized( ComponentEvent e ) {

                // Dynamically get one of the buttons size (all of them are identical )
                // int division intentional here. Otherwise, in every small changes code will run therefore, inefficient
                if ( previousHeight != jButtons[0].getHeight() / 8 && previousWidth != jButtons[0].getWidth() / 8 ) {
                    previousHeight = jButtons[0].getHeight() / 8;
                    previousWidth = jButtons[0].getWidth() / 8;
                    responsiveFontSize = previousHeight + previousWidth;

                    // Assign a new font
                    for ( JButton button: jButtons ) {
                        button.setFont( new Font( Font.SERIF, Font.PLAIN, responsiveFontSize ) );
                    }
                }
            }
        } );

        jFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        jFrame.setVisible( true );
    }

    /**
     * This method generate random indexes for bombs and prize.
     * @return an Arraylist of random indexes
     */
    private static ArrayList<Integer> generateRandomIndexes() {

        // Variables
        ArrayList<Integer> randomIndexes;
        int index;
        int counter;

        // Program Code
        randomIndexes = new ArrayList<>();
        counter = 0;

        while ( counter < 3 ) {
            index = (int) ( Math.random() * 25 + 1 );
            if ( !randomIndexes.contains( index ) ) {
                randomIndexes.add( index );
                counter++;
            }
        }

        // sort the list as an increasing order.
        randomIndexes.sort( new Comparator<Integer>() {
            /**
             * This method provide a method for list to compare its elements.
             * @param o1 one element of the list
             * @param o2 another element of the list
             * @return 0 if two are equal, value < 0 if o1 < o2, value > 0 if o2 < o1
             */
            @Override
            public int compare( Integer o1, Integer o2 ) {
                return o1.compareTo( o2 );
            }
        } );
        return randomIndexes;
    }

    /**
     * This method finish the game by showing all bombs and prize,
     * making all buttons disable and showing a message for restarting game
     */
    public static void finishGame() {

        for ( JButton jbutton: jButtons ) {
            if ( jbutton instanceof PrizeButton ) {
                PrizeButton.isRevealed = true;
                jbutton.repaint();
            }
            else if ( jbutton instanceof  BombButton ) {
                BombButton.isExploded = true;
                jbutton.repaint();
            }
            jbutton.setEnabled( false );
        }

        // 0 for yes, 1 for No
        int answer = JOptionPane.showConfirmDialog( jFrame,statusLabel.getText(), "Restart Game?",
                JOptionPane.YES_NO_OPTION );
        if ( answer == 0 ) {
            startGame();
        }
        else {
            // Close the application
            System.exit( 0 );
        }
    }

    /**
     * This method starting the game
     */
    public static void startGame() {

        // Variables
        ArrayList<Integer> randomList;
        int counter;
        JButton jButton;

        // Method Implementation
        counter = (int) ( Math.random() * 3 );
        randomList = generateRandomIndexes();
        gamePanel.removeAll(); // if the game restart, there are already buttons.
        jButtons = new JButton[25];

        for ( int i = 1; i <= 25; i++ ) {
            if ( randomList.contains( i ) ) {
                if ( counter == 0 ) {
                    jButton = new PrizeButton("" + i );
                }
                else {
                    jButton = new BombButton("" + i );
                }
                counter--;
            }
            else {
                jButton = new JButton("" + i );
            }
            jButton.setFocusable( false ); // removes the outline of selected button
            jButton.addActionListener( new ClickListener() );
            jButtons[i-1] = jButton; // i started from 1, ending 25
            gamePanel.add( jButton );
            jButton.setFont( new Font( Font.SERIF, Font.PLAIN, responsiveFontSize ) );
        }

        BombButton.isExploded = false;
        PrizeButton.isRevealed = false;
        ClickListener.counter = 1;
        statusLabel.setText("");
    }
}
