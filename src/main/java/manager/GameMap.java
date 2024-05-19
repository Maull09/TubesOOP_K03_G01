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
    private static int MAX_ZOMBIES = 10;

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

    public void removePlant(int row, int col) {
        grid[row][col].removePlant(grid[row][col].getPlants().get(0));
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
        for (int row = rows - 1; row >= 0; row--) {
            for (int col = cols - 1; col >= 0; col--) {
                Tile currentTile = grid[row][col];
                Tile nextTile = col - 1 >= 0 ? grid[row][col - 1] : null;
                
                for (int i = 0; i < currentTile.getZombies().size(); i++) {
                    Zombie zombie = currentTile.getZombies().get(i);
                    if (zombie.canMove(TimeKeeper.getInstance().getCurrentTime())) {
                        if (nextTile != null && nextTile.getPlants().isEmpty()) {
                            currentTile.removeZombie(zombie);
                            nextTile.addZombie(zombie);
                            zombie.move();
                            System.out.println("Zombie yang bergerak: " + zombie.getName());
                            System.out.println("Posisi Zombie Bergerak: " + zombie.getRow() + ", " + zombie.getCol());
                        } else {
                            zombie.attack();
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
                    plant.attack();
                }
            }
        }
    }

    public void zombieSpawner(){
        if(TimeKeeper.getInstance().getCurrentTime() == 160){
            MAX_ZOMBIES = 25;
        } 

        if (getTotalZombies() >= MAX_ZOMBIES) {
            return; // Do not spawn new zombies if the limit is reached
        }

        System.out.println("Total Zombies: " + getTotalZombies());

        Random random = new Random();
        ListOf<Integer> spawnLand = new ListOf<Integer>();
        spawnLand.add(0);
        spawnLand.add(1);
        spawnLand.add(4);
        spawnLand.add(5);
        ListOf<Integer> spawnWater = new ListOf<Integer>();
        spawnWater.add(2);
        spawnWater.add(3);
        ListOf<String> zombieTypes = new ListOf<String>();
        zombieTypes.add("Normal");
        zombieTypes.add("ConeHead");
        // zombieTypes.add("Pole Vaulting");
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
            String zombieType = zombieTypes.get(random.nextInt(zombieTypes.size()));
            int spawnRow;
    
            if (zombieType.equals("Ducky Tube") || zombieType.equals("Dolphin Rider")) {
                spawnRow = spawnWater.get(random.nextInt(spawnWater.size()));
            } else {
                spawnRow = spawnLand.get(random.nextInt(spawnLand.size()));
            }

            Zombie zombie = ZombieFactory.createZombie(zombieType, spawnRow, 10);
            grid[spawnRow][10].addZombie(zombie);
            
            System.out.println("Zombie yang spawn: " + zombie.getName());
            System.out.println("Posisi Zombie: " + zombie.getRow() + ", " + zombie.getCol()); // Zombie position
        }
    }
    

    public void plantSpawner(String plantType, int row, int col) {
        Plant plant = PlantFactory.createPlant(plantType, row, col);

            // Check if the plant is on cooldown
        if (isOnCooldown(plantType)) {
            System.out.println("Plant " + plant.getName() + " is on cooldown");
            return;
        }

        // Check if the player has enough sun points to plant the plant
        if (plant.getCost() > GameState.getInstance().getSunPoints()) {
            System.out.println("Not enough sun points to plant " + plant.getName());
            return;
        }

        // Check if the plant can be placed on the tile
        if(plantType.equals("Lilypad") || plantType.equals("TangleKelp")){
            if(row != 2 && row != 3){
                System.out.println("Lilypad and TangleKelp can only be placed on row 2 and 3");
                return;
            }
        }

        // Check if the plant can be placed on the tile
        if (!canPlacePlant(row, col, plantType, grid[row][col])){
            System.out.println("Cannot place plant " + plant.getName() + " at (" + row + ", " + col + ")");
            return;
        }

        // pumpkins can only be placed on top of other plants
        if (plantType.equals("Pumpkin") && grid[row][col].getPlants().isEmpty()) {
            System.out.println("Pumpkin can only be placed on top of other plants");
            return;
        }

        // Check if the plant can be placed on the tile
        if (!grid[row][col].getPlants().isEmpty() && !plantType.equals("Pumpkin") && !plantType.equals("Lilypad") && !grid[row][col].containsPlant("Lilypad")) {
            System.out.println("There's already a plant on this tile and it is not a Lilypad");
            return;
        }

        // Check tile boundaries
        if (col == 0 || col == 11) {
            System.out.println("Can't plant on the first or last column");
            return;
        }

        GameState.getInstance().setSunPoints(GameState.getInstance().getSunPoints() - plant.getCost());
        grid[row][col].addPlant(plant);  
        System.out.println("Tanaman yang ditanam: " + plant.getName());
        System.out.println("Posisi Tanaman: " + plant.getRow() + ", " + plant.getCol()); // Plant position
    }

    private boolean canPlacePlant(int row, int col, String plantType, Tile tile) {
        int plantCount = tile.getPlants().size();
    
        // Check if the row is 2 or 3
        if (row == 2 || row == 3) {
            // Handle Lilypad and TangleKelp
            if (plantType.equals("Lilypad")) {
                return plantCount == 0;
            }
            if (plantType.equals("TangleKelp")) {
                return plantCount == 0; // TangleKelp can only be alone
            }
    
            // Handle other plants, allowing up to 3 plants (Lilypad, one plant, and Pumpkin)
            if (tile.containsPlant("Lilypad")) {
                if (plantCount < 2) {
                    return true;
                } else if (plantCount == 2 && plantType.equals("Pumpkin")) {
                    return !tile.containsPlant("Squash") && !tile.containsPlant("TangleKelp");
                }
            }
            return false; // If no Lilypad is present, other plants can't be placed
        } else {
            // Handle normal rows, allowing up to 2 plants (one plant and Pumpkin)
            if (plantCount < 1) {
                return true;
            } else if (plantCount == 1 && plantType.equals("Pumpkin")) {
                return !tile.containsPlant("Squash") && !tile.containsPlant("TangleKelp");
            }
            return false;
        }
    }

    private boolean isOnCooldown(String plantType) {
        long currentTime = System.currentTimeMillis();
        Long lastUsedTime = GameState.getInstance().getPlantCooldowns().get(plantType);
        if (lastUsedTime == null) {
            return false;
        }
        long cooldownTime = PlantFactory.createPlant(plantType, 0, 0).getCooldown();
        return currentTime - lastUsedTime < cooldownTime;
    }
    
    private void updateCooldown(String plantType) {
        GameState.getInstance().getPlantCooldowns().put(plantType, System.currentTimeMillis());
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

