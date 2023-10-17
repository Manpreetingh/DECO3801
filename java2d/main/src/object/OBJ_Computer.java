package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Computer extends SuperObject {
    public String dialogue[] = new String[20];
    public OBJ_Computer(GamePanel gp) {
        this.gp = gp;
        setDialogue();
        name = "Computer";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/desk_midmid.png"));
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
        dialogue[0] = "Log in to computer for meeting.";
    }

}
