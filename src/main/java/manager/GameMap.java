package manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import entity.*;
import entity.plant.Plant;
import entity.plant.PlantFactory;
import entity.zombie.Zombie;
import entity.zombie.ZombieFactory;
import util.ListOf;
import data.*;

// public class GameMap {
//     private ListOf<ListOf<Tile>> tiles;
//     private int width;
//     private int height;

//     public GameMap() {
//         this.width = 11;
//         this.height = 6;
//         initializeTiles();
//     }

//     private void initializeTiles() {
//         this.tiles = new ListOf<>();
//         for (int y = 0; y < height; y++) {
//             ListOf<Tile> row = new ListOf<>();
//             for (int x = 0; x < width; x++) {
//                 Tile tile = createTileByPosition(x, y);
//                 row.add(tile);
//             }
//             this.tiles.add(row);
//         }
//     }

//     // Factory method to create different types of tiles based on their location
//     private Tile createTileByPosition(int x, int y) {
//         if (y == 0) {
//             return new Tile(TileType.SPAWN_ZONE);  // Zombie spawn area
//         } else if (y == height - 1) {
//             return new Tile(TileType.TARGET_ZONE);  // Zombie target area
//         } else if (y == 3 || y == 4) {  // Assuming pool area is on rows 3 and 4
//             return new Tile(TileType.POOL);
//         } else {
//             return new Tile(TileType.REGULAR);  // Regular gameplay area
//         }
//     }

//     public void addEntity(int x, int y, Entity entity) {
//         tiles.get(y).get(x).addEntity(entity);
//     }

//     public void removeEntity(int x, int y, Entity entity) {
//         tiles.get(y).get(x).removeEntity(entity);
//     }

//     public ListOf<Entity> getEntitiesAt(int x, int y) {
//         return tiles.get(y).get(x).getEntities();
//     }

//     public ListOf<ListOf<Tile>> getTiles() {
//         return tiles;
//     }

//     public int getRows() {
//         return height;
//     }

//     public int getCols() {
//         return width;
//     }

//     public void moveZombies(){
//         for (int y = tiles.size() - 1; y >= 0; y--) {
//             for (int x = tiles.get(y).size() - 1; x >= 0; x--) {
//                 ListOf<Entity> tile = tiles.get(y).get(x).getEntities();
//                 ListOf<Entity> nextTile = x + 1 < tiles.get(y).size() ? tiles.get(y).get(x + 1).getEntities() : null;

//                 ListOf<Entity> toMove = new ListOf<>();
//                 for (int i = 0; i < tile.size(); i++) {
//                     if (tile.get(i) instanceof Zombie) {
//                         if (nextTile != null && !nextTile.contains(Plant.class, nextTile)) {
//                             toMove.add(tile.get(i));
//                         } else {
//                             ((Zombie) tile.get(i)).attack(tile.getAllOfType(Plant.class, tile));
//                         }
//                     }
//                 }

//                 for (int i = 0; i < toMove.size(); i++) {
//                     tile.remove(toMove.get(i));
//                     if (nextTile != null) {
//                         nextTile.add(toMove.get(i));
//                     }
//                 }
//             }
//         }
//     }

//     public void plantAttack(GameState gameState) {
//         for (int y = 0; y < tiles.size(); y++) {
//             for (int x = 0; x < tiles.get(y).size(); x++) {
//                 ListOf<Entity> tile = tiles.get(y).get(x).getEntities();
//                 for (int i = 0; i < tile.size(); i++) {
//                     if (tile.get(i) instanceof Plant) {
//                         ((Plant) tile.get(i)).attack(tile.getAllOfType(Zombie.class, tile), gameState.getTimeKeeper());
//                     }
//                 }
//             }
//         }
//     }

//     public void spawnZombies() {
//         Random random = new Random();
//         List<Integer> spawnLand = List.of(1, 2, 5, 6);
//         List<Integer> spawnWater = List.of(3, 4);
//         List<String> zombieTypes = List.of("Normal", "ConeHead", "Pole Vaulting", "Bucket Head", "Ducky Tube", "Dolphin Rider", "Door", "Football", "Flag", "Newspaper");
//         double spawnChance = 0.3;
//         if (random.nextDouble() < spawnChance) {
//             Zombie zombie = ZombieFactory.createZombie(zombieTypes.get(random.nextInt(zombieTypes.size())));
//             if (zombie.getName().equals("Ducky Tube") || zombie.getName().equals("Dolphin Rider")) {
//                 int randomIndex = random.nextInt(spawnWater.size());
//                 tiles.get(randomIndex).get(11).addEntity(zombie);
//             } else {
//                 int randomIndex = random.nextInt(spawnLand.size());
//                 tiles.get(randomIndex).get(11).addEntity(zombie);
//             }
//         }
//     }

//     public void spawnPlants(String plantType, int x, int y, GameState gameState) {
//         // Handler if sun is not enough
//         Plant plant = PlantFactory.createPlant(plantType);
//         if (plant.getCost() > gameState.getSunPoints()) {
//             return;
//         }

//         // Handler if plant is not allowed to be planted on the tile because there not lilypad
//         if (!canPlacePlant(tiles.get(y).get(x), plantType)){
//             return;
//         }

//         // Handler if plant is not allowed to be planted on the tile because there is already a plant except pumpkin
//         if (tiles.get(y).get(x).contains(Plant.class, tiles.get(y).get(x)) && !plantType.equals("Pumpkin")) {
//             return;
//         } 

//         // Deduct sun points and add plant to tile
//         gameState.setSunPoints(gameState.getSunPoints() - plant.getCost());
//         tiles.get(y).get(x).addEntity(plant);  
//     }

//     // Helper method to check if a plant can be placed on a given tile
//     private boolean canPlacePlant(Tile tile, String plantType) {
//         if (tile.getType() == TileType.POOL && !plantType.equals("Lilypad") && !tile.contains("Lilypad")) {
//             return false;  // Only Lilypad or plants on Lilypads can be placed in pool tiles
//         }
//         return true;
//     }

//     public void update() {
//     }

//     public boolean checkForGameOverConditions() {
//         // Check if zombies have reached the target zone
//         for (int y = 0; y < tiles.size(); y++) {
//             ListOf<Entity> targetZone = tiles.get(y).get(0).getEntities();
//             for (int i = 0; i < targetZone.size(); i++) {
//                 if (targetZone.get(i) instanceof Zombie) {
//                     return true;
//                 }
//             }
//         }
//         return false;
//     }

// }

public class GameMap {
    private Tile[][] grid;
    private final int rows = 6;
    private final int cols = 11;

    public GameMap() {
        grid = new Tile[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = new Tile();
            }
        }
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public void addPlant(int row, int col, Plant plant) {
        grid[row][col].addPlant(plant);
    }

    public void addZombie(int row, int col, Zombie zombie) {
        grid[row][col].addZombie(zombie);
    }

    public ListOf<Plant> getPlants(int row, int col) {
        return grid[row][col].getPlants();
    }

    public ListOf<Zombie> getZombies(int row, int col) {
        return grid[row][col].getZombies();
    }

    public Tile getTile(int row, int col) {
        return grid[row][col];
    }

    public ListOf<Plant> getAllPlants() {
        ListOf<Plant> plants = new ListOf<Plant>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                plants.addAll(grid[i][j].getPlants());
            }
        }
        return plants;
    }

    public ListOf<Zombie> getAllZombies() {
        ListOf<Zombie> zombies = new ListOf<Zombie>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                zombies.addAll(grid[i][j].getZombies());
            }
        }
        return zombies;
    }

    public void moveZombies() {
        for (int row = rows - 1; row >= 0; row--) {
            for (int col = cols - 1; col >= 0; col--) {
                Tile currentTile = grid[row][col];
                Tile nextTile = col + 1 < cols ? grid[row][col + 1] : null;

                ListOf<Zombie> toMove = new ListOf<Zombie>();
                for (int i = 0; i < currentTile.getZombies().size(); i++) {
                    Zombie zombie = currentTile.getZombies().get(i);
                    if (nextTile != null && nextTile.getPlants().isEmpty()) {
                        toMove.add(zombie);
                    } else {
                        zombie.attackPlants(currentTile);
                    }
                }

                for (int i = 0; i < toMove.size(); i++) {
                    currentTile.removeZombie(toMove.get(i));
                    if (nextTile != null) {
                        nextTile.addZombie(toMove.get(i));
                    }
                }
            }
        }
    }

    public void plantAttack() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Tile currentTile = grid[row][col];
                for (int i = 0; i < currentTile.getPlants().size(); i++) {
                    Plant plant = currentTile.getPlants().get(i);
                    plant.attackZombies(currentTile);
                }
            }
        }
    }

    public void zombieSpawner(){
        Random random = new Random();
        ListOf<Integer> spawnLand = new ListOf<Integer>();
        spawnLand.add(1);
        spawnLand.add(2);
        spawnLand.add(5);
        spawnLand.add(6);
        ListOf<Integer> spawnWater = new ListOf<Integer>();
        spawnWater.add(3);
        spawnWater.add(4);
        ListOf<String> zombieTypes = new ListOf<String>();
        zombieTypes.add("Normal");
        zombieTypes.add("ConeHead");
        zombieTypes.add("Pole Vaulting");
        zombieTypes.add("Bucket Head");
        zombieTypes.add("Ducky Tube");
        zombieTypes.add("Dolphin Rider");
        zombieTypes.add("Door");
        zombieTypes.add("Football");
        zombieTypes.add("Flag");
        zombieTypes.add("Newspaper");
        double spawnChance = 0.3;
        if (random.nextDouble() < spawnChance) {
            Zombie zombie = ZombieFactory.createZombie(zombieTypes.get(random.nextInt(zombieTypes.size())));
            if (zombie.getName().equals("Ducky Tube") || zombie.getName().equals("Dolphin Rider")) {
                int randomIndex = random.nextInt(spawnWater.size());
                grid[randomIndex][11].addZombie(zombie);
            } else {
                int randomIndex = random.nextInt(spawnLand.size());
                grid[randomIndex][11].addZombie(zombie);
            }
        }
    }

    public void plantSpawner(String plantType, int row, int col, GameState gameState) {
        Plant plant = PlantFactory.createPlant(plantType);
        if (plant.getCost() > gameState.getSunPoints()) {
            return;
        }

        if (!canPlacePlant(col, plantType, grid[row][col])){
            return;
        }

        if (!grid[row][col].getPlants().isEmpty() && !plantType.equals("Pumpkin")) {
            return;
        } 

        gameState.setSunPoints(gameState.getSunPoints() - plant.getCost());
        grid[row][col].addPlant(plant);  
    }

    private boolean canPlacePlant(int col, String plantType, Tile tile) {
        if (col == 3 && col == 4 && !plantType.equals("Lilypad") && !tile.getPlants().contains("Lilypad")) {
            return false;
        }
        return true;
    }

    public boolean checkForGameOverConditions() {
        for (int row = 0; row < rows; row++) {
            ListOf<Zombie> targetZone = grid[row][0].getZombies();
            if (!targetZone.isEmpty()) {
                return true;
            }
        }
        return false;
    }
}

