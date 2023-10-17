package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Sofa extends SuperObject {
    public String dialogue[] = new String[20];

    public OBJ_Sofa(GamePanel gp) {
        this.gp = gp;
        setDialogue();
        name = "Sofa";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/sofa_botmid.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }


    public void speak() {
        if(dialogue[dialogueIndex] == null) {
            dialogueIndex = 0;
        }
        gp.ui.currentDialogue = dialogue[dialogueIndex];
        dialogueIndex ++;
    }

    public void setDialogue() {
        dialogue[0] = "What a nice break time after meeting right?";
    }

}