import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This tests some of the players inventory functions.
 * Adding items, deleting them, adding gold and removing it.
 *
 * @author  (Anthony Wythe)
 * @version ('Escape' Version 5 - 06/01/2018)
 */
public class PlayerTest
{
    Player player; //Instance of the player class
    Item item; //Instance of the item class
    /**
     * Constructor to initialise a player and an item.
     */
    public PlayerTest()
    {
        player = new Player(); //Sets up the player class
        //Sets up an item object for testing purposes
        item = new Item("Test Item", "instance of an item object for testing", 100);
    }
    
    /**
     * This tests the players inventory functions.
     * An item is added and then checked it has worked.
     * The same is done for deletion of the item.
     * @return void
     */
    @Test
    public void testInventory()
    {
        //Adds the test item to the players inventory
        player.addItem(item);
        //Checks the player has the item in their inventory
        assertEquals(player.searchInventory(100), true);
        
        //Removes the item from the players inventory
        player.deleteItem(100);
        //Checks the item has been removed
        assertEquals(player.searchInventory(100), false);
    }
    
    /**
     * This tests the players gold functions.
     * Gold is added then checked it has worked.
     * The same is done for removal of gold.
     * @return void
     */
    @Test
    public void testGold()
    {
        //Adds gold to the players inventory
        player.addGold(10);
        //Checks the gold has been added
        assertEquals(player.checkGold(), 10);
        
        //Removes gold from the players inventory
        player.removeGold(10);
        //Checks the gold has been removed
        assertEquals(player.checkGold(), 0);
    }
}
