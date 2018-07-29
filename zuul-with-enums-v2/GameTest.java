import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This class tests the main objectives within the game.
 * It runs through the game as if it were being played by a real user.
 * Various checks are made to make sure the mechanics are working properly.
 *
 * @author  (Anthony Wythe)
 * @version ('Escape' Version 5 - 06/01/2018)
 */
public class GameTest
{
    Game game;//Instance of the 'game' class

    /**
     * Constructor that initiliases the instance of the game class.
     */
    public GameTest()
    {
        game = new Game(); //Sets up the game class
    }

    /**
     * Tests the first game objective.
     * The player finds the water item and trades it for 5 gold at the campfire.
     * @return void
     */
    @Test
    public void testCampfireTrade()
    {
        //Moves the player to the waterfall
        game.goRoom2("west");
        //Takes the water item
        game.take();
        //Checks the water has been collected
        assertEquals(game.player.searchInventory(104), true);

        //Moves the player to the campfire
        game.goRoom2("south");
        game.goRoom2("east");
        //Trades the water for gold
        game.trade();
        //Checks the trade has worked
        assertEquals(game.player.searchInventory(104), false);
        assertEquals(game.player.checkGold(), 5);
    }

    /**
     * Tests collection of the 1st key.
     * The payer navigates to the top of the totem pole and collects.
     * @return void
     */
    @Test
    public void testTotemPole()
    {
        //Moves the player to the top totem pole
        game.goRoom2("south");
        game.goRoom2("south");
        game.goRoom2("up");
        //Takes the key
        game.take();
        //Checks the key has been collected
        assertEquals(game.player.searchInventory(101), true);
    }

    /**
     * Tests the objectives within the cave.
     * The player collects the rope and uses it to navigate to the bottom of the cave.
     * They can then collect the 2nd of the games keys.
     * @return void
     */
    @Test
    public void testCave()
    {
        //Moves the player to the tent
        game.goRoom2("west");
        game.goRoom2("north");
        game.goRoom2("west");
        //Takes the rope item
        game.take();
        //Checks the player has the rope
        assertEquals(game.player.searchInventory(106), true);

        //Moves the player to the main area of the cave
        game.goRoom2("east");
        game.goRoom2("north");
        //Use the rope item
        game.use();
        //Checks the rope worked
        assertEquals(game.player.getCurrentRoom().getLongDescription(), "\n------------------------------------CAVE HOLE------------------------------------\n");

        //Moves the player to the bottom of the cave
        game.goRoom2("down");
        //Take the key item
        game.take();
        //Checks the player has the key
        assertEquals(game.player.searchInventory(102), true);
    }

    /**
     * Tests collection and use of the machette.
     * The player trades coins for the machette at the caravan.
     * This is then used to navigate through the jungle.
     * @return void
     */
    @Test
    public void testMachette()
    {
        //Moves the player to the caravan
        game.goRoom2("north");
        //Adds gold to the player to carry out the trade
        game.player.addGold(10);
        //Trades gold for the machette
        game.trade();
        //Checks the player has the machette
        assertEquals(game.player.searchInventory(107), true);
        //Checks the player has traded their gold
        assertEquals(game.player.checkGold(), 0);

        //Moves the player to the jungle clearing
        game.goRoom2("south");
        //Uses the machette
        game.use();
        //Checks the player is in the jungle
        assertEquals(game.player.getCurrentRoom().getLongDescription(), "\n------------------------------------  JUNGLE  ------------------------------------\n");
    }

    /**
     * Tests the objectives within the temple.
     * The player collects gold from the temple library.
     * They then complete the two puzzle rooms to get to where the guard is.
     * The guard is paid 10 gold coins for entry to the museum.
     * The 3rd key is collected inside the museum.
     * @return void
     */
    @Test
    public void testTemple()
    {
        //Moves the player to the caravan and collects the machette
        game.goRoom2("north");
        game.player.addGold(10);
        game.trade();
        //Move the player from the caravan to the temple (using the machette)
        game.goRoom2("south");
        game.use();
        game.goRoom2("east");
        game.goRoom2("north");

        //Moves the player to the library
        game.goRoom2("west");
        //Takes the gold
        game.take();
        //Checks the player has the gold
        assertEquals(game.player.checkGold(), 5);

        //Moves the player to puzzle room 1
        game.goRoom2("east");
        game.goRoom2("east");
        //Sets the pillar to the correct face
        game.use();
        game.use();
        game.use();
        game.use();
        //Checks the pillar is on the correct face
        assertEquals(game.player.getCurrentRoom().getPillar().isCorrect(), true);
        //Moves the player to puzzle room 2
        game.goRoom2("north");
        //Checks the player has moved to puzzle room 2
        assertEquals(game.player.getCurrentRoom().getLongDescription(), "\n------------------------------------  TEMPLE PUZZLE 2  ------------------------------------\n");

        //Sets the pillar to the correct face
        game.use();
        game.use();
        game.use();
        //Checks the pillar is on the correct face
        assertEquals(game.player.getCurrentRoom().getPillar().isCorrect(), true);
        //Moves the player to puzzle room 2
        game.goRoom2("north");
        //Checks the player has moved to puzzle room 2
        assertEquals(game.player.getCurrentRoom().getLongDescription(), "\n------------------------------------  GUARD  ------------------------------------\n");

        //Adds gold to the player to carry out the trade
        game.player.addGold(5);
        //Trades gold with the guard to open the door
        game.trade();
        //Checks the player traded their gold
        assertEquals(game.player.checkGold(), 0);
        //Moves the player through the now unlocked door
        game.goRoom2("east");
        //Checks the player is in the museum
        assertEquals(game.player.getCurrentRoom().getLongDescription(), "\n------------------------------------  MUSEUM  ------------------------------------\n");

        //Takes the key
        game.take();
        //Checks the player has the key
        assertEquals(game.player.searchInventory(103), true);
    }
}
