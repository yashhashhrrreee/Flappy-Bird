// Importing necessary libraries
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;

// Defining a class named Render
public class Render {
    
    // Defining public instance variables
    public int x;
    public int y;
    public Image image;
    public AffineTransform transform;

    // Default constructor
    public Render() {
    }

    // Constructor to set the x, y coordinates and load the image from the specified file path
    public Render(int x, int y, String imagePath) 
    {
        // Calling the sync method on the default toolkit to ensure all pending paint requests have been processed
        Toolkit.getDefaultToolkit().sync();
        
        // Setting the x and y coordinates
        this.x = x;
        this.y = y;
        
        // Loading the image from the specified file path using the loadImage method from the Util class
        this.image = Util.loadImage(imagePath);
    }
}
