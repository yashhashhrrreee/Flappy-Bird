import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable 
{

    private Game game; // Declare private variable game of type Game

    public GamePanel()
    {
        game = new Game(); // Initialize the game object with a new instance of the Game class
        new Thread(this).start(); // Start a new thread with this GamePanel instance
    }
    
    public void update() 
    {
        game.update(); // Call the update method of the game object
        repaint(); // Repaint the panel
    }
    
    // Override the paintComponent method to draw on the panel
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g); // Call the parent class's paintComponent method
    
        Graphics2D g2D = (Graphics2D) g; // Cast the Graphics object to a Graphics2D object
        // Iterate through the list of renders in the game object and draw each one on the panel
        for (Render r : game.getRenders())
            if (r.transform != null)
                g2D.drawImage(r.image, r.transform, null);
            else
                g.drawImage(r.image, r.x, r.y, null);
    
        g2D.setColor(Color.BLACK); // Set the drawing color to black
    
        // If the game hasn't started yet, display instructions on the panel
        if (!game.started) 
        {
            g2D.setFont(new Font("TimesRoman", Font.PLAIN, 20)); // Set the font for the text
            g2D.drawString("Press SPACE to start", 150, 240); // Display the "Press SPACE to start" message
            g2D.drawString("Press P to pause", 150, 280); // Display the "Press P to pause" message
        } 
        else // If the game has started, display the score
        {
            g2D.setFont(new Font("TimesRoman", Font.PLAIN, 24)); // Set the font for the text
            g2D.drawString(Integer.toString(game.score), 10, 465); // Display the score
        }
        // If the game is over, display a message to restart
        if (game.gameover) 
        {
            g2D.setFont(new Font("TimesRoman", Font.PLAIN, 20)); // Set the font for the text
            g2D.drawString("Press R to restart", 150, 240); // Display the "Press R to restart" message
        }
    }
    
    // Implement the Runnable interface's run method
    public void run() {
        try {
            while (true) { // Keep running indefinitely
                update(); // Call the update method
                Thread.sleep(25); // Pause the thread for 25 milliseconds
            }
        }
        catch (Exception e) {
            e.printStackTrace(); // Print the stack trace if an exception occurs
        }
    }
    
}
