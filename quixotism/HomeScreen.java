import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)\
import java.awt.Point;

/**
 * The screen into which the program opens up
 * 
 * @author Sarah Kronenfeld
 * @version 28 December 2017
 */
public class HomeScreen extends World
{
    
    Label title;
    Label byline;
    Button playButton;
    Button instructionsButton;
    Button leaderButton;
    Button creditButton;
    public boolean debug;
    String instructionText = new String("Welcome to quixotism!\nThis can be played as a simple point-and-click game- you steer with your mouse, and press the w and s keys to go forward and backwards. To attack, press the right-hand mouse button. The enemies WILL get harder and more plentiful as you go along, so be warned!");
    String creditText = new String("Game by Sarah Kronenfeld and Nick Ward. Graphics by Sarah Kronenfeld (hopefully). UI components originally created by Taylor Bourne on greenfoot forums.");
    
    
    /**
     * Constructor for objects of class HomeScreen.
     * 
     */
    public HomeScreen()
    {    
        
        // Create a new world with 800x600 cells with a cell size of 1x1 pixels.
        super(800, 600, 1); 
        
        // Create the title
        Font thisFont = new Font ("Courier", 100);
        title = new Label("quixotism",thisFont, Color.BLACK);
        addObject(title, 420, 250);
        
        // Default dimentions for a button
        Point bxy = new Point (150, 50);
        
        
        // Creates buttons to access the game, instructions, leaderboard, and credits screens
        
        playButton = new Button("play", bxy);
        addObject(playButton, 300, 350);
        
        instructionsButton = new Button("instructions", bxy);
        addObject(instructionsButton, 500, 350);
        
        leaderButton = new Button("leaderboard", bxy);
        addObject(leaderButton, 300, 450);
        
        creditButton = new Button("credits", bxy);
        addObject(creditButton, 500, 450);
        
    }
    
    
    /**
     * Act - do whatever the HomeScreen wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        
        if (playButton.wasClicked()) { // if the play button was clicked, navigate to a character select screen
            
            CharacterSelectScreen charselect = new CharacterSelectScreen();
            Greenfoot.setWorld(charselect);
            
        }
        else if (instructionsButton.wasClicked()) { // if the instructions button was clicked, navigate to an instructions screen
            
            InfoScreen inst = new InfoScreen("back", this, "instructions", instructionText);
            Greenfoot.setWorld(inst);
            
        }
        else if (leaderButton.wasClicked()) { // if the leaderboard button was clicked, navigate to a leaderboard screen
            
            LeaderboardScreen leader = new LeaderboardScreen(this);
            Greenfoot.setWorld(leader);
            
        }
        else if (creditButton.wasClicked()) { // if the leaderboard button was clicked, navigate to a leaderboard screen
            
            InfoScreen credits = new InfoScreen("back", this, "credits", creditText);
            Greenfoot.setWorld(credits);
            
        }
        
    }
    
}








