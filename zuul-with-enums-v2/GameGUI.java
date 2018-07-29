import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

/**
 * This class is responsible for creating and running the UI for the game.
 * All the various panels are organised and added to the JFrame.
 * These panels use a combination of buttons, text and images to build the UI.
 * 
 * Although this class is what runs the game, it is not responsible for the game mechanics.
 * It communicates with the 'Game' class to understand how the game state is changing,
 * and it also prompts commands within that class to manipulate the game.
 * 
 * @author (Anthony Wythe) 
 * @version ('Escape' Version 5 - 06/01/2018)
 */
public class GameGUI extends JFrame
{
    Game g1; //Creates an instance of the game class
    private int logCount; //Keeps a track of how often the log is called

    public GameGUI()
    {
        g1 = new Game(); //Initialses the game class
        this.setPreferredSize(new Dimension(1280,1024)); //Sets window size
        setup(); //Runs the setup for the GUI
        logCount=1; //Initlisaes the log count field
        
        //This creates the inital player log text file, throws an exception if this is not possible
        try{writeStringToFile("Player Log: ");}catch(IOException e){System.out.println("Couldn't write to or create log file");}
    }

    /**
     * Method that creates an image icon.
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
     * Sets up the GUI for the game.
     * Responsible for many things and is broken down into further sections with commenting.
     * @return void
     */
    public void setup()
    {
        /**
         * CONTAINERS:
         * This is where the main 'containers' (JPanels) are set.
         * They use a combination of X/Y box layouts to add everything together.
         */
        //MAIN CONTAINER, stores the left/right main panels in an X axis
        JPanel containerMain = new JPanel();
        containerMain.setLayout(new BoxLayout(containerMain, BoxLayout.X_AXIS));
        containerMain.setBorder(BorderFactory.createLineBorder(Color.black));
        Container contentPane = this.getContentPane();

        //LEFT SIDE CONTAINER, stores the left main panels in a Y axis
        JPanel containerLeft = new JPanel();
        containerLeft.setBorder(BorderFactory.createLineBorder(Color.black));
        containerLeft.setLayout(new BoxLayout(containerLeft, BoxLayout.Y_AXIS));

        //TOP LEFT CONTAINER, stores the inv/help text area and menu buttons in an X axis
        JPanel containerTopLeft = new JPanel();
        containerTopLeft.setBorder(BorderFactory.createLineBorder(Color.black));
        containerTopLeft.setLayout(new BoxLayout(containerTopLeft, BoxLayout.X_AXIS));

        //BOTTOM LEFT CONTAINER, stores the main interaction hubs in an X axis
        JPanel containerBtmLeft = new JPanel();
        containerBtmLeft.setBorder(BorderFactory.createLineBorder(Color.black));
        containerBtmLeft.setLayout(new BoxLayout(containerBtmLeft, BoxLayout.X_AXIS));

        //BOTTOM LEFT CONTAINER 2,
        //Stores the messages hub, items hub and command hub in a Y Axis
        JPanel containerBtmLeft2 = new JPanel();
        containerBtmLeft2.setBorder(BorderFactory.createLineBorder(Color.black));
        containerBtmLeft2.setLayout(new BoxLayout(containerBtmLeft2, BoxLayout.Y_AXIS));

        /**
         * MAIN PANELS:
         * This is where the main panels are created.
         * They each slot into the various containers listed above.
         * All the panels use a GridBagLayout to store components.
         */
        //BUTTON HUB, panel for the interaction buttons (look, take, trade, use)
        JPanel buttonHub = new JPanel(new GridBagLayout());
        buttonHub.setBackground(new Color(176, 136, 94));

        //MOVEMENT HUB, panel for the movement buttons (north, east, south, west, up, down)
        JPanel movementHub = new JPanel(new GridBagLayout());
        movementHub.setBackground(new Color(176, 136, 94));
        movementHub.setBorder(BorderFactory.createLineBorder(Color.black));

        //MESSAGE HUB, panel for the message areas (actions, errors)
        JPanel messageHub = new JPanel(new GridBagLayout());
        messageHub.setBackground(new Color(176, 136, 94));

        //ITEM HUB, panel that displays item images when they are in a room
        JPanel itemHub = new JPanel(new GridBagLayout());
        itemHub.setBackground(new Color(176, 136, 94));

        //TERMINAL, panel for the room view/map view and the room description
        JPanel terminal = new JPanel(new GridBagLayout());
        terminal.setBackground(new Color(154, 107, 75));
        terminal.setBorder(BorderFactory.createLineBorder(Color.black));

        //INVENTORY/HELP DISPLAY, panel that stores the text area for the inventory/help
        JPanel invMapPanel = new JPanel(new GridBagLayout());
        invMapPanel.setBackground(new Color(154, 107, 75));

        //MENU HUB, panel to store the menu buttons (inventory, map, help, quit)
        JPanel menuButtons = new JPanel(new GridBagLayout());
        menuButtons.setBackground(new Color(154, 107, 75));

        /**
         * IMAGE SETUP:
         * Initilises the images in the game.
         * Creates the labels for the images to be added to.
         */
        //-----IMAGES-----
        //Non changing images (map, coins, title, compass)
        ImageIcon mapImage = createImageIcon("Images/EscapeMap.png", "escapeMap");
        ImageIcon coinImage = createImageIcon("Images/Coins.jpg", "Coins");
        ImageIcon titleImage = createImageIcon("Images/EscapeTitle.png", "Title");
        ImageIcon compassImage = createImageIcon("Images/Compass.jpg", "Compass");

        //Dynamic images (room, item, pillar)
        //Images that don't appear on the starting room set to 'null' to avoid error
        ImageIcon viewImage = g1.player.getCurrentRoom().returnRoomImage();
        ImageIcon itemImage = null;
        ImageIcon pillarImage = null;

        //-----LABELS-----
        //Creates a label for each of the image icons
        JLabel mapPic = new JLabel("", mapImage, JLabel.CENTER);
        JLabel viewPic = new JLabel("", viewImage, JLabel.CENTER);
        JLabel itemPic = new JLabel("", itemImage, JLabel.CENTER);
        JLabel coinsPic = new JLabel("", coinImage, JLabel.CENTER);
        JLabel pillarPic = new JLabel("", pillarImage, JLabel.CENTER);
        JLabel titlePic = new JLabel("", titleImage, JLabel.CENTER);
        JLabel compassPic = new JLabel("", compassImage, JLabel.CENTER);

        //GridBagConstraints setup
        GridBagConstraints c = new GridBagConstraints();

        /**
         * TERMINAL:
         * This is the largest panel in the game and takes up all of the right side.
         * It has an image view area, that changes between the map and the room view.
         * It also has a text area to print the room descriptions.
         */
        //TERMINAL TEXT
        //Initialises a text area and sets to uneditable
        final JTextArea roomDescription = new JTextArea("");
        roomDescription.setEditable(false);
        //Set the position information
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 1;
        //Sets the terminal text as the current room information
        roomDescription.setText(g1.player.getCurrentRoom().getLongDescription());
        //Sets the background and border
        roomDescription.setBackground(new Color (245, 193, 118));
        roomDescription.setBorder(BorderFactory.createLoweredBevelBorder());
        //Adds to the terminal
        terminal.add(roomDescription,c);

        //TERMINAL VIEW
        //Sets the position information
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        //Adds the current room view to be displayed as the game opens
        terminal.add(viewPic, c);

        /**
         * INVENTORY/HELP DISPLAY + MENU HUB:
         * Text area that displays either the inventory or the help information.
         * Hub of menu buttons (inventory, map, help, quit)
         */
        //-----INVENTORY/HELP DISPLAY-----
        //Sets up an uneditable text area that initally prints the inventory
        final JTextArea invHelp = new JTextArea(g1.player.printInventory());
        invHelp.setEditable(false);
        //Sets up the position, background and border
        c.fill = GridBagConstraints.NONE;
        c.gridx = 0;
        c.gridy = 1;
        invHelp.setBackground(new Color (245, 193, 118));
        invHelp.setBorder(BorderFactory.createLoweredBevelBorder());
        //Add to the appropriate panel
        invMapPanel.add(invHelp,c);
        //Set position and add the title image
        c.gridy = 0;
        invMapPanel.add(titlePic, c);

        //-----MENU HUB-----
        //'HELP' BUTTON SETUP
        //Initialise the help button and its position
        final JButton HELP = new JButton("HELP");
        c.fill = GridBagConstraints.NONE;
        c.gridx = 0;
        c.gridy = 1;
        //Action listener for the button
        HELP.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) 
                {
                    try{addStringToFile("Help button pressed");}catch(IOException ev){System.out.println("Couldn't write to log file");}
                    //Resets the display box and prints the help information
                    invHelp.setText("");
                    invHelp.setText(g1.printHelp2());

                }
            });
        //Add to the appropriate panel
        menuButtons.add(HELP, c);

        //'INVENTORY' BUTTON SETUP
        //Initialise the inventory button and its position
        JButton INV = new JButton("INVENTORY");
        c.fill = GridBagConstraints.NONE;
        c.gridx = 0;
        c.gridy = 0;
        //Action listener for the button
        INV.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) 
                {
                    //Updates the player log
                    try{addStringToFile("Inventory button pressed");}catch(IOException ev){System.out.println("Couldn't write to log file");}
                    
                    //Resets the display box and prints the inventory information
                    invHelp.setText("");
                    invHelp.setText(g1.player.printInventory());

                }
            });
        //Add to the appropriate panel
        menuButtons.add(INV,c);

        //'QUIT' BUTTON SETUP
        //Initialise the inventory button and its position
        JButton QUIT = new JButton("QUIT");
        c.fill = GridBagConstraints.NONE;
        c.gridx = 1;
        c.gridy = 1;
        //Action listener for the button
        QUIT.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) 
                {
                    //Updates player log
                    try{addStringToFile("Player quits the game");}catch(IOException ev){System.out.println("Couldn't write to log file");}
                    
                    //Shuts the system down
                    System.exit(0);
                }
            });
        //Add to the appropriate panel
        menuButtons.add(QUIT,c);

        //'MAP' BUTTON SETUP
        //Initialise the map button and its position
        final JButton MAP = new JButton("MAP");
        c.fill = GridBagConstraints.NONE;
        c.gridx = 1;
        c.gridy = 0;
        //Action listener for the button
        MAP.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) 
                {
                    //Updates player log
                    try{addStringToFile("Map button pressed");}catch(IOException ev){System.out.println("Couldn't write to log file");}
                    
                    //Clears the terminal view
                    terminal.remove(viewPic);
                    updateMe();

                    //Sets image position
                    c.fill = GridBagConstraints.NONE;
                    c.gridx = 0;
                    c.gridy = 0;

                    //Adds the map image to the terminal
                    terminal.add(mapPic, c);
                    updateMe();

                }
            });
        //Adds to the appropriate panel
        menuButtons.add(MAP,c);

        /**
         * MESSAGE HUB + COMMAND HUB:
         * This is the area with the main interaction buttons.
         * 'Look, Take, Use, Trade'.
         * It also includes the error/action terminals,
         * as well as the item/coin view.
         */
        //ACTION TERMINAL
        //Sets up an uneditable text area that prints the action terminal
        final JTextArea actionM = new JTextArea("|>|>|>|>|> Action terminal <|<|<|<|<|");
        actionM.setEditable(false);
        //Sets up the position, background and border
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        actionM.setBackground(new Color (245, 193, 118));
        actionM.setBorder(BorderFactory.createLoweredBevelBorder());
        //Adds to the appropriate pane
        messageHub.add(actionM, c);

        //ERROR TERMINAL
        //Sets up an uneditable text area that prints the error terminal
        final JTextArea errorM = new JTextArea("|>|>|>|>|>  Error terminal  <|<|<|<|<|");
        errorM.setEditable(false);
        //Sets up the position, background and border
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        errorM.setBackground(new Color (143,188,143));
        errorM.setBorder(BorderFactory.createLoweredBevelBorder());
        //Adds to the appropriate panel
        messageHub.add(errorM, c);

        //'LOOK' BUTTON SETUP
        //Initialise the 'look' button and its position
        JButton LOOK = new JButton("LOOK");
        c.fill = GridBagConstraints.NONE;
        c.gridx = 1;
        c.gridy = 1;
        //Action listener for the button
        LOOK.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) 
                {
                    //Updates player log
                    try{addStringToFile("Look command");}catch(IOException ev){System.out.println("Couldn't write to log file");}
                    
                    //Set the full room description
                    roomDescription.setText("");
                    roomDescription.setText(g1.player.getCurrentRoom().getFullDescription());

                    //Refresh the terminal view
                    terminal.remove(viewPic);
                    terminal.remove(mapPic);
                    terminal.remove(pillarPic);
                    updateMe();

                    //Set position details
                    c.fill = GridBagConstraints.NONE;
                    c.gridx = 0;
                    c.gridy = 0;

                    //Load the coins or item in a room
                    Coins coins = g1.player.getCurrentRoom().getCurrentCoins(); 
                    Item item = g1.player.getCurrentRoom().getCurrentItem();
                    Pillar pillar = g1.player.getCurrentRoom().getPillar();

                    //If there uncollected coins, add the image to the itemhub
                    if(coins != null && coins.areCoinsCollected() == false)
                    {
                        itemHub.add(coinsPic, c);
                        updateMe();
                    }
                    //If there is an uncollected item, locate the image and add to itemhub
                    else if(item != null && item.isItemCollected() == false)
                    {
                        itemPic.setIcon(item.returnItemImage());
                        itemHub.add(itemPic, c);
                        updateMe();
                    }
                    //If there is a pillar object, locate the image and add to the terminal view
                    else if(pillar !=null){
                        pillarPic.setIcon(pillar.returnPillarImage());
                        terminal.add(pillarPic, c);
                        updateMe();
                    }

                    //Locate the current room image and add to the terminal view
                    viewPic.setIcon(g1.player.getCurrentRoom().returnRoomImage());
                    terminal.add(viewPic, c);
                    updateMe();

                }
            });
        //Add to the appropriate panel
        buttonHub.add(LOOK, c);

        //'TAKE' BUTTON SETUP
        //Initialise the 'take' button and its location
        JButton TAKE = new JButton("TAKE");
        c.fill = GridBagConstraints.NONE;
        c.gridx = 2;
        c.gridy = 1;
        //Action listener for the buttono
        TAKE.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) 
                {
                    //Updates player log
                    try{addStringToFile("Take command");}catch(IOException ev){System.out.println("Couldn't write to log file");}
                    
                    //Run the take command in the game
                    g1.take();

                    //Check for error, return and refresh if found
                    if(g1.errorNumberReturn() != 0){
                        errorM.setText("");
                        errorM.setText(runErrorHandler());
                        g1.setErrorNumber(0);
                        return;
                    }

                    //Remove item or coin picture
                    itemHub.remove(coinsPic);
                    itemHub.remove(itemPic);
                    updateMe();

                    //Update inventory
                    invHelp.setText("");
                    invHelp.setText(g1.player.printInventory());

                    //Update action message
                    actionM.setText("");
                    actionM.setText("|>|>|>   You found something    <|<|<|");

                }
            });
        //Adds to the appropriate panel
        buttonHub.add(TAKE, c);

        //'USE' BUTTON SETUP
        //Initialise the 'use' button and its location
        JButton USE = new JButton("USE");
        c.fill = GridBagConstraints.NONE;
        c.gridx = 2;
        c.gridy = 2;
        //Action listener for the button
        USE.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) 
                {
                    //Updates player log
                    try{addStringToFile("Use command");}catch(IOException ev){System.out.println("Couldn't write to log file");}
                    
                    //Run the use command in the game
                    g1.use();

                    //Check for error, return and refresh if found
                    if(g1.errorNumberReturn() != 0){
                        errorM.setText("");
                        errorM.setText(runErrorHandler());
                        g1.setErrorNumber(0);
                        return;
                    }

                    //Refresh the image view
                    terminal.remove(viewPic);
                    terminal.remove(mapPic);
                    updateMe();

                    //Update room description
                    roomDescription.setText(g1.player.getCurrentRoom().getLongDescription());

                    //Set position details
                    c.fill = GridBagConstraints.NONE;
                    c.gridx = 0;
                    c.gridy = 0;

                    //Locates the current rooms pillar
                    Pillar pillar = g1.player.getCurrentRoom().getPillar();

                    //If there is a pillar, locate the image and add to the terminal view
                    if(pillar !=null){
                        pillarPic.setIcon(pillar.returnPillarImage());
                        terminal.add(pillarPic, c);
                        updateMe();
                        //Adds full description for current room
                        roomDescription.setText(g1.player.getCurrentRoom().getFullDescription());
                    }

                    //Notify the player when the pillar is on the correct face
                    if(pillar !=null && pillar.isCorrect() == true){
                        actionM.setText("|>|>|>  The door is unlocked!  <|<|<|");
                    }

                    //Update action message for non-pillar interactions
                    if(pillar == null){
                        actionM.setText("|>|>|>    You used something    <|<|<|");
                    }

                    //Update error message
                    errorM.setText("|>|>|>|>|>  Error terminal  <|<|<|<|<|");

                    //Locate the current room image and add to terminal view
                    viewPic.setIcon(g1.player.getCurrentRoom().returnRoomImage());
                    terminal.add(viewPic, c);
                    updateMe();
                }
            });
        buttonHub.add(USE, c);

        //'TRADE' BUTTON SETUP
        //Initialise the 'trade' button and its location
        final JButton TRADE = new JButton("TRADE");
        c.fill = GridBagConstraints.NONE;
        c.gridx = 1;
        c.gridy = 2;
        //Action listener for the button
        TRADE.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) 
                {
                    //Updates player log
                    try{addStringToFile("Trade command");}catch(IOException ev){System.out.println("Couldn't write to log file");}
                    
                    //Run the trade command in the game
                    g1.trade();

                    //Check for error, return and refresh if found
                    if(g1.errorNumberReturn() != 0){
                        errorM.setText("");
                        errorM.setText(runErrorHandler());
                        g1.setErrorNumber(0);
                        return;
                    }

                    //Update inventory
                    invHelp.setText("");
                    invHelp.setText(g1.player.printInventory());

                    //Update room description
                    roomDescription.setText("");
                    roomDescription.setText(g1.player.getCurrentRoom().getFullDescription());

                    //Update action message
                    actionM.setText("");
                    actionM.setText("|>|>|>   You traded something   <|<|<|");
                }
            });
        //Add to the appropriate panel
        buttonHub.add(TRADE,c);

        /**
         * MOVEMENT HUB:
         * These are where all the movement buttons are located.
         * The player can go 'north, east, south, west, up and down'.
         * All the buttons have the same code except for their direction input,
         * unfortunately they can't be shortened because of the way they communicate with the UI.
         */
        //Set position and add compass image
        c.gridx = 1;
        c.gridy = 2;
        movementHub.add(compassPic, c);

        //'NORTH' BUTTON SETUP
        //Initialise the 'north' button its location
        final JButton NORTH = new JButton("NORTH");
        c.fill = GridBagConstraints.NONE;
        c.gridx = 1;
        c.gridy = 1;
        //Action listener for the button
        NORTH.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) 
                {
                    //Updates player log
                    try{addStringToFile("Moves north");}catch(IOException ev){System.out.println("Couldn't write to log file");}
                    
                    //Runs the command and checks for errors
                    g1.goRoom2("north");
                    if(g1.errorNumberReturn() != 0){
                        errorM.setText("");
                        errorM.setText(runErrorHandler());
                        g1.setErrorNumber(0);
                        return;
                    }

                    //Refresh the terminal view
                    terminal.remove(viewPic);
                    terminal.remove(pillarPic);
                    updateMe();

                    //Update room description
                    roomDescription.setText(g1.player.getCurrentRoom().getLongDescription());

                    //Add the map image to the terminal view
                    viewPic.setIcon(mapImage);
                    c.gridx = 0;
                    c.gridy = 0;
                    terminal.add(mapPic, c);
                    updateMe();

                    //Resets the message terminals
                    actionM.setText("|>|>|>|>|> Action terminal <|<|<|<|<|");
                    errorM.setText("|>|>|>|>|>  Error terminal  <|<|<|<|<|");
                }
            });
        //Adds to the appropriate panel
        movementHub.add(NORTH,c);

        //'EAST' BUTTON SETUP
        //Initialise the 'east' button and its location
        final JButton EAST = new JButton("EAST");
        c.fill = GridBagConstraints.NONE;
        c.gridx = 2;
        c.gridy = 2;
        //Action listener for the button
        EAST.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) 
                {
                    //Updates player log
                    try{addStringToFile("Moves east");}catch(IOException ev){System.out.println("Couldn't write to log file");}
                    
                    //Runs the command and checks for errors
                    g1.goRoom2("east");
                    if(g1.errorNumberReturn() != 0){
                        errorM.setText("");
                        errorM.setText(runErrorHandler());
                        g1.setErrorNumber(0);
                        return;
                    }

                    //Refresh the terminal view
                    terminal.remove(viewPic);
                    terminal.remove(pillarPic);
                    updateMe();

                    //Update room description
                    roomDescription.setText(g1.player.getCurrentRoom().getLongDescription());

                    //Add the map image to the terminal view
                    viewPic.setIcon(mapImage);
                    c.gridx = 0;
                    c.gridy = 0;
                    terminal.add(mapPic, c);
                    updateMe();

                    //Resets the message terminals
                    actionM.setText("|>|>|>|>|> Action terminal <|<|<|<|<|");
                    errorM.setText("|>|>|>|>|>  Error terminal  <|<|<|<|<|");
                }
            });
        //Adds to the appropriate panel
        movementHub.add(EAST,c);

        //'SOUTH' BUTTON SETUP
        //Initialise the 'south' button and its location
        final JButton SOUTH = new JButton("SOUTH");
        c.fill = GridBagConstraints.NONE;
        c.gridx = 1;
        c.gridy = 3;
        //Action listener for the button
        SOUTH.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) 
                {
                    //Updates player log
                    try{addStringToFile("Moves south");}catch(IOException ev){System.out.println("Couldn't write to log file");}
                    
                    //Runs the command and checks for errors
                    g1.goRoom2("south");
                    if(g1.errorNumberReturn() != 0){
                        errorM.setText("");
                        errorM.setText(runErrorHandler());
                        g1.setErrorNumber(0);
                        return;
                    }

                    //Refresh the terminal view
                    terminal.remove(viewPic);
                    terminal.remove(pillarPic);
                    updateMe();

                    //Update the room desccription
                    roomDescription.setText(g1.player.getCurrentRoom().getLongDescription());

                    //Add the map image to the terminal view
                    viewPic.setIcon(mapImage);
                    c.gridx = 0;
                    c.gridy = 0;
                    terminal.add(mapPic, c);
                    updateMe();

                    //Resets the message terminals
                    actionM.setText("|>|>|>|>|> Action terminal <|<|<|<|<|");
                    errorM.setText("|>|>|>|>|>  Error terminal  <|<|<|<|<|");
                }
            });
        //Adds to the appropriate panel
        movementHub.add(SOUTH,c);

        //'WEST' BUTTON SETUP
        //Initialise the 'west' button and its location
        final JButton WEST = new JButton("WEST");
        c.fill = GridBagConstraints.NONE;
        c.gridx = 0;
        c.gridy = 2;
        //Action listener for the button
        WEST.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) 
                {
                    //Updates player log
                    try{addStringToFile("Moves west");}catch(IOException ev){System.out.println("Couldn't write to log file");}
                    
                    //Runs the command and checks for errors
                    g1.goRoom2("west");
                    if(g1.errorNumberReturn() != 0){
                        errorM.setText("");
                        errorM.setText(runErrorHandler());
                        g1.setErrorNumber(0);
                        return;
                    }

                    //Refresh the terminal view
                    terminal.remove(viewPic);
                    terminal.remove(pillarPic);
                    updateMe();

                    //Update the room description
                    roomDescription.setText(g1.player.getCurrentRoom().getLongDescription());

                    //Add the map image to the terminal view
                    viewPic.setIcon(mapImage);
                    c.gridx = 0;
                    c.gridy = 0;
                    terminal.add(mapPic, c);
                    updateMe();

                    //Resets the message terminals
                    actionM.setText("|>|>|>|>|> Action terminal <|<|<|<|<|");
                    errorM.setText("|>|>|>|>|>  Error terminal  <|<|<|<|<|");
                }
            });
        //Adds to the appropriate panel
        movementHub.add(WEST,c);

        //'UP' BUTTON SETUP
        //Initialise the 'up' button and its location
        final JButton UP = new JButton("UP");
        c.fill = GridBagConstraints.NONE;
        c.gridx = 1;
        c.gridy = 0;
        //Action listener for the button
        UP.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) 
                {
                    //Updates player log
                    try{addStringToFile("Moves up");}catch(IOException ev){System.out.println("Couldn't write to log file");}
                    
                    //Runs the command and checks for errors
                    g1.goRoom2("up");
                    if(g1.errorNumberReturn() != 0){
                        errorM.setText("");
                        errorM.setText(runErrorHandler());
                        g1.setErrorNumber(0);
                        return;
                    }

                    //Refresh the terminal view
                    terminal.remove(viewPic);
                    terminal.remove(pillarPic);
                    updateMe();

                    //Update the room description
                    roomDescription.setText(g1.player.getCurrentRoom().getLongDescription());

                    //Adds the map image to the terminal view
                    viewPic.setIcon(mapImage);
                    c.gridx = 0;
                    c.gridy = 0;
                    terminal.add(mapPic, c);
                    updateMe();

                    //Resets the message terminals
                    actionM.setText("|>|>|>|>|> Action terminal <|<|<|<|<|");
                    errorM.setText("|>|>|>|>|>  Error terminal  <|<|<|<|<|");
                }
            });
        //Adds to the appropriate panel
        movementHub.add(UP,c);

        //'DOWN' BUTTON SETUP
        //Initialises the 'down' button and its location
        final JButton DOWN = new JButton("DOWN");
        c.fill = GridBagConstraints.NONE;
        c.gridx = 1;
        c.gridy = 4;
        //Action listener for the button
        DOWN.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) 
                {
                    //Updates player log
                    try{addStringToFile("Moves down");}catch(IOException ev){System.out.println("Couldn't write to log file");}
                    
                    //Runs the command and checks for errors
                    g1.goRoom2("down");
                    if(g1.errorNumberReturn() != 0){
                        errorM.setText("");
                        errorM.setText(runErrorHandler());
                        g1.setErrorNumber(0);
                        return;
                    }

                    //Refresh the terminal view
                    terminal.remove(viewPic);
                    terminal.remove(pillarPic);
                    updateMe();

                    //Update the room description
                    roomDescription.setText(g1.player.getCurrentRoom().getLongDescription());

                    //Add the map image to the terminal view
                    viewPic.setIcon(mapImage);
                    c.gridx = 0;
                    c.gridy = 0;
                    terminal.add(mapPic, c);
                    updateMe();

                    //Resets the message terminals
                    actionM.setText("|>|>|>|>|> Action terminal <|<|<|<|<|");
                    errorM.setText("|>|>|>|>|>  Error terminal  <|<|<|<|<|");
                }
            });
        //Adds to the appropriate panel
        movementHub.add(DOWN,c);

        /**
         * FINALISE PANELS:
         * This section adds all of the panels to their containers.
         * And then adds the containers into each other to complete the UI.
         */
        //Creates the 'top left' container and adds to the 'left main' container
        containerTopLeft.add(invMapPanel);
        containerTopLeft.add(menuButtons);
        containerLeft.add(containerTopLeft);

        //Creates the '2nd bottom left' container
        containerBtmLeft2.add(messageHub);
        containerBtmLeft2.add(itemHub);
        containerBtmLeft2.add(buttonHub);

        //Creates the 'bottom left' container and adds to the 'left main' container
        containerBtmLeft.add(containerBtmLeft2);
        containerBtmLeft.add(movementHub);
        containerLeft.add(containerBtmLeft);

        //Adds the main left and right panels to the 'main' container
        containerMain.add(containerLeft);
        containerMain.add(terminal);

        //Finalises the UI
        this.add(containerMain);
        this.pack();
        this.setVisible(true);

    }

    /**
     * Method to run the error handler.
     * Checks the games current error number return.
     * Returns the appropriate string for that specific error.
     * @void String
     */
    public String runErrorHandler()
    {
        //The string variable to be returned
        String errorText = "";

        //Runs through the error numbers to set the correct string
        if(g1.errorNumberReturn() == 0){
            errorText = "|>|>|>|>|>  Error terminal  <|<|<|<|<|";
        }
        else if(g1.errorNumberReturn() == 1){
            errorText = "|>|>|> There is nothing to take <|<|<|";
        }
        else if(g1.errorNumberReturn() == 2){
            errorText = "|>|>|>  There is nothing to use  <|<|<|";
        }
        else if(g1.errorNumberReturn() == 3)
        {
            errorText = "|>|>   You need to use something   <|<|";
        }
        else if(g1.errorNumberReturn() == 4)
        {
            errorText = "|>|>|>    You can't go that way    <|<|<|";
        }
        else if(g1.errorNumberReturn() == 6)
        {
            errorText = "|>|>|>  There is nothing to trade  <|<|<|";
        }

        //Returns the error string to be printed in the UI
        return errorText;
    }

    /**
     * Method that updates the UI.
     * Called when changes to the images are made.
     * @return void
     */
    public void updateMe()
    {
        //Resets the UI
        GameGUI.this.invalidate();
        GameGUI.this.validate();
        GameGUI.this.repaint();
    }

    /**
     * This method creates an external text file for the player log.
     * @param String str : the text to be added
     * @return void
     * @throws IOException
     */
    public void writeStringToFile(String str) 
    throws IOException {
        //Initialise writers
        BufferedWriter writer = new BufferedWriter(new FileWriter("PlayerLog.txt"));
        //Add text
        writer.write(str);
        writer.write(System.getProperty("line.separator"));
        writer.close();
    }

    /**
     * This method adds text to the already created text file for the player log.
     * @param String str : the text to be added
     * @return void
     * @throws IOException
     */
    public void addStringToFile(String str) 
    throws IOException {
        //Initialise writers
        BufferedWriter writer = new BufferedWriter(new FileWriter("PlayerLog.txt", true));
        //Add text with counter
        writer.append(System.getProperty("line.separator"));
        writer.append(logCount+"- "+str);
        writer.close();
        logCount++;
    }

}
