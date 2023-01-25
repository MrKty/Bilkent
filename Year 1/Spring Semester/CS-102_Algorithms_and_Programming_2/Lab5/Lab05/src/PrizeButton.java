import javax.swing.*;
import java.awt.*;

/**
 * This class represents prize buttons.
 * @author Omer Oktay Gultekin
 * @version 31.03.2021 2.0.0 final
 */
public class PrizeButton extends JButton {

    // Properties
    public static boolean isRevealed;

    // Constructor
    public PrizeButton( String number ) {
        setText( number );
    }

    // Draw the star onto the screen if it was clicked
    @Override
    protected void paintComponent( Graphics g ) {
        super.paintComponent( g );
        if ( isRevealed ) {
            setText("");
            Graphics2D graphics2D = (Graphics2D) g;
            graphics2D.setColor( Color.orange );
            graphics2D.setFont( new Font( Font.SERIF, Font.PLAIN, ( this.getWidth() + this.getHeight() ) / 2 ) );
            graphics2D.drawString( "★", 3 * this.getWidth() / 16, 13 * this.getHeight() / 16 );
            setOpaque( true );
        }
    }
}
