package entity.plant;

import javax.swing.ImageIcon;

import data.TimeKeeper;
import entity.Projectile;
import entity.zombie.Zombie;
import manager.GameMap;
import manager.Tile;

public class Peashooter extends Plant {
    public Peashooter(int row, int col) {
        super("Peashooter", 100, 100, 25, 4, -1, 10, new ImageIcon("/resources/images/plant/Peashooter.png"), row, col);
    }

    public void attack(Zombie zombie)
    {
        super.attack(zombie);
    }

    public void attackZombies(GameMap gameMap, TimeKeeper timeKeeper) {
        if (timeKeeper.getCurrentTime() % this.getAttackSpeed() == 0) {
            for (int col = this.getCol() + 1; col < gameMap.getCols(); col++) {
                Tile tile = gameMap.getTile(this.getRow(), col);
                if (!tile.getZombies().isEmpty()) {
                    // Directly attack the first zombie in the tile
                    Zombie targetZombie = tile.getZombies().get(0);
                    targetZombie.takeDamage(this.getAttackDamage());
                    if (!targetZombie.getIsAlive()) {
                        tile.removeZombie(targetZombie);
                    }
                    // Add projectile for visualization
                    Projectile projectile = new Projectile("Peashooter", this.getRow(), this.getCol(), this.getAttackDamage());
                    gameMap.addProjectile(projectile);
                    break;
                }
            }
        }
    }

}
