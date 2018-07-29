import java.util.ArrayList;

/**
 * The player class holds a players information; current room, inventory, gold.
 * It uses an ArrayList to contain the items in the inventory.
 * 
 * There are methods for adding/deleting/locating items from the inventory.
 * There is also a method to return a string version of the inventory to the UI.
 *
 * @author (Anthony Wythe)
 * @version ('Escape' Version 5 - 06/01/2018)
 */
public class Player
{
    private Room currentRoom; //Holds the players current rooom
    private ArrayList<Item> inventory = new ArrayList<Item>(); //ArrayList for a players inventory
    private int gold; //Variable that holds a players gold

    /**
     * Constructor for the player class. Sets the current room to 'null'.
     */
    public Player()
    {
        currentRoom = null;
    }

    /**
     * Returns the players current room.
     * @return Room
     */
    public Room getCurrentRoom()
    {
        return currentRoom;
    }

    /**
     * Method to set the players current room.
     * @param Room room: the room to be set
     * @return void
     */
    public void setCurrentRoom(Room room)
    {
        currentRoom = room;
    }

    /**
     * Adds gold to a players gold amount and then prints the total.
     * @param int addGold: the amount to be added
     * @return void
     */
    public void addGold(int addGold)
    {
        gold = (gold+addGold);
    }

    /**
     * Removes gold from a players gold amount and then prints the total.
     * @param int removeGold: the amount to be removed
     * @return void
     */
    public void removeGold(int removeGold)
    {
        gold = (gold-removeGold);
    }

    /**
     * Returns how much gold the player has.
     * @return int
     */
    public int checkGold()
    {
        return gold;
    }

    /**
     * Method to add an item to a players inventory.
     * @param Item item: the item to add
     * @return void
     */
    public void addItem(Item item)
    {
        inventory.add(item);
    }

    /**
     * Method to remove an item from a players inventory.
     * @param Item item: the item to remove
     * @return void
     */
    public void removeItem(Item item)
    {
        inventory.remove(item);
    }

    /**
     * Searches a players inventory for a specfic item using an ID number.
     * Returns true if owned, false if not.
     * @param int itemID: the item being searched for
     * @return boolean
     */
    public boolean searchInventory(int itemID)
    {     
        //Runs through the inventory and checks each item ID for a match
        for(int i=0; i<inventory.size(); i++){
            if(itemID == inventory.get(i).getItemID())
            {
                //Return true if foun, flase if not
                return true;  
            }
        }
        //Return false if not found
        return false;
    }

    /**
     * Searches a players inventory for a specific ID number and then removes it.
     * @param int itemID: the item to be deleted
     * @return void
     */
    public void deleteItem(int itemID)
    {
        //Runs through the inventory and checks each item ID for a match
        for(int i=0; i<inventory.size(); i++){
            if(itemID == inventory.get(i).getItemID()){
                //Removes the undesired item
                inventory.remove(i);
            }

        }
    }

    /**
     * Returns a particular item from the inventory.
     * @param int item: the item required
     * @return Item
     */
    public Item getInventoryItem(int item)
    {
        return inventory.get(item);  
    }

    /**
     * Returns the ArrayList that acts as a players inventory.
     * @return ArrayList
     */
    public ArrayList returnInventory()
    {
        return inventory;
    }

    /**
     * Returns the size of a players inventory.
     * @return int
     */
    public int getInventorySize()
    {
        return inventory.size();   
    }

    /**
     * Method to create and return a string version of the inventory for the UI.
     * @return String
     */
    public String printInventory()
    {
        String itemName; //Name of the item
        String itemDescription; //Item details
        String x = ""; //String variable to return

        //Adds the title and gold amount
        x = x + "\n------------------INVENTORY-----------------";
        x = x + "\n\n>>>You have " + gold + " gold<<<\n\n";
        
        //Runs through the inventory and adds each name/details to the inventory string
        for(int i=0; i<inventory.size(); i++){
            itemName = inventory.get(i).getItemName();
            itemDescription = inventory.get(i).getItemDescription();
            x = x + itemName + ": ";
            x = x + itemDescription + "\n\n";
        }
        
        //Returns the string variable for the UI
        return x;
    }

    
}

