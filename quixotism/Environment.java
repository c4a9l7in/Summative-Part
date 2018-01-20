import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Point;
import java.util.Random;

/**
 * The environment in which the game directly runs
 * 
 * @author Sarah Kronenfeld + Nick Ward
 * @version 5 January 2018
 */
public class Environment extends World
{    
    int timer; // The timer
    long lastSec;
    Button pause; // the pause button
    Label timeRecord; // The place where the current value of the timer is shown to the character
    Character player; // The player
    int difficulty;
    Font thisFont;
    Label armourLabel;
    Label weaponLabel;
    Label healthLabel;
    String userName;
    boolean addEnemy;
    
    /**
     * Constructor for objects of class Environment.
     * 
     */
    public Environment(Character newPlayer, int difficulty, String userName)
    {    
        
        // Create a new world with 800x600 cells with a cell size of 1x1 pixels.
        super(800, 600, 1); 
        
        this.difficulty = difficulty;
        this.userName = userName;
        
        // Create and add the timer
        lastSec = System.currentTimeMillis();
        timer = 0;
        timeRecord = new Label("" + timer);
        addObject(timeRecord, 700, 50);
        
        // Create and add the pause button
        Point bxy = new Point(20, 20);
        pause = new Button("||", bxy);
        addObject(pause, 750, 40);
        
        // Create and add the player
        player = newPlayer;
        addObject(player, 100, 100);
        
        
        thisFont = new Font ("Courier", 15);
        armourLabel = new Label("Armour: No Armour",thisFont, Color.BLACK);
        addObject(armourLabel, 90, getHeight() / 40);
        
        weaponLabel = new Label("Weapon:",thisFont, Color.BLACK);
        addObject(weaponLabel, 40, getHeight() / 40 + 40);
        
        healthLabel = new Label("Health:",thisFont, Color.BLACK);
        addObject(healthLabel, getWidth() / 20, getHeight() / 40 + 80);
    }
    
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act(){
        
        timer += 1; // Add 1 to the value of the timer
        timeRecord.setText(((Integer)(timer / 60)).toString()); // Update the record of the current score
            
            if (addEnemy == true) { // if another enemy must be added
                
                Character newEnemy = newEnemy(); // Create a new enemy using the newEnemy function
                
                Random random = new Random();
                if (getWeightedBoolean(2)) { // Get a random boolean; if it's true, add the enemy at a random location at the top of the screen
                    addObject(newEnemy, random.nextInt(700) + 50, 50);
                }
                else { // If it's false, add it at a random location at the bottom
                    addObject(newEnemy, random.nextInt(700) + 50, 550);
                }
                addEnemy = false;
                
            }
            
        
        timeRecord.setText(((Integer)(timer/60)).toString()); // Update the record of the current score
        
        if (pause.wasClicked()) { // If the pause button was clicked
            InfoScreen pauseScreen = new InfoScreen("unpause", this, "game paused");
            Greenfoot.setWorld(pauseScreen); // Send the user to a pause screen
        }
        
        if (getObjects(EnemyMelee.class).size() + getObjects(EnemyRanged.class).size() + getObjects(EnemyBoss.class).size() <= ((timer / (15 / difficulty))/60)) { // If there are too few enemies
            addEnemy = true; // Add an enemy next time a second passes
        }
        
    }
    
    /**
     * Creates a new enemy for the player to fight
     */
    private Character newEnemy() {
        
        if (getWeightedBoolean(50)) {     // One in every 50 times, create a boss
            return new EnemyBoss(2, 5000, 0.5, 10, 50);
        }
        
        else if (getWeightedBoolean(2)) { // If a boss is not created, either create a melee enemy-
            return new EnemyMelee(5, 500, 0.25, 10);
        }
        
        else {                            //  - or a ranged enemy, giving even chances for either
            return new EnemyRanged(5, 500,0.25,10);
        }
        
    }
    
    /**
     * Passes all necessary info to the end screen
     */
    public void end () {
        
        EndScreen end = new EndScreen(timer, userName);
        Greenfoot.setWorld(end);
        
    }
    
    /**
     * Gets the current time of the world
     * 
     * @return The current time of the world
     */
    public int getGameTime(){
        return timer;
    }
    
    public boolean getWeightedBoolean (int weight) {
        
        Random random = new Random();
        int rand = random.nextInt(weight);
        if (rand == 0) {  
            return true;
        }
        return false;
        
    }
    
    public Character getPlayer() {
        return player;
    }
}
