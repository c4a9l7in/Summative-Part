import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The methods that control the Enemy Melee
 * 
 * @author (Nick Ward) 
 * @version (Version 1.0)
 */
public class EnemyMelee extends Enemy
{
    private int directionChangeTime = 1*60; // The time between each direction change
    Environment world; // The game world
    
    /**
     * Constructor of the EnemyMelee class
     * 
     * @param double speedIn - The speed of the enemy
     * @param double healthIn - The health of the enemy
     * @param double defenseIn - The defense of the enemy
     * @param double attackDamageIn - The damage of the enemy
     */
    public EnemyMelee(double speedIn, int healthIn, double defenseIn, int attackDamageIn){
        speed = speedIn;
        health = healthIn;
        defense = defenseIn;
        attackDamage = attackDamageIn;
    }
    
    /**
     * Act - do whatever the Enemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        touchingPlayerBullet(); // Check if touching a bullet
        
        movement(); // Move
        
        kill(); // Remove this if health is zero
    }
}
