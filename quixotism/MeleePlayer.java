import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * The methods for the Melee Player.
 * 
 * @author (Nick Ward) 
 * @version (Version 1.0)
 */
public class MeleePlayer extends Player
{
    /**
     * Constructor of the MeleePlayer class
     * 
     * @param double speedIn - The speed of the player
     * @param double healthIn - The health of the player
     * @param double defenseIn - The defense of the player
     * @param double attackDamageIn - The damage of the player
     * @param double attackRadiusIn - The radius in which the player can damage an enemy
     */
    public MeleePlayer(double speedIn, int healthIn, double defenseIn, int attackDamageIn, int attackRadiusIn){
        ORIGINALSPEED = speedIn;
        ORIGINALATTACKDAMAGE = attackDamageIn;
        ORIGINALHEALTH = healthIn;
        speed = ORIGINALSPEED;
        health = ORIGINALHEALTH;
        defense = defenseIn;
        attackDamage = ORIGINALATTACKDAMAGE;
        attackRadius = attackRadiusIn;
        spawned = true;
    }
    
    /**
     * Act - do whatever the MeleePlayer wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(spawned){ // Check if this just spawned, if true initialize variables
            world = (Environment) getWorld();
            
            world.healthLabel.setText("Health: " + this.getHealth());
            world.healthLabel.setLocation(60, world.getHeight() / 40 + 80);
            
            world.weaponLabel.setText("Weapon: Rapier");
            world.weaponLabel.setLocation(75, world.getHeight() / 40 + 40);
            weaponSpeedModifier = 2;
            setSpeed(armourSpeedModifier, potionSpeedModifier, weaponSpeedModifier);

            spawned = false;
        } else { // if false, do the normal movements for the player
            debug(); // For debugging
            
            touchingEnemyBullet(); // Check if touching a bullet
            
            touchingEnemy(); // Check if touching an enemy
            
            movement(); // Move
            
            getItems(); // Get items if touching one
            
            getWeapons(); // Get weapons if touching one
    
            inRange(); // Check if an enemy is in range
            
            potionLabels(); // Add labels if touching a potion
            
            kill(); // Switch worlds if health is zero
        }
    }
    
    /**
     * Check if an enemy is in range, and do damage accordingly
     */
    public void inRange(){
        List enemiesMelee = getObjectsInRange(attackRadius, EnemyMelee.class);
        if (! enemiesMelee.isEmpty()) { // Check if an enemy melee is in range
            EnemyMelee enemy = (EnemyMelee) enemiesMelee.get(0);
            enemy.setDamageTaken(this.getAttackDamage(), enemyMelee.getDefense());
        }
        
        List enemiesRanged = getObjectsInRange(attackRadius, EnemyRanged.class);
        if (! enemiesRanged.isEmpty()) { // Check if an enemy ranged is in range
            EnemyRanged enemy = (EnemyRanged) enemiesRanged.get(0);
            enemy.setDamageTaken(this.getAttackDamage(), enemyRanged.getDefense());
        }
        
        List enemyBosses = getObjectsInRange(attackRadius, EnemyBoss.class);
        if (! enemyBosses.isEmpty()) { // Check if an enemy boss is in range
            EnemyBoss enemy = (EnemyBoss) enemyBosses.get(0);
            enemy.setDamageTaken(this.getAttackDamage(), enemyBoss.getDefense());
        }
    }
    
    /**
     * Gets weapons for the Melee Player
     */
    public void getWeapons(){
        world = (Environment) getWorld();
        Actor rapier;
        rapier = getOneIntersectingObject(Rapier.class);
        
        if(rapier != null){ // Check if touching a rapier object
            world.weaponLabel.setText("Weapon: Rapier");
            world.weaponLabel.setLocation(75, world.getHeight() / 40 + 40);
            setAttackDamage(30);
            setAttackRadius(70);
            weaponSpeedModifier = 2;
            setSpeed(armourSpeedModifier, potionSpeedModifier, weaponSpeedModifier);
            world.removeObject(rapier);
        }
        
        Actor falchion;
        falchion = getOneIntersectingObject(Falchion.class);
        
        if(falchion != null){ // Check if touching a falchion object
            world.weaponLabel.setText("Weapon: Falchion");
            world.weaponLabel.setLocation(85, world.getHeight() / 40 + 40);
            setAttackDamage(50);
            setAttackRadius(50);
            weaponSpeedModifier = 1.5;
            setSpeed(armourSpeedModifier, potionSpeedModifier, weaponSpeedModifier);
            world.removeObject(falchion);
        }
        
        Actor longSword;
        longSword = getOneIntersectingObject(LongSword.class);
        
        if(longSword != null){ // Check if touching a long sword object
            world.weaponLabel.setText("Weapon: Long Sword");
            world.weaponLabel.setLocation(95, world.getHeight() / 40 + 40);
            setAttackDamage(100);
            setAttackRadius(150);
            weaponSpeedModifier = 1;
            setSpeed(armourSpeedModifier, potionSpeedModifier, weaponSpeedModifier);
            world.removeObject(longSword);
        }
        
        Actor poleArm;
        poleArm = getOneIntersectingObject(PoleArm.class);
        
        if(poleArm != null){ // Check if touching a polearm object
            world.weaponLabel.setText("Weapon: Polearm");
            world.weaponLabel.setLocation(80, world.getHeight() / 40 + 40);
            setAttackDamage(150);
            setAttackRadius(200);
            weaponSpeedModifier = 0.5;
            setSpeed(armourSpeedModifier, potionSpeedModifier, weaponSpeedModifier);
            world.removeObject(poleArm);
        }
    }
}
