import javax.swing.*;

/**
 * The item class holds information about an item the player can take.
 * It has two fields of String type: one for the name and one for the description.
 * It also has; int: the ID number, boolean: whether the item has been collected or not.
 * 
 * I have added an image variable that can be set for each item and returned to the UI.
 *
 * @author (Anthony Wythe)
 * @version ('Escape' Version 5 - 06/01/2018)
 */
public class Item
{
    private String description; //Field for the description
    private String name; //Field for the name
    private int itemID; //Field for the items ID
    private boolean itemCollected; //Whether the item has been picked up or not
    private ImageIcon image; //Holds the items image

    /**
     * Constructor for the item object, sets up the name, description and ID.
     * @param String name: item name
     * @param String description: item description
     * @param int itemID: item ID number
     */
    public Item(String name, String description, int itemID)
    {
        this.name = name;
        this.description = description;
        this.itemID = itemID;
    }

    /**
     * Sets an items name, description and ID.
     * @param String name: item name
     * @param String description: item description
     * @param int itemID: item ID number
     * @return void
     */
    public void setItem(String name, String description, int itemID)
    {
        this.name = name;
        this.description = description;
        this.itemID = itemID;
    }

    /**
     * Returns the item name.
     * @return String
     */
    public String getItemName()
    {
        return name;
    }

    /**
     * Returns the item description.
     * @return String
     */
    public String getItemDescription()
    {
        return description;
    }

    /**
     * Returns the item ID number.
     * @return int
     */
    public int getItemID()
    {
        return itemID;
    }

    /**
     * Returns true or false depending if an item has been collected before.
     * @return boolean
     */
    public boolean isItemCollected()
    {
        return itemCollected;
    }

    /**
     * Method to set whether an item has been collected or not.
     * @param boolean answer: input to change boolean value
     * @return void
     */
    public void itemCollected(boolean answer)
    {
        itemCollected = answer;
    }

    /**
     * Method that creates an image icon to add to the item.
     * @param String path : the image file location
     * @param String description : title for the image file
     * @return ImageIcon
     */
    protected ImageIcon createImageIcon(String path, String description) {
        //Tries to locate the image
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            //Returns image icon if located
            return new ImageIcon(imgURL, description);
        } else {
            //Returns null and an error if no file found
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    /**
     * Method that triggers the creation of an image and adds it to the item.
     * @param String path : the image file location
     * @param String description : title for the image file
     * @return void
     */
    protected void setItemImage(String path, String description)
    {
        image = createImageIcon(path, description);
    }

    /**
     * Returns the items image.
     * @return ImageIcon
     */
    protected ImageIcon returnItemImage()
    {
        return image;
    }
}

