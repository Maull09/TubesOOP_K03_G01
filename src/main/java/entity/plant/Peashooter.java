package entity.plant;

import data.GameState;
import data.TimeKeeper;
import entity.Projectile;
import entity.zombie.Zombie;
import manager.GameMap;
import manager.Tile;

public class Peashooter extends Plant {
    public Peashooter(int row, int col) {
        super("Peashooter", 100, 100, 25, 4, -1, 10, row, col);
    }

    public void attack(Zombie zombie)
    {
        super.attack(zombie);
    }

    public void attackZombies() {
        int currentTime = TimeKeeper.getInstance().getCurrentTime();
        if (currentTime - this.getlastAttackTime() >= this.getAttackSpeed()) {
            for (int col = this.getCol() + 1; col < GameState.getInstance().getGameMap().getCols(); col++) {
                Tile tile = GameState.getInstance().getGameMap().getTile(this.getRow(), col);
                if (!tile.getZombies().isEmpty()) {
                    // Directly attack the first zombie in the tile
                    Zombie targetZombie = tile.getZombies().get(0);
                    targetZombie.takeDamage(this.getAttackDamage());
                    System.out.println(this.getAttackDamage() + " attack " + this.getName());
                    if (!targetZombie.getIsAlive()) {
                        tile.removeZombie(targetZombie);
                    }
                    // Add projectile for visualization
                    Projectile projectile = new Projectile("ProjectTile1", this.getRow(), this.getCol(), this.getAttackDamage());
                    GameState.getInstance().getGameMap().addProjectile(projectile);
                    this.setlastAttackTime(currentTime); // Update last attack time
                    System.out.println(this.getlastAttackTime());
                    break;
                }
            }
        }
    }

}

