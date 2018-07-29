import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This is for testing the set-up of item objects.
 * The collection status, name, description and ID number.
 *
 * @author  (Anthony Wythe)
 * @version ('Escape' Version 5 - 06/01/2018)
 */
public class ItemTest
{
    Item item; //Instance of an item object
    
    /**
     * Constructor that initialises the item for testing.
     */
    public ItemTest()
    {
        item = new Item("Test Item", "instance of an item object for testing", 100);
    }

    /**
     * This checks the item collection status can be set properly.
     * @return void
     */
    @Test
    public void collectionTest()
    {
        //Sets the item as collected
        item.itemCollected(true);
        //Checks it has been set
        assertEquals(item.isItemCollected(),true);
    }

    /**
     * This checks the items details can be set properly.
     * @return void
     */
    @Test
    public void detailsTest()
    {
        //Checks the items name was set correctly
        assertEquals(item.getItemName(), "Test Item");
        //Checks the item description was set correctly
        assertEquals(item.getItemDescription(), "instance of an item object for testing");
        //Checks the item ID was set correctly
        assertEquals(item.getItemID(), 100);
    }

}
