import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The methods that control the Enemy Ranged
 * 
 * @author (Nick Ward) 
 * @version (Version 1.0)
 */
public class EnemyRanged extends Enemy
{
    private int fireTime; // The amount of time between each shot
    Environment world; // The game world
    private boolean spawned;
    
    /**
     * Constructor of the EnemyRanged class
     * 
     * @param double speedIn - The speed of the enemy
     * @param double healthIn - The health of the enemy
     * @param double defenseIn - The defense of the enemy
     * @param double attackDamageIn - The damage of the enemy
     */
    public EnemyRanged(double speedIn, int healthIn, double defenseIn, int attackDamageIn){
        speed = speedIn;
        health = healthIn;
        defense = defenseIn;
        attackDamage = attackDamageIn;
 
        spawned = true;
    }
    
    /**
     * Act - do whatever the Enemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(spawned){ // Check if this just spawned, if true initialize variables
            world =(Environment) getWorld();
            spawnTime = world.getGameTime();
            fireTime = spawnTime + 10;
            spawned = false;
        } else { // if false, do the normal movements for the player
            touchingPlayerBullet(); // Check if touching a bullet
        
            movement(); // Move
        
            shoot(); // Shoot
        
            kill(); // Remove this if health is zero
        }
    }
    
    /**
     * Controls the shooting of the Enemy Ranged
     */
    public void shoot(){
        EnemyBullet enemyBullet = (EnemyBullet) new EnemyBullet();
        if(world.getGameTime() > fireTime){ // Check if the world time is greater than the next time the player can fire
            world.addObject(enemyBullet, this.getX(), this.getY());
            enemyBullet.setRotation(this.getRotation());
            fireTime += 10;
        }
    }
}
