package gui;

import javax.swing.*;
import manager.DeckTanaman;
import manager.Inventory;
import java.awt.*;

public class GameGUI extends JFrame {
    private MainMenu mainMenu;
    private PlantList plantList;
    private ZombieList zombieList;
    private PlantSelectScreen plantSelectScreen;
    private HelpScreen helpScreen;  // Assuming you have a HelpScreen class

    public GameGUI() {
        initializeFrame();
        initializePanels();
        showMainMenu();
    }

    private void initializeFrame() {
        setTitle("Plants vs. Zombies");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setBackground(Color.BLACK);
        setVisible(true);
    }

    private void initializePanels() {
        mainMenu = new MainMenu(this);
        plantList = new PlantList(this);
        zombieList = new ZombieList(this);
        plantSelectScreen = new PlantSelectScreen(new DeckTanaman(), new Inventory());
        helpScreen = new HelpScreen(this);  // Initialize your help screen here
    }

    public void showMainMenu() {
        setPanel(mainMenu);
    }

    public void showPlantList() {
        setPanel(plantList);
    }

    public void showZombieList() {
        setPanel(zombieList);
    }

    public void showPlantSelectScreen() {
        setPanel(plantSelectScreen);
    }

    public void showHelpScreen() {
        setPanel(helpScreen);
    }

    private void setPanel(JPanel panel) {
        setContentPane(panel);
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        new GameGUI();  // Start the game GUI
    }
}
