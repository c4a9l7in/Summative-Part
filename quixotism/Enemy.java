import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The methods and variables for the Enemy classes
 * 
 * @author (Nick Ward) 
 * @version (Version 1.0)
 */
public class Enemy extends Character
{
    protected int directionChangeTime = 1*60; // The time between each direction change
    protected int spawnTime; // Get the time when an object has spawned
    private int itemDrop; // Chooses which item to spawn
    Environment world; // The game world
    
    /**
     * Act - do whatever the Enemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }
    
    /**
     * Move the enemy
     */
    public void movement(){
        world = (Environment) getWorld();
        if (world.getGameTime() > directionChangeTime){ // Check if the world game time is greater than when the enemy needs to change directions
            turn(Greenfoot.getRandomNumber(360));
            directionChangeTime = directionChangeTime + 60;
        }
        
        if(this.isAtEdge()){ // Check if this is at the edge of the world
                turn(Greenfoot.getRandomNumber(360));
        }
        move((int)speed);
    }
    
    /**
     * Check if the player is touching a player bullet
     */
    public void touchingPlayerBullet(){
        RangedPlayer rangedPlayer = new RangedPlayer(5, 500, 0.25, 15);
        if(this.isTouching(PlayerBullet.class)){ // Check if this is touching a player bullet
            this.setDamageTaken(rangedPlayer.getAttackDamage(), this.getDefense());
        }
    }
    
    /**
     * Switch worlds if enemy health is zero
     */
    public void kill(){
        if(this.getHealth() <= 0){ // Check if health is zero or less, switch worlds if true
            dropItem();
            world.removeObject(this);  
        }
    }
    
    /**
     * Drops an item when this an enemy is killed
     */
    public void dropItem(){
        itemDrop = Greenfoot.getRandomNumber(16) + 1;
        
        switch(itemDrop){
            
            case 1:
            Actor lightArmour = new LightArmour();
            world.addObject(lightArmour, this.getX(), this.getY());
            break;
            
            case 2:
            Actor mediumArmour = new MediumArmour();
            world.addObject(mediumArmour, this.getX(), this.getY());
            break;
            
            case 3:
            Actor heavyArmour = new HeavyArmour();
            world.addObject(heavyArmour, this.getX(), this.getY());
            break;
            
            case 4:
            Actor pistol = new Pistol();
            world.addObject(pistol, this.getX(), this.getY());
            break;
            
            case 5:
            Actor assaultRifle = new AssaultRifle();
            world.addObject(assaultRifle, this.getX(), this.getY());
            break;
            
            case 6:
            Actor lmg = new LMG();
            world.addObject(lmg, this.getX(), this.getY());
            break;
            
            case 7:
            Actor rapier = new Rapier();
            world.addObject(rapier, this.getX(), this.getY());
            break;
            
            case 8:
            Actor falchion = new Falchion();
            world.addObject(falchion, this.getX(), this.getY());
            break;
            
            case 9:
            Actor longSword = new LongSword();
            world.addObject(longSword, this.getX(), this.getY());
            break;
            
            case 10:
            Actor poleArm = new PoleArm();
            world.addObject(poleArm, this.getX(), this.getY());
            break;
            
            case 11:
            Actor potionOfInvigoration = new PotionOfInvigoration();
            world.addObject(potionOfInvigoration, this.getX(), this.getY());
            break;
            
            case 12:
            Actor potionOfCuring = new PotionOfCuring();
            world.addObject(potionOfCuring, this.getX(), this.getY());
            break;
            
            case 13:
            Actor potionOfRestoration = new PotionOfRestoration();
            world.addObject(potionOfRestoration, this.getX(), this.getY());
            break;
            
            case 14:
            Actor potionOfAlacrity = new PotionOfAlacrity();
            world.addObject(potionOfAlacrity, this.getX(), this.getY());
            break;
            
            case 15:
            Actor potionOfSwiftness = new PotionOfSwiftness();
            world.addObject(potionOfSwiftness, this.getX(), this.getY());
            break;
            
            case 16:
            Actor potionOfFleetness = new PotionOfFleetness();
            world.addObject(potionOfFleetness, this.getX(), this.getY());
            break;
            
            case 17:
            Actor ammo = new Ammo();
            world.addObject(ammo, this.getX(), this.getY());
            break;
        }
    }
}
