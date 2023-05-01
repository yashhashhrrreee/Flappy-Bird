
//Importing the necessary Libraries
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener 
{
	// Declare a singleton instance of the Keyboard class
	private static Keyboard instance;

	// Declare a boolean array to store the state of each key
	private boolean[] keys;

	// The constructor initializes the boolean array
	private Keyboard() 
	{
		keys = new boolean[256];
	}

	// This method returns the singleton instance of the Keyboard class
	public static Keyboard getInstance() 
	{
		// If the instance has not yet been created, create it
		if (instance == null) 
		{
			instance = new Keyboard();
		}
		
		return instance;
	}

	// This method is called when a key is pressed
	public void keyPressed(KeyEvent e) 
	{
		// Update the state of the key in the boolean array
		if (e.getKeyCode() >= 0 && e.getKeyCode() < keys.length) 
		{
			keys[e.getKeyCode()] = true;
		}
	}
	
	// This method is called when a key is released
	public void keyReleased(KeyEvent e) 
	{
		// Update the state of the key in the boolean array
		if (e.getKeyCode() >= 0 && e.getKeyCode() < keys.length) 
		{
			keys[e.getKeyCode()] = false;
		}
	}

	// This method is called when a key is typed (pressed and released)
	public void keyTyped(KeyEvent e) 
	{
		// This method is not used in this implementation
	}

	// This method checks if a particular key is currently pressed down
	public boolean isDown(int key) 
	{
		// Return the state of the corresponding element in the boolean array
		if (key >= 0 && key < keys.length) 
		{
			return keys[key];
		}
		
		return false;
	}
}

