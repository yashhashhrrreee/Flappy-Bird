import javax.swing.*;

public class App 
{

    public static int WIDTH = 500; // create a public static variable to hold the width of the frame
    public static int HEIGHT = 520; // create a public static variable to hold the height of the frame

    public static void main(String[] args) {
        JFrame frame = new JFrame(); // create a new JFrame object
        frame.setVisible(true); // make the frame visible
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // set the default close operation for the frame
        frame.setLocationRelativeTo(null); // center the frame on the screen

        Keyboard keyboard = Keyboard.getInstance(); // create a new instance of the Keyboard class
        frame.addKeyListener(keyboard); // add the keyboard listener to the frame

        GamePanel panel = new GamePanel(); // create a new GamePanel object
        frame.add(panel); // add the panel to the frame
        frame.setResizable(false); // make the frame not resizable
        frame.setSize(WIDTH, HEIGHT); // set the size of the frame to the values of the width and height variables
    }
}
