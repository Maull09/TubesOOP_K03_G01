package manager;

import data.*;


public class Flag {
    private boolean isActive;
    private int triggerTime; // Time at which this flag should be evaluated
    private GameState gameState; // Reference to the game state to affect changes

    public Flag(int triggerTime, GameState gameState) {
        this.isActive = false;
        this.triggerTime = triggerTime;
        this.gameState = gameState;
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
        // gameState.spawnAdditionalZombies(); // This method should be implemented in GameState
    }

    // Check whether it's time to trigger this flag
    public void update() {
        if (gameState.getTimeKeeper().getCurrentTime() == triggerTime) {
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


