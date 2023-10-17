package main;

import main.GamePanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

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
        gamePanel.setupGame();
        // Initialize and add sideBar
        sideBar = createSideBar();
        add(sideBar, BorderLayout.LINE_START);

        pack();
        setLocationRelativeTo(null);
    }

    private JPanel createSideBar() {
        JPanel sideBar = new JPanel();
        sideBar.setLayout(new BoxLayout(sideBar, BoxLayout.Y_AXIS));
        sideBar.setPreferredSize(new Dimension(200, gamePanel.screenHeight));
        sideBar.setBackground(Color.LIGHT_GRAY);
        sideBar.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 10),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        String[] roomNames = {"Room 1", "Room 2", "Room 3", "Room 4", "Room 5"};
        String[][] participants = {
                {"Alice", "Bob"},
                {"Charlie", "Dave"},
                {"Eva", "Frank"},
                {"Grace", "Hank"},
                {"Ian", "Jack"}
        };

        Font boldFont = new Font("Arial", Font.BOLD, 16);

        for (int i = 0; i < roomNames.length; i++) {
            // Step 1: Consolidate the Components
            JPanel roomPanel = new JPanel();
            roomPanel.setLayout(new BoxLayout(roomPanel, BoxLayout.Y_AXIS));
            roomPanel.setBackground(new Color(245, 245, 245)); // Light gray background for contrast
            roomPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            JTextArea roomTextArea = new JTextArea(roomNames[i].toUpperCase());
            roomTextArea.setFont(boldFont);
            roomTextArea.setEditable(false);
            roomTextArea.setFocusable(false);
            roomTextArea.setWrapStyleWord(true);
            roomTextArea.setLineWrap(true);
            roomTextArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            roomPanel.add(roomTextArea);

            for (String participant : participants[i]) {
                JLabel participantLabel = new JLabel(participant);
                participantLabel.setFont(boldFont);
                participantLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                roomPanel.add(participantLabel);
            }

            // Step 2: Set Mouse Listener
            roomPanel.addMouseListener(new MouseAdapter() {
                Color originalColor;

                @Override
                public void mouseEntered(MouseEvent e) {
                    originalColor = roomPanel.getBackground();
                    roomPanel.setBackground(new Color(220, 220, 220));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    roomPanel.setBackground(originalColor);
                }
            });

            // Step 3: Apply Styling (already done in the component creation above)

            // Step 4: Add to `sideBar`
            sideBar.add(roomPanel);
            JSeparator separator = new JSeparator();
            separator.setMaximumSize(new Dimension(Integer.MAX_VALUE, 5));
            sideBar.add(separator);
        }

        return sideBar;
    }

    public void startGame() {
        gamePanel.startGameThread();
    }
}