// Importing necessary classes
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;

// The Bird class
public class Bird {
// Declaring instance variables
public int x; // x-coordinate of the bird
public int y; // y-coordinate of the bird
public int width; // Width of the bird
public int height; // Height of the bird
public boolean dead; // A boolean flag indicating whether the bird is dead or not
public double yvel; // The velocity of the bird in the y-direction
public double gravity; // The gravitational acceleration acting on the bird
private int jumpDelay; // The delay between two jumps of the bird
private double rotation; // The rotation of the bird
private Image image; // The image of the bird
private Keyboard keyboard; // An instance of the Keyboard class

// The constructor of the Bird class
public Bird() 
{
    // Initializing instance variables
    x = 100;
    y = 150;
    yvel = 0;
    width = 45;
    height = 32;
    gravity = 0.5;
    jumpDelay = 0;
    rotation = 0.0;
    dead = false;

    // Getting an instance of the Keyboard class
    keyboard = Keyboard.getInstance();
}

// The update method of the Bird class
public void update() 
{
    // Updating the velocity of the bird
    yvel += gravity;

    // Decrementing the jump delay
    if (jumpDelay > 0)
        jumpDelay--;

    // If the space bar is pressed, and the bird is not dead, and the jump delay is less than or equal to 0
    if (!dead && keyboard.isDown(KeyEvent.VK_SPACE) && jumpDelay <= 0) {
        // Making the bird jump
        yvel = -8;
        // Setting the jump delay
        jumpDelay = 10;
    }

    // Updating the position of the bird
    y += (int)yvel;
}

// The getRender method of the Bird class
public Render getRender() 
{
    // Creating a new instance of the Render class
    Render r = new Render();
    // Setting the x and y coordinates of the bird
    r.x = x;
    r.y = y;

    // If the image of the bird is null, load it
    if (image == null) {
        image = Util.loadImage("lib/bird.png");     
    }
    // Setting the image of the bird in the render object
    r.image = image;

    // Calculating the rotation of the bird
    rotation = (90 * (yvel + 20) / 20) - 90;
    rotation = rotation * Math.PI / 180;

    // If the rotation of the bird is greater than PI/2, set it to PI/2
    if (rotation > Math.PI / 2)
        rotation = Math.PI / 2;

    // Creating a new AffineTransform object
    r.transform = new AffineTransform();
    // Translating the AffineTransform object to the center of the bird
    r.transform.translate(x + width / 2, y + height / 2);
    // Rotating the AffineTransform object by the rotation angle
    r.transform.rotate(rotation);
    // Translating the AffineTransform object back to the top-left corner of the bird
    r.transform.translate(-width / 2, -height / 2);

    // Returning the Render object
    return r;
}

}
