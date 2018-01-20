import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Methods and variables for characters to use.
 * 
 * @author (Nick Ward) 
 * @version (Version 1.0)
 */
public class Character extends Actor
{
    protected double speed; // The speed of the character
    protected int health; // The health of the character
    protected double defense; // The defense of the character
    protected int attackDamage; // The amount of damage the character can do
    protected double ORIGINALSPEED; // The starting speed of the character
    protected int ORIGINALHEALTH; // The starting health of the character
    protected int ORIGINALATTACKDAMAGE; // The starting damage of the character
    protected int attackRadius; // The attack radius of the character
    protected double armourSpeedModifier = 1; // The speed modifier caused by a piece of armour
    protected double weaponSpeedModifier = 1; // The speed modifier caused by a weapon
    protected double potionSpeedModifier = 1; // The speed modifier caused by a potion
    
    /**
     * Act - do whatever the Character wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        
    }
    
    /**
     * Sets the health of the character
     * 
     * @param int addedHealth - The amount of health that is to be added to
     * the players original health.
     */
    public void addHealth(int addedHealth){
        if(health + addedHealth > ORIGINALHEALTH){
            health = ORIGINALHEALTH;
        } else {
            health += addedHealth;
        }
    }
    
    /**
     * Removes health from the character based on the damage from another character's
     * attack value and the amount of defense this character has.
     * 
     * @param int damageIn - The damage that the character will be taking.
     * @param double defense - The amount of defense this character has.
     */
    public void setDamageTaken(int damageIn, double defense){
        health = health - (int)(damageIn - (damageIn * defense));
    }
    
    /**
     * Returns the amount of health the character has.
     * 
     * @return health - The value of health for the character
     */
    public int getHealth(){
        return health;
    }
    
    /**
     * Returns the amount of attack the character has.
     * 
     * @return attackDamage - The value of attackDamage for the character
     */
    public int getAttackDamage(){
        return attackDamage;
    }
    
    
    /**
     * Sets the attack of a character
     * 
     * @param int newAttack - The new attack damage for the player
     */
    public void setAttackDamage(int newAttack){
        attackDamage = newAttack;
    }
    
    /**
     * Sets the defense of a character
     * 
     * @param double newDefense - The new defense the stat the player will have
     */
    public void setDefense(double newDefense){
        defense = newDefense;
    }
    
    /**
     * Returns the amount of defense the character has.
     * 
     * @return defense - The value of defense for the character
     */
    public double getDefense(){
        return defense;
    }
    
    /**
     * Sets the speed of a character
     * 
     * @param double armourSpeedModifier - The speed modifier caused by a piece of armour
     * @param double potionSpeedModifier - The speed modifier caused by a potion
     * @param double weaponSpeedModifier - The speed modifier caused by a weapon
     */
    public void setSpeed(double armourSpeedModifier, double potionSpeedModifer, double weaponSpeedModifier){
        speed = (((ORIGINALSPEED * armourSpeedModifier) * potionSpeedModifer) * weaponSpeedModifier);
    }
    
    /**
     * Returns the amount of speed the character has.
     * 
     * @return speed - The value of speed for the character
     */
    public double getSpeed(){
        return speed;
    }
    
    /**
     * Sets the attack radius of a character
     * 
     * @param attackRadiusIn - The attack radius that will be the new attack radius
     */
    public void setAttackRadius(int attackRadiusIn){
        attackRadius = attackRadiusIn;
    }
}
