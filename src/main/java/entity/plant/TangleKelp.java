package entity.plant;

import data.GameState;
import data.TimeKeeper;
import entity.zombie.Zombie;
import manager.Tile;

public class TangleKelp extends Plant{
    private boolean hasAttacked = false;

    public TangleKelp(int row, int col) {
        super("TangleKelp", 25, 100, 5000, 0, 1, 20, row, col);
    }

    public void attack() {
        int currentTime = TimeKeeper.getInstance().getCurrentTime();
        if (!hasAttacked) {
            Tile tile = GameState.getInstance().getGameMap().getTile(this.getRow(), this.getCol());
            if (!tile.getZombies().isEmpty()) {
                // Directly attack the zombie in the tile
                for (int i = 0; i < tile.getZombies().size(); i++) {
                    Zombie targetZombie = tile.getZombies().get(i);
                    targetZombie.takeDamage(this.getAttackDamage());
                    System.out.println(this.getAttackDamage() + " attack " + this.getName());
                    if (!targetZombie.getIsAlive()) {
                        tile.removeZombie(targetZombie);
                        i--;
                    }
                }
                hasAttacked = true;
                this.setIsAlive(false); // The plant dies after attacking
                tile.removePlant(this);
                this.setlastAttackTime(currentTime); // Update last attack time
            }
        }
    }
}