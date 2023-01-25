import javax.swing.*;
import java.awt.*;

/**
 * This class represents bomb buttons.
 * @author Omer Oktay Gultekin
 * @version 31.03.2021 2.0.0 final
 */
public class BombButton extends JButton {

    // Properties
    public static boolean isExploded;

    // Constructor
    public BombButton( String number ) {
        setText( number );
    }

    // Draw the bomb onto the screen if it was clicked
    @Override
    protected void paintComponent( Graphics g ) {
        super.paintComponent( g );
        if ( isExploded ) {
            setText("");
            Graphics2D graphics2D = (Graphics2D) g;
            graphics2D.setColor( Color.gray );
            graphics2D.fillRect(this.getWidth() / 2 - this.getWidth() / 16, this.getHeight() / 2 - this.getHeight() / 8 - this.getHeight() / 30, this.getWidth() / 8, this.getHeight() / 10 );
            graphics2D.setColor( Color.black );
            graphics2D.fillOval(this.getWidth() / 2 - this.getWidth() / 8, this.getHeight() / 2 - this.getHeight() / 8, this.getWidth() / 4, this.getHeight() / 4 );
            graphics2D.setColor( Color.red );
            graphics2D.setStroke( new BasicStroke( 3 ) );
            graphics2D.drawArc( this.getWidth() / 2, this.getHeight() / 2 - this.getHeight() / 8 - this.getHeight() / 10, this.getWidth() / 8, this.getHeight() / 8, 20, 170 );
        }
    }
}
