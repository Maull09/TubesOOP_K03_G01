package main;

import data.GameState;
import gui.GameGUI;

import javax.swing.SwingUtilities;

import data.GameEngine;


public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            System.out.println("Initializing GameState...");
            GameState gameState = GameState.getInstance();
            System.out.println("Initializing GameGUI...");
            GameGUI gameGUI = new GameGUI();
            System.out.println("Starting GameEngine...");
            GameEngine gameEngine = new GameEngine(gameState, gameGUI); // Assuming 60 FPS
            gameEngine.start();
            System.out.println("Setting GameGUI visible...");
            gameGUI.setVisible(true);
        });
    }
}


