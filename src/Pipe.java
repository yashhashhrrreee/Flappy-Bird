// Importing necessary libraries
import java.awt.Image;

// Defining a class named Pipe
public class Pipe 
{

    // Defining public instance variables
    public int x;
    public int y;
    public int width;
    public int height;
    public int speed = 3;

    public String orientation;

    // Private instance variable
    private Image image;

    // Constructor to set the orientation and reset the values
    public Pipe(String orientation) 
    {
        // Setting the orientation
        this.orientation = orientation;
        
        // Calling the reset method to initialize values
        reset();
    }

    // Method to reset the values
    public void reset() 
    {
        // Setting the width and height of the pipe
        width = 66;
        height = 400;
        
        // Setting the x coordinate to be outside of the screen
        x = App.WIDTH + 2;

        // If the orientation is "south", set the y coordinate to a random value above the top of the screen
        if (orientation.equals("south")) 
        {
            y = -(int)(Math.random() * 120) - height / 2;
        }
    }

    // Method to update the x coordinate
    public void update() 
    {
        x -= speed;
    }

    // Method to check if the pipe collides with another object
    public boolean collides(int _x, int _y, int _width, int _height) 
    {
        // Setting a margin to give some leeway for collision detection
        int margin = 2;

        // Checking for collision based on the orientation of the pipe
        switch (orientation) 
        {
            case "south":
                if (_x + _width - margin > x && _x + margin < x + width && _y < y + height) 
                {
                    return true;
                }
                break;
            case "north":
                if (_x + _width - margin > x && _x + margin < x + width && _y + _height > y) 
                {
                    return true;
                }
                break;
        }

        // If there is no collision, return false
        return false;
    }

    // Method to get the Render object for the pipe
    public Render getRender() 
    {
        // Creating a new Render object
        Render r = new Render();
        
        // Setting the x and y coordinates
        r.x = x;
        r.y = y;

        // If the image has not been loaded yet, load it using the Util class and set it to the image variable
        if (image == null) 
        {
            image = Util.loadImage("lib/pipe-" + orientation + ".png");
        }
        
        // Set the image to the image variable of the Render object
        r.image = image;

        // Return the Render object
        return r;
    }
}
