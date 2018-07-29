import java.util.ArrayList;
import javax.swing.*;

/**
 * The pillar class creates a puzzle object that locks a room.
 * It needs 5 String parameters to set the 'faces', and the int type number of the correct face.
 * It stores the faces in an ArrayList and returns its current face.
 * When the current face is set to the correct face it returns true.
 * 
 * Each face also has an accompanying image that needs to be set.
 * These are stored in an ArrayList and managed the same was as the string version of each face.
 *
 * @author (Anthony Wythe)
 * @version ('Escape' Version 5 - 06/01/2018)
 */
public class Pillar
{

    private String face; //Pillars current face
    private ArrayList<String> faces; //ArrayList to store the faces

    private int faceN = 0; //Pillars current face number
    private boolean correctFace; //Boolean for whether it is on the correct face
    private int correctFaceN; //The number of the correct face

    private ArrayList<ImageIcon> faceImages; //ArrayList to store the face images
    private ImageIcon image;  //Pillars current face image

    /**
     * Constructor for objects of class Pillar takes 5 Strings and adds them to the ArrayList.
     * Also takes the number of the corect face. Sets correct face to 'false'.
     * @param String face1, face2, face3, face4, face5: the faces on the pillar
     * @param int correctN: the correct face number
     */
    public Pillar(String face1, String face2, String face3, String face4, String face5, int correctN)
    {
        //Initialises ArrayLists
        faces = new ArrayList<String>();
        faceImages = new ArrayList<ImageIcon>();

        //Adds the string faces
        faces.add(face1);
        faces.add(face2);
        faces.add(face3);
        faces.add(face4);
        faces.add(face5);

        //Sets the current face
        face = face1;

        //Sets the correct face number
        correctFaceN = correctN;
        correctFace = false;
    }

    /**
     * Returns the current face.
     * @return String
     */
    public String getFace()
    {
        return face;
    }

    /**
     * Returns whether to pillar is set to the correct face.
     * @return boolean
     */
    public boolean isCorrect()
    {
        return correctFace;
    }

    /**
     * When this method is called the pillar changes its current face to the next one in the ArrayList.
     * It checks whether the current face is correct and updates the boolean field.
     * @return void
     */
    public void changeFace()
    {
        if(faceN == 0){
            //Sets the string/image to the current face
            face = faces.get(faceN);
            image = faceImages.get(faceN);

            //If correct, set to 'true' and return
            if(faceN == correctFaceN){
                correctFace = true;
                return;
            }

            //Rotates the pillar to the next face
            faceN = faceN + 1;
            face = faces.get(faceN);
            image = faceImages.get(faceN);

            //If correct, set to 'true and return
            if(faceN == correctFaceN){
                correctFace = true;
                return;
            }

        }
        else if(faceN == 1){
            //Sets the string/image to the current face
            face = faces.get(faceN);
            image = faceImages.get(faceN);

            //If correct, set to 'true' and return
            if(faceN == correctFaceN){
                correctFace = true;
                return;
            }

            //Rotates the pillar to the next face
            faceN = faceN + 1;
            face = faces.get(faceN);
            image = faceImages.get(faceN);

            //If correct, set to 'true' and return
            if(faceN == correctFaceN){
                correctFace = true;
                return;
            }
        }
        else if(faceN == 2){
            //Sets the string/image to the current face
            face = faces.get(faceN);
            image = faceImages.get(faceN);

            //If correct, set to 'true' and return
            if(faceN == correctFaceN){
                correctFace = true;
                return;
            }

            //Rotates the pillar to the next face
            faceN = faceN + 1;
            face = faces.get(faceN);
            image = faceImages.get(faceN);

            //If correct, set to 'true' and return
            if(faceN == correctFaceN){
                correctFace = true;
                return;
            }
        }
        else if(faceN == 3){
            //Sets the string/image to the current face
            face = faces.get(faceN);
            image = faceImages.get(faceN);

            //If correct, set to 'true' and return
            if(faceN == correctFaceN){
                correctFace = true;
                return;
            }

            //Rotates the pillar to the next face
            faceN = faceN + 1;
            face = faces.get(faceN);
            image = faceImages.get(faceN);

            //If correct, set to 'true' and return
            if(faceN == correctFaceN){
                correctFace = true;
                return;
            }
        }

        else if(faceN == 4){
            //Sets the string/image to the current face
            face = faces.get(faceN);
            image = faceImages.get(faceN);

            //If correct, set to 'true' and return
            if(faceN == correctFaceN){
                correctFace = true;
                return;
            }

            //Rotates the pillar back to the first face
            faceN = faceN - 4;
            face = faces.get(faceN);
            image = faceImages.get(faceN);

            //If correct, set to 'true' and return
            if(faceN == correctFaceN){
                correctFace = true;
                return;
            }
        }
    }

    /**
     * Method that creates an image icon to add to the pillar.
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
     * Method that triggers the creation of an image and adds it to the pillars faces.
     * @param String path : the image file location
     * @param String description : title for the image file
     * @return void
     */
    protected void setPillarImage(String path, String description)
    {
        image = createImageIcon(path, description);
        faceImages.add(image);
    }

    /**
     * Returns the pillars current face image.
     * @return ImageIcon
     */
    protected ImageIcon returnPillarImage()
    {
        return image;
    } 

    /**
     * Method to set the inital current face image.
     * If this isnt done, when the player finds the pillar, it won't have an image till rotated.
     * @return void
     */
    public void setStartImage()
    {
        image = faceImages.get(faceN);
    }

}
