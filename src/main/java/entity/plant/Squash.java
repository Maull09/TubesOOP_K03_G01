package entity.plant;

import data.TimeKeeper;
import entity.zombie.Zombie;
import manager.GameMap;
import manager.Tile;

public class Squash extends Plant{
    private boolean hasAttacked;

    public Squash(int row, int col) {
        super("Squash", 50, 100, 5000, 0, 1, 20, row, col);
        this.hasAttacked = false;
    }

    public void attack(Zombie zombie)
    {
        super.attack(zombie);
    }

    public void attackZombies(GameMap gameMap, TimeKeeper timeKeeper) {
        if (timeKeeper.getCurrentTime() % this.getAttackSpeed() == 0 && !hasAttacked) {
            for (int col = this.getCol() + 1; col < gameMap.getCols(); col++) {
                Tile tile = gameMap.getTile(this.getRow(), col);
                if (!tile.getZombies().isEmpty()) {
                    // Directly attack the first zombie in the tile
                    Zombie targetZombie = tile.getZombies().get(0);
                    targetZombie.takeDamage(this.getAttackDamage());
                    if (!targetZombie.getIsAlive()) {
                        tile.removeZombie(targetZombie);
                    }
                    hasAttacked = true;
                    this.setIsAlive(false); // The plant dies after attacking
                    break;
                }
            }
        }
    }

    
}