package main;

import main.GamePanel;
import javax.swing.*;
import java.awt.*;


public class GameWindow extends JFrame {
    private GamePanel gamePanel;
    private JPanel sideBar;

    public GameWindow() {
        // Window configuration
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setTitle("2D Adventure");
        setLayout(new BorderLayout());

        // Initialize and add GamePanel
        gamePanel = new GamePanel();
        add(gamePanel, BorderLayout.CENTER);

        // Initialize and add sideBar
        sideBar = createSideBar();
        add(sideBar, BorderLayout.LINE_START);

        pack();
        setLocationRelativeTo(null);
    }

    private JPanel createSideBar() {
        JPanel sideBar = new JPanel();
        sideBar.setPreferredSize(new Dimension(200, gamePanel.screenHeight));
        sideBar.setBackground(Color.LIGHT_GRAY);
        // Add more customization or components to sideBar as needed
        // Customize the appearance of the JList
        sideBar.setBackground(Color.WHITE); // Set list background to white
        sideBar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        sideBar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));
        // Add padding
        return sideBar;
    }

    public void startGame() {
        gamePanel.startGameThread();
    }
}