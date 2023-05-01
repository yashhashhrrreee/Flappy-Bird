// Importing necessary libraries
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;

// Defining a class named Util
public class Util {
    
    // Creating a static HashMap cache to store images
    private static final HashMap<String, Image> cache = new HashMap<>(); 
    
    // Defining a method named loadImage to load an image from the specified path
    public static Image loadImage(String path) 
    {
        // Retrieving the image from the cache if it already exists
        Image image = cache.get(path);

        // If the image is not already in the cache
        if (image == null) 
        { 
            // Adding synchronization to avoid multiple threads from accessing the cache at the same time
            synchronized (cache) 
            {
                // Checking the cache again to see if the image has been added while the thread was waiting
                image = cache.get(path);
                
                // If the image is still not in the cache, load it from the file and add it to the cache
                if (image == null) 
                {
                    try 
                    {
                        // Reading the image from the specified file path
                        image = ImageIO.read(new File(path));
                        
                        // Adding the image to the cache
                        cache.put(path, image);
                    } 
                    catch (IOException e) 
                    {
                        // Printing the stack trace if an exception occurs while loading the image
                        e.printStackTrace();
                    }
                }
            }
        }

        // Returning the loaded image
        return image;
    }
}
