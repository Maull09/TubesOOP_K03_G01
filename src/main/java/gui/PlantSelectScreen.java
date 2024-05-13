package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import entity.plant.Plant;
import manager.DeckTanaman;
import manager.Inventory;
import util.ListOf;
public class PlantSelectScreen extends JFrame {
    private DeckTanaman deckTanaman;
    private Inventory inventory;
    private JPanel deckPanel;
    private JPanel inventoryPanel;

    public PlantSelectScreen(DeckTanaman deckTanaman, Inventory inventory) {
        this.deckTanaman = deckTanaman;
        this.inventory = new Inventory();
        
        setTitle("Choose Your Plants");
        setSize(1280, 720);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        initializeUI(deckTanaman, inventory);
        setVisible(true);
    }

    private void initializeUI(DeckTanaman deckTanaman, Inventory inventory) {
        // North panel for deck
        deckPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        deckPanel.setBorder(BorderFactory.createTitledBorder("Your Deck"));

        // Populate deck from deckTanaman
        refreshDeckPanel();

        // South panel for inventory
        inventoryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        inventoryPanel.setBorder(BorderFactory.createTitledBorder("Inventory - Drag plants to your deck"));
        
        // Populate inventory from inventory class
        ListOf<Plant> availablePlants = inventory.getAllItems();
        for (int i = 0; i < availablePlants.size(); i++) {
            Plant plant = availablePlants.get(i);
            JButton button = new JButton(new ImageIcon(getClass().getResource("/resources/images/plants/" + plant.getName() + ".png")));
            button.addActionListener(e -> {
                if (deckTanaman.addPlant(plant)) {
                    refreshDeckPanel();
                } else {
                    JOptionPane.showMessageDialog(this, "Deck is full or plant already in deck");
                }
            });
            inventoryPanel.add(button);
        }
        // Add a button to start the game
        JButton startButton = new JButton("Start Game");

        add(deckPanel, BorderLayout.NORTH);
        add(inventoryPanel, BorderLayout.SOUTH);
        add(startButton, BorderLayout.CENTER);  

    }

    private void refreshDeckPanel() {
        deckPanel.removeAll();
        for (int i = 0; i < deckTanaman.getPlants().size(); i++) {
            Plant plant = deckTanaman.getPlants().get(i);
            JButton button = new JButton(new ImageIcon(getClass().getResource("/resources/images/plants/" + plant.getName() + ".png")));            button.addActionListener(e -> {
                deckTanaman.removePlant(plant);
                refreshDeckPanel();
            });
            deckPanel.add(button);
        }
        deckPanel.revalidate();
        deckPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DeckTanaman deckTanaman = new DeckTanaman();
            Inventory inventory = new Inventory();
            PlantSelectScreen plantSelectScreen = new PlantSelectScreen(deckTanaman, inventory);
        });
    }
}
