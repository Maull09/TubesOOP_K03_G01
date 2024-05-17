package entity.plant;

import data.GameState;
import data.TimeKeeper;
import entity.zombie.Zombie;
import manager.GameMap;
import manager.Tile;

public class Chomper extends Plant{
    public Chomper(int row, int col) {
        super("Chomper", 150, 100, 5000, 10, 1, 20, row, col);
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
                    break;
                }
            }
        }
    }
    







}