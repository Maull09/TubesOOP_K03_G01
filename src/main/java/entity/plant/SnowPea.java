package entity.plant;

import data.GameState;
import data.TimeKeeper;
import entity.Projectile;
import entity.zombie.Zombie;
import manager.GameMap;
import manager.Tile;

public class SnowPea extends Plant{
    public SnowPea(int row, int col) {
        super("SnowPea", 175, 100, 25, 4, -1, 10,  row, col);
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
                    // Slow the zombie
                    if (TimeKeeper.getInstance().getCurrentTime() % 3 == 0){
                        targetZombie.setIsSlowed(true);
                    }
                    if (!targetZombie.getIsAlive()) {
                        tile.removeZombie(targetZombie);
                    }
                    // Add projectile for visualization
                    Projectile projectile = new Projectile("SnowPeaProjectile", this.getRow(), this.getCol()+1, this.getAttackDamage());
                    GameState.getInstance().getGameMap().addProjectile(projectile);

                    this.setlastAttackTime(currentTime); // Update last attack time
                    break;
                }
            }
        }
    }
    
}