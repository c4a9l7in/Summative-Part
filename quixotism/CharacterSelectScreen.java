import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Point;

/**
 * The screen in which players can select the type of character they want to play.
 * 
 * @author Sarah Kronenfeld
 * @version 28 December 2017
 */
public class CharacterSelectScreen extends World
{
    
    private Button playButton; // The button to progress to the next screen
    private CheckBoxLabel[] characters = new CheckBoxLabel[4]; // An array of the character selection checkboxes
    private CheckBoxLabel selected; // A record of whichever character type is currently selected
    private TextBox nameBox; //
    
    private Character player;

    /**
     * Constructor for objects of class CharacterSelectScreen.
     * 
     */
    public CharacterSelectScreen()
    {    
        
        // Create a new world with 800x600 cells with a cell size of 1x1 pixels.
        super(800, 600, 1); 
        
        // Create the button with which the player can enter another 
        Point bxy = new Point (150, 50);
        playButton = new Button("play", bxy);
        addObject(playButton, 400, 500);
        
        Label nbl = new Label("What's your name?");
        addObject(nbl, 650, 250);
        Point txy = new Point(150, 20);
        nameBox = new TextBox(txy, "");
        addObject(nameBox, 650, 275);
        
        // Initialize character selection checkboxes and add them to the world
        characters[0] = new CheckBoxLabel("ranger", false, 0);
        characters[1] = new CheckBoxLabel("mage", false, 1);
        characters[2] = new CheckBoxLabel("fighter", false, 2);
        characters[3] = new CheckBoxLabel("monk", false, 3);
        addObject(characters[0], 250, 150);
        addObject(characters[1], 450, 150);
        addObject(characters[2], 250, 350);
        addObject(characters[3], 450, 350);
        
    }
    
    /**
     * Act - do whatever the CharacterSelectScreen wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        
        if (playButton.wasClicked()) { // If the player has indicated that they're ready to progress to the next screen
            
            player = null; // Initialize a player to pass to the next screen
            
            if (selected != null) { // If the player's selected which type of character they want to play
                switch (selected.getId()) {
                    
                    case 0:
                        player = new RangedPlayer(7, 500, 0, 50); // Create a player with the stats for a Ranger
                        break;
                        
                    case 1:
                        player = new RangedPlayer(5, 750, 0, 50); // Create a player with the stats for a Mage
                        break;
                        
                    case 2:
                        player = new MeleePlayer(3, 1000, 0, 30, 70); // Create a player with the stats for a Fighter
                        break;
                        
                    case 3:
                        player = new MeleePlayer(9, 300, 0, 30, 70); // Create a player with the stats for a Monk
                        break;
                        
                }
            }
            
            else { // if they haven't selected any character type
                
                removeObjects(getObjectsAt(420, 570, Label.class)); // Remove any previous warning messages
                
                Label warning = new Label("Please choose a type of character to play first."); // Warn the player that they need to choose a character archetype to play
                addObject(warning, 420, 570);
                
                return; // Don't do anything else this turn
                
            }
            
            Environment game = new Environment(player, 1, nameBox.getText()); // Create a new game environment
            Greenfoot.setWorld(game); // Switch screens to that environment
            
        }
    }
    
    /**
     * Set whichever checkbox is currently selected
     * @param selection- the checkbox to be labeled as selected
     */
    public void setSelected (CheckBoxLabel selection) {
        if (selection != null) selected = selection;
    }
    
    /**
     * Check which checkbox is currently selected
     * @return the currently selected checkbox
     */
    public CheckBoxLabel getSelected () {
        return selected;
    }
    
    public Character getPlayer(){
        return player;
    }
}
