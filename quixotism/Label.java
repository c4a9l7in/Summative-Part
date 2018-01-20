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
    protected Font font;
    protected Color color;
    protected int fontWidth,fontHeight;
    protected boolean debug = false;

    public Label(String text, Font font, Color color)
    {
        this.text = text;
        this.font = font;
        this.color = color;
        fontWidth = font.getSize() * 2/3;
        fontHeight = font.getSize() * 3/2;
        if (debug) System.out.println(fontWidth + " " + fontHeight);
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
        this.font = new Font("Courier", 12);
        this.color = Color.BLACK;
        this.fontWidth = font.getSize() * 2/3;
        this.fontHeight = font.getSize() * 3/2;
        if (debug) System.out.println(fontWidth + " " + fontHeight);
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
        int len = text.length() * fontWidth;
        if (debug) System.out.println(len);
        GreenfootImage image = new GreenfootImage(len, fontHeight);
        image.setFont(font);
        image.setColor(textColor);
        image.drawString(text, 0, fontHeight/2);
        return image;
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