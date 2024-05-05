package data;

public class TimeKeeper {
    private int currentTime;
    private static final int DAY_LENGTH = 200; // Total seconds in a day
    private static final int NIGHT_START = 100; // Night starts at 100 seconds
    private static final int ZOMBIE_SPAWN_START = 20; // Zombies start spawning at 20 seconds
    private static final int ZOMBIE_SPAWN_END = 160; // Zombies stop spawning at 160 seconds

    public TimeKeeper() {
        this.currentTime = 0; // Start time at the beginning of the day
    }

    public void update() {
        currentTime = (currentTime + 1) % DAY_LENGTH; // Increment time and loop back every day
    }

    public boolean isDaytime() {
        return currentTime < NIGHT_START;
    }

    public boolean isZombieSpawnTime() {
        return currentTime >= ZOMBIE_SPAWN_START && currentTime <= ZOMBIE_SPAWN_END;
    }

    public int getCurrentTime() {
        return currentTime;
    }
}

