package main;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        GameWindow window = new GameWindow();
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();
        window.setVisible(true);
        gamePanel.setupGame();
        window.startGame();

    }
}
