import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Point;

/**
 * A screen that simply communicates some text to the player
 * 
 * @author Sarah Kronenfeld
 * @version 28 December 2017
 */
public class InfoScreen extends World
{
    
    Button button; // The back button
    World lastWorld;
    Label title;
    TextBox infoText;

    /** 
     * Constructor for objects of class InfoScreen.
     * 
     */
    public InfoScreen(String buttonMessage, World lastWorld, String titleIn, String... infoTextIn)
    {    
        
        // Create a new world with 800x600 cells with a cell size of 1x1 pixels.
        super(800, 600, 1); 
        
        // Create and add a back button to the world
        Point bxy = new Point (150, 50);
        button = new Button(buttonMessage, bxy);
        addObject(button, 400, 500);
        
        // Set the world the back button points to
        this.lastWorld = lastWorld;
        
        if (infoTextIn.length > 0) { // If there's a block of text to add
            
            // Create and add a text box for that text
            Point txy = new Point(400, 250);
            infoText = new TextBox(txy, infoTextIn[0]);
            addObject(infoText, 400, 300);
            
        }
        
        // Create and add a title
        Font font = new Font("Courier", 24);
        title = new Label(titleIn, font, Color.BLACK);
        addObject(title, 400, 150);
        
    }
    
    /**
     * Act - do whatever the InfoScreen wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        
        if (button.wasClicked()) { // if the back button was clicked, navigate back to the last world
            Greenfoot.setWorld(lastWorld);
        }
        
    }
    
}
