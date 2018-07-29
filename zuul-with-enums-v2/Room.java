import java.util.Set;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.*;

/**
 * The "Room" class represents one location of the map in the game.
 * Each room has a short description, a full description and an ID number.
 * An image can be added to each room as well.
 * 
 * Each room is connected to other rooms via exits.
 * For each existing exit, the room stores a reference to the neighboring room.
 * Exits can be set as 'locked' or 'unlocked'.
 *
 * A room can hold a number of objects in the game; items, pillars, coins and a character.
 * These are stored in ArrayLists (except for 'Character', there may only be one in a room).
 * 
 * @author  (Anthony Wythe)
 * @version ('Escape' Version 5 - 06/12/2018)
 */
public class Room 
{
    private String description; //Field for the short descriptin of a room
    private String fullDescription; //Field for the full description of a room
    private int roomID; //Field for the ID of a room

    private Item item; //Stores an Item object
    private ArrayList<Item> items; //Stores a collection of Items

    private Coins coins; //Stores a Coins object
    private ArrayList<Coins> coinsCollection; //Stores a collection of Coins

    private Character character; //Stores a Character object

    private Pillar pillar; //Stores a Pillar object
    private ArrayList <Pillar> pillars; //Stores a collection of Pillars

    private HashMap<String, Room> exits; //Stores the exits of a room
    private HashMap<String, String> locks; //Stores which exits are locked

    private ImageIcon image; //Stores the rooms image for the UI

    /**
     * Create a room described by its "description". Initially, it has
     * no exits. Also creates the full description, the ArrayLists and HashMaps.
     * @param int roomID: rooms ID numer
     * @param String description: short description of the room 
     * @param String fullDescription: full description of the room
     */
    public Room(int roomID, String description, String fullDescription) 
    {
        this.description = description;
        this.fullDescription = fullDescription;
        this.roomID = roomID;

        //Initialises the ArrayLists and HashMaps
        coinsCollection = new ArrayList<Coins>();
        items = new ArrayList<Item>();
        pillars = new ArrayList<Pillar>();
        exits = new HashMap<String, Room>();
        locks = new HashMap<String, String>();

    }

    /**
     * Returns the rooms ID number.
     * @return int
     */
    public int getRoomID()
    {
        return roomID;
    }

    /**
     * Define an exit from this room.
     * @param String direction: the direction of the exit
     * @param Room neighbor: the room to which the exit leads
     * @return void
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    /**
     * Return a string describing the room's exits, for example.
     * "Exits: north west"
     * @return String
     */
    private String getExitString()
    {
        String returnString = "----->Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit ;
        }
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param String direction : the exit's direction
     * @return Room
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }

    /**
     * Set a lock on a rooms exit.
     * @param String direction: the direction of the exit
     * @param String locked: whether a room is locked or unlocked
     * @return void
     */
    public void setLock(String direction, String locked)
    {
        locks.put(direction, locked);   
    }

    /**
     * Returns whether an exit is locked.
     * @param String direction: the direction to be checked
     * @return String
     */
    public String getLock(String direction)
    {
        return locks.get(direction);
    }

    

    /**
     * Method to change the rooms full description.
     * @param String newDescription: the new full description to be set
     * @return void
     */
    public void setFullDescription(String newDescription)
    {
        fullDescription = newDescription;
    }

    /**
     * Returns a description of the current room.
     * @return String
     */
    public String getLongDescription()
    {
        return "\n------------------------------------" + description + "------------------------------------\n";
    }

    /**
     * Returns a full description of the room, including exits.
     * @return String
     */
    public String getFullDescription()
    {
        return "\n------------------------------------" + description + "-------------------------------------\n\n" + fullDescription + "\n\n" + getExitString() + "\n";
    }

    /**
     * Creates a new item and adds it to the ArrayList for the room.
     * @param String itemName : the items name
     * @param String itemDescription : the items details
     * @return void
     */
    public void addItem(String itemName, String itemDescription, int itemID)
    {
        item = new Item(itemName, itemDescription, itemID);
        items.add(item);
    }

    /**
     * Returns the item in this room.
     * @return Item
     */
    public Item getCurrentItem()
    {
        return item;
    }

    /**
     * Adds coins to a room and stores in an ArrayList of coins.
     * @param int amount : the amount of coins
     * @return void
     */
    public void addCoins(int amount)
    {
        coins = new Coins(amount);
        coinsCollection.add(coins);
    }

    /**
     * Returns the coins in a room.
     * @return Coins
     */
    public Coins getCurrentCoins()
    {
        return coins;
    }

    /**
     * Adds a character object to the room.
     * Main object for the trade interaction.
     * @param String name : the character name
     * @param String description : characters description
     * @param int IDNumber : character ID number
     * @param int gold : characters gold requirement
     * @param int itemID : characters item requirement
     * @return void
     */
    public void addCharacter(String name, String description, int IDNumber, int gold, int itemID)
    {
        character = new Character(name, description, IDNumber, gold, itemID);
    }

    /**
     * Returns the rooms character.
     * @return Character
     */
    public Character getCharacter()
    {
        return character;
    }

    /**
     * Sets a pillar object for the room.
     * Requires 5 String type 'faces' and an int for the correct number.
     * Adds the pillar to the ArrayList.
     * @param String face1, face2, face3, face4, face5: the pillars faces
     * @param int correctFaceN: the correct face number
     * @return void
     */
    public void setPillar(String face1, String face2, String face3, String face4, String face5, int correctFaceN)
    {
        pillar = new Pillar (face1, face2, face3, face4, face5, correctFaceN);
        pillars.add(pillar);
    }

    /**
     * Returns a rooms pillar object
     * @return Pillar
     */
    public Pillar getPillar()
    {
        return pillar;
    }
    
    /**
     * Method that creates an image icon to add to the room.
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
     * Method that triggers the creation of an image and adds it to the room.
     * @param String path : the image file location
     * @param String description : title for the image file
     * @return void
     */
    protected void setRoomImage(String path, String description)
    {
        image = createImageIcon(path, description);
    }
    
    /**
     * Returns the rooms image.
     * @return ImageIcon
     */
    protected ImageIcon returnRoomImage()
    {
        return image;
    }
}

