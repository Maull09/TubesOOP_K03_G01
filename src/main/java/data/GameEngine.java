package data;

import javax.swing.*;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import gui.GameGUI;

public class GameEngine {
    private volatile boolean running; // Flag to control the game loop
    private final GameGUI gameGUI;    // Reference to the main GUI
    private Timer timer;              // Timer to schedule updates and repaints

    public GameEngine(GameState gameState, GameGUI gameGUI) {
        this.gameGUI = gameGUI;
    }

    // Starts the game loop using Timer
    public void start() {
        running = true;
        showMainMenu();
    }

    private void showMainMenu() {
        SwingUtilities.invokeLater(() -> gameGUI.showMainMenu());
        waitForGameScreen();
    }

    private void waitForGameScreen() {
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                while (!gameGUI.isGameScreenDisplayed()) {
                    Thread.sleep(100); // Check every 100ms
                }
                return null;
            }

            @Override
            protected void done() {
                startGameLoop();
            }
        }.execute();
    }

    private void startGameLoop() {
        long interval = 1000; // 1 second

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (running) {
                    update(); // Update game state
                    repaint(); // Repaint GUI
                }
            }
        }, 0, interval);

        scheduleSunAddition();
    }

    // Stops the game loop
    public void stop() {
        running = false;
        if (timer != null) {
            timer.cancel();
        }
    }

    // Updates the game state
    private void update() {
        GameState.getInstance().update(); // Update all components of the game
    }

    // Repaints the GUI on the Event Dispatch Thread (EDT)
    private void repaint() {
        SwingUtilities.invokeLater(() -> gameGUI.getGameScreen().repaint());
    }

    // Schedule sun addition every 5 to 10 seconds
    private void scheduleSunAddition() {
        Random random = new Random();
        int initialDelay = random.nextInt(6) + 5; // Initial delay between 5 to 10 seconds

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                int currentTime = TimeKeeper.getInstance().getCurrentTime();
                if (TimeKeeper.getInstance().isDaytime()){
                    GameState.getInstance().addSunPoints(25);
                    System.out.println("Added sun points. Current time: " + currentTime);
                } else {
                    System.out.println("No sun points added. Current time: " + currentTime);
                }
            }
        }, initialDelay * 1000, 5000); // Reschedule every 5 seconds
    }
}
