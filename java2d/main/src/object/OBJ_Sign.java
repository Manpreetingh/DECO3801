package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Sign extends SuperObject {

    GamePanel gp;

    public OBJ_Sign(GamePanel gp) {
        this.gp = gp;
        name = "Sign";
        setDialogue();
        collision = true;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/sign.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void speak() {
        if(dialogue[dialogueIndex] == null) {
            dialogueIndex = 0;
        }
        gp.ui.currentDialogue = dialogue[dialogueIndex];
        dialogueIndex ++;
    }

    public void setDialogue() {
        dialogue[0] = "Welcome to reception!";
        dialogue[1] = "You collected a key!";
    }

}
