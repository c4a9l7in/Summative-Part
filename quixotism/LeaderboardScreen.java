import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Point;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A screen in which top scores on a computer are recorded
 * 
 * @author Sarah Kronenfeld
 * @version 28 December 2017
 */
public class LeaderboardScreen extends World {
        
    Button button; // The back button
    World lastWorld;
    ListBox scoreBox;
    ArrayList scores = new ArrayList();
    boolean debug = false;

    /**
     * Constructor for objects of class LeaderboardScreen.
     * 
     */
    public LeaderboardScreen(World lastWorld)
    {      
        
        // Create a new world with 800x600 cells with a cell size of 1x1 pixels.
        super(800, 600, 1); 
        
        // Create and add a back button to the world
        Point bxy = new Point (150, 50);
        button = new Button("back", bxy);
        addObject(button, 400, 500  );
        
        // Set the world the back button points to
        this.lastWorld = lastWorld;
        
        // Create and add a title
        Font font = new Font("Courier", 24);    
        Label title = new Label("leaderboard", font, Color.BLACK);
        addObject(title, 400, 150);
        
        File location = new File("save/scores"); // Find the file with the saved scores
        BufferedReader scoreReader = readFile(location, debug);
        readScores(scoreReader); // Create the list of high scores from the file
        // scoreReader.close();
        
        // Create and add the box with the list of scores
        Point sxy = new Point(400, 200);
        scoreBox = new ListBox(sxy, scores);
        addObject(scoreBox, 400, 275);
       
    }
    
    public void act() {
        
        if (button.wasClicked()) { // if the back button was clicked, navigate back to the last world
            Greenfoot.setWorld(lastWorld);
        }
        
    }
    
    public static BufferedReader readFile (File location, boolean... debugOn) { 
        
        boolean debug = false;
        
        if (debugOn.length > 0) {
            debug = debugOn[0];
        }
        
        // Create a file reader for the scores file
        FileReader reader = null;
        try {
            reader = new FileReader(location);
        }
        catch (Throwable t) {
            if (debug) System.out.println(location.toString());
        }
        
        // Wrap the file reader in a buffered reader
        return new BufferedReader(reader);
        
    }
    
    private void readScores(BufferedReader scoreReader) {
        
        // Add the headings to the list of chart lines
        scores.add( "RANK  NAME                  SCORE");
        if (debug) System.out.println(scores.get(0));
        
        for (int i = 1; i < 10; i++) { // For the first 9 slots
            
            try {
                
                String name = scoreReader.readLine(); // Read the first line: the name of the player
                
                if (name == null) { // If the line is blank
                    
                    // Add a blank line
                    scores.add(i, i + "     n/a                   n/a");
                    if(debug) System.out.println("no data");
                    
                }
                
                else {
                    
                    if (name.length() > 20) name = name.substring(0, 19); // If the name is longer than 20 characters, cut it off after the first 20
                    
                    // Create an array with the number of spaces needed and then turn it into a string of the required length
                    char[] ws = new char[22 - name.length()];
                    for(int j = 0; j < 22 - name.length(); j++) {
                        ws[j] = ' ';
                    }
                    String whitespace = String.copyValueOf(ws);
                    
                    String score = scoreReader.readLine(); // Read the next line: the score that player achieved
                    
                    // Add the player + score info as a new line
                    scores.add(i, i + "     " + name.toUpperCase() + whitespace + score);
                    if (debug) System.out.println(scores.get(i));
                    
                }
                
            }
            
            catch (IOException t) { // If there are no further lines
                
                // Add a blank line
                scores.add(i, i + "     n/a                   n/a");
                if(debug) t.printStackTrace();
                if(debug) System.out.println("no further lines")
                ;
            }
            
        }
        
    }
    
}