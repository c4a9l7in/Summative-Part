import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * The methods that control the Enemy Boss
 * 
 * @author (Nick Ward) 
 * @version (Version 1.0)
 */
public class EnemyBoss extends Enemy
{
    private int fireTime = 1*45; // The amount of time between each shot
    private int attackRadius; // The radius in which this can damage players
    RangedPlayer rangedPlayer; // Get the functions of the ranged player
    private boolean spawned; // Check if the player just spawned
    
    /**
     * Constructor of the EnemyBoss class
     * 
     * @param double speedIn - The speed of the enemy
     * @param double healthIn - The health of the enemy
     * @param double defenseIn - The defense of the enemy
     * @param double attackDamageIn - The damage of the enemy
     * @param double attackRadiusIn - The radius in which the enemy can damage a player
     */
    public EnemyBoss(double speedIn, int healthIn, double defenseIn, int attackDamageIn, int attackRadiusIn){
        speed = speedIn;
        health = healthIn;
        defense = defenseIn;
        attackDamage = attackDamageIn;
        attackRadius = attackRadiusIn;
        spawned = true;
    }
    
    /**
     * Act - do whatever the EnemyBoss wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(spawned){ // Check if this just spawned, if true initialize variables
            world =(Environment) getWorld();
            spawnTime = world.getGameTime();
            fireTime = spawnTime + 5;
            spawned = false;
        } else { // if false, do the normal movements for the player
            touchingPlayerBullet(); // Check if touching a bullet
            
            movement(); // Move
            
            shoot(); // Shoot
            
            inRange(); // Check if an enemy is in range
            
            kill(); // Switch worlds if health is zero
        }
    }
    
    /**
     * Controls the shooting of the Enemy Boss
     */
    public void shoot(){
        EnemyBullet bullet1 = new EnemyBullet(); 
        EnemyBullet bullet2 = new EnemyBullet();
        EnemyBullet bullet3 = new EnemyBullet();
        EnemyBullet bullet4 = new EnemyBullet();
        if(world.getGameTime() > fireTime){ // Check if the game time of the world is greater than the time in which this can fire next
            bullet1.setRotation(this.getRotation());
            world.addObject(bullet1, this.getX(), this.getY());
            bullet2.setRotation(this.getRotation() + 90);
            world.addObject(bullet2, this.getX(), this.getY());
            bullet3.setRotation(this.getRotation() + 180);
            world.addObject(bullet3, this.getX(), this.getY());
            bullet4.setRotation(this.getRotation() + 270);
            world.addObject(bullet4, this.getX(), this.getY());
            fireTime += 5;
        }
    }
    
    /**
     * Check if a player is in range, and do damage accordingly
     */
    public void inRange(){
        List rangedPlayers = getObjectsInRange(attackRadius, RangedPlayer.class);
        if (! rangedPlayers.isEmpty()) { // Check if a ranged enemy is in range
            RangedPlayer player = (RangedPlayer) rangedPlayers.get(0);
            player.setDamageTaken(this.getAttackDamage(), player.getDefense());
        }
    }
}
