import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Methods and variables for items to inherit.
 * 
 * @author (Nick Ward) 
 * @version (Version 1.0)
 */
public class Items extends Actor
{
    private boolean spawned = true; // Check if item just spawned
    Environment world; // Get the world functions
    private int spawnTime; // The time when the item spawned
    private int despawnTime; // Set the time for the item to despawn
    
    public Items(){
        
    }
    
    /**
     * Act - do whatever the Items wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        
    }
    
    public void despawn(){
        if(spawned){ // Check if this just spawned, if true initialize variables
            world = (Environment) getWorld();
            spawnTime = world.getGameTime();
            despawnTime = spawnTime + 600;
            spawned = false;
        }else if(world.getGameTime() >= despawnTime){ // Check if the world time is greater than the despawn time for an item
            world.removeObject(this);
        }
    }
}
