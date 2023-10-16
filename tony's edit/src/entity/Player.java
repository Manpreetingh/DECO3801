package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;




    public Player(GamePanel gp, KeyHandler keyH)
    {
        this.gp = gp;
        this.keyH =keyH;
        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - - (gp.tileSize/2);
        solArea = new Rectangle(0,0,gp.tileSize,gp.tileSize);

        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues()
    {
        worldx = gp.tileSize * 23;
        worldy = gp.tileSize * 21;
        speed = 4;
        direction = "down";
    }
    public void getPlayerImage()
    {
        try {
                //if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed ==true) {
                    up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/girl_up1.png")));
                    up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/girl_up2.png")));
                    down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/girl_down1.png")));
                    down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/girl_down2.png")));
                    left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/girl_left1.png")));
                    left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/girl_left2.png")));
                    right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/girl_right1.png")));
                    right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/girl_right2.png")));
                //}
                //else {
                //    no = ImageIO.read(getClass().getResourceAsStream("/pixil-frame-0-2.png"));
                //}

        } catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    public void update()
    {
        if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed ==true) {
            if (keyH.upPressed == true) { // the java upper left corner in origin
                // X increases to the right
                // Y increases as they go down
                direction = "up";


            }
            if (keyH.downPressed == true) {
                direction = "down";

            }

            if (keyH.leftPressed == true) {
                direction = "left";

            }
            if (keyH.rightPressed == true) {
                direction = "right";
              ;
            }
            collisionOn = false;
            gp.cChecker.checkTile(this);

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
            if (spriteCounter > 10) {
                if (sprintNum == 1) {
                    sprintNum = 2;
                } else if (sprintNum == 2) {
                    sprintNum = 1;
                }
                spriteCounter = 0;
            }


        }

    }
    public void draw(Graphics2D g2)
    {
        //  g2.setColor(Color.white);
        // g2.fillRect(x, y , gp.tileSize , gp.tileSize);
        BufferedImage image = null;

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
        g2.drawImage(image,screenX,screenY,gp.tileSize,gp.tileSize,null);
    }

}
