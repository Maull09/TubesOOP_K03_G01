package manager;

import entity.*;
import entity.plant.Plant;
import entity.zombie.Zombie;
import util.ListOf;


public class GameMap {
    private ListOf<ListOf<Tile>> tiles;
    private int width;
    private int height;

    public GameMap(int width, int height) {
        this.width = width;
        this.height = height;
        initializeTiles();
    }

    private void initializeTiles() {
        this.tiles = new ListOf<>();
        for (int y = 0; y < height; y++) {
            ListOf<Tile> row = new ListOf<>();
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

    public ListOf<Entity> getEntitiesAt(int x, int y) {
        return tiles.get(y).get(x).getEntities();
    }

    public void moveZombies(){
        for (int y = tiles.size() - 1; y >= 0; y--) {
            for (int x = tiles.get(y).size() - 1; x >= 0; x--) {
                ListOf<Entity> tile = tiles.get(y).get(x).getEntities();
                ListOf<Entity> nextTile = x + 1 < tiles.get(y).size() ? tiles.get(y).get(x + 1).getEntities() : null;

                ListOf<Entity> toMove = new ListOf<>();
                for (int i = 0; i < tile.size(); i++) {
                    if (tile.get(i) instanceof Zombie) {
                        if (nextTile != null && !nextTile.contains(Plant.class, nextTile)) {
                            toMove.add(tile.get(i));
                        } else {
                            ((Zombie) tile.get(i)).attack(tile.getAllOfType(Plant.class, tile));
                        }
                    }
                }

                for (int i = 0; i < toMove.size(); i++) {
                    tile.remove(toMove.get(i));
                    if (nextTile != null) {
                        nextTile.add(toMove.get(i));
                    }
                }
            }
        }
    }

    public void plantAttack() {
        for (int y = 0; y < tiles.size(); y++) {
            for (int x = 0; x < tiles.get(y).size(); x++) {
                ListOf<Entity> tile = tiles.get(y).get(x).getEntities();
                for (int i = 0; i < tile.size(); i++) {
                    if (tile.get(i) instanceof Plant) {
                        ((Plant) tile.get(i)).attack(tile.getAllOfType(Zombie.class, tile));
                    }
                }
            }
        }
    }

    public void zombieAttack() {
        
    }

    public void spawnZombies() {
        
    }

    public void spawnPlants() {
        
    }

    public void update() {
    }

    public boolean checkForGameOverConditions() {


        return false;
    }

}

// Enum to define tile types
enum TileType {
    REGULAR, POOL, SPAWN_ZONE, TARGET_ZONE
}

