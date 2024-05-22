package data;

import manager.*;
import util.*;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import entity.*;
import gui.GameGUI;

public class GameState {
    private GameMap gameMap;
    private DeckTanaman deck;
    private Inventory inventory;
    private Sun sunPoints;
    private ListOf<Flag> flags;
    private Map<String, Long> plantCooldowns;

    private static GameState instance;

    public GameState() {
        this.gameMap = new GameMap();
        this.deck = new DeckTanaman();
        this.inventory = new Inventory();
        this.sunPoints = new Sun();
        this.plantCooldowns = new HashMap<>();
    }

    public static synchronized GameState getInstance() {
        if (instance == null) {
            instance = new GameState();
        }
        return instance;
    }

    public void update() {
        TimeKeeper.getInstance().update();
        System.out.println("Current Time: " + TimeKeeper.getInstance().getCurrentTime());
        flags.getAll().forEach(Flag::update);
        spawnZombie();
        processPlantActions();
        processZombieActions();
        processProjectiles();
        // updateGameMap();
        checkGameOver();
    }
    
    private void spawnZombie() {
        if (TimeKeeper.getInstance().isZombieSpawnTime()){
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

    private void checkGameOver() {
        // Check conditions that would end the game
        if (gameMap.checkForGameOverConditions()) {
            stopGame();
            reset();
            if (gameMap.WinCondition()){
                GameGUI.getInstance().showWinScreen();
            } else {
                GameGUI.getInstance().showLoseScreen();
            }
            stopGame();
        }
    }

    // Metode untuk menyimpan game
    public void saveGame(String filePath) {
        try (FileOutputStream fileOut = new FileOutputStream(filePath);
            ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(this);
            System.out.println("Game saved successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void stopGame() {
        GameEngine.getInstance().stop();
        // Perform actions to end the game
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

    public Map<String, Long> getPlantCooldowns() {
        return plantCooldowns;
    }

    public void reset(){
        instance = new GameState();
    }
}
