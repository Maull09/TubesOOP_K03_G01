package gui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import manager.DeckTanaman;
import manager.GameMap;
import util.ListOf;
import data.GameState;
import entity.Projectile;
import entity.plant.Plant;
import entity.plant.PlantFactory;
import entity.zombie.Zombie;

public class GamePanel extends JPanel implements ActionListener{
    private Image backgroundImage;
    private GameMap gameMap;
    private GameState gameState;
    private final int CELL_SIZE = 100;
    private GameGUI gameGUI;
    private JPanel topBarPanel;
    private JPanel deckPanel;
    private JLabel sunPointsLabel;
    private String selectedPlant;

    public GamePanel(GameMap gameMap, GameState gameState, GameGUI gameGUI) {
        this.gameMap = gameMap;
        this.gameState = gameState;
        this.gameGUI = gameGUI;
        loadImage();
        setPreferredSize(new Dimension(1280, 720));  
        setLayout(new BorderLayout());
        drawTopBar();
        drawMenuButton();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                handleMouseClick(e.getX(), e.getY());
            }
        });
    }

    private void loadImage() {
        try {
            backgroundImage = new ImageIcon(getClass().getResource("/resources/images/background/23.png")).getImage();
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
        drawBackground(g);
        drawPlants(g);
        drawZombies(g);
        drawProjectiles(g);
    }

    private void drawTopBar() {
        // Panel top bar
        topBarPanel = new JPanel();
        topBarPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        topBarPanel.setPreferredSize(new Dimension(1280, 130));
        topBarPanel.setOpaque(false);
        
        // Placeholder for deck tanaman panel
        deckPanel = new BackgroundPanel("/resources/images/background/SunDeck.png");
        deckPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        deckPanel.setPreferredSize(new Dimension(646, 111));  // Adjust size as needed
        deckPanel.setOpaque(false);
        
        // Label sun points
        sunPointsLabel = new JLabel("" + gameState.getSunPoints());
        sunPointsLabel.setPreferredSize(new Dimension(90, 170));
        sunPointsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        sunPointsLabel.setFont(new Font("Arial", Font.BOLD, 20));
        sunPointsLabel.setForeground(Color.BLACK);
        deckPanel.add(sunPointsLabel);
        
        // membuat panel untuk tanaman
        JPanel plantPanel = new JPanel();
        plantPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 8, -26));
        plantPanel.setPreferredSize(new Dimension(505, 121));  // Adjust size as needed
        plantPanel.setOpaque(false);

        // Menambahkan tanaman ke deckPanel
        DeckTanaman plantDeck = gameState.getDeck();
        
        // isi deck tanaman
        plantDeck.addPlant(PlantFactory.createPlant("Sunflower"));
        plantDeck.addPlant(PlantFactory.createPlant("Peashooter"));
        plantDeck.addPlant(PlantFactory.createPlant("Wallnut"));
        plantDeck.addPlant(PlantFactory.createPlant("SnowPea"));
        plantDeck.addPlant(PlantFactory.createPlant("Repeater"));
        plantDeck.addPlant(PlantFactory.createPlant("Lilypad"));

        // Menambahkan tanaman ke deckPanel
        for (int i = 0; i < plantDeck.getPlants().size(); i++) {
            String plant = plantDeck.getPlants().get(i).getName();
            ImageIcon plantIcon = new ImageIcon(getClass().getResource("/resources/images/cards/cards_" + plant + ".png"));
            JButton plantButton = new JButton(plantIcon);
            plantButton.setPreferredSize(new Dimension(76, 121));
            plantButton.setBorderPainted(false);
            plantButton.setContentAreaFilled(false);
            plantButton.setFocusPainted(false);
            plantButton.addActionListener(e -> selectedPlant = plant);
            plantPanel.add(plantButton);
        }

        deckPanel.add(plantPanel);
        topBarPanel.add(deckPanel);

        // Panel untuk shovel
        JPanel shovelPanel = new JPanel();
        shovelPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        shovelPanel.setPreferredSize(new Dimension(130, 111));  // Adjust size as needed
        shovelPanel.setOpaque(false);
        
        // Button shovel
        ImageIcon shovelIcon = new ImageIcon(getClass().getResource("/resources/images/background/Shovel.png"));
        JButton shovelButton = new JButton(shovelIcon);
        shovelButton.setBorderPainted(false);
        shovelButton.setContentAreaFilled(false);
        shovelButton.setFocusPainted(false);
        shovelPanel.add(shovelButton);

        // Add shovel panel to top bar panel
        topBarPanel.add(shovelPanel);
        
        // Panel untuk progress bar
        JPanel progressBarPanel = new BackgroundPanel("/resources/images/background/FlagLevel.png");
        progressBarPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        progressBarPanel.setPreferredSize(new Dimension(430, 111));  // Adjust size as needed
        progressBarPanel.setOpaque(false);
        
        // Progress bar
        JLabel progressBarlabel = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawProgressBar(g);
            }
        };
        progressBarlabel.setPreferredSize(new Dimension(430, 111));  // Adjust size as needed
        progressBarlabel.setOpaque(false);
        progressBarPanel.add(progressBarlabel);
        topBarPanel.add(progressBarPanel);

        // Add progress bar panel to top bar panel
        topBarPanel.add(progressBarPanel);

        add(topBarPanel, BorderLayout.NORTH);
    }

    private void handleMouseClick(int x, int y) {
        int startX = 15;
        int startY = 15;
        
        if (selectedPlant != null) {
            // Calculate the cell that was clicked
            int col = (x - startX) / CELL_SIZE - 2;
            int row = (y - startY) / CELL_SIZE - 1;
            
            if (col >= 0 && col < 11 && row >= 0 && row < 6) {  // Adjust according to the grid range
                gameMap.plantSpawner(selectedPlant, row, col, gameState);
                selectedPlant = null;
                
                // Draw a debug rectangle
                Graphics g = getGraphics();
                g.setColor(Color.RED);
                g.drawRect(startX + (col + 2) * CELL_SIZE, startY + (row + 1) * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }
        }
    }

    private void drawProgressBar(Graphics g) {
        // Draw the progress bar as a rectangle
        int x = 83;
        int y = 30; // Vertical position of the bar
        int width = 71 * 4; // Scale value for width
        int height = 29; // Height of the bar
        g.setColor(Color.RED);
        g.fillRect(x, y, width, height);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);
    }

    private void drawMenuButton() {
        ImageIcon menuIcon = new ImageIcon(getClass().getResource("/resources/images/background/MenuButton.png"));
        JButton menuButton = new JButton(menuIcon);
        menuButton.setBorderPainted(false);
        menuButton.setContentAreaFilled(false);
        menuButton.setFocusPainted(false);
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameGUI.showMainMenu();
            }
        });
        add(menuButton, BorderLayout.WEST);
    }

    private void drawBackground(Graphics g) {
        int startX = 15; 
        int startY = 15; 
        // Draw grid
        for (int row = 1; row < 7; row++) {
            for (int col = 2; col < 13; col++) {
                g.drawRect(startX + col * CELL_SIZE, startY + row * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }
        }
    }

    private void drawPlants(Graphics g) {
        int startX = 15;
        int startY = 15;

        // Draw every plant in the grid
        for(int i = 0; i < 6; i++){
            for(int j = 1; j < 10; j++){
                ListOf<Plant> plants = gameMap.getTile(i, j).getPlants();
                for(int k = 0; k < plants.size(); k++){
                    Plant plant = plants.get(k);
                    ImageIcon planticon = new ImageIcon(getClass().getResource("/resources/images/plants/" + plant.getName() + ".png"));
                    g.drawImage(planticon.getImage(), startX + (j + 2) * CELL_SIZE, startY + (i + 1) * CELL_SIZE, 90, 90, this);
                }
            }
        }
    }

    private void drawZombies(Graphics g) {
        // Implement drawing zombies based on gameState
        int startX = 15;
        int startY = 15;

        // Draw every zombie in the grid
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 11; j++){
                ListOf<Zombie> zombies = gameMap.getTile(i, j).getZombies();
                for(int k = 0; k < zombies.size(); k++){
                    Zombie zombie = zombies.get(k);
                    ImageIcon zombieicon = new ImageIcon(getClass().getResource("/resources/images/zombie/" + zombie.getName() + ".png"));
                    g.drawImage(zombieicon.getImage(), startX + (j + 2) * CELL_SIZE, startY + (i + 1) * CELL_SIZE, 90, 90, this);
                }
            }
        }
    }

    private void drawProjectiles(Graphics g) {
        // Implement drawing projectiles based on gameState
        int startX = 15;
        int startY = 15;

        // Draw every projectile in the grid
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 11; j++){
                ListOf<Projectile> projectiles = gameMap.getTile(i, j).getProjectiles();
                for(int k = 0; k < projectiles.size(); k++){
                    Projectile projectile = projectiles.get(k);
                    ImageIcon projectileicon = new ImageIcon(getClass().getResource("/resources/images/projectiles/" + projectile.getType() + ".png"));
                    g.drawImage(projectileicon.getImage(), startX + (j + 2) * CELL_SIZE, startY + (i + 1) * CELL_SIZE, 90, 90, this);
                }
            }
        }
    }

    // Additional methods to handle game events (planting, spawning, etc.)

    @Override
    public void actionPerformed(ActionEvent e) {
        // Update game state and repaint
        gameState.update();
        sunPointsLabel.setText("" + gameState.getSunPoints()); // Update sun points label
        // progressBarValue = gameState.getProgressBarValue(); // Example method to get progress value
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Plants vs. Zombies");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new GamePanel(new GameMap(), new GameState(), new GameGUI()));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
