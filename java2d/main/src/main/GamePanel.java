package main;

import entity.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    //Settings of the screen
    final int originalTileszie =16; //16*16 tile
    final int scale =3;

    public final int tileSize = originalTileszie * scale; //48*48 tile

    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 768
    final int screemHeight = tileSize * maxScreenRow; // 576

    // FPS
    int FPS = 60;
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this,keyH);

    int playerx = 100;
    int playery = 100;
    int playerspeed = 4;
    private ImageIcon background;

    public GamePanel()
    {

        this.setPreferredSize(new Dimension(screenWidth,screemHeight));
        this.setBackground(Color.BLACK); // background colour
        this.setDoubleBuffered(true); // Double-buffering is the process of drawing graphics into an off-screen image buffer and then copying the contents of the buffer to the screen all at once. For the complex graphics, using double-buffering can reduce flickering issues.
        this.addKeyListener(keyH);
        this.setFocusable(true); // the game panel can be "focused" to receive key input

    }
    public void startGameThread() {
        gameThread = new Thread(this); //Instantiate a thread
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS; //since we are using nanoseconds so instead of 1s we wrote 1000000000 nanoseconds
        // drawInterval means we draw the screen every 0.1667 seconds
        double nextDrawTime = System.nanoTime() + drawInterval;
        // when the interval hits this time draw the screen again

        while(gameThread != null) // as long as this game thread exist it will repets the process inside brackets
        {
            //System.out.println("yo");
            // 1: Update information such as character positions
            update();
            // 2: Draw the screen with the update info
            repaint(); // here we are calling paint component method

            try
            {
                double remainingTime = nextDrawTime - System.nanoTime();// how much time remaining until the next cycle/loop will start (0.01667s)
                remainingTime /= 1000000;
                if(remainingTime < 0)
                {
                    remainingTime = 0;
                }
                Thread.sleep((long)remainingTime);

                nextDrawTime += drawInterval;

            }
            catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }
        }

    }

    public void update()
    {
      player.update();
    }

    public void paintComponent(Graphics g) // to draw something on JPanel other than drawing the background color
    {
        super.paintComponent(g);// whenever you use g in paintcomponent you need to type this out
        // super - parent class of gamePanel ( JPanel)
        Graphics2D g2 = (Graphics2D)g; // we are changing the g to the Graphics 2d class With the Java .
        // Graphics2D class, you have control over high-quality, two-dimensional graphics. This includes lines, shapes, images, fonts, etc.
        player.draw(g2);
        g2.dispose(); // Good practice to save memory  as it disposes off this graphics context and release any system resources that it is using.



    }
}
