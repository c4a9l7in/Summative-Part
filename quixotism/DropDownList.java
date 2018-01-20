import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.MouseInfo;
import greenfoot.World;
import java.util.ArrayList;
import greenfoot.Font;
import greenfoot.Color;

import java.awt.RenderingHints;

import java.awt.Point;

/**
 * DropDownList
 * <p>
 * List of items where there is always one item selected and shown. Clicking on it will display the other items in the list and are able to be selected.<p>
 * When expanded to display items, is removed from and added back to World to bring to front.<p>
 * <p>
 * Action listener: hasChanged()
 * 
 * @author Taylor Born
 * @version March 2011 - April 2014
 */
public class DropDownList<E> extends WindowComponent
{
    private ArrayList<E> items = new ArrayList<E>();
    private int index = -1;
    private Point offsetMouse = new Point(-25, -25);
    private boolean selecting = false;
    private boolean changed = false;
    private Font thisFont;
    private int fontWidth = 7;
    private int fontHeight = 12;

    /**
     * Create a new DropDownList.
     * @param items The contents of the DropDownList.
     * @param index The initial selected item index.
     */
    public DropDownList(ArrayList<E> items, int index)
    {
        this.items = items;
        this.index = index;
        thisFont = new Font("Courier", 12);
        setFont(thisFont);
        draw();
    }
    
    private int cellHeight = fontHeight;
    
    @Override
    public void setFont(Font font)
    {
        super.setFont(font);
        cellHeight = fontHeight;
    }
    
    @Override
    public void setLocation(int x, int y)
    {
        super.setLocation(x, selecting ? y - 6 + getImage().getHeight() / 2 : y);
    }
    
    @Override
    public void act() 
    {
        super.act();
        
        if (Greenfoot.mouseMoved(null) || Greenfoot.mouseDragged(null)) {
            MouseInfo mouse = Greenfoot.getMouseInfo();
            offsetMouse.setLocation(mouse.getX() - (getX() - (getImage().getWidth() / 2)), mouse.getY() - (getY() - (getImage().getHeight() / 2)));
        }
        
        if (Greenfoot.mouseClicked(this)) {
            if (offsetMouse.getY() > cellHeight) {
                int i = (int)(offsetMouse.getY() - cellHeight) / cellHeight;
                setIndex(i);
            }
            toggleSelecting();
        }
        else if (!hasFocus() && selecting)
            toggleSelecting();
        
        draw();
    }
    
    private void draw()
    {
        int height = cellHeight;
        if (selecting)
            height += items.size() * cellHeight;
        
        int width = 0;
        
        GreenfootImage image = getImage();
        if (image == null) image = new GreenfootImage(1, 1);
        image.setFont(thisFont);
        for (E e : items) {
            int w = e.toString().length()*fontWidth;
            if (w > width)
                width = w;
        }
        
        width += 4;
        
        if (image == null || image.getWidth() != width + 1 || image.getHeight() != height + 1) {
            image = new GreenfootImage(width + 1, height + 1);
            setImage(image);
        }
        else if (backColor.getAlpha() != 255)
            image.clear();
        image.setColor(backColor);
        image.fill();
        
        if (index > -1 && index < items.size()) {
            image.setColor(selecting ? disableColor : textColor);
            image.drawString(items.get(index).toString(), 2, 11);
        }
        image.setColor(borderColor);
        image.drawRect(0, 0, width, cellHeight);
        
        if (selecting)
            for (int i = 0; i < items.size(); i++) {
                if (mouseOverThis() && offsetMouse.getY() > cellHeight * (i + 1) && offsetMouse.getY() <= cellHeight * (i + 2)) {
                    image.setColor(hoverColor);
                    image.fillRect(0, cellHeight * (i + 1), width, cellHeight);
                }
                image.setColor(textColor);
                image.drawString(items.get(i).toString(), 2, (i + 2) * cellHeight - 1);
                image.setColor(borderColor);
                image.drawRect(0, cellHeight * (i + 1), width, cellHeight);
            }
        
    }
    
    /**
     * When considered in Container cells, treat height always like when not expanded.
     */
    @Override
    public int getGUIHeight()
    {
        return fontHeight + 1;
    }
    
    private void toggleSelecting()
    {
        selecting = !selecting;
        if (selecting)
        {
            int x = getX();
            int y = getY();
            World w = getWorld();
            w.removeObject(this);// don't use removeFromWorld(); since it will set that the mouse is no longer over this
            w.addObject(this, x, y - 6 + ((items.size() + 1) * cellHeight) / 2);
        }
        else
            super.setLocation(getX(), getY() - getImage().getHeight() / 2 + 6);
    }
    
    /**
     * @return String for the item that is selected. Null if none selected.
     */
    public E getSelected()
    {
        if (index == -1)
            return null;
        return items.get(index);
    }
    
    /**
     * @return The index of the item that is selected. -1 if none selected.
     */
    public int getIndex()
    {
        return index;
    }
    
    /**
     * Set the selected item from its list.
     * @param i The index from list to be selected item.
     */
    public void setIndex(int i)
    {
        if (index != i)
        {
            changed = true;
            index = i;
        }
    }
    
    /**
     * @return The contents in this list.
     */
    public ArrayList<E> getList()
    {
        return items;
    }
    
    /**
     * The action listener for this DropDownList.
     * @return Whether a new item has been selected.
     */
    public boolean hasChanged()
    {
        boolean c = changed;
        changed = false;
        return c;
    }
}