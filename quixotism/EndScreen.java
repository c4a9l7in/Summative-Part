import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Point;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The screen that is displayed when the game comes to an end
 * 
 * @author Sarah Kronenfeld
 * @version 5 January 2018
 */
public class EndScreen extends World
{
    
    Button play; // The replay button
    Button leader; // The leaderboard button
    Label title;
    Label infoText;

    /** 
     * Constructor for objects of class InfoScreen.
     * 
     */
    public EndScreen(int score, String userName)
    {    
        
        // Create a new world with 800x600 cells with a cell size of 1x1 pixels.
        super(800, 600, 1); 
        
        // Create and add navigation buttons to the world
        Point bxy = new Point (150, 50);
        play = new Button("play again", bxy);
        addObject(play, 300, 500);
        leader = new Button("leaderboard", bxy);
        addObject(leader, 500, 500);
        
        // Create and add a title
        Font font = new Font("Courier", 24);
        title = new Label("game over!", font, Color.BLACK);
        addObject(title, 400, 150);
        
        // Create and add flavour text
        font = new Font("Courier", 14);
        title = new Label("you lasted " + score + " seconds!", font, Color.BLACK);
        addObject(title, 400, 200);
        
        recordScore(score, userName);
        
    }
    
    /**
     * Act - do whatever the InfoScreen wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        
        if (play.wasClicked()) { // if the play button was clicked, open a new game
            CharacterSelectScreen newGame = new CharacterSelectScreen();
            Greenfoot.setWorld(newGame);
        }
        
        if (leader.wasClicked()) { // if the leaderboard button was clicked, open a leaderboard that goes back to the home screen
            HomeScreen home = new HomeScreen();
            LeaderboardScreen newLeader = new LeaderboardScreen(home);
            Greenfoot.setWorld(newLeader);
        }
        
    }
    
    private void recordScore(int score, String userName) {
        
        File scLoc = new File("save/scores");
        BufferedReader scoreReader = LeaderboardScreen.readFile(scLoc);
        
    }
    
}
