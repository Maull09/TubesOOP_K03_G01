package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import entity.plant.Plant;
import manager.DeckTanaman;
import manager.Inventory;
import util.ListOf;

public class PlantSelectScreen extends JPanel {
    private Image backgroundImage;
    private DeckTanaman deckTanaman;
    private Inventory inventory;
    private JPanel deckPanel;
    private JPanel inventoryPanel;

    public PlantSelectScreen(DeckTanaman deckTanaman, Inventory inventory) {
        this.deckTanaman = deckTanaman;
        this.inventory = inventory;
        setLayout(new BorderLayout());
        // loadImage();
        initializeUI();
    }

    private void loadImage() {
        try {
            backgroundImage = new ImageIcon(getClass().getResource("/resources/images/description/bgplantdesc.png")).getImage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    private void initializeUI() {
        // North panel for deck
        deckPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        deckPanel.setOpaque(false);
        deckPanel.setBorder(BorderFactory.createTitledBorder("Your Deck"));
        refreshDeckPanel();

        // South panel for inventory
        inventoryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        inventoryPanel.setOpaque(false);
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
        startButton.setOpaque(false);

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
        JFrame frame = new JFrame("Plant Selection");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new PlantSelectScreen(new DeckTanaman(), new Inventory()));
        frame.setSize(1280, 720);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
