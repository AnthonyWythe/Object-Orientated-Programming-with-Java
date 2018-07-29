import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This is for testing the pillar objects in the game.
 * It sets the pillar up and then rotates through the faces.
 * Checks are made to ensure it is working properly.
 *
 * @author  (Anthony Wythe)
 * @version ('Escape' Version 5 - 06/01/2018)
 */
public class PillarTest
{
    
    Pillar pillar; //Instance of the pillar class
    
    /**
     * Constructor to initialise the pillar object.
     * Adds 5 faces, each with a String and an image.
     * The correct face is set as the fifth one.
     */
    public PillarTest()
    {
        //Creates a new pillar, the correct face is set as 'Black'
        pillar = new Pillar("Red","Green","Blue","Yellow","Black", 4);

        //Adds an image to each pillar face
        pillar.setPillarImage("Images/RedPillar.png","Red");
        pillar.setPillarImage("Images/GreenPillar.png","Green");
        pillar.setPillarImage("Images/BluePillar.png", "Blue");
        pillar.setPillarImage("Images/YellowPillar.png", "Yellow");
        pillar.setPillarImage("Images/BlackPillar.png", "Black");
    }

    /**
     * This runs through the different faces of the pillar.
     * As each face changes, checks are made to ensure it is working as it should.
     * @return void
     */
    @Test
    public void rotationTest()
    {
        //Checks the pillar is on the correct starting face
        assertEquals(pillar.getFace(), "Red");
        //Checks that the face returns not correct
        assertEquals(pillar.isCorrect(), false);

        //Rotates the pillar
        pillar.changeFace();
        //Checks the pillar has changed to the second face
        assertEquals(pillar.getFace(), "Green");
        //Checks that the face returns not correct
        assertEquals(pillar.isCorrect(), false);

        //Rotates the pillar
        pillar.changeFace();
        //Checks the pillar has changed to the third face
        assertEquals(pillar.getFace(), "Blue");
        //Checks that the face returns not correct
        assertEquals(pillar.isCorrect(), false);

        //Rotates the pillar
        pillar.changeFace();
        //Checks the pillar has changed to the fourth face
        assertEquals(pillar.getFace(), "Yellow");
        //Checks that the face returns not correct
        assertEquals(pillar.isCorrect(), false);

        //Rotates the pillar
        pillar.changeFace();
        //Checks the pillar has changed to the fifth face
        assertEquals(pillar.getFace(), "Black");
        //Checks that the face is now correct
        assertEquals(pillar.isCorrect(), true);

    }
}
