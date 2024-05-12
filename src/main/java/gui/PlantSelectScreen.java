package gui;

import javax.swing.*;
import java.awt.*;


public class PlantSelectScreen extends JFrame {
    private JPanel inventoryPanel;
    private JPanel deckPanel;
    
    public PlantSelectScreen() {
        setTitle("Choose Your Plants");
        setSize(800, 600); // Adjust size based on your needs
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10)); // Margin between components
        initializeUI();
        setLocationRelativeTo(null); // Center on screen
        setVisible(true);
    }

    private void initializeUI() {
        // North panel for deck
        deckPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        deckPanel.setBorder(BorderFactory.createTitledBorder("Your Deck"));
        // Simulate adding plants to deck (example)
        for (int i = 0; i < 6; i++) {
            JButton button = new JButton("Plant " + (i+1));
            deckPanel.add(button);
        }

        // South panel for inventory
        inventoryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        inventoryPanel.setBorder(BorderFactory.createTitledBorder("Inventory - Drag plants to your deck"));
        // Simulate adding plants to inventory (example)
        for (int i = 0; i < 10; i++) {
            JButton button = new JButton("Inv " + (i+1));
            inventoryPanel.add(button);
        }

        // Adding a button at the bottom
        JButton startButton = new JButton("Let's Rock!");
        startButton.setPreferredSize(new Dimension(100, 40));
        startButton.addActionListener(e -> System.out.println("Starting Game..."));

        // Add panels to the frame
        add(deckPanel, BorderLayout.NORTH);
        add(inventoryPanel, BorderLayout.SOUTH);
        add(startButton, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PlantSelectScreen::new);
    }
}
