package main;

import entity.Entity;
import entity.Player;
import object.SuperObject;
import tile.Tile;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable {

    //Settings of the screen
    final int originalTileszie =16; //16*16 tile
    final int scale =3;
    public final int tileSize = originalTileszie * scale; //48*48 tile
    public final int maxScreenCol = 40;
    public final int maxScreenRow = 22;
    public final int screenWidth = tileSize * maxScreenCol; // 768
    public final int screenHeight = tileSize * maxScreenRow; // 576

    //world setting
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize *  maxWorldRow;

    // Sidebar member variables
    private DefaultListModel<String> roomListModel;
    private JList<String> roomList;
    private JScrollPane roomListScrollPane;

    Sound sound = new Sound();
    Sound se = new Sound();
    public UI ui = new UI(this);
    // FPS
    int FPS = 60;

    TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    Thread gameThread;
    public AssetSetter aSetter = new AssetSetter(this);
    public Collision cChecker = new Collision(this);
    public Player player = new Player(this,keyH);
    public SuperObject obj[] = new SuperObject[40];
    public int gameState;
    public final int dialogueState = 3;

    public final int playState = 1;
    public Entity npc[] = new Entity[10];
    int playerx = 100;
    int playery = 100;
    int playerspeed = 4;
    private ImageIcon background;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.BLACK); // background colour
        this.setDoubleBuffered(true); // Double-buffering is the process of drawing graphics into an off-screen image buffer and then copying the contents of the buffer to the screen all at once. For the complex graphics, using double-buffering can reduce flickering issues.
        this.addKeyListener(keyH);
        this.setFocusable(true); // the game panel can be "focused" to receive key input

        // Initialize the Sidebar
        initializeSidebar();

        // Set the layout for the entire GamePanel
        this.setLayout(new BorderLayout());

        // Add the sidebar to the left side of the GamePanel
        this.add(roomListScrollPane, BorderLayout.WEST);
    }

    public void setupGame() {
        aSetter.setObject();
        aSetter.setNPC();
        //playMusic(0);
    }

    private void initializeSidebar() {
        roomListModel = new DefaultListModel<>();
        roomList = new JList<>(roomListModel);
        roomListScrollPane = new JScrollPane(roomList);

        // Populate the list with room names
        roomListModel.addElement("Room 1");
        roomListModel.addElement("Room 2");
        roomListModel.addElement("Room 3");

        // Style the sidebar
        roomListScrollPane.setPreferredSize(new Dimension(150, this.getHeight()));
        roomList.setBackground(Color.LIGHT_GRAY);
        roomList.setForeground(Color.BLACK);

        // Add an event listener for room selection
        roomList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedRoom = roomList.getSelectedValue();
                System.out.println("Selected Room: " + selectedRoom);
                // Handle room selection logic here
            }
        });
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

      for(int i = 0; i < npc.length; i++) {
          if(npc[i] != null) {
              npc[i].update();
          }
      }
    }

    public void paintComponent(Graphics g) // to draw something on JPanel other than drawing the background color
    {
        super.paintComponent(g);// whenever you use g in paintcomponent you need to type this out
        // super - parent class of gamePanel ( JPanel)
        Graphics2D g2 = (Graphics2D)g; // we are changing the g to the Graphics 2d class With the Java .
        // Graphics2D class, you have control over high-quality, two-dimensional graphics. This includes lines, shapes, images, fonts, etc.
        tileM.draw(g2);
        // Object draw
        for (int i = 0; i < obj.length; i++) {
            if (obj[i] != null) {
                obj[i].draw(g2, this); //iterate 0 to 9 object array
            }
        }
        //NPC
        for (int i = 0; i < npc.length; i++) {
            if(npc[i] != null) {
                npc[i].draw(g2);
            }
        }
        player.draw(g2);
        ui.draw(g2);
        g2.dispose(); // Good practice to save memory  as it disposes off this graphics context and release any system resources that it is using.
    }

    public void playMusic(int i) {
        sound.setFile(i);
        sound.play();
        sound.loop();
    }

    public void stopMusic() {
        sound.stop();
    }

    public void playSE(int i) {
        se.setFile(i);
        se.play();
    }

    public void drawImage(BufferedImage image, int x, int tileSize, Object o) {
    }
}
