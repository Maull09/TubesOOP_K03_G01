package manager;

import java.util.List;
import java.util.Random;

import entity.*;
import entity.plant.Plant;
import entity.plant.PlantFactory;
import entity.zombie.Zombie;
import entity.zombie.ZombieFactory;
import util.ListOf;
import data.*;

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

    public void spawnZombies() {
        Random random = new Random();
        List<Integer> spawnLand = List.of(1, 2, 5, 6);
        List<Integer> spawnWater = List.of(3, 4);
        List<String> zombieTypes = List.of("Normal", "ConeHead", "Pole Vaulting", "Bucket Head", "Ducky Tube", "Dolphin Rider", "Door", "Football", "Flag", "Newspaper");
        double spawnChance = 0.3;
        if (random.nextDouble() < spawnChance) {
            Zombie zombie = ZombieFactory.createZombie(zombieTypes.get(random.nextInt(zombieTypes.size())));
            if (zombie.getName().equals("Ducky Tube") || zombie.getName().equals("Dolphin Rider")) {
                int randomIndex = random.nextInt(spawnWater.size());
                tiles.get(randomIndex).get(11).addEntity(zombie);
            } else {
                int randomIndex = random.nextInt(spawnLand.size());
                tiles.get(randomIndex).get(11).addEntity(zombie);
            }
        }
    }

    public void spawnPlants(String plantType, int x, int y, GameState gameState) {
        // Handler if sun is not enough
        Plant plant = PlantFactory.createPlant(plantType);
        if (plant.getCost() > gameState.getSunPoints()) {
            return;
        }

        // Handler if plant is not allowed to be planted on the tile because there not lilypad
        if (!canPlacePlant(tiles.get(y).get(x), plantType)) {
            return;
        }

        // Deduct sun points and add plant to tile
        gameState.setSunPoints(gameState.getSunPoints() - plant.getCost());
        tiles.get(y).get(x).addEntity(plant);  
    }

    // Helper method to check if a plant can be placed on a given tile
    private boolean canPlacePlant(Tile tile, String plantType) {
        if (tile.getType() == TileType.POOL && !plantType.equals("Lilypad") && !tile.contains("Lilypad")) {
            return false;  // Only Lilypad or plants on Lilypads can be placed in pool tiles
        }
        // Add other specific conditions if necessary
        return true;
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

