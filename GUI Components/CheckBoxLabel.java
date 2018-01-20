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

    /**
     * New CheckBoxLabel.
     * @param text The String to display.
     * @param leftJustifyInContainers Whether or not this Label will left justify within cells of Containers.
     * @param checked The initial "checked" status.
     */
    public CheckBoxLabel(String text, boolean checked)
    {
        super(text);
        this.checked = checked;
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
            changed = true;
            setImage(draw());
        }
    }
    
    @Override
    protected GreenfootImage draw()
    {
        GreenfootImage image = getImage();
        int[] atts = getTextAttributes();
        GreenfootImage pic = new GreenfootImage(16 + atts[0], 1 + atts[1] + atts[2]);
        image.setColor(enabled ? textColor : disableColor);
        int mid = atts[1] / 2;
        image.drawRect(0, mid - 4, 8, 8);
        if (checked)
        {
            image.drawLine(0, mid - 4, 8, mid + 4);
            image.drawLine(0, mid + 4, 8, mid - 4);
        }
        image.setFont(font);
        image.drawString(text, 16, atts[1]);
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
}