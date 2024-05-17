package entity.plant;

import data.TimeKeeper;
import entity.zombie.Zombie;
import manager.GameMap;
import manager.Tile;

public class TangleKelp extends Plant{
    private boolean hasAttacked = false;

    public TangleKelp(int row, int col) {
        super("TangleKelp", 25, 100, 5000, 0, 1, 20, row, col);
    }

    public void attack(Zombie zombie)
    {
        super.attack(zombie);
    }

    public void attackZombies(GameMap gameMap, TimeKeeper timeKeeper) {
        if (timeKeeper.getCurrentTime() % this.getAttackSpeed() == 0 && !hasAttacked) {
            Tile tile = gameMap.getTile(this.getRow(), this.getCol());
            if (!tile.getZombies().isEmpty()) {
                // Directly attack the first zombie in the tile
                Zombie targetZombie = tile.getZombies().get(0);
                targetZombie.takeDamage(this.getAttackDamage());
                if (!targetZombie.getIsAlive()) {
                    tile.removeZombie(targetZombie);
                }
                hasAttacked = true;
                this.setIsAlive(false); // The plant dies after attacking
            }
        }
    }
}