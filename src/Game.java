import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Game 
{

    public static final int PIPE_DELAY = 100; // delay for generating new pipes

    private Boolean paused; // flag for whether the game is paused

    private int pauseDelay; // delay between pausing and unpausing
    private int restartDelay; // delay between restarting the game
    private int pipeDelay; // delay between generating new pipes

    private Bird bird; // the player-controlled bird object
    private ArrayList<Pipe> pipes; // a list of pipes for the bird to avoid
    private Keyboard keyboard; // an object for detecting user input

    public int score; // the player's score
    public Boolean gameover; // flag for whether the game is over
    public Boolean started; // flag for whether the game has started

    public Game() {
        keyboard = Keyboard.getInstance(); // create a new keyboard object for detecting user input
        restart(); // initialize the game state
    }

    public void restart() 
    { // reset the game state to its initial values
        paused = false;
        started = false;
        gameover = false;

        score = 0;
        pauseDelay = 0;
        restartDelay = 0;
        pipeDelay = 0;

        bird = new Bird(); // create a new bird object
        pipes = new ArrayList<Pipe>(); // create a new list of pipes
    }

    public void update() 
    { // update the game state
        watchForStart(); // check for user input to start the game

        if (!started) // if the game hasn't started yet, do nothing
            return;

        watchForPause(); // check for user input to pause or unpause the game
        watchForReset(); // check for user input to restart the game

        if (paused) // if the game is paused, do nothing
            return;

        bird.update(); // update the bird's position

        if (gameover) // if the game is over, do nothing
            return;

        movePipes(); // move the pipes across the screen
        checkForCollisions(); // check for collisions between the bird and the pipes
    }

    public ArrayList<Render> getRenders() 
    { // get the game objects to be rendered on the screen
        ArrayList<Render> renders = new ArrayList<Render>(); // create a new list of Render objects
        renders.add(new Render(0, 0, "lib/background.png")); // add the background image
        for (Pipe pipe : pipes)
            renders.add(pipe.getRender()); // add the pipe images
        renders.add(new Render(0, 0, "lib/foreground.png")); // add the foreground image
        renders.add(bird.getRender()); // add the bird image
        return renders; // return the list of images to be rendered
    }

    private void watchForStart() 
    { // check for user input to start the game
        if (!started && keyboard.isDown(KeyEvent.VK_SPACE)) {
            started = true;
        }
    }

    private void watchForPause() 
    { // check for user input to pause or unpause the game
        if (pauseDelay > 0)
            pauseDelay--;

        if (keyboard.isDown(KeyEvent.VK_P) && pauseDelay <= 0) 
        {
            paused = !paused; // toggle the paused flag
            pauseDelay = 10; // set the delay before allowing the player to pause or unpause again
        }
    }

    // Watch for game reset
    private void watchForReset() {
        // If there is still a delay for restart, decrement it
        if (restartDelay > 0)
            restartDelay--;
        // If the 'R' key is pressed and there is no delay, restart the game and set a
        // delay
        if (keyboard.isDown(KeyEvent.VK_R) && restartDelay <= 0) {
            restart();
            restartDelay = 10;
            return;
        }
    }

    // Move pipes and check for collisions
    private void movePipes() 
    {
        // Decrement the delay for pipe movement
        pipeDelay--;
        // If it is time to move the pipes
        if (pipeDelay < 0) {
            pipeDelay = PIPE_DELAY;
            Pipe northPipe = null;
            Pipe southPipe = null;

            // Look for pipes that have gone off the screen
            for (Pipe pipe : pipes) {
                if (pipe.x - pipe.width < 0) {
                    // If there is no north pipe, assign the pipe to it
                    if (northPipe == null) {
                        northPipe = pipe;
                    }
                    // If there is no south pipe, assign the pipe to it and break out of the loop
                    else if (southPipe == null) {
                        southPipe = pipe;
                        break;
                    }
                }
            }

            // If there is no north pipe, create a new one and add it to the list of pipes
            if (northPipe == null) {
                Pipe pipe = new Pipe("north");
                pipes.add(pipe);
                northPipe = pipe;
            }
            // If there is a north pipe that has gone off the screen, reset it
            else {
                northPipe.reset();
            }

            // If there is no south pipe, create a new one and add it to the list of pipes
            if (southPipe == null) {
                Pipe pipe = new Pipe("south");
                pipes.add(pipe);
                southPipe = pipe;
            }
            // If there is a south pipe that has gone off the screen, reset it
            else {
                southPipe.reset();
            }

            // Set the y-coordinate of the north pipe so that it is below the south pipe
            northPipe.y = southPipe.y + southPipe.height + 175;
        }

        // Update the position of each pipe
        for (Pipe pipe : pipes) {
            pipe.update();
        }
    }

    // Check for collisions with pipes and the ground
    private void checkForCollisions() 
    {
        // Check each pipe for a collision with the bird
        for (Pipe pipe : pipes) {
            // If there is a collision with a pipe, end the game and set the bird to dead
            if (pipe.collides(bird.x, bird.y, bird.width, bird.height)) {
                gameover = true;
                bird.dead = true;
            }
            // If the bird has passed through a pipe, increment the score
            else if (pipe.x == bird.x && pipe.orientation.equalsIgnoreCase("south")) {
                score++;
            }
        }
        // Check for a collision between the bird and the ground
        if (bird.y + bird.height > App.HEIGHT - 80) {
            gameover = true;
            bird.y = App.HEIGHT - 80 - bird.height;
        }
    }
}
