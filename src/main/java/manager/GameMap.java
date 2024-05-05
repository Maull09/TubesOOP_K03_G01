package manager;

import java.util.ArrayList;
import java.util.List;
import entity.*;


public class GameMap {
    private List<List<Tile>> tiles;
    private int width;
    private int height;

    public GameMap(int width, int height) {
        this.width = width;
        this.height = height;
        initializeTiles();
    }

    private void initializeTiles() {
        this.tiles = new ArrayList<>();
        for (int y = 0; y < height; y++) {
            List<Tile> row = new ArrayList<>();
            for (int x = 0; x < width; x++) {
                Tile tile = createTileByPosition(x, y);
                row.add(tile);
            }
            this.tiles.add(row);
        }
    }

    // Factory method to create different types of tiles based on their location
    private Tile createTileByPosition(int x, int y) {
        if (y == 0) {
            return new Tile(TileType.SPAWN_ZONE);  // Zombie spawn area
        } else if (y == height - 1) {
            return new Tile(TileType.TARGET_ZONE);  // Zombie target area
        } else if (y == 3 || y == 4) {  // Assuming pool area is on rows 3 and 4
            return new Tile(TileType.POOL);
        } else {
            return new Tile(TileType.REGULAR);  // Regular gameplay area
        }
    }

    public void addEntity(int x, int y, Entity entity) {
        tiles.get(y).get(x).addEntity(entity);
    }

    public void removeEntity(int x, int y, Entity entity) {
        tiles.get(y).get(x).removeEntity(entity);
    }

    public List<Entity> getEntitiesAt(int x, int y) {
        return tiles.get(y).get(x).getEntities();
    }

    // Additional methods for handling specific tile logic could be added here
}

// Tile class to handle different tile behaviors
class Tile {
    private TileType type;
    private List<Entity> entities;

    public Tile(TileType type) {
        this.type = type;
        this.entities = new ArrayList<>();
    }

    public void addEntity(Entity entity) {
        // Example: Only allow water-based plants in POOL type tiles
        if (this.type == TileType.POOL) {
            entities.add(entity);
        } else if (this.type != TileType.POOL) {
            entities.add(entity);
        }
    }

    public void removeEntity(Entity entity) {
        entities.remove(entity);
    }

    public List<Entity> getEntities() {
        return entities;
    }
}

// Enum to define tile types
enum TileType {
    REGULAR, POOL, SPAWN_ZONE, TARGET_ZONE
}

