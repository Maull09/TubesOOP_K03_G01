package entity.plant;

import data.GameState;
import data.TimeKeeper;
import entity.Projectile;
import entity.zombie.Zombie;
// package src.entity;
import manager.GameMap;
import manager.Tile;

public class Pumpkin extends Plant{
    public Pumpkin(int row, int col) {
        super("Pumpkin", 125, 4000, 0, 0, 0, 20, row, col);
    }

    public void attack(Zombie zombie)
    {
        zombie.setHealth(getHealth()-getAttackDamage());
    }

    public void attackZombies() {
        if (TimeKeeper.getInstance().getCurrentTime() % this.getAttackSpeed() == 0) {
            for (int col = this.getCol() + 1; col < GameState.getInstance().getGameMap().getCols(); col++) {
                Tile tile = GameState.getInstance().getGameMap().getTile(this.getRow(), col);
                if (!tile.getZombies().isEmpty()) {
                    // Directly attack the first zombie in the tile
                    Zombie targetZombie = tile.getZombies().get(0);
                    targetZombie.takeDamage(this.getAttackDamage());
                    if (!targetZombie.getIsAlive()) {
                        tile.removeZombie(targetZombie);
                    }
                    // Add projectile for visualization
                    Projectile projectile = new Projectile("Peashooter", this.getRow(), this.getCol(), this.getAttackDamage());
                    GameState.getInstance().getGameMap().addProjectile(projectile);
                    break;
                }
            }
        }
    }
}