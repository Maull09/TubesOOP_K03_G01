package manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import entity.Projectile;
import entity.plant.Plant;
import entity.plant.PlantFactory;
import entity.zombie.Zombie;
import entity.zombie.ZombieFactory;
import util.ListOf;
import data.*;

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

    public void addProjectile(Projectile projectile) {
        grid[projectile.getRow()][projectile.getCol()].addProjectile(projectile);
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

        if (col == 0 || col == 11) {
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

    public void moveProjectiles() {
        for (int row = 0; row < rows; row++) {
            for (int col = cols - 1; col >= 0; col--) {
                Tile currentTile = grid[row][col];
                ListOf<Projectile> projectilesToMove = currentTile.getProjectiles();

                for (int i = 0; i < projectilesToMove.size(); i++) {
                    Projectile projectile = projectilesToMove.get(i);
                    currentTile.removeProjectile(projectile);
                    int newCol = projectile.getCol() + 1;
                    if (newCol < cols) {
                        Tile nextTile = grid[row][newCol];
                        nextTile.addProjectile(projectile);
                        projectile.move();
                    }
                }
            }
        }
    }
}

