package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Kitchen extends SuperObject {
    public String dialogue[] = new String[20];

    public OBJ_Kitchen(GamePanel gp) {
        this.gp = gp;
        setDialogue();
        name = "Kitchen";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/kitchen_botmid.png"));
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
        dialogue[0] = "Remember to clean after use, its time to cook?";
    }

}

