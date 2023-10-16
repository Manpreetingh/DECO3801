package entity;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    GamePanel gp;
    public  int worldx , worldy;
    public int speed;
    public BufferedImage up1,up2,down1,down2,left1,left2,right1,right2,no;
    public String direction;
    public int spriteCounter = 0 ;
    public int sprintNum = 1 ;
    public Rectangle solArea = new Rectangle(0, 0, 48, 48);
    public boolean collisionOn = false;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public int actionLockCounter;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    String dialogue[] = new String[20];
    int dialogueIndex = 0;


    public Entity(GamePanel gp) {
        this.gp = gp;
        this.gp.gameState = gp.playState;
    }

    public void setAction() {};
    public void speak() {
        if(dialogue[dialogueIndex] == null) {
            dialogueIndex = 0;
        }
        gp.ui.currentDialogue = dialogue[dialogueIndex];
        dialogueIndex ++;

        switch (gp.player.direction) {
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;
        }
    };
    public void update() {
        setAction();

        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkPlayer(this);

        if(collisionOn == false)
        {
            switch(direction)
            {
                case "up" : worldy -= speed; break;
                case "down":  worldy += speed; break;
                case "left":  worldx -= speed; break;
                case "right":  worldx += speed; break;
            }
        }
        spriteCounter++;
        if (spriteCounter > 12) {
            if (sprintNum == 1) {
                sprintNum = 2;
            } else if (sprintNum == 2) {
                sprintNum = 1;
            }
            spriteCounter = 0;
        }


    }


    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        int screenX = worldx - gp.player.worldx + gp.player.screenX;
        int screenY = worldy - gp.player.worldy + gp.player.screenY;

        if(worldx + gp.tileSize > gp.player.worldx - gp.player.screenX &&
                worldx - gp.tileSize < gp.player.worldx + gp.player.screenX &&
                worldy + gp.tileSize > gp.player.worldy - gp.player.screenY &&
                worldy - gp.tileSize < gp.player.worldy + gp.player.screenY ) {
            switch(direction)
            {
                case "up":
                    if(sprintNum == 1)
                    {
                        image = up1;
                    }
                    if(sprintNum == 2) {
                        image = up2;
                    }
                    break;
                case "down":
                    if(sprintNum == 1)
                    {
                        image = down1;
                    }
                    if(sprintNum == 2) {
                        image = down2;
                    }
                    break;
                case "left":
                    if(sprintNum == 1)
                    {
                        image = left1;
                    }
                    if(sprintNum == 2) {
                        image = left2;
                    }
                    break;
                case "right":
                    if(sprintNum == 1)
                    {
                        image = right1;
                    }
                    if(sprintNum == 2) {
                        image = right2;
                    }
                    break;

            }
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

        }
    }
}
