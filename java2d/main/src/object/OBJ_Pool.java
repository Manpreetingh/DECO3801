package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Pool extends SuperObject {
    public String dialogue[] = new String[20];

    public OBJ_Pool(GamePanel gp) {
        this.gp = gp;
        setDialogue();
        name = "Pool";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/pool_topright.png"));
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
        dialogue[0] = "Time to have fun time after meeting!";
    }

}