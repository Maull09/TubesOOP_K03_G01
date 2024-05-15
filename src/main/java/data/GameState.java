package data;

import manager.*;
import util.*;
import entity.*;
import java.util.Random;

public class GameState {
    private GameMap gameMap;
    private DeckTanaman deck;
    private Inventory inventory;
    private Sun sunPoints;
    private TimeKeeper timeKeeper;
    private ListOf<Flag> flags;

    public GameState() {
        this.gameMap = new GameMap();
        this.deck = new DeckTanaman();
        this.inventory = new Inventory();
        this.sunPoints = new Sun();
        this.timeKeeper = new TimeKeeper();
        this.flags = new ListOf<Flag>();
        initializeFlags();
    }

    private void initializeFlags() {
        // Initialize flag to trigger at a specific time, assuming it triggers daily at a set time
        this.flags.add(new Flag(130, this, gameMap)); 
    }

    public void update() {
        timeKeeper.update();
        flags.getAll().forEach(Flag::update);
        Random random = new Random();
        int spawnsun = random.nextInt(10) + 1;
        if (timeKeeper.getCurrentTime() % spawnsun == 0) {
            addSunPoints(25);
        }
        // spawnZombie();
        // processZombieActions();
        // processPlantActions();
        // updateGameMap();
        // checkGameOver();
    }
    
    // private void spawnZombie() {
    //     gameMap.spawnZombies();
    // }

    // private void processZombieActions() {
    //     // Zombies move or attack every 5 seconds
    //     gameMap.moveZombies();
    // }

    // private void processPlantActions() {
    //     // Plants perform actions (like shooting)
    //     gameMap.plantAttack(this);
    // }

    // private void updateGameMap() {
    //     // Update the visual or state representation of the map
    //     // Could be simplified or elaborated based on actual game mechanics
    //     gameMap.update();
    // }

    // private void checkGameOver() {
    //     // Check conditions that would end the game
    //     if (gameMap.checkForGameOverConditions()) {
    //         stopGame();
    //     }
    // }

    private void stopGame() {
        // Perform actions to end the game
        // Could be showing a game over screen, saving high scores, etc.
        System.out.println("Game Over!");
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public DeckTanaman getDeck() {
        return deck;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public int getSunPoints() {
        return sunPoints.getTotalSun();
    }

    public void addSunPoints(int amount) {
        sunPoints.addSun(amount);
    }

    public void setSunPoints(int amount) {
        sunPoints.setTotalSun(amount);
    }

    public TimeKeeper getTimeKeeper() {
        return timeKeeper;
    }
}
