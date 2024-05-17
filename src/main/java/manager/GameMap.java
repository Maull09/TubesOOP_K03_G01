package manager;

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
    private static final int MAX_ZOMBIES = 10;

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

    private int getTotalZombies() {
        int totalZombies = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                totalZombies += grid[i][j].getZombies().size();
            }
        }
        return totalZombies;
    }

    public void moveZombies() {
        int currentTime = TimeKeeper.getInstance().getCurrentTime();
        for (int row = rows - 1; row >= 0; row--) {
            for (int col = cols - 1; col >= 0; col--) {
                Tile currentTile = grid[row][col];
                Tile nextTile = col + 1 < cols ? grid[row][col + 1] : null;
                
                for (int i = 0; i < currentTile.getZombies().size(); i++) {
                    Zombie zombie = currentTile.getZombies().get(i);
                    if (zombie.canMove(currentTime)) {
                        if (nextTile != null && nextTile.getPlants().isEmpty()) {
                            currentTile.removeZombie(zombie);
                            nextTile.addZombie(zombie);
                            zombie.move();
                            System.out.println("Zombie yang bergerak: " + zombie.getName());
                            System.out.println("Posisi Zombie Bergerak: " + zombie.getRow() + ", " + zombie.getCol());
                        } else {
                            zombie.attackPlants(currentTile);
                        }
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
                    plant.attackZombies();
                }
            }
        }
    }

    public void zombieSpawner(){
        if (getTotalZombies() >= MAX_ZOMBIES) {
            return; // Do not spawn new zombies if the limit is reached
        }

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
        double chance = random.nextDouble();
        if (chance > spawnChance) {
            System.out.println("SpawnChance: " + chance);
            Zombie zombie = ZombieFactory.createZombie(zombieTypes.get(random.nextInt(zombieTypes.size())), random.nextInt(rows), 10);
            if (zombie.getName().equals("Ducky Tube") || zombie.getName().equals("Dolphin Rider")) {
                int randomIndex = random.nextInt(spawnWater.size());
                grid[randomIndex][10].addZombie(zombie);
                System.out.println("Zombie yang spawn: " + zombie.getName());;
                System.out.println("Posisi Zombie: " + zombie.getRow() + ", " + zombie.getCol()); // Zombie position
            } else {
                int randomIndex = random.nextInt(spawnLand.size());
                grid[randomIndex][10].addZombie(zombie);
                System.out.println("Zombie yang spawn: " + zombie.getName());;
                System.out.println("Posisi Zombie: " + zombie.getRow() + ", " + zombie.getCol()); // Zombie position
            }
        }
    }

    public void plantSpawner(String plantType, int row, int col, GameState gameState) {
        Plant plant = PlantFactory.createPlant(plantType, row, col);

        // Check if the player has enough sun points to plant the plant
        if (plant.getCost() > gameState.getSunPoints()) {
            return;
        }

        // Check if the plant can be placed on the tile
        if (!canPlacePlant(col, plantType, grid[row][col])){
            return;
        }

        // Check if the plant can be placed on the tile
        if (!grid[row][col].getPlants().isEmpty() && !plantType.equals("Pumpkin")) {
            return;
        } 

        // Check tile boundaries
        if (col == 0 || col == 11) {
            return;
        }

        gameState.setSunPoints(gameState.getSunPoints() - plant.getCost());
        grid[row][col].addPlant(plant);  
        System.out.println("Tanaman yang ditanam: " + plant.getName());
        System.out.println("Posisi Tanaman: " + plant.getRow() + ", " + plant.getCol()); // Plant position
    }

    private boolean canPlacePlant(int col, String plantType, Tile tile) {
        if (col == 3 && col == 4 && !plantType.equals("Lilypad") && !tile.getPlants().contains("Lilypad")) {
            return false;
        }
        return true;
    }

    public boolean LoseCondition() {
        for (int row = 0; row < rows; row++) {
            ListOf<Zombie> targetZone = grid[row][0].getZombies();
            if (!targetZone.isEmpty()) {
                return true;
            }
        }
        return false;
    }   

    public boolean WinCondition() {
        TimeKeeper.getInstance();
        if (getTotalZombies() == 0 && TimeKeeper.getInstance().getCurrentTime() >= TimeKeeper.DAY_LENGTH){
            return true;
        }
        return false;
    }

    public boolean checkForGameOverConditions() {
        return LoseCondition() || WinCondition();
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

    public void removeDeadEntities() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Tile currentTile = grid[row][col];
                ListOf<Plant> plants = currentTile.getPlants();
                ListOf<Zombie> zombies = currentTile.getZombies();
                
                for (int i = 0; i < plants.size(); i++) {
                    Plant plant = plants.get(i);
                    if (!plant.getIsAlive()) {
                        currentTile.removePlant(plant);
                    }
                }

                for (int i = 0; i < zombies.size(); i++) {
                    Zombie zombie = zombies.get(i);
                    if (!zombie.getIsAlive()) {
                        currentTile.removeZombie(zombie);
                    }
                }
            }
        }
    }
}

