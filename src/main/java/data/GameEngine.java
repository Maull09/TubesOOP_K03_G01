package data;

public class GameEngine implements Runnable {
    private Thread gameThread;        // Thread on which the game loop runs
    private GameState gameState;      // Shared game state
    private volatile boolean running; // Flag to control the game loop
    private final long frameRate;     // Target frame rate, e.g., 60 FPS

    public GameEngine(GameState gameState, long frameRate) {
        this.gameState = gameState;
        this.frameRate = frameRate;
    }

    // Starts the game loop in a new thread
    public void start() {
        if (gameThread == null || !gameThread.isAlive()) {
            gameThread = new Thread(this);
            running = true;
            gameThread.start(); // Start the thread, which invokes the run method
        }
    }

    // The main game loop
    @Override
    public void run() {
        long lastTime = System.nanoTime();
        final double ns = 1000000000 / frameRate;
        double delta = 0;

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while (delta >= 1) {
                update(); // Update game state
                delta--;
            }
        }
    }

    // Updates the game state
    private void update() {
        gameState.update(); // Update all components of the game
    }

    // Stops the game loop
    public void stop() {
        running = false;
        try {
            gameThread.join(); // Wait for the thread to finish executing
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Failed to stop game thread gracefully");
        }
    }
}

