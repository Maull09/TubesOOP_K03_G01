package entity.plant;

import data.GameState;
import data.TimeKeeper;
import entity.Projectile;
import entity.zombie.Zombie;
import manager.GameMap;
import manager.Tile;

public class Repeater extends Plant{
    public Repeater(int row, int col) {
        super("Repeater", 200, 100, 25, 2, 0, 10, row, col);
    }

    public void attack(Zombie zombie)
    {
        super.attack(zombie);
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
                    Projectile projectile = new Projectile("ProjectTile1", this.getRow(), this.getCol()+1, this.getAttackDamage());
                    GameState.getInstance().getGameMap().addProjectile(projectile);
                    break;
                }
            }
        }
    }




}