package gui;

import javax.swing.*;

import data.GameState;

import java.awt.*;
import entity.plant.Plant;
import manager.DeckTanaman;
import util.ListOf;

public class PlantSelectScreen extends JPanel {
    private Image backgroundImage;
    private JPanel deckPanel;
    private JPanel inventoryPanel;
    private JPanel topPanel;
    private JButton startButton;
    private JButton backButton;
    private GameGUI gameGUI;
    private Runnable onContinue;

    private Plant selectedPlantForSwap = null;
    private JButton selectedButtonForSwap = null;
    private ListOf<JButton> deckButtons = new ListOf<JButton>();

    public PlantSelectScreen(GameGUI gameGUI) {
        this.gameGUI = gameGUI;
        setSize(1280, 720);
        setLayout(new BorderLayout());
        loadImage();
        initializeUI();
    }

    private void loadImage() {
        try {
            backgroundImage = new ImageIcon(getClass().getResource("/resources/images/background/SelectPlant.png")).getImage();
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
        setLayout(new BorderLayout());
        
        // Main top panel with BoxLayout for horizontal alignment
        topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.LINE_AXIS));
        topPanel.setOpaque(false);
        
        // Deck Panel setup
        deckPanel = new BackgroundPanel("/resources/images/background/FrameAtas.png");
        deckPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        deckPanel.setPreferredSize(new Dimension(365, 100));  // Adjust dimensions as needed
        deckPanel.setOpaque(false);
        
        // Button Panel setup
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
        
        startButton = new JButton(new ImageIcon(getClass().getResource("/resources/images/background/startselect.png")));
        backButton = new JButton(new ImageIcon(getClass().getResource("/resources/images/background/backselect.png")));
        styleButton(startButton);
        styleButton(backButton);

        startButton.addActionListener(e -> {
            if (GameState.getInstance().getDeck().isFull()) {
                if (onContinue != null) {
                    onContinue.run();
                }
                gameGUI.showGameScreen();
            } else {
                JOptionPane.showMessageDialog(this, "Deck is not full");
            }
        });
        backButton.addActionListener(e -> gameGUI.showMainMenu());

        buttonPanel.add(Box.createHorizontalGlue()); // Add glue to push buttons to the right
        buttonPanel.add(startButton);
        buttonPanel.add(backButton);
        
        // Bot panel setup
        JPanel botPanel = new JPanel();
        botPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        botPanel.setOpaque(false);
        botPanel.setPreferredSize(new Dimension(1090, 160));  // Adjust dimensions as needed

        // South panel for inventory
        inventoryPanel = new BackgroundPanel("/resources/images/background/FrameBawah.png"); // specify the path to your background image
        inventoryPanel.setPreferredSize(new Dimension(920, 160));
        inventoryPanel.setLayout(new FlowLayout(3, 10, 50));
        
        // Populate inventory from inventory class
        ListOf<Plant> availablePlants = GameState.getInstance().getInventory().getAllItems();
        for (int i = 0; i < availablePlants.size(); i++) {
            final Plant plant = availablePlants.get(i);  // make plant final for use in lambda
            JButton button = new JButton(new ImageIcon(getClass().getResource("/resources/images/background/selectplant" + plant.getName() + ".png")));
            styleButton(button);
            button.setPreferredSize(new Dimension(80, 85));
            button.addActionListener(e -> {
                if (GameState.getInstance().getDeck().getPlants().contains(plant)) {
                    JOptionPane.showMessageDialog(this, "Plant already in deck");
                } else if (GameState.getInstance().getDeck().isFull()) {
                    JOptionPane.showMessageDialog(this, "Deck is full");
                } else {
                    GameState.getInstance().getDeck().addPlant(plant);
                    refreshDeckPanel();
                }
            });
            inventoryPanel.add(button);
        }
        
        // Add components to top panel
        topPanel.add(deckPanel);
        topPanel.add(Box.createHorizontalStrut(10)); // Spacer between deckPanel and buttonPanel
        topPanel.add(buttonPanel);
        
        // Add the top panel to the main frame
        add(topPanel, BorderLayout.NORTH);

        // add components to bot panel
        botPanel.add(inventoryPanel);
        botPanel.add(Box.createHorizontalStrut(10)); // Spacer between inventoryPanel and buttonPanel
        
        // Add the bot panel to the main frame
        add(botPanel, BorderLayout.SOUTH);
    }

    private void refreshDeckPanel() {
        deckPanel.removeAll();
        deckButtons.clear();
        for (int i = 0; i < GameState.getInstance().getDeck().getPlants().size(); i++) {
            final Plant plant = GameState.getInstance().getDeck().getPlants().get(i);
            JButton button = new JButton(new ImageIcon(getClass().getResource("/resources/images/background/selectplant" + plant.getName() + ".png")));
            styleButton(button);
            button.setPreferredSize(new Dimension(80, 85));
            button.addActionListener(e -> {
                if (selectedPlantForSwap == null) {
                    selectedPlantForSwap = plant;
                    selectedButtonForSwap = button;
                    button.setBorderPainted(true);
                    button.setBorder(BorderFactory.createLineBorder(Color.RED));
                } else if (selectedPlantForSwap == plant) {
                    GameState.getInstance().getDeck().removePlant(plant);
                    refreshDeckPanel();
                    // selectedPlantForSwap.setBorderPainted(false);
                    selectedPlantForSwap = null;
                    selectedButtonForSwap = null;
                } else {
                    swapPlants(selectedPlantForSwap, plant);
                    selectedPlantForSwap = null;
                    selectedButtonForSwap.setBorderPainted(false);
                    selectedButtonForSwap = null;
                    refreshDeckPanel();
                }
            });
            deckButtons.add(button);
            deckPanel.add(button);
        }
        deckPanel.revalidate();
        deckPanel.repaint();
    }

    private void swapPlants(Plant plant1, Plant plant2) {
        DeckTanaman deck = GameState.getInstance().getDeck();
        int index1 = deck.getPlants().indexOf(plant1);
        int index2 = deck.getPlants().indexOf(plant2);
        deck.getPlants().swap(index1, index2);
    }

    private void styleButton(JButton button) {
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
    }

    public void setOnContinue(Runnable onContinue) {
        this.onContinue = onContinue;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Plant Selection");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GameGUI gameGUI = new GameGUI();
        frame.setContentPane(new PlantSelectScreen(gameGUI));
        frame.setSize(1280, 720);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    // Constructor to load the background image
    public BackgroundPanel(String imagePath) {
        try {
            backgroundImage = new ImageIcon(getClass().getResource(imagePath)).getImage();
            setOpaque(false); // Make panel transparent
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            // Draw the background image scaled to fit the panel
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
