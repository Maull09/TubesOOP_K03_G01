package data;

import manager.*;
import util.*;
import entity.*;

public class GameState {
    private GameMap gameMap;
    private DeckTanaman deck;
    private Inventory inventory;
    private Sun sunPoints;
    private TimeKeeper timeKeeper;
    private ListOf<Flag> flags;

    private static GameState instance;

    public GameState() {
        this.gameMap = new GameMap();
        this.deck = new DeckTanaman();
        this.inventory = new Inventory();
        this.sunPoints = new Sun();
        this.timeKeeper = TimeKeeper.getInstance();
        this.flags = new ListOf<Flag>();
        initializeFlags();
    }

    public static synchronized GameState getInstance() {
        if (instance == null) {
            instance = new GameState();
        }
        return instance;
    }

    private void initializeFlags() {
        // Initialize flag to trigger at a specific time, assuming it triggers daily at a set time
        this.flags.add(new Flag(130, this, gameMap)); 
    }

    public void update() {
        timeKeeper.update();
        System.out.println("Current Time: " + timeKeeper.getCurrentTime());
        // flags.getAll().forEach(Flag::update);
        spawnZombie();
        processZombieActions();
        processPlantActions();
        processProjectiles();
        // updateGameMap();
        checkGameOver();
    }
    
    private void spawnZombie() {
        // Zombies spawn
        if (timeKeeper.isZombieSpawnTime()){
            gameMap.zombieSpawner();
        }
    }

    private void processZombieActions() {
        // Zombies move and attack 
        gameMap.moveZombies();
    }

    private void processPlantActions() {
        // Plants perform actions 
        gameMap.plantAttack();
    }

    private void processProjectiles() {
        // Projectiles move and attack
        gameMap.moveProjectiles();
    }

    // private void updateGameMap() {
    //     // Update the visual or state representation of the map
    //     // Could be simplified or elaborated based on actual game mechanics
    //     gameMap.update();
    // }

    private void checkGameOver() {
        // Check conditions that would end the game
        if (gameMap.checkForGameOverConditions()) {
            stopGame();
        }
    }

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

    public void setTimeKeeper(TimeKeeper timeKeeper) {
        this.timeKeeper = timeKeeper;
    }
}
