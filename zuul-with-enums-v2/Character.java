
/**
 * The character class creates an NPC that the player can trade with.
 * It has the standard variables (name, descipriton, ID)
 * as well as the int number of the characters gold/item req.
 * It also holds a boolean for completion, and has the potential to store an item.
 *
 * @author (Anthony Wythe)
 * @version ('Escape' Version 5 - 06/01/2018)
 */
public class Character
{

    private boolean completed = false; //Whether the charcter has been completed

    private int gold; //Characters gold req.
    private int itemID; //Characters item req.
    private Item item; //Characters owned Item

    private String name; //Characters name
    private String description; //Characters description
    private int IDNumber; //Characters ID number

    /**
     * Constructor for objects of class Character.
     * @param String name : the characters name
     * @param String description : the characters description
     * @param int IDNumber : the characters ID
     * @param int gold : the characters gold requirement
     * @param int itemID : the characters item requirement
     */
    public Character(String name, String description, int IDNumber, int gold, int itemID)
    {      
        this.name = name;
        this.description = description;
        this.IDNumber = IDNumber;

        this.gold = gold;
        this.itemID = itemID;
    }

    /**
     * Returns the characters ID number
     * @return int
     */
    public int getID()
    {
        return IDNumber;
    }

    /**
     * Returns the characters item requirement.
     * @return int
     */
    public int getItemID()
    {
        return itemID;
    }

    /**
     * Returns the characters gold requirement.
     * @return int
     */
    public int getGold()
    {
        return gold;
    }

    /**
     * Returns whether the character has been completed.
     * @return boolean
     */
    public boolean isCompleted()
    {
        return completed;
    }

    /**
     * Method to set the character as completed.
     * @return void
     */
    public void setCompleted()
    {
        completed = true;
    }

    /**
     * Method to add an item to a character.
     * @param String itemName : the items name
     * @param String itemDescription : the items description
     * @param int itemID : the items ID number
     * @return void
     */
    public void addItem(String itemName, String itemDescription, int itemID)
    {
        item = new Item(itemName, itemDescription, itemID);
    }

    /**
     * Returns the characters item.
     * @return Item
     */
    public Item getCurrentItem()
    {
        return item;
    }
}
