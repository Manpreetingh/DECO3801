package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Random;

public class NPC_OldMan extends Entity{
    public NPC_OldMan(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;
        //can override the solide area
        getImage();
        setDialogue();
    }

    public void getImage()
    {
        try {
            //if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed ==true) {
            up1 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/npc/oldman_right_2.png"));
            //}
            //else {
            //    no = ImageIO.read(getClass().getResourceAsStream("/pixil-frame-0-2.png"));
            //}

        } catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    public void setAction() {
        actionLockCounter ++;

        if(actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;

            if(i <= 25) {
                direction = "up";
            }

            if(i > 25 && i <= 50) {
                direction = "down";
            }

            if(i > 50 && i <= 75) {
                direction = "left";
            }

            if(i > 75 && i <= 100) {
                direction = "right";
            }
            actionLockCounter = 0;
        }



    }

    public void setDialogue() {
        dialogue[0] = "Hello, How can I help you today?";
        dialogue[1] = "This is the office meeting room, you need to go to reception and get key for each room.";
        dialogue[2] = "I am your assistant, please don't be hesitate to ask me any questions!";
        dialogue[3] = "Once you have the key, the door will be lock.";
    }
    public void speak() {
        super.speak();
    }
}
