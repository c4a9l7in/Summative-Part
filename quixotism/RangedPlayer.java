import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The methods for the Ranged Player.
 * 
 * @author (Nick Ward) 
 * @version (Version 1.0)
 */
public class RangedPlayer extends Player
{
    Label ammoLabel; // Label to show the ammo
    private int shotTime; // The time between each shot caused by the weapon
    /**
     * Constructor of the RangedPlayer class
     * 
     * @param double speedIn - The speed of the player
     * @param double healthIn - The health of the player
     * @param double defenseIn - The defense of the player
     * @param double attackDamageIn - The damage of the player
     */
    public RangedPlayer(double speedIn, int healthIn, double defenseIn, int attackDamageIn){
        ORIGINALSPEED = speedIn;
        ORIGINALATTACKDAMAGE = attackDamageIn;
        ORIGINALHEALTH = healthIn;
        speed = ORIGINALSPEED;
        health = ORIGINALHEALTH;
        defense = defenseIn;
        attackDamage = ORIGINALATTACKDAMAGE;
        ammo = 500;
        spawned = true;
    }
    
    /**
     * Act - do whatever the RangedPlayer wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(spawned){ // Check if this just spawned, if true initialize variables
            world = (Environment) getWorld();
            
            world.healthLabel.setText("Health: " + this.getHealth());
            world.healthLabel.setLocation(60, world.getHeight() / 40 + 80);
        
            ammoLabel = new Label("Ammo: " + ammo, world.thisFont,Color.BLACK);
            world.addObject(ammoLabel, 50, world.getHeight() / 40 + 120);
            
            world.weaponLabel.setText("Weapon: Pistol");
            world.weaponLabel.setLocation(75, world.getHeight() / 40 + 40);
            weaponSpeedModifier = 2;
            setSpeed(armourSpeedModifier, potionSpeedModifier, weaponSpeedModifier);
            
            spawnTime = world.getGameTime();
            shotTime = 10;
            fireTime = spawnTime + shotTime;
            spawned = false;
        } else { // if false, do the normal movements for the player
            debug(); // For debugging
            
            touchingEnemyBullet(); // Check if touching a bullet
            
            touchingEnemy(); // Check if touching an enemy
            
            movement(); // Move
            
            getItems(); // Get items if touching one
            
            getWeapons(); // Get weapons if touching one
            
            shoot(); // Shoot
            
            potionLabels(); // Add labels if touching a potion
            
            kill(); // Switch worlds if health is zero
        }
    }
    
    /**
     * Sets the ammo of the player
     * 
     * @param int ammoIn - The amount of ammo to add or remove
     */
    public void setAmmo(int ammoIn){
        ammo = ammo + ammoIn;
        ammoLabel.setText("Ammo: " + ammo);
    }
    
    /**
     * Controls the shooting of the Ranged Players
     */
    public void shoot(){
        MouseInfo mouse = Greenfoot.getMouseInfo();
        world = (Environment) getWorld();
        playerBullet = new PlayerBullet();
        
        if(mouse != null){ // Check if the mouse is in the environment
            if(mouse.getButton() == 1){ // Check if the left mouse button is clicked
                if(ammo > 0){ // Check if ammo is greater than zero
                    if (world.getGameTime() > fireTime){ // Check if the world time is greater than the next time the player can fire
                            world.addObject(playerBullet, this.getX(), this.getY());
                            playerBullet.setRotation(this.getRotation());
                            fireTime = world.getGameTime() + shotTime;
                            setAmmo(-1);
                    }
                }
            }
        }
    }
    
    /**
     * Gets weapons for the Ranged Player
     */
    public void getWeapons(){
        world = (Environment) getWorld();
        Actor pistol;
        pistol = getOneIntersectingObject(Pistol.class);
        
        if(pistol != null){ // Check if touching a pistol object
            world.weaponLabel.setText("Weapon: Pistol");
            world.weaponLabel.setLocation(75, world.getHeight() / 40 + 40);
            setAttackDamage(50);
            setAmmo(100);
            shotTime = 10;
            weaponSpeedModifier = 2;
            setSpeed(armourSpeedModifier, potionSpeedModifier, weaponSpeedModifier);
            world.removeObject(pistol);
        }
        
        Actor assaultRifle;
        assaultRifle = getOneIntersectingObject(AssaultRifle.class);
        
        if(assaultRifle != null){ // Check if touching an assault rifle object
            world.weaponLabel.setText("Weapon: Assault Rifle");
            world.weaponLabel.setLocation(110, world.getHeight() / 40 + 40);
            setAttackDamage(100);
            setAmmo(300);
            shotTime = 5;
            weaponSpeedModifier = 1;
            setSpeed(armourSpeedModifier, potionSpeedModifier, weaponSpeedModifier);
            world.removeObject(assaultRifle);
        }
        
        Actor lmg;
        lmg = getOneIntersectingObject(LMG.class);
        
        if(lmg != null){ // Check if touching a lmg object
            world.weaponLabel.setText("Weapon: LMG");
            world.weaponLabel.setLocation(60, world.getHeight() / 40 + 40);
            setAttackDamage(150);
            setAmmo(600);
            shotTime = 1;
            weaponSpeedModifier = 0.5;
            setSpeed(armourSpeedModifier, potionSpeedModifier, weaponSpeedModifier);
            world.removeObject(lmg);
        }
        
        Actor ammo;
        ammo = getOneIntersectingObject(Ammo.class);
        
        if(ammo != null){ // Check if touching an ammo object
            setAmmo(1000);
            world.removeObject(ammo);
        }
    }
}
