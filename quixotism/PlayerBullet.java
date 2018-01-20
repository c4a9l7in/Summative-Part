import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The methods for the Player Bullet.
 * 
 * @author (Nick Ward) 
 * @version (Version 1.0)
 */
public class PlayerBullet extends Actor
{
    Environment world; // The game world
    
    /**
     * Act - do whatever the Bullet wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        world = (Environment) getWorld();
        
        move(25);
        
        if(this.isAtEdge() == true){ // Check if this is at the edge of the world
            world.removeObject(this);
        }
    }    
}
