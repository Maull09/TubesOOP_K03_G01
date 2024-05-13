package gui;

import javax.swing.*;
import java.awt.*;
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
    private JPanel topPanel;
    private JButton startButton;
    private JButton backButton;
    private GameGUI gameGUI;

    public PlantSelectScreen(DeckTanaman deckTanaman, Inventory inventory, GameGUI gameGUI) {
        this.gameGUI = gameGUI;
        this.deckTanaman = deckTanaman;
        this.inventory = inventory;
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

        // startButton.addActionListener(e -> gameGUI.showGameScreen());
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
        ListOf<Plant> availablePlants = inventory.getAllItems();
        for (int i = 0; i < availablePlants.size(); i++) {
            final Plant plant = availablePlants.get(i);  // make plant final for use in lambda
            JButton button = new JButton(new ImageIcon(getClass().getResource("/resources/images/background/selectplant" + plant.getName() + ".png")));
            styleButton(button);
            button.setPreferredSize(new Dimension(80, 85));
            button.addActionListener(e -> {
                if (deckTanaman.getPlants().contains(plant)) {
                    JOptionPane.showMessageDialog(this, "Plant already in deck");
                } else if (!deckTanaman.addPlant(plant)) {
                    JOptionPane.showMessageDialog(this, "Deck is full");
                } else {
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
        for (int i = 0; i < deckTanaman.getPlants().size(); i++) {
            final Plant plant = deckTanaman.getPlants().get(i);
            JButton button = new JButton(new ImageIcon(getClass().getResource("/resources/images/background/selectplant" + plant.getName() + ".png")));
            styleButton(button);
            button.setPreferredSize(new Dimension(80, 85));
            button.addActionListener(e -> {
                    deckTanaman.removePlant(plant);
                    refreshDeckPanel();
            });
            deckPanel.add(button);
        }
        deckPanel.revalidate();
        deckPanel.repaint();
    }

    private void styleButton(JButton button) {
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Plant Selection");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GameGUI gameGUI = new GameGUI();
        frame.setContentPane(new PlantSelectScreen(new DeckTanaman(), new Inventory(), gameGUI));
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

        // // North panel for deck
        // deckPanel = new BackgroundPanel("/resources/images/background/FrameAtas.png"); // specify the path to your background image
        // deckPanel.setPreferredSize(new Dimension(30, 121));
        // deckPanel.setLayout(new FlowLayout(3, 10, 10));

        // // Buttons Panel
        // JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        // buttonPanel.setOpaque(false);
        // JButton startButton = new JButton(new ImageIcon(getClass().getResource("/resources/images/background/startselect.png")));
        // styleButton(startButton);
        // JButton backButton = new JButton(new ImageIcon(getClass().getResource("/resources/images/background/backselect.png")));
        // styleButton(backButton);

        // // startButton.addActionListener(e -> startGame());
        // // backButton.addActionListener(e -> goBack());

        // buttonPanel.add(backButton);
        // buttonPanel.add(startButton);

        // add(deckPanel, BorderLayout.NORTH);
        // add(buttonPanel, BorderLayout.EAST);

// package gui;

// import javax.swing.*;
// import java.awt.*;
// import entity.plant.Plant;
// import manager.DeckTanaman;
// import manager.Inventory;
// import util.ListOf;

// public class PlantSelectScreen extends JPanel {
//     private Image backgroundImage;
//     private DeckTanaman deckTanaman;
//     private Inventory inventory;
//     private JPanel deckPanel, inventoryPanel, buttonPanel;
//     private JButton startButton, backButton;

//     public PlantSelectScreen(DeckTanaman deckTanaman, Inventory inventory) {
//         this.deckTanaman = deckTanaman;
//         this.inventory = inventory;
//         loadImage();
//         initializeUI();
//     }

//     private void loadImage() {
//         try {
//             backgroundImage = new ImageIcon(getClass().getResource("/resources/images/background/SelectPlant.png")).getImage();
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//     }

//     @Override
//     protected void paintComponent(Graphics g) {
//         super.paintComponent(g);
//         if (backgroundImage != null) {
//             g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
//         }
//     }

//     private void initializeUI() {
//         setLayout(new BorderLayout());

//         // Create the panels using custom background panels for decorative purposes
//         deckPanel = createBackgroundPanel("/resources/images/background/FrameAtas.png");
//         inventoryPanel = createBackgroundPanel("/resources/images/background/FrameBawah.png");
//         buttonPanel = createButtonPanel();

//         // Set the preferred sizes for alignment and design
//         deckPanel.setPreferredSize(new Dimension(612, 122));
//         inventoryPanel.setPreferredSize(new Dimension(1090, 160));
//         buttonPanel.setPreferredSize(new Dimension(201, 51)); // Adjust size as per design requirement

//         populateDeck();

//         // Add to main panel using BorderLayout for better alignment and distribution
//         add(deckPanel, BorderLayout.NORTH);
//         add(inventoryPanel, BorderLayout.SOUTH);
//         add(buttonPanel, BorderLayout.EAST);

//         // Set the overall size of the window
//         setPreferredSize(new Dimension(1280, 720));
//     }

//     private JPanel createBackgroundPanel(String imagePath) {
//         BackgroundPanel panel = new BackgroundPanel(imagePath);
//         panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5)); // Modify layout and spacing as needed
//         return panel;
//     }

//     private JPanel createButtonPanel() {
//         JPanel panel = new JPanel();
//         panel.setOpaque(false);
//         panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

//         startButton = new JButton(new ImageIcon(getClass().getResource("/resources/images/background/startselect.png")));
//         backButton = new JButton(new ImageIcon(getClass().getResource("/resources/images/background/backselect.png")));
        
//         styleButton(startButton);
//         styleButton(backButton);

//         // Adjust button positioning within the panel
//         backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
//         startButton.setAlignmentX(Component.CENTER_ALIGNMENT);

//         panel.add(Box.createVerticalGlue());
//         panel.add(backButton);
//         panel.add(startButton);
//         panel.add(Box.createVerticalGlue());

//         return panel;
//     }

//     private void styleButton(JButton button) {
//         button.setBorderPainted(false);
//         button.setContentAreaFilled(false);
//         button.setFocusPainted(false);
//         button.setOpaque(false);
//     }

//     private void populateDeck() {
//         ListOf<Plant> availablePlants = inventory.getAllItems();
//         for (int i = 0; i < availablePlants.size(); i++) {
//             final Plant plant = availablePlants.get(i);  // make plant final for use in lambda
//             JButton button = new JButton(new ImageIcon(getClass().getResource("/resources/images/background/selectplant" + i + ".png")));
//             styleButton(button);
//             button.addActionListener(e -> {
//                 if (deckTanaman.getPlants().contains(plant)) {
//                     JOptionPane.showMessageDialog(this, "Plant already in deck");
//                 } else if (!deckTanaman.addPlant(plant)) {
//                     JOptionPane.showMessageDialog(this, "Deck is full");
//                 } else {
//                     refreshDeckPanel();
//                 }
//             });
//             inventoryPanel.add(button);
//         }    }

//     private void refreshDeckPanel() {
//         deckPanel.removeAll();
//         for (int i = 0; i < deckTanaman.getPlants().size(); i++) {
//             Plant plant = deckTanaman.getPlants().get(i);
//             JButton button = new JButton(new ImageIcon(getClass().getResource("/resources/images/background/selectplant" + i + ".png")));
//             styleButton(button);
//                 button.addActionListener(e -> {
//                     deckTanaman.removePlant(plant);
//                     refreshDeckPanel();
//             });
//             deckPanel.add(button);
//         }
//         deckPanel.revalidate();
//         deckPanel.repaint();
//     }

//     public static void main(String[] args) {
//         JFrame frame = new JFrame("Plant Selection Screen");
//         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         frame.setContentPane(new PlantSelectScreen(new DeckTanaman(), new Inventory()));
//         frame.pack();
//         frame.setLocationRelativeTo(null);
//         frame.setVisible(true);
//     }
// }

// class BackgroundPanel extends JPanel {
//     private Image backgroundImage;

//     public BackgroundPanel(String imagePath) {
//         try {
//             backgroundImage = new ImageIcon(getClass().getResource(imagePath)).getImage();
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//         setOpaque(false);
//     }

//     @Override
//     protected void paintComponent(Graphics g) {
//         super.paintComponent(g);
//         g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
//     }
// }
