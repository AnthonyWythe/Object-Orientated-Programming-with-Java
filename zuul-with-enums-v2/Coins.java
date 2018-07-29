import javax.swing.*;

/**
 * Creates collectable coins to be placed around the map.
 * Requires an amount, and a boolean to stop it being collected more than once.
 *
 * @author (Anthony Wythe)
 * @version (Escape V2)
 */
public class Coins
{
    private int amount; //The amount of coins
    private boolean coinsCollected = false; //Whether they have been collected before
    
    /**
     * Constructor, requires an amount to be set.
     * @param int amount : the amount of coins
     */
    public Coins(int amount)
    {
        this.amount = amount;
    }

    /**
     * Returns the value of the coins.
     * @return int
     */
    public int getAmount()
    {
        return amount;
    }

    /**
     * Returns whether coins have been collected yet.
     * @return boolean
     */
    public boolean areCoinsCollected()
    {
        return coinsCollected;
    }

    /**
     * Sets the coins as collected.
     * @return void
     */
    public void setCoinsCollected()
    {
        coinsCollected = true;
    }

    /**
     * Method to set the value of the coins.
     * @param int amount : the amount of coins
     * @return void
     */
    public void setCoinAmount(int amount)
    {
        amount = amount;
    }
    
}
