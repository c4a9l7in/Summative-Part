import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The methods and variables for the Player classes.
 * 
 * @author (Nick Ward) 
 * @version (Version 1.0)
 */
public class Player extends Character
{
    Environment world; // The game world
    protected double fireTime; // The time that it takes between each shot
    protected int ammo; // The amount of ammo the player has
    protected boolean debug = false; // Check whether to debug or not
    EnemyRanged enemyRanged = new EnemyRanged(5,500,0.25,10); // Get the functions from enemy ranged
    EnemyMelee enemyMelee = new EnemyMelee(5,500,0.25,10); // Get the functions from enemy melee
    EnemyBoss enemyBoss = new EnemyBoss(2, 5000, 0.5, 10, 50); // Get the functions from enemy boss
    Font potionFont = new Font("Courier", 20); // Set the font for the potion labels
    Label potionHealthLabel = new Label(" ", potionFont, Color.BLACK); // Create a potion health label
    Label potionSpeedLabel = new Label(" ", potionFont, Color.BLACK); // Create a potion speed label
    protected boolean potionHealthLabelOn; // Check if the potion health label is on
    protected int potionHealthLabelDespawn; // The time for when the potion health label should be despawned
    protected boolean potionSpeedLabelOn; // Check if the potion speed label is on
    protected int potionSpeedLabelDespawn; // The time for when the potion speed label should be despawned
    protected boolean spawned; // Check if the player has just spawned
    protected int spawnTime; // Get the time when an object has spawned
    Actor playerBullet; // Get the functions from player bullet
    
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
    }
    
    /**
     * Check if the player is touching an enemy bullet
     */
    public void touchingEnemyBullet(){
        if(this.isTouching(EnemyBullet.class)){ // Check if the player is touching an enemy bullet
            this.setDamageTaken(enemyRanged.getAttackDamage(), this.getDefense());
            world.healthLabel.setText("Health: " + this.getHealth());
            world.healthLabel.setLocation(world.getWidth() / 14, world.getHeight() / 40 + 80);
        }
    }
    
    /**
     * Check if the player is touching an enemy
     */
    public void touchingEnemy(){
        if(this.isTouching(EnemyMelee.class)){ // Check if the player is touching an enemy melee
            this.setDamageTaken(enemyMelee.getAttackDamage(), this.getDefense());
            world.healthLabel.setText("Health: " + this.getHealth());
            world.healthLabel.setLocation(world.getWidth() / 14, world.getHeight() / 40 + 80);
        }
    }
    
    /**
     * To check values for the player
     */
    public void debug(){
        if(debug) System.out.println("Armour Speed " + armourSpeedModifier);
        if(debug) System.out.println("Potion Speed " + potionSpeedModifier);
        if(debug) System.out.println("Weapon Speed " + weaponSpeedModifier);
        if(debug) System.out.println("Defense " + this.getDefense());
        if(debug) System.out.println("Attack " + this.getAttackDamage());
        if(debug) System.out.println("Health " + this.getHealth());
        if(debug) System.out.println("Speed " + this.getSpeed());
    }
    
    /**
     * Move the player
     */
    public void movement(){
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if(mouse != null){ // Check if the mouse is in the game world
            turnTowards(mouse.getX(), mouse.getY());
        }
        
        if (Greenfoot.isKeyDown("w")){ // Check if the key being pressed is w
            move((int)speed);
        } else if (Greenfoot.isKeyDown("s")){ // Check if the key being pressed is s
            move((int)-speed);
        } else {
            move(0);
        }
    }
    
    /**
     * Switch worlds if player health is zero
     */
    public void kill(){
        if (health <= 0) { // Check if health is zero or less, switch worlds if true
            world = (Environment) getWorld();
            world.end();
        }
    }
    
    public void potionLabels(){
        if(world.getGameTime() > potionHealthLabelDespawn && potionHealthLabelOn == true){ // Check if the game time is greater than the despawn time for the potiont health label and that the potion label is on, if true remove potion health label
            world.removeObject(potionHealthLabel);
            potionHealthLabelOn = false;
        }
            
        if(world.getGameTime() > potionSpeedLabelDespawn && potionSpeedLabelOn == true){ // Check if the game time is greater than the despawn time for the potiont speed label and that the potion label is on, if true remove potion speed label
            world.removeObject(potionSpeedLabel);
            potionSpeedLabelOn = false;
            potionSpeedModifier = 1.0;
            speed = ORIGINALSPEED;
        } else if(world.getGameTime() < potionSpeedLabelDespawn && potionSpeedLabelOn == true && potionSpeedModifier == 1.3){ // Check if the game time is less than the despawn time for the potiont speed label and that the potion label is on, if true add potion speed label
            potionSpeedLabel.setText("1.3x Speed: " + (potionSpeedLabelDespawn - world.getGameTime()) /60);
        } else if(world.getGameTime() < potionSpeedLabelDespawn && potionSpeedLabelOn == true && potionSpeedModifier == 1.5){ // Check if the game time is less than the despawn time for the potiont speed label and that the potion label is on, if true add potion speed label
            potionSpeedLabel.setText("1.5x Speed: " + (potionSpeedLabelDespawn - world.getGameTime()) /60);
        } else if(world.getGameTime() < potionSpeedLabelDespawn && potionSpeedLabelOn == true && potionSpeedModifier == 1.8){ // Check if the game time is less than the despawn time for the potiont speed label and that the potion label is on, if true add potion speed label
            potionSpeedLabel.setText("1.8x Speed: " + (potionSpeedLabelDespawn - world.getGameTime()) /60);
        }
    }
    
    /**
     * Get items for the player
     */
    public void getItems(){
        world = (Environment) getWorld();
        Actor lightArmour;
        lightArmour = getOneIntersectingObject(LightArmour.class);
        
        if(lightArmour != null){ // Check if touching a light armour object
            world.armourLabel.setText("Armour: Light Armour");
            world.armourLabel.setLocation(105, world.getHeight() / 40);
            setDefense(0.25);
            armourSpeedModifier = 2;
            setSpeed(armourSpeedModifier, potionSpeedModifier, weaponSpeedModifier);
            world.removeObject(lightArmour);
        }
        
        Actor mediumArmour;
        mediumArmour = getOneIntersectingObject(MediumArmour.class);
        
        if(mediumArmour != null){ // Check if touching a medium armour object
            world.armourLabel.setText("Armour: Medium Armour");
            world.armourLabel.setLocation(110, world.getHeight() / 40);
            setDefense(0.5);
            armourSpeedModifier = 1;
            setSpeed(armourSpeedModifier, potionSpeedModifier, weaponSpeedModifier);
            world.removeObject(mediumArmour);
        }
        
        Actor heavyArmour;
        heavyArmour = getOneIntersectingObject(HeavyArmour.class);
        
        if(heavyArmour != null){ // Check if touching a heavy armour object
            world.armourLabel.setText("Armour: Heavy Armour");
            world.armourLabel.setLocation(105, world.getHeight() / 40);
            setDefense(0.8);
            armourSpeedModifier = 0.5;
            setSpeed(armourSpeedModifier, potionSpeedModifier, weaponSpeedModifier);
            world.removeObject(heavyArmour);
        }
        
        Actor potionOfInvigoration;
        potionOfInvigoration = getOneIntersectingObject(PotionOfInvigoration.class);
        
        if(potionOfInvigoration != null){ // Check if touching a potion of invigoration object
            potionHealthLabel.setText("+150 Health");
            world.addObject(potionHealthLabel, world.getWidth() / 2, world.getHeight() / 2);
            potionHealthLabelOn = true;
            potionHealthLabelDespawn = world.getGameTime() + 120;
            addHealth(150);
            world.healthLabel.setText("Health: " + this.getHealth());
            world.healthLabel.setLocation(60, world.getHeight() / 40 + 80);
            world.removeObject(potionOfInvigoration);
        }
        
        Actor potionOfCuring;
        potionOfCuring = getOneIntersectingObject(PotionOfCuring.class);
        
        if(potionOfCuring != null){ // Check if touching a potion of curing object
            potionHealthLabel.setText("+300 Health");
            world.addObject(potionHealthLabel, world.getWidth() / 2, world.getHeight()/2);
            potionHealthLabelOn = true;
            potionHealthLabelDespawn = world.getGameTime() + 120;
            addHealth(300);
            world.healthLabel.setText("Health: " + this.getHealth());
            world.healthLabel.setLocation(60, world.getHeight() / 40 + 80);
            world.removeObject(potionOfCuring);
        }
        
        Actor potionOfRestoration;
        potionOfRestoration = getOneIntersectingObject(PotionOfRestoration.class);
        
        if(potionOfRestoration != null){ // Check if touching a potion of restoration object
            potionHealthLabel.setText("+600 Health");
            world.addObject(potionHealthLabel, world.getWidth() / 2, world.getHeight() / 2);
            potionHealthLabelOn = true;
            potionHealthLabelDespawn = world.getGameTime() + 120;
            addHealth(600);
            world.removeObject(potionOfRestoration);
            world.healthLabel.setText("Health: " + this.getHealth());
            world.healthLabel.setLocation(60, world.getHeight() / 40 + 80);
        }
        
        Actor potionOfAlacrity;
        potionOfAlacrity = getOneIntersectingObject(PotionOfAlacrity.class);
        
        if(potionOfAlacrity != null){ // Check if touching a potion of alacrity object
            potionSpeedLabel.setText("1.3x Speed");
            world.addObject(potionSpeedLabel, world.getWidth()/8, world.getHeight()-20);
            potionSpeedLabelOn = true;
            potionSpeedLabelDespawn = world.getGameTime() + 1800;
            potionSpeedModifier = 1.3;
            setSpeed(armourSpeedModifier, potionSpeedModifier, weaponSpeedModifier);
            world.removeObject(potionOfAlacrity);
        }
        
        Actor potionOfSwiftness;
        potionOfSwiftness = getOneIntersectingObject(PotionOfSwiftness.class);
        
        if(potionOfSwiftness != null){ // Check if touching a potion of swiftness object
            potionSpeedLabel.setText("1.5x Speed");
            world.addObject(potionSpeedLabel, world.getWidth()/8, world.getHeight()-20);
            potionSpeedLabelOn = true;
            potionSpeedLabelDespawn = world.getGameTime() + 1800;
            potionSpeedModifier = 1.5;
            setSpeed(armourSpeedModifier, potionSpeedModifier, weaponSpeedModifier);
            world.removeObject(potionOfSwiftness);
        }
        
        Actor potionOfFleetness;
        potionOfFleetness = getOneIntersectingObject(PotionOfFleetness.class);
        
        if(potionOfFleetness != null){ // Check if touching a potion of fleetness object
            potionSpeedLabel.setText("1.8x Speed");
            world.addObject(potionSpeedLabel, world.getWidth()/8, world.getHeight()-20);
            potionSpeedLabelOn = true;
            potionSpeedLabelDespawn = world.getGameTime() + 1800;
            potionSpeedModifier = 1.8;
            setSpeed(armourSpeedModifier, potionSpeedModifier, weaponSpeedModifier);
            world.removeObject(potionOfFleetness);
        }
    }
}
