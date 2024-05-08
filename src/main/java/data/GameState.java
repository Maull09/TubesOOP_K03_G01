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

    public GameState() {
        this.gameMap = new GameMap(11, 6);
        this.deck = new DeckTanaman();
        this.inventory = new Inventory();
        this.sunPoints = new Sun();
        this.timeKeeper = new TimeKeeper();
        this.flags = new ListOf<>();
        initializeFlags();
    }

    private void initializeFlags() {
        // Initialize flag to trigger at a specific time, assuming it triggers daily at a set time
        this.flags.add(new Flag(150, this)); // Example trigger time
    }

    public void update() {
        timeKeeper.update();
        flags.getAll().forEach(Flag::update); // Update all flags to check their triggers
        // Additional updates for gameMap, entities, etc.
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
