package entity.zombie;

import data.GameState;
import data.TimeKeeper;
import entity.plant.Plant;
import manager.Tile;

public class BucketHeadZombie extends Zombie{

    public BucketHeadZombie(int row, int col) {
        super("BucketHeadZombie", 300, 100, 1, false, row, col, true, false, false);
    }

    @Override
    public void attack() {
        int currentTime = TimeKeeper.getInstance().getCurrentTime();
        if (currentTime - this.getlastAttackTime() >= this.getAttackSpeed()) {
            for (int col = this.getCol(); col >= 0; col--) {
                Tile tile = GameState.getInstance().getGameMap().getTile(this.getRow(), col);
                if (!tile.getPlants().isEmpty()) {
                    // Directly attack the first plant in the tile
                    Plant targetPlant = tile.getPlants().get(0);
                    targetPlant.takeDamage(this.getAttackDamage());
                    if (!targetPlant.getIsAlive()) {
                        tile.removePlant(targetPlant);
                    }
                    this.setlastAttackTime(currentTime); // Update last attack time
                    break;
                }
            }
        }
    }
}
