import java.util.Iterator;
import java.util.ArrayList;

/**
 * This is the class responsible for the bulk of the game mechanics.
 * It creates the player and initilises the map and its various features.
 * Here you can add; rooms, items, coins, characters and pillar objects.
 * 
 * This class runs most of the command word methods.
 * These include; take, use, trade, go (as well as the help printout).
 * 
 * The class also outputs an error number that changes depeneding on what error was caused.
 * 
 * @author  (Anthony Wythe)
 * @version ('Escape' Version 5 - 06/01/2018)
 */

public class Game 
{
    public Player player; //Creates a player object to be the player in the game
    int errorNum = 0; //The current error number of the game state (0 is none)

    /**
     * Creates the game and the player. Then initialises its internal map.
     */
    public Game() 
    {
        player = new Player(); //Initialises the player
        createRooms(); //Initialises the map
    }

    /**
     * Creates all the rooms and links their exits together. 
     * Sets the player to start in the correct room. 
     * Adds all objects to rooms.
     * @return void
     */
    private void createRooms()
    {
        //Declares all of the room objects
        Room jungleClearing, tradingCaravan, stoneDoor, winGame, campfire, totemPole, totemPoleTop, stream, waterfall, cave, tent, cave1, hole, cave2, jungle, templeEntrance, templeGardens, temple, templeSide, temple1, temple2, guard, museum;

        //Create the rooms, providing an ID number and their descriptions (short and full)
        jungleClearing = new Room(101, "JUNGLE CLEARING", "     You find yourself in an open clearing of a thick jungle\n     There are pathways in every direction but the route to the east is blocked by foliage");

        tradingCaravan = new Room(102,"TRADING CARAVAN","     A trading caravan has been set up amongst the trees\n     The owner says he can trade you a machette for 10 gold coins");
        stoneDoor = new Room(103,"STONE DOOR","     This is the way to get out of the jungle and back to civilisation\n     The door requires 3 different keys to open it");
        winGame = new Room(104,"WIN GAME","    Congratulations, you've escaped the jungle...for now");

        campfire = new Room(105, "CAMPFIRE", "     A weary traveller rests beside an open fire\n     He asks if you have any fresh water he could trade for gold");
        totemPole = new Room(106, "TOTEM POLE", "     A large totem pole stares ominously into the distance\n     You notice it is possible to climb");
        totemPoleTop = new Room(107, "TOP OF TOTEM POLE", "     You have a clear view of the surronding area from up here\n     The jungle looks as if it continues endlessly over the horizon\n     The eye of the top face seems to be a box and it pops open when pushed");
        stream = new Room(108,"STREAM","     A freshwater stream flows south from a waterfall\n     The water is definitely good enough to drink from");

        waterfall = new Room(109,"WATERFALL","     A powerful waterfall crashes into a pool of clear water\n     It appears to flow over the entrance of a cave");
        cave = new Room(110,"CAVE ENTRANCE","     Tucked behind the waterfall is an entrance to a deep cave\n     You can't see much inside the cave because it is so dark\n     Just to the west of the entrance, there is a small tent");
        tent = new Room(111,"    TENT    ","     The inside of the tent is deceptivley more spacious than it looks from the outside\n     Perhaps a miner stayed here, there are various bits of digging and climbing gear");
        cave1 = new Room(112,"CAVE MAIN","     You are in the main chamber of the cave system\n     There is a hole that goes deeper into the darkness, you'd need a rope to get down it");
        hole = new Room(113,"CAVE HOLE","     This hole leads straight down to the bottom of the cave\n     It is so dark you can barely see your hand in front of your face");
        cave2 = new Room(114,"CAVE BOTTOM","     You have navigated your way to the bottom of the cave system\n     There is a pool of beautiful water and you can see a chest of some sort underneath the surface");

        jungle = new Room(115,"  JUNGLE  ","     There are thick vines and foliage everywhere\n     Who knows what animals might be watching???");
        templeEntrance = new Room(116, "TEMPLE ENTRANCE","     You stand beneath the entrance to a grand temple\n     The architecture is fantastic, there is so much detail");
        templeGardens = new Room(117, "TEMPLE GARDENS","     The gardens hare so colourful and healthy, they are clearly well looked after\n     A dog comes to greet you, he looks very thirsty, perhaps you could give him some water?\n     There is a small bag tied around his neck, it looks like a coin purse");
        temple = new Room(118,"TEMPLE MAIN","     This place is amazing, it is full of impressive sculptures and artwork\n     To the west is a library and to the east the rest of the temple");
        templeSide = new Room(119,"TEMPLE LIBRARY","     The temple library is full of ancient scrolls and books");
        temple1 = new Room(120,"  TEMPLE PUZZLE 1  ","     There is a rotating pillar in the centre of the room\n     It has 5 faces with different coloured birds\n     An inscription on the wall reads: |---Think of the systems in place---|");
        temple2 = new Room(121,"  TEMPLE PUZZLE 2  ","     There is a rotating pillar in the centre of the room\n     It has 5 faces, each with a different letter\n     An inscription on the wall reads: |---Think of the systems in place---|");
        guard = new Room(122, "  GUARD  ","     A guard stands in the way of the door\n     The museum is for the monks only, but if you pay him 10 coins he will let you pass");
        museum = new Room(123,"  MUSEUM  ","     This room is full of religious artefacts in display cases\n     Tucked away in the corner, there is a picture of a stone door and a case with a key inside");

        //Sets the starting room for the player
        player.setCurrentRoom(jungleClearing);

        //Add an image to a room
        jungleClearing.setRoomImage("/Images/JungleClearing.jpg", "Jungle Clearing");
        tradingCaravan.setRoomImage("/Images/TradingCaravan.jpg", "Trading Caravan");
        stoneDoor.setRoomImage("/Images/StoneDoor.jpg", "Stone Door");
        winGame.setRoomImage("/Images/WinGame.png", "Win Game");

        campfire.setRoomImage("/Images/CampFire.jpg", "Campfire");
        totemPole.setRoomImage("/Images/TotemPole.png", "Totem Pole");
        totemPoleTop.setRoomImage("/Images/TotemPoleTop.jpg", "Totem Pole Top");
        stream.setRoomImage("/Images/Stream.jpg", "Stream");
        waterfall.setRoomImage("/Images/Waterfall.jpg", "Waterfall");

        cave.setRoomImage("/Images/CaveEntrance.jpg", "Cave Entrance");
        tent.setRoomImage("/Images/Tent.jpg", "Tent");
        cave1.setRoomImage("/Images/CaveMain.jpg", "Cave Main");
        hole.setRoomImage("/Images/Hole.jpg", "Cave Hole");
        cave2.setRoomImage("/Images/CaveBottom.jpg", "Cave Bottom");

        jungle.setRoomImage("/Images/Jungle.jpg", "Jungle");
        templeEntrance.setRoomImage("/Images/TempleEntrance.jpg", "Temple Entrance");
        templeGardens.setRoomImage("/Images/TempleGardens.jpg", "Temple Gardens");
        temple.setRoomImage("/Images/TempleMain.jpg", "Temple Main");
        templeSide.setRoomImage("/Images/TempleLibrary.jpg", "Temple Library");
        temple1.setRoomImage("/Images/TempleMain.jpg", "Temple One");
        temple2.setRoomImage("/Images/TempleMain.jpg", "Temple Two");
        guard.setRoomImage("/Images/TempleGuard.jpg", "Temple Guard");
        museum.setRoomImage("/Images/Museum.jpg", "Museum");

        //Sets the exits for the various rooms
        jungleClearing.setExit("north", tradingCaravan);
        jungleClearing.setExit("east", jungle);
        jungleClearing.setExit("south", campfire);
        jungleClearing.setExit("west", waterfall);

        tradingCaravan.setExit("north", stoneDoor);
        tradingCaravan.setExit("south", jungleClearing);

        stoneDoor.setExit("north", winGame);
        stoneDoor.setExit("south", tradingCaravan);

        campfire.setExit("north", jungleClearing);
        campfire.setExit("south", totemPole);
        campfire.setExit("west", stream);

        totemPole.setExit("north", campfire);
        totemPole.setExit("up", totemPoleTop);
        totemPoleTop.setExit("down", totemPole);

        stream.setExit("north", waterfall);
        stream.setExit("east", campfire);

        waterfall.setExit("north", cave);
        waterfall.setExit("east", jungleClearing);
        waterfall.setExit("south", stream);

        cave.setExit("north", cave1);
        cave.setExit("south", waterfall);
        cave.setExit("west", tent);
        tent.setExit("east", cave);

        cave1.setExit("south", cave);
        cave1.setExit("down", hole);
        hole.setExit("up", cave1);
        hole.setExit("down", cave2);
        cave2.setExit("up", hole);

        jungle.setExit("east", templeEntrance);
        jungle.setExit("west", jungleClearing);

        templeEntrance.setExit("north", temple);
        templeEntrance.setExit("south", templeGardens);
        templeEntrance.setExit("west", jungle);
        templeGardens.setExit("north", templeEntrance);

        temple.setExit("east", temple1);
        temple.setExit("south", templeEntrance);
        temple.setExit("west", templeSide);
        templeSide.setExit("east", temple);

        temple1.setExit("north", temple2);
        temple1.setExit("west", temple);
        temple2.setExit("north", guard);
        temple2.setExit("south", temple1);

        guard.setExit("east", museum);
        guard.setExit("south", temple2);
        museum.setExit("west", guard);

        //Initialise locked rooms
        stoneDoor.setLock("north", "locked");
        cave1.setLock("down", "locked");
        jungleClearing.setLock("east", "locked");
        temple1.setLock("north", "locked");
        temple2.setLock("north", "locked");
        guard.setLock("east", "locked");

        //Adds items to a room - requires a name, description and ID number
        totemPoleTop.addItem("KEY ONE", "One of three objects needed to escape", 101);
        cave2.addItem("KEY TWO", "One of three objects needed to escape", 102);
        museum.addItem("KEY THREE", "One of three objects needed to escape", 103);
        stream.addItem("WATER", "A fresh bottle of water", 104);
        waterfall.addItem("WATER", "A fresh bottle of water", 104);
        tent.addItem("ROPE", "A grappling hook and rope for climbing", 106);

        //Add item images
        tent.getCurrentItem().setItemImage("/Images/Rope.jpg", "Rope");
        cave2.getCurrentItem().setItemImage("/Images/Key.jpg", "Key");
        totemPoleTop.getCurrentItem().setItemImage("/Images/Key.jpg", "Key");
        museum.getCurrentItem().setItemImage("/Images/Key.jpg", "Key");
        waterfall.getCurrentItem().setItemImage("/Images/Water.png", "Water");
        stream.getCurrentItem().setItemImage("/Images/Water.png", "Water");

        //Initialise pillar objects - requires 5 faces and the correct face number
        temple1.setPillar("RED BIRD", "GREEN BIRD", "YELLOW BIRD", "BLACK BIRD", "BLUE BIRD", 4);
        temple2.setPillar("G", "H", "I", "J", "K", 3);

        //Add the face images to the pillar objects and initilise starting image
        temple1.getPillar().setPillarImage("Images/PillarRed.png","Red Pillar");
        temple1.getPillar().setPillarImage("Images/PillarGreen.png","Green Pillar");
        temple1.getPillar().setPillarImage("Images/PillarYellow.png","Yellow Pillar");
        temple1.getPillar().setPillarImage("Images/PillarBlack.png","Black Pillar");
        temple1.getPillar().setPillarImage("Images/PillarBlue.png","Blue Pillar");
        temple1.getPillar().setStartImage();

        temple2.getPillar().setPillarImage("Images/PillarG.png","G Pillar");
        temple2.getPillar().setPillarImage("Images/PillarH.png","H Pillar");
        temple2.getPillar().setPillarImage("Images/PillarI.png","I Pillar");
        temple2.getPillar().setPillarImage("Images/PillarJ.png","J Pillar");
        temple2.getPillar().setPillarImage("Images/PillarK.png","K Pillar");
        temple2.getPillar().setStartImage();

        //Add Coins to rooms, requires the amount
        cave1.addCoins(5);
        templeSide.addCoins(5);

        //Add a character to a room
        //Requires a name, description, ID number, gold req + item req
        campfire.addCharacter("MAN", "trades water for gold", 301, 5, 104);
        templeGardens.addCharacter("DOG", "trades water for gold", 302, 5, 104);
        tradingCaravan.addCharacter("SHOPKEEPER", "trades gold for machette", 303, 10, 107);
        tradingCaravan.getCharacter().addItem("MACHETTE", "A large knife for chopping through the jungle", 107);
        guard.addCharacter("GUARD", "trades gold for entrance", 304, 10, 108);   

    }

    /**
     * Method to print help information to the UI.
     * @return String
     */ 
    protected String printHelp2()
    {
        //Adds the help text to a String variable then returns it to the UI
        String x = "\n-------------------- HELP --------------------\n";
        x = x + "\n     ***       You need to escape the jungle        ***\n";
        x = x + "     ***        There are three keys to find           ***\n";
        x = x + "     ***********************************************\n\n";
        x = x + "     ***       Use the MOVE panel to navigate     ***\n";
        x = x + "     ***   LOOK, TAKE, USE, TRADE to interact  ***\n";
        x = x + "     ***********************************************\n\n";
        x = x + "                          Good Luck!!!\n";
        return x;
    }

    /**
     * Method for movement in the game.
     * Requires a direction, then checks if movement is possible and carries out.
     * @param String direction : the intended direction
     * @return void
     */
    protected void goRoom2(String direction)
    {
        //Checks if the desired new room has a lock on it
        String lock = player.getCurrentRoom().getLock(direction);

        //If locked, sets an error number and end the method
        if(lock == "locked"){
            setErrorNumber(3);
            return;
        }

        //Loads the intended next room
        Room nextRoom = player.getCurrentRoom().getExit(direction);

        //If there is no room that direction, sets an error number
        if (nextRoom == null ) {
            setErrorNumber(4);
            return;
        }
        //If movement is possible, moves the player into the desired room
        else {
            player.setCurrentRoom(nextRoom);
        }
    }

    /**
     * Method for the take command.
     * Finds the item in the players current room.
     * Adds the item to players inventory and then lists them it as 'collected'.
     * Similar process for coins in a room.
     * Prints message if no item/coins to take.
     * @return void
     */
    protected void take()
    {
        //Current room Item or Coins
        Item thisItem = player.getCurrentRoom().getCurrentItem();
        Coins theseCoins = player.getCurrentRoom().getCurrentCoins();
        //Current room ID
        int currentRoomID = player.getCurrentRoom().getRoomID();

        //If there is an uncollected Item, adds to inventory
        //Updates room descriptions and collection status
        if (thisItem != null && thisItem.isItemCollected() == false) {
            player.addItem(thisItem);
            thisItem.itemCollected(true);
        }
        //If there are uncollected Coins, adds to player coins and set as collected
        else if(theseCoins != null && theseCoins.areCoinsCollected() == false)
        {
            player.addGold(theseCoins.getAmount()); 
            theseCoins.setCoinsCollected();
        }
        //If there is nothing to take, sets an error number
        else {
            setErrorNumber(1);
        }
    }

    /**
     * Method for the trade command.
     * Interacts with a character in the game, carrying out a trade of somesort.
     * @return void
     */
    protected void trade()
    {
        //Loads the character from the current room
        Character thisChar = player.getCurrentRoom().getCharacter();

        //Sends error if no character
        if(thisChar == null){
            setErrorNumber(6);
            return;
        }

        //Loads character details from the current room
        int thisCharID = thisChar.getID();
        boolean completed = thisChar.isCompleted();
        int thisCharGold = thisChar.getGold();
        int thisCharItemID = thisChar.getItemID();
        Item thisCharItem = thisChar.getCurrentItem();

        //Checks if a player owns a characters item requirement
        boolean itemOwned = player.searchInventory(thisCharItemID);

        if(thisChar != null && completed == false)
        {
            //MAN - trades water for gold
            if(thisCharID == 301 && itemOwned == true){
                //Updates room description, trades item for coins, sets as completed
                player.getCurrentRoom().setFullDescription("     A weary traveller rests beside an open fire\n     He thanks you for the help and wishes you well on your travels");
                player.addGold(thisCharGold);
                player.deleteItem(thisCharItemID);
                thisChar.setCompleted();
            }
            //DOG - trades water for gold
            else if(thisCharID == 302 && itemOwned == true){
                //Updates room description, trades item for coins, sets as completed
                player.getCurrentRoom().setFullDescription("     The gardens here are so colourful and healthy, they are clearly well looked after\n     The dog wags his tail with excitement and comes to say hello\n     He seems much happier with some water (and without a bag of coins around his neck)");
                player.addGold(thisCharGold);
                player.deleteItem(thisCharItemID);
                thisChar.setCompleted();
            }
            //SHOPKEEPER - trades gold for machette
            else if(thisCharID == 303 && player.checkGold() >= thisCharGold){
                //Updates room description, trades hgold for item, sets as completed
                player.getCurrentRoom().setFullDescription("     A trading caravan has been set up amongst the trees\n     The owner has nothing else he can trade with you right now");
                player.removeGold(thisCharGold);
                player.addItem(thisCharItem);
                thisCharItem.itemCollected(true);
                thisChar.setCompleted();
            }
            //GUARD - trades gold to unlock museum door
            else if(thisCharID == 304 && completed == false && player.checkGold() >= thisCharGold){
                //Updates room description, takes gold and unlocks exit, sets as completed
                player.removeGold(thisCharGold);
                player.getCurrentRoom().setFullDescription("     A guard stands in the way of the door\n     You have already bribed him for entry");
                player.getCurrentRoom().setLock("east", "unlocked");
                thisChar.setCompleted();
            }
            //Sends error if requirements not met
            else{
                setErrorNumber(6);
            }
        }

        //Sends error if character completed
        if(thisChar != null && completed == true){
            setErrorNumber(6);
        }

    }

    /**
     * Method for the use command.
     * Boolean fields search a players inventory to return true if they have a specific item.
     * If the player is in a specific room (located with an ID) and the requirements met, 
     * it carries out the correct action and updates the game state.
     * @return void
     */
    protected void use()
    {   
        //Searches the players inventory for requirement items
        boolean rope = player.searchInventory(106);
        boolean machette = player.searchInventory(107);
        boolean key1 = player.searchInventory(101);
        boolean key2 = player.searchInventory(102);
        boolean key3 = player.searchInventory(103);

        //Locates the current room ID
        int currentRoomID = player.getCurrentRoom().getRoomID();

        //ROPE - Uses the rope to move down the cave
        if(currentRoomID == 112 && rope == true){
            //Unlocks the exit and moves the player into the room
            Room nextRoom = player.getCurrentRoom().getExit("down");
            nextRoom.setLock("down", "unlocked");
            player.setCurrentRoom(nextRoom);
        }
        //MACHETTE - Uses the machette to move through the jungle
        else if(currentRoomID == 101 && machette == true){
            //Unlocks the exit and moves the player into the room
            Room nextRoom = player.getCurrentRoom().getExit("east");
            nextRoom.setLock("east", "unlocked");
            player.setCurrentRoom(nextRoom);
        }
        //PILLAR OBJECTS - rotates the pillar till the door unlocks
        else if(currentRoomID == 120 || currentRoomID == 121)
        {
            //Checks if the pillar is on the correct face
            boolean correct = player.getCurrentRoom().getPillar().isCorrect();   
            //If the face is not correct, rotate the pillar
            if(correct == false){
                player.getCurrentRoom().getPillar().changeFace();
            }
            //Checks if the pillar is on the correct face
            boolean correct2 = player.getCurrentRoom().getPillar().isCorrect();
            //If the face is correct, unlocks the door to the next room
            if(correct2 == true){
                Room nextRoom = player.getCurrentRoom().getExit("north");
                player.getCurrentRoom().setLock("north", "unlocked");
            }
        }
        //ESCAPE - unlocks the main door to win the game
        else if(currentRoomID == 103 && key1 == true && key2 == true && key3 ==true){
            //Confirms if the player has the 3 keys, then unlocks the exit and moves the player
            Room nextRoom = player.getCurrentRoom().getExit("north");
            player.getCurrentRoom().setLock("north", "unlocked");
            player.setCurrentRoom(nextRoom);
        }
        //Prints an error if no possible interactions
        else{
            setErrorNumber(2);
        }
    }

    /**
     * Method to set the error number for the UI to read.
     * @return void
     */
    protected void setErrorNumber(int number)
    {
        errorNum = number;
    }

    /**
     * Returns the error number of the game state.
     * @return int errorNum : the error number
     */
    protected int errorNumberReturn()
    {
        return errorNum;   
    }

}
