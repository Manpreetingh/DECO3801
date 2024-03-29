package entity;

import main.GamePanel;
import main.KeyHandler;
import object.SuperObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class Player extends Entity{

    public KeyHandler keyH;
    SuperObject temp[] = new SuperObject[1];
    public final int screenX;
    public final int screenY;
    public int hasKey = 0;
    public int inRoom = 0;
    int doorCount = 0;
    int doorIndex = -1;

    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);

        this.keyH =keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - - (gp.tileSize/2);
        solArea = new Rectangle();
        solArea.x = 8;
        solArea.y = 16;
        solidAreaDefaultX = solArea.x;
        solidAreaDefaultY = solArea.y;
        solArea.width = 32;
        solArea.height = 32;

        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues() {
        worldx = gp.tileSize * 18;
        worldy = gp.tileSize * 5;
        speed = 4;
        direction = "down";
    }
    public void getPlayerImage() {
        try {
                //if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed ==true) {
                    up1 = ImageIO.read(getClass().getResourceAsStream("/Players/boy_up_1.png"));
                    up2 = ImageIO.read(getClass().getResourceAsStream("/Players/boy_up_2.png"));
                    down1 = ImageIO.read(getClass().getResourceAsStream("/Players/boy_down_1.png"));
                    down2 = ImageIO.read(getClass().getResourceAsStream("/Players/boy_down_2.png"));
                    left1 = ImageIO.read(getClass().getResourceAsStream("/Players/boy_left_1.png"));
                    left2 = ImageIO.read(getClass().getResourceAsStream("/Players/boy_left_2.png"));
                    right1 = ImageIO.read(getClass().getResourceAsStream("/Players/boy_right_1.png"));
                    right2 = ImageIO.read(getClass().getResourceAsStream("/Players/boy_right_2.png"));
                //}
                //else {
                //    no = ImageIO.read(getClass().getResourceAsStream("/pixil-frame-0-2.png"));
                //}

        } catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * executeCommand
     *  Used to execute a given string command in terminal
     *  Has considerations for both mac and windows
     * @param command - the command to be run in terminal
     * @throws IOException if command is run from an unknown machine
     */
    private void executeCommand(String... command) throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        // Redirecting errors to standard output for consolidated logging.
        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();

        // Reading the output in a separate thread to prevent blocking.
        new Thread(() -> {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void update() {
        Process process;
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed || keyH.zoom) {

            // Handle the execution of the zoom clone
            if (keyH.zoom) {
                try {
                    String OS = System.getProperty("os.name").toLowerCase();
                    if (OS.contains("win")) {
                        // If Windows, execute the .exe file and open the default browser.
                        executeCommand("./zoom-clone-win.exe");
                        executeCommand("cmd", "/c", "start", "http://localhost:3030");
                    } else if (OS.contains("mac")) {
                        // Assuming zoom-clone-macos is a binary. Adjust if it's a .sh or other type.
                        executeCommand("./zoom-clone-macos");
                        executeCommand("open", "http://localhost:3030");
                    } else {
                        // Log or handle unsupported OS more gracefully
                        System.err.println("Unsupported OS: " + OS);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (keyH.upPressed) {
                // the java upper left corner in origin
                // X increases to the right
                // Y increases as they go down
                direction = "up";
            }
            if (keyH.downPressed) {
                direction = "down";
            }
            if (keyH.leftPressed) {
                direction = "left";
            }
            if (keyH.rightPressed) {
                direction = "right";
            }

            collisionOn = false;
            gp.cChecker.checkTile(this);
            int objIndex = gp.cChecker.checkObject(this, true);

            if (objIndex != 999 && Objects.equals(gp.obj[objIndex].name, "Door") && (this.hasKey > 0)) {
                doorCount = 0;
                temp[0] = gp.obj[objIndex];
                doorIndex = objIndex;
            }

            pickUpObject(objIndex);

            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            if(collisionOn == false) {
                switch(direction) {
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

    public void pickUpObject(int i) {
        if (i != 999) {
            String objectName = gp.obj[i].name;

            switch ((objectName)) {
                case "Sign":

                    if (gp.keyH.enterPressed == true) {
                        gp.gameState = gp.dialogueState;
                        gp.obj[i].speak();
                        if (gp.obj[i].dialogueIndex == 2) {
                            hasKey+=2;
                            gp.playSE(1);
                            gp.ui.showMessage("You got a room key, noted room will locked in one second.");
                        }
                    }

                    break;
                case "Door":
                    if (hasKey > 0) {
                        gp.playSE(3);
                        gp.obj[i] = null;
                        hasKey --;
                    }
                    break;
                case "Computer":
                    if (gp.keyH.enterPressed == true) {
                        gp.gameState = gp.dialogueState;
                        gp.obj[i].speak();
                    }
                    break;
                case "Kitchen":
                    if (gp.keyH.enterPressed == true) {
                        gp.gameState = gp.dialogueState;
                        gp.obj[i].speak();
                    }
                    break;
                case "Sofa":
                    if (gp.keyH.enterPressed == true) {
                        gp.gameState = gp.dialogueState;
                        gp.obj[i].speak();
                    }
                    break;
                case "Pool":
                    if (gp.keyH.enterPressed == true) {
                        gp.gameState = gp.dialogueState;
                        gp.obj[i].speak();
                    }
                    break;
            }
        }

        doorCount++;
        if (doorCount > 60 && doorIndex != -1) {
            doorCount = 0;
            gp.obj[doorIndex] = temp[0];
            doorIndex = -1;
            gp.ui.showMessage("Room is locked");
        }
    }

    public void interactNPC(int i) {
        if(i != 999) {
            if (gp.keyH.enterPressed) {
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            }
        }
        gp.keyH.enterPressed = false;
    }

    public void draw(Graphics2D g2)
    {
        //  g2.setColor(Color.white);
        // g2.fillRect(x, y , gp.tileSize , gp.tileSize);
        BufferedImage image = null;

        switch (direction) {
            case "up" -> {
                if (sprintNum == 1) {
                    image = up1;
                }
                if (sprintNum == 2) {
                    image = up2;
                }
            }
            case "down" -> {
                if (sprintNum == 1) {
                    image = down1;
                }
                if (sprintNum == 2) {
                    image = down2;
                }
            }
            case "left" -> {
                if (sprintNum == 1) {
                    image = left1;
                }
                if (sprintNum == 2) {
                    image = left2;
                }
            }
            case "right" -> {
                if (sprintNum == 1) {
                    image = right1;
                }
                if (sprintNum == 2) {
                    image = right2;
                }
            }
        }
        g2.drawImage(image,screenX,screenY,gp.tileSize,gp.tileSize,null);
    }

}
