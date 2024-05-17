package gui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import manager.DeckTanaman;
import util.ListOf;
import data.GameState;
import data.TimeKeeper;
import entity.Projectile;
import entity.plant.Plant;
import entity.plant.PlantFactory;
import entity.zombie.Zombie;
import entity.zombie.ZombieFactory;

public class GamePanel extends JPanel implements ActionListener{
    private Image backgroundImage;
    private final int CELL_SIZE = 96;
    private final int startX = 8;
    private final int startY = 42;
    private GameGUI gameGUI;
    private JPanel topBarPanel;
    private JPanel deckPanel;
    private JLabel sunPointsLabel;
    private String selectedPlant;
    private Timer timer = new Timer(1000, this);

    public GamePanel(GameGUI gameGUI) {
        this.gameGUI = gameGUI;
        loadImage();
        setPreferredSize(new Dimension(1280, 720));  
        setLayout(new BorderLayout());
        drawMenuButton();
        drawTopBar();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                handleMouseClick(e.getX(), e.getY());
            }
        });

        timer.start();

    }

    private void loadImage() {
        try {
            backgroundImage = new ImageIcon(getClass().getResource("/resources/images/background/PoolBg.png")).getImage();
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

    @Override
    public void actionPerformed(ActionEvent ev) {
        if (ev.getSource() == timer) {
            repaint(); // This will call repaint every 1 second
        }
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
        sunPointsLabel = new JLabel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    drawSunPoints(g);
                }
        };
        sunPointsLabel.setPreferredSize(new Dimension(100, 100));
        sunPointsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        deckPanel.add(sunPointsLabel);

        // membuat panel untuk tanaman
        JPanel plantPanel = new JPanel();
        plantPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 8, -26));
        plantPanel.setPreferredSize(new Dimension(505, 121));  // Adjust size as needed
        plantPanel.setOpaque(false);

        // Menambahkan tanaman ke deckPanel
        DeckTanaman plantDeck = GameState.getInstance().getDeck();

        // // isi deck tanaman
        // plantDeck.addPlant(PlantFactory.createPlant("Sunflower"));
        // plantDeck.addPlant(PlantFactory.createPlant("Peashooter"));
        // plantDeck.addPlant(PlantFactory.createPlant("Wallnut"));
        // plantDeck.addPlant(PlantFactory.createPlant("SnowPea"));
        // plantDeck.addPlant(PlantFactory.createPlant("Repeater"));
        // plantDeck.addPlant(PlantFactory.createPlant("Lilypad"));

        // Menambahkan tanaman ke deckPanel
        for (int i = 0; i < plantDeck.getPlants().size(); i++) {
            String plant = plantDeck.getPlants().get(i).getName();
            ImageIcon plantIcon = new ImageIcon(getClass().getResource("/resources/images/cards/cards_" + plant + ".png"));
            JButton plantButton = new JButton(plantIcon);
            plantButton.setPreferredSize(new Dimension(76, 144));
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

        
        if (selectedPlant != null) {
            // Calculate the cell that was clicked
            int col = ((x - startX) / CELL_SIZE) - 2;
            int row = ((y - startY) / CELL_SIZE) - 1;
            
            if (col >= 1 && col < 10 && row >= 0 && row < 6) {  // Adjust according to the grid range
                GameState.getInstance().getGameMap().plantSpawner(selectedPlant, row, col, GameState.getInstance());
                selectedPlant = null;
                
                // Draw a debug rectangle
                Graphics g = getGraphics();
                g.setColor(Color.RED);
                g.drawRect(startX + (col + 2) * CELL_SIZE, startY + (row + 1) * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }
        }
    }

    private void drawSunPoints(Graphics g) {
        // Draw sun points
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("" + GameState.getInstance().getSunPoints(), 30, 80);
    }

    private void drawProgressBar(Graphics g) {
        int x = 83;
        int y = 30; // Vertical position of the bar
        int totalDuration = TimeKeeper.DAY_LENGTH; // Total duration in seconds
        int maxWidth = 71 * 4; // Maximum width of the progress bar

        // Get current time from TimeKeeper
        int currentTime = TimeKeeper.getInstance().getCurrentTime();

        // Calculate the width of the progress bar based on current time
        int currentWidth = (int) ((double) currentTime / totalDuration * maxWidth);

        // Draw the progress bar
        g.setColor(Color.RED);
        g.fillRect(x, y, currentWidth, 29);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, maxWidth, 29);
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

        // Draw grid
        for (int row = 1; row < 7; row++) {
            for (int col = 2; col < 13; col++) {
                g.drawRect(startX + col * CELL_SIZE, startY + row * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }
        }
    }

    private void drawPlants(Graphics g) {
        // Draw every plant in the grid
        for(int i = 0; i < 6; i++){
            for(int j = 1; j < 10; j++){
                ListOf<Plant> plants = GameState.getInstance().getGameMap().getTile(i, j).getPlants();
                for(int k = 0; k < plants.size(); k++){
                    Plant plant = plants.get(k);
                    ImageIcon planticon = new ImageIcon(getClass().getResource("/resources/images/plants/" + plant.getName() + ".png"));
                    g.drawImage(planticon.getImage(), startX + (j + 2) * CELL_SIZE, startY + (i + 1) * CELL_SIZE, 90, 90, this);
                }
            }
        }
    }

    private void drawZombies(Graphics g) {

        // // dummy zombie
        // Zombie dummy1 = ZombieFactory.createZombie("Normal", 0, 10);
        // Zombie dummy2 = ZombieFactory.createZombie("ConeHead", 1, 10);
        // Zombie dummy3 = ZombieFactory.createZombie("Pole Vaulting", 2, 10);

        // // add dummy zombie to the game map
        // GameState.getInstance().getGameMap().getTile(0, 10).addZombie(dummy1);
        // GameState.getInstance().getGameMap().getTile(1, 10).addZombie(dummy2);
        // GameState.getInstance().getGameMap().getTile(2, 10).addZombie(dummy3);

        for (int i = 0; i < GameState.getInstance().getGameMap().getRows(); i++) {
            for (int j = 0; j < GameState.getInstance().getGameMap().getCols(); j++) {
                ListOf<Zombie> zombies = GameState.getInstance().getGameMap().getTile(i, j).getZombies();

                for (int k = 0; k < zombies.size(); k++) {
                    Zombie zombie = zombies.get(k);
                    double partialMove = zombie.getPartialMove();
                    int offset = (int) (partialMove * CELL_SIZE); // Calculate offset based on partial move
                    ImageIcon zombieIcon = new ImageIcon(getClass().getResource("/resources/images/zombie/" + zombie.getName() + ".png"));
                    g.drawImage(zombieIcon.getImage(), startX + (j + 2) * CELL_SIZE - offset, startY + (i + 1) * CELL_SIZE, 90, 90, this);
                }
            }
        }
    }

    private void drawProjectiles(Graphics g) {
        // Implement drawing projectiles based on gameState

        // Draw every projectile in the grid
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 11; j++){
                ListOf<Projectile> projectiles = GameState.getInstance().getGameMap().getTile(i, j).getProjectiles();
                for(int k = 0; k < projectiles.size(); k++){
                    Projectile projectile = projectiles.get(k);
                    boolean hitZombie = false;

                    // Check if the projectile hits a zombie
                    ListOf<Zombie> zombies = GameState.getInstance().getGameMap().getTile(i, j).getZombies();
                    if (!zombies.isEmpty()) {
                        hitZombie = true;
                    }

                    // If the projectile hits a zombie, remove the projectile
                    if (hitZombie) {
                        projectiles.remove(projectile);
                    } else {
                        ImageIcon projectileicon = new ImageIcon(getClass().getResource("/resources/images/background/" + projectile.getType() + ".png"));
                        g.drawImage(projectileicon.getImage(), (startX + (j + 2) * CELL_SIZE) + 53, (startY + (i + 1) * CELL_SIZE) + 5, 33, 30, this);                      
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Plants vs. Zombies");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new GamePanel(new GameGUI()));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}