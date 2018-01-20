import greenfoot.GreenfootImage;
import greenfoot.Color;
import greenfoot.Font;

import java.awt.FontMetrics;
import java.awt.RenderingHints;


/**
 * Label
 * <p>
 * Used to display a String.
 * 
 * @author Taylor Born
 * @version November 2010 - March 2014
 */
public class Label extends WindowComponent
{
    protected String text;

    public Label(String text, Font font, Color color)
    {
        this.text = text;
        this.font = font;
        textColor = color;
        setImage(draw());
    }

    /**
     * Create a new Label.
     * @param text The text this label will display.
     * @param leftJustifyInContainers Whether or not this Label will left justify within cells of Containers.
     */
    public Label(String text)
    {
        this.text = text;
        setImage(draw());
    }
    
    @Override
    protected void redraw()
    {
        setImage(draw());
    }
    
    /**
     * Update this Label's image.
     */
    protected GreenfootImage draw()
    {
        int[] atts = getTextAttributes();
        GreenfootImage image = new GreenfootImage(1 + atts[0], 1 + atts[1] + atts[2]);
        image.setFont(font);
        image.setColor(textColor);
        image.drawString(text, 0, atts[1]);
        return image;
    }
    
    protected int[] getTextAttributes()
    {
        int[] atts = new int[3];
        GreenfootImage image = getImage();        
        
        image.setFont(font);
        
        atts[0] = (image.toString()).length();
        
        return atts;
    }
    
    /**
     * Set what text this Label will display.
     * @param text The text this Label will display.
     */
    public void setText(String text)
    {
        this.text = text;
        setImage(draw());
    }
    
    /**
     * Get the text this Label is displaying.
     * @return The text this Label is displaying.
     */
    public String getText()
    {
        return text;
    }
}