import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class provides a modified actionListener for the buttons in the program
 * @author Omer Oktay Gultekin
 * @version 31.03.2021 2.0.0 final
 */
public class ClickListener implements ActionListener {

    // Properties
    public static int counter = 1;

    // What will be done when the button pressed
    @Override
    public void actionPerformed( ActionEvent e ) {

        if ( e.getSource() instanceof BombButton ) {
            Lab05a.statusLabel.setText( "Sorry! You are blown up at attempt " + counter + "!" );
            Lab05a.finishGame();
        }
        else if ( e.getSource() instanceof PrizeButton ) {
            Lab05a.statusLabel.setText( "You got it in "  + counter + " attempts!" );
            Lab05a.finishGame();
        }
        else {
            Lab05a.statusLabel.setText( "Number of attempts: " + counter );
            counter++;
        }
        ( (JButton) e.getSource() ).setEnabled( false );
    }
}
