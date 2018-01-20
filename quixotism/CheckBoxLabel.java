import greenfoot.GreenfootImage;
import greenfoot.Font;
import greenfoot.Color;
import java.awt.RenderingHints;

/**
 * CheckBoxLabel
 * <p>
 * A Label that has a "checked" status and is visually represented and altered when clicked on.
 * 
 * @author Taylor Born
 * @version March 2013 - April 2014
 */
public class CheckBoxLabel extends Label
{
    private boolean checked;
    private boolean changed;
    private boolean enabled = true;
    private int id;
    //private String text;
    //private int fontWidth, fontHeight;

    /**
     * New CheckBoxLabel.
     * @param text The String to display.
     * @param leftJustifyInContainers Whether or not this Label will left justify within cells of Containers.
     * @param checked The initial "checked" status.
     */
    public CheckBoxLabel(String text, boolean checked, int id)
    {
        super(text);
        this.id = id;
        setImage(draw());
    }
    
    public void enable(boolean e)
    {
        if (enabled != e) {
            enabled = e;
            setImage(draw());
        }
    }
    
    /**
     * Act.<p>
     * Listen for mouse press, to alter "checked" status.
     */
    @Override
    public void act()
    {
        super.act();
        if (mousePressedOnThisOrComponents() && enabled)
        {
            checked = !checked;
            if (checked) {
                if (((CharacterSelectScreen)getWorld()).getSelected() != null) ((CharacterSelectScreen)getWorld()).getSelected().setChecked(false);
                ((CharacterSelectScreen)getWorld()).setSelected(this);
            }
            changed = true;
            setImage(draw());
        }
    }
    
    @Override
    protected GreenfootImage draw()
    {
        GreenfootImage pic = new GreenfootImage(text.length()*fontWidth + 24, fontHeight+4);
        pic.setColor(enabled ? textColor : disableColor);
        pic.drawRect(0, 4, 8, 8);
        if (checked)
        {
            pic.drawLine(0, 4, 8, 12);
            pic.drawLine(0, 12, 8, 4);
        }
        pic.drawString(text, 16, 12);
        return pic;
    }
    
    /**
     * Check this CheckBoxLabel's "checked" status.
     * @return Whether or not "checked" status is true.
     */
    public boolean isChecked()
    {
        return checked;
    }
    
    /**
     * Set the "checked" status.
     * @param c Whether or not "checked" status will be set true.
     */
    public void setChecked(boolean c)
    {
        if (checked != c) {
            changed = true;
            checked = c;
            setImage(draw());
        }
    }
    
    /**
     * Action listener for this CheckBoxLabel.
     * @return Whether or not the "checked" status of this CheckBoxLabel has changed.
     */
    public boolean hasChanged()
    {
        boolean c = changed;
        changed = false;
        return c;
    }
    
    public int getId() {
        return id;
    }
}