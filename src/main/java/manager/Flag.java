package manager;

import data.GameState;
import data.TimeKeeper;


public class Flag {
    private boolean isActive;
    private int triggerTime; // Time at which this flag should be evaluated
    private GameState gameState; // Reference to the game state to affect changes
    private GameMap gameMap; // Reference to the game map to trigger events

    public Flag(int triggerTime, GameState gameState, GameMap gameMap) {
        this.isActive = false;
        this.triggerTime = triggerTime;
        this.gameState = gameState;
        this.gameMap = gameMap;
    }

    // Method to activate the flag
    public void trigger() {
        if (!isActive) {
            isActive = true;
            increaseZombieSpawn(); // Custom method to handle the logic when the flag is triggered
        }
    }

    // Increase the number of zombies for each path
    private void increaseZombieSpawn() {
        // Example implementation: doubling the number of zombies
        // This should be tied into how zombies are spawned in your game logic
        System.out.println("Zombie wave increased!");
        gameMap.zombieSpawner();
    }

    // Check whether it's time to trigger this flag
    public void update() {
        if (TimeKeeper.getInstance().getCurrentTime() == triggerTime) {
            trigger();
        }
    }

    public boolean isActive() {
        return isActive;
    }

    public void reset() {
        isActive = false; // Reset the flag for future triggers
    }
}


