package gui;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import manager.DeckTanaman;
import manager.Inventory;
import java.awt.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class GameGUI extends JFrame {
    private MainMenu mainMenu;
    private PlantList plantList;
    private ZombieList zombieList;
    private PlantSelectScreen plantSelectScreen;
    private HelpScreen helpScreen;  // Assuming you have a HelpScreen class

    public GameGUI() {
        initializeFrame();
        initializePanels();
        playBackgroundSound("/resources/sound/backsound.wav");
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
        plantSelectScreen = new PlantSelectScreen(new DeckTanaman(), new Inventory(), this);
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

    private void playBackgroundSound(String resourcePath) {
        try {
            // Use getClass().getResourceAsStream inside AudioSystem to get an AudioInputStream
            InputStream audioSrc = getClass().getResourceAsStream(resourcePath);
            // Check if the input stream is null
            if (audioSrc == null) {
                System.err.println("Resource not found: " + resourcePath);
                return;
            }
            InputStream bufferedIn = new BufferedInputStream(audioSrc);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(bufferedIn);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        new GameGUI();  // Start the game GUI
    }
}
